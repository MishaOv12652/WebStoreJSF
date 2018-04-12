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
public class WishListController implements Serializable {
    private WishListDBUtils wishListDBUtils;
    private ArrayList<Item> wishListItems;

    public WishListController() {
        this.wishListDBUtils = new WishListDBUtils();
    }

    /**
     * loads items that user has in his wish list by his email
     *
     * @param email - email of the user
     */
    public void loadWishList(String email) {
        try {
            this.wishListItems = this.wishListDBUtils.loadWishList(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles adding item to a wish list
     * @param email - email of the user that owns the wish list
     * @param itemId - id of the item to add to the wish list
     */
    public void addItemToWishList(String email, int itemId) {
        try {
            this.wishListDBUtils.addItemToWishList(email, itemId);
            RedirectHelper.redirectToWishList();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * removes an item from the wish list
     * @param itemId - id of the item in the wish list
     */
    public void removeItemFromWishList(int itemId) {
        try {
            this.wishListDBUtils.removeItemFromWishList(itemId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
