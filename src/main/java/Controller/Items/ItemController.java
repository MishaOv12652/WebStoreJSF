package Controller.Items;

import DAO.Items.ItemDBUtils;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by Misha on 24/03/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class ItemController implements Serializable {
    private Item item;
    private ItemDBUtils itemDBUtils;

    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";
    public ItemController() {
        this.itemDBUtils = new ItemDBUtils();
    }

    //add item
    public void addItem(Item item,String email) {
        try {
            int idOfUser = CommonUtils.getUserIdByEmail(email);
            int idOfItem = this.itemDBUtils.addItem(item, idOfUser);
            if ( idOfItem == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Wasn't Successfully added",
                        "The item " + item.getName() + " wasn't added for sale"));

            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Added Successfully",
                        "The item " + item.getName() + " was added for sale"));
                item.setId(idOfItem);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Wasn't Successfully added",
                    "The item " + item.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
