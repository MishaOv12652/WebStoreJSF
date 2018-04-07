package Controller.Lists;

import DAO.Lists.ShoppingCartDBUtils;
import ModelManagedBeans.Items.Item;
import ModelManagedBeans.Lists.ShoppingCart;
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
public class ShoppingCartController implements Serializable{
    private static DecimalFormat df2 = new DecimalFormat(".##");

    private static final String SHOPPING_CART_REDIRECT =
            "/NewSadna_war_exploded/secured/shoppingCart.xhtml";

    private static final String CHECKOUT_PAGE_REDIRECT =
            "/NewSadna_war_exploded/secured/checkOutCart.xhtml";

    private ShoppingCartDBUtils shoppingCartDBUtils;
    @ManagedProperty(value = "#{shoppingCart}")
    private ShoppingCart shoppingCart;

    public ShoppingCartController() {
        this.shoppingCartDBUtils = new ShoppingCartDBUtils();
    }

    //add item to cart
    public void addItemToCart(String email, int itemId, int numOfItems) {
        try {
            this.shoppingCartDBUtils.addItemToCart(email, itemId, numOfItems);
            FacesContext.getCurrentInstance().getExternalContext().redirect(SHOPPING_CART_REDIRECT);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //update item in cart
    //remove item from cart
    public void removeItemFromCart(String email, Item item) {
        try {
            this.shoppingCartDBUtils.removeItemFromCart(email, item.getId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(SHOPPING_CART_REDIRECT);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //load cart
    public void loadCart(String email) {
        try {
            this.shoppingCart.setShoppingCartItems(this.shoppingCartDBUtils.loadShoppingCart(email));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void proceedToCheckOut(){
        SessionUtils.getSession().setAttribute("cart",this.shoppingCart.getShoppingCartItems());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(CHECKOUT_PAGE_REDIRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //calculate price without shipping
    public void calculatePriceWithoutShipping() {
        double sum = 0;
        Set<Item> keys = this.shoppingCart.getShoppingCartItems().keySet();
        for (Item key : keys) {
            sum+= key.getPrice()*this.shoppingCart.getShoppingCartItems().get(key);
        }
        this.shoppingCart.setPriceWithoutShipping(Double.parseDouble(df2.format(sum)));
    }
    //calculate price of shipping
    public void calculateShippingPrice() {
        double sum = 0;
        Set<Item> keys = this.shoppingCart.getShoppingCartItems().keySet();
        for (Item key : keys) {
            sum+= key.getShippingPrice();
        }
        this.shoppingCart.setPriceOfShipping(Double.parseDouble(df2.format(sum)));
    }
    //calculate subtotal
    public void calculateTotal(){
        this.shoppingCart.setTotal(Double.parseDouble(df2.format(this.shoppingCart.getPriceOfShipping() + this.shoppingCart.getPriceWithoutShipping())));
    }

}
