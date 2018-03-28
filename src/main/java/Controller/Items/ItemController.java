package Controller.Items;

import DAO.Items.ItemDBUtils;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
@ViewScoped

public class ItemController implements Serializable {
    private Item item;
    private ItemDBUtils itemDBUtils;

    private ArrayList<Item> itemsForSale;


    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";
    //    private static final String EDIT_ITEM_PAGE = "/NewSadna_war_exploded/secured/add-edit-item?faces-redirect=true";
    private static final String EDIT_ITEM_PAGE = "/secured/add-edit-item.xhtml";

    public ItemController() {
        this.itemDBUtils = new ItemDBUtils();
    }

    //add item for sale
    public void addItemForSale(Item item, String email) {
        try {
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

    public String loadItemForSale(int id) {
        try {
            this.item = this.getItemDBUtils().loadItemForSale(id);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("item", this.item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EDIT_ITEM_PAGE;
    }


}
