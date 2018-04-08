package Controller.Lists;

import DAO.Lists.WishListDBUtils;
import ModelManagedBeans.Items.Item;
import Utils.RedirectHelper;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Misha on 4/8/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class WishListController implements Serializable{
    private WishListDBUtils wishListDBUtils;
    private ArrayList<Item> wishListItems;

    public WishListController(){
        this.wishListDBUtils = new WishListDBUtils();
    }

    public void loadWishList(String email) {
        try {
            this.wishListItems = this.wishListDBUtils.loadWishList(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemToWishList(String email, int itemId){
        try {
            this.wishListDBUtils.addItemToWishList(email,itemId);
            RedirectHelper.redirectToWishList();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void removeItemFromWishList(int itemId){
        try {
            this.wishListDBUtils.removeItemFromWishList(itemId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
