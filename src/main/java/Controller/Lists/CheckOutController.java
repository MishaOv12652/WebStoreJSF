package Controller.Lists;

import DAO.Lists.CheckOutDBUtils;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.RedirectHelper;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
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
public class CheckOutController implements Serializable {
    private static DecimalFormat df2 = new DecimalFormat(".##");
    private CheckOutDBUtils checkOutDBUtils;

    public CheckOutController() {
        this.checkOutDBUtils = new CheckOutDBUtils();
    }

    /**
     * calculates price without shipping for all items in the checkout
     *
     * @param items - hash table of items and there quantity  to buy
     * @return - calculated price without shipping
     */
    public double calculatePriceWithoutShipping(Hashtable<Item, Integer> items) {
        double sum = 0;
        Set<Item> keys = items.keySet();
        for (Item key : keys) {
            sum += key.getPrice() * items.get(key);
        }
        return Double.parseDouble(df2.format(sum));
    }

    /**
     * calculate price of shipping for all items in the checkout
     * @param items - hash table of items and there quantity  to buy
     * @return - calculated price of shipping
     */
    public double calculateShippingPrice(Hashtable<Item, Integer> items) {
        double sum = 0;
        Set<Item> keys = items.keySet();
        for (Item key : keys) {
            sum += key.getShippingPrice();
        }
        return Double.parseDouble(df2.format(sum));
    }


    /**
     * calculates the total price of items in checkout
     * @param items - hash table of items and there quantity  to buy
     * @return - calculated total price
     */
    public double calculateTotal(Hashtable<Item, Integer> items) {
        return Double.parseDouble(df2.format(this.calculatePriceWithoutShipping(items) + this.calculateShippingPrice(items)));
    }

    public void confirmPayment(Hashtable<Item, Integer> items, String email) {

        try {
            this.checkOutDBUtils.confirmPayment(items, CommonUtils.getUserIdByEmail(email));
            RedirectHelper.redirectToShoppinCart();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
