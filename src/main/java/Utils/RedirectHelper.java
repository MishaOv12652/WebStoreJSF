package Utils;

import lombok.Getter;
import lombok.Setter;

import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by Misha on 4/7/2018.
 */
@Getter
@Setter
public class RedirectHelper {
    //no redirect pages
    public static final String EDIT_ITEM_PAGE = "/secured/edit-item";

    public static final String CHECKOUT_PAGE = "/secured/checkOut";

    public static final String VIEW_ITEM_PAGE = "/public/item-view";

    //redirect pages
    private static final String PROFILE_PAGE_REDIRECT =
            "/NewSadna_war_exploded/secured/profile-personal-info.xhtml";

    private static final String LOGOUT_PAGE_REDIRECT =
            "/logout.xhtml?faces-redirect=true";

    private static final String SHOPPING_CART_REDIRECT =
            "/NewSadna_war_exploded/secured/shoppingCart.xhtml";

    private static final String CHECKOUT_PAGE_REDIRECT =
            "/NewSadna_war_exploded/secured/checkOutCart.xhtml";

    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml?faces-redirect=true";


    private static final String WISH_LIST_PAGE = "/NewSadna_war_exploded/secured/wishlist.xhtml";

    private static final String SEARCH_RESULT_PAGE = "/NewSadna_war_exploded/public/searchResults.xhtml";

    private static final String LOGIN_PAGE = "/NewSadna_war_exploded/public/login.xhtml";
    /**
     * redirects to profiles info page
     * @throws IOException
     */
    public static void redirectToProfileInfoPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT);
    }

    /**
     * redirect to profiles selling list page
     * @throws IOException
     */
    public static void redirectToProfileSellingList() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
    }

    /**
     * redirect to profiles wish list page
     * @throws IOException
     */
    public static void redirectToWishList() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(WISH_LIST_PAGE);
    }

    /**
     * redirect to profiles search result page
     * @throws IOException
     */
    public static void redirectToSearchResults() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(SEARCH_RESULT_PAGE);
    }

    /**
     * redirect to profiles shopping cart page
     * @throws IOException
     */
    public static void redirectToShoppinCart() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(SHOPPING_CART_REDIRECT);
    }

    /**
     * redirect to profiles checkout page
     * @throws IOException
     */
    public static void redirectToCheckOut() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(CHECKOUT_PAGE_REDIRECT);
    }

    /**
     * redirect to login page
     * @throws IOException
     */
    public static void redirectToLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(LOGIN_PAGE);
    }



}
