package Controller.Items;

import DAO.Items.ItemDBUtils;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.RedirectHelper;
import Utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Misha on 24/03/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
@ViewScoped
public class ItemController implements Serializable {
    private Item item;
    private ItemDBUtils itemDBUtils;

    private ArrayList<Item> itemsForSale;
    private ArrayList<Item> wishListItems;

    private String inputSearchString;
    private int category;
    private ArrayList<Item> searchResults;



    public ItemController() {
        this.itemDBUtils = new ItemDBUtils();
    }

    /**
     * adds item for sale - category other
     *
     * @param item  - item to insert
     * @param email - email of the seller to get the id
     */
    public void addItemForSale(Item item, String email) {
        try {
            if (item.getBookSpecs() == null || item.getBookSpecs() == 0) {
                item.setBookSpecs(null);
            }
            if (item.getMovieSpecs() == null || item.getMovieSpecs() == 0) {
                item.setMovieSpecs(null);
            }
            if (item.getCellSpecs() == null || item.getCellSpecs() == 0) {
                item.setCellSpecs(null);
            }
            if (item.getCompSpecs() == null || item.getCompSpecs() == 0) {
                item.setCompSpecs(null);
            }
            this.addItemForSale(item, email, this.itemDBUtils);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Wasn't Successfully added",
                    "The item " + item.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadImageOfItemByItemId(int itemId){
        try {
            return this.itemDBUtils.loadImageOfItemByItemId(itemId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * loads list of items the user sells
     *
     * @param email - email of the user
     */
    public void loadListItemForSale(String email) {
        try {
            this.itemsForSale = this.itemDBUtils.loadItemListForSale(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads item for sale - for update or view
     *
     * @param id   - item id
     * @param edit - edit or view page bool
     * @return return a string of the url to populate data to
     */
    public String loadItemForSale(int id, boolean edit) {
        try {
            this.item = this.getItemDBUtils().loadItemForSale(id);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("item", this.item);
            SessionUtils.getSession().setAttribute("itemId", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.checkIfEdit(edit,id);

    }

    /**
     * updates an item for sale - category other
     *
     * @param item - item object to update
     */
    public void updateItemForSale(Item item) {
        try {
            if (item.getBookSpecs() == null || item.getBookSpecs() == 0) {
                item.setBookSpecs(null);
            }
            if (item.getMovieSpecs() == null || item.getMovieSpecs() == 0) {
                item.setMovieSpecs(null);
            }
            if (item.getCellSpecs() == null || item.getCellSpecs() == 0) {
                item.setCellSpecs(null);
            }
            if (item.getCompSpecs() == null || item.getCompSpecs() == 0) {
                item.setCompSpecs(null);
            }
            this.itemDBUtils.updateItemForSale(item);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            RedirectHelper.redirectToProfileSellingList();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds an item for sale - for use of other categories controllers
     *
     * @param item        - item to add for selling - for attributes like name and ec.
     * @param email       - email of the seller
     * @param itemDBUtils - DBUtils object of the category
     * @throws SQLException - thrown if something fails in inserting to db stage
     * @throws IOException  - thrown if there is a problem with the redirect
     */
    protected void addItemForSale(Item item, String email, ItemDBUtils itemDBUtils) throws SQLException, IOException {
        int idOfUser = CommonUtils.getUserIdByEmail(email);

        int idOfItem = itemDBUtils.addItemForSale(item, idOfUser);
        String extension = FilenameUtils.getExtension(item.getImg().getSubmittedFileName());
        String fileName = email + "-" + idOfItem + "." + extension;
        String uploadFolder = "C:\\Users\\Misha\\IdeaProjects\\DreamBuyAndSellProject\\web\\resources\\imageUploads\\";

        try (InputStream inputStream = item.getImg().getInputStream()){

            Files.copy(inputStream,new File(uploadFolder,fileName).toPath());
        }
        if (idOfItem == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Wasn't Successfully added",
                    "The item " + item.getName() + " wasn't added for sale"));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Added Successfully",
                    "The item " + item.getName() + " was added for sale"));
            item.setId(idOfItem);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            RedirectHelper.redirectToProfileSellingList();
        }
    }

    /**
     * deletes item for sale - category other
     *
     * @param id - id of the item
     */
    public void deleteItemForSale(Integer id) {
        try {
            this.itemDBUtils.deleteItemForSale(id);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            RedirectHelper.redirectToProfileSellingList();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads items the were found - excluding items the user sells
     *
     * @param email - email of the user
     */
    public void loadItemsToBuy(String email) {
        HttpSession httpSession = SessionUtils.getSession();
        this.inputSearchString = (String) httpSession.getAttribute("searchInput");
        this.category = (Integer) httpSession.getAttribute("category");
        try {
            this.searchResults = this.itemDBUtils.searchForItemsToBuy(this.inputSearchString, CommonUtils.getUserIdByEmail(email), this.category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * searches for items for buying by an input string and category
     * saves the input string and category to session for loading later
     */
    public void searchForItemsToBuy() {
        HttpSession httpSession = SessionUtils.getSession();
        httpSession.setAttribute("searchInput", this.inputSearchString);
        httpSession.setAttribute("category", this.category);
        try {
            RedirectHelper.redirectToSearchResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String buyItemNow(Integer id, int numOfItemsToBuy) {
        try {
            this.item = this.getItemDBUtils().loadItemForSale(id);
            this.item.setNumOfItemsToBuy(numOfItemsToBuy);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("item", this.item);
            if (item.getNumOfItemsToBuy() > item.getNumOfItems() || item.getNumOfItemsToBuy() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong on action buy it now",
                        "Something went wrong on action buy it now"));
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return RedirectHelper.CHECKOUT_PAGE;
    }

    public void confirmPayment(int numOfItemsBought) {
        String sellerEmail = (String) SessionUtils.getSession().getAttribute("sellerEmail");
        try {
            this.itemDBUtils.confirmPayment(sellerEmail, numOfItemsBought);
            RedirectHelper.redirectToProfileSellingList();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWishList(String email) {
        try {
            this.wishListItems = this.itemDBUtils.loadWishList(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemToWishList(String email, int itemId){
        try {
            this.itemDBUtils.addItemToWishList(email,itemId);
            RedirectHelper.redirectToWishList();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void removeItemFromWishList(int itemId){
        try {
            this.itemDBUtils.removeItemFromWishList(itemId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String checkIfEdit(boolean edit,Integer itemId){
        if(edit){
            return RedirectHelper.EDIT_ITEM_PAGE;
        }else{
            SessionUtils.getSession().setAttribute("itemId",itemId);
            SessionUtils.getSession().setAttribute("sellerEmail",CommonUtils.getEmailByUserId(CommonUtils.getSellerIdByItemId(itemId)));
            return RedirectHelper.VIEW_ITEM_PAGE;
        }
    }


}
