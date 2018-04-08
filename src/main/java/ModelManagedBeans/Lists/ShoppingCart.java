package ModelManagedBeans.Lists;

import ModelManagedBeans.Items.Item;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Misha on 4/5/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class ShoppingCart implements Serializable{
    private Hashtable<Item,Integer> shoppingCartItems;
    private double priceWithoutShipping;
    private double priceOfShipping;
    private double total;

    public ShoppingCart(){}

    public ShoppingCart (Hashtable<Item,Integer> shoppingCartItems){
        this.shoppingCartItems = shoppingCartItems;
    }

    public ShoppingCart (Hashtable<Item,Integer> shoppingCartItems,double priceWithoutShipping,double priceOfShipping,double total){
        this.shoppingCartItems = shoppingCartItems;
        this.priceWithoutShipping = priceWithoutShipping;
        this.priceOfShipping = priceOfShipping;
        this.total = total;
    }


}
