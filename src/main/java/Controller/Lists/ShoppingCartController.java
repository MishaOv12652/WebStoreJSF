package Controller.Lists;

import DAO.Lists.ShoppingCartDBUtils;
import ModelManagedBeans.Items.Item;
import ModelManagedBeans.Lists.ShoppingCart;
import Utils.RedirectHelper;
import Utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by Misha on 4/7/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class ShoppingCartController implements Serializable {
    private static DecimalFormat df2 = new DecimalFormat(".##");


    private ShoppingCartDBUtils shoppingCartDBUtils;
    @ManagedProperty(value = "#{shoppingCart}")
    private ShoppingCart shoppingCart;

    public ShoppingCartController() {
        this.shoppingCartDBUtils = new ShoppingCartDBUtils();
    }

    //add item to cart

    /**
     * handles adding an item to the shopping cart
     *
     * @param email      - email of the user that owns the shopping cart
     * @param itemId     - id of the item to add to the shopping cart
     * @param numOfItems - quantity of the item to add
     */
    public void addItemToCart(String email, int itemId, int numOfItems) {
        try {
            this.shoppingCartDBUtils.addItemToCart(email, itemId, numOfItems);
            RedirectHelper.redirectToShoppinCart();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //remove item from cart

    /**
     * handles removing an item from a shopping cart
     *
     * @param email - email of the user that owns the shopping cart
     * @param item  - the item to remove
     */
    public void removeItemFromCart(String email, Item item) {
        try {
            this.shoppingCartDBUtils.removeItemFromCart(email, item.getId());
            RedirectHelper.redirectToShoppinCart();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //load cart

    /**
     * loads the cart and its content by email of the user
     *
     * @param email - email of the user that owns the shopping cart
     */
    public void loadCart(String email) {
        try {
            this.shoppingCart.setShoppingCartItems(this.shoppingCartDBUtils.loadShoppingCart(email));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles proceeding to check out action
     */
    public void proceedToCheckOut() {
        SessionUtils.getSession().setAttribute("cart", this.shoppingCart.getShoppingCartItems());
        try {
            RedirectHelper.redirectToCheckOut();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * calculates price without shipping for all items in the checkout
     */
    public void calculatePriceWithoutShipping() {
        double sum = 0;
        Set<Item> keys = this.shoppingCart.getShoppingCartItems().keySet();
        for (Item key : keys) {
            sum += key.getPrice() * this.shoppingCart.getShoppingCartItems().get(key);
        }
        this.shoppingCart.setPriceWithoutShipping(Double.parseDouble(df2.format(sum)));
    }

    /**
     * calculate price of shipping for all items in the checkout
     */
    public void calculateShippingPrice() {
        double sum = 0;
        Set<Item> keys = this.shoppingCart.getShoppingCartItems().keySet();
        for (Item key : keys) {
            sum += key.getShippingPrice();
        }
        this.shoppingCart.setPriceOfShipping(Double.parseDouble(df2.format(sum)));
    }

    /**
     * calculates the total price of items in checkout
     */
    public void calculateTotal() {
        this.shoppingCart.setTotal(Double.parseDouble(df2.format(this.shoppingCart.getPriceOfShipping() + this.shoppingCart.getPriceWithoutShipping())));
    }

}
