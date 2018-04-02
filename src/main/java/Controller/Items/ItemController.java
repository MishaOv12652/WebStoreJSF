package Controller.Items;

import DAO.Items.ItemDBUtils;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
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

    private String inputSearchString;
    private int category;
    private ArrayList<Item> searchResults;

    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml?faces-redirect=true";
    private static final String EDIT_ITEM_PAGE = "/secured/edit-item";
    private static final String VIEW_ITEM_PAGE = "/item-view";
    private static final String SEARCH_RESULT_PAGE = "/NewSadna_war_exploded/public/searchResults.xhtml";


    public ItemController() {
        this.itemDBUtils = new ItemDBUtils();
    }

    //add item for sale
    public void addItemForSale(Item item, String email) {
        try {
            if(item.getBookSpecs()==0){
                item.setBookSpecs(null);
            }
            if(item.getMovieSpecs()==0){
                item.setMovieSpecs(null);
            }
            if(item.getCellSpecs()==0){
                item.setCellSpecs(null);
            }
            if(item.getCompSpecs()==0){
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

    public void loadListItemForSale(String email) {
        try {
            this.itemsForSale = this.itemDBUtils.loadItemListForSale(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String loadItemForSale(int id, boolean edit) {
        try {
            this.item = this.getItemDBUtils().loadItemForSale(id);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("item", this.item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(edit){
            return EDIT_ITEM_PAGE;
        }else{
            return VIEW_ITEM_PAGE;
        }

    }

    public void updateItemForSale(Item item) {
        try {
            if(item.getBookSpecs()==0){
                item.setBookSpecs(null);
            }
            if(item.getMovieSpecs()==0){
                item.setMovieSpecs(null);
            }
            if(item.getCellSpecs()==0){
                item.setCellSpecs(null);
            }
            if(item.getCompSpecs()==0){
                item.setCompSpecs(null);
            }
            this.itemDBUtils.updateItemForSale(item);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    protected void addItemForSale(Item item, String email, ItemDBUtils itemDBUtils) throws SQLException, IOException {
        int idOfUser = CommonUtils.getUserIdByEmail(email);
        int idOfItem = itemDBUtils.addItemForSale(item, idOfUser);
        if (idOfItem == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Wasn't Successfully added",
                    "The item " + item.getName() + " wasn't added for sale"));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Added Successfully",
                    "The item " + item.getName() + " was added for sale"));
            item.setId(idOfItem);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
        }
    }

    public void deleteItemForSale(Integer id){
        try {
            this.itemDBUtils.deleteItemForSale(id);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void loadItemsToBuy(String email){
        HttpSession httpSession = SessionUtils.getSession();
        this.inputSearchString = (String) httpSession.getAttribute("searchInput");
        this.category = (Integer) httpSession.getAttribute("category");
        try {
            this.searchResults =  this.itemDBUtils.searchForItemsToBuy(this.inputSearchString,CommonUtils.getUserIdByEmail(email),this.category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchForItemsToBuy(){
        HttpSession httpSession = SessionUtils.getSession();
        httpSession.setAttribute("searchInput",this.inputSearchString);
        httpSession.setAttribute("category",this.category);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(SEARCH_RESULT_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
