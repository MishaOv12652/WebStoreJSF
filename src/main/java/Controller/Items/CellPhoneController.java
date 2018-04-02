package Controller.Items;

import DAO.Items.CellPhoneDBUtils;
import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.CellPhone;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Misha on 3/25/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class CellPhoneController extends ItemController implements Serializable {
    @ManagedProperty(value = "#{item}")
    private Item itemBean;

    private CellPhoneDBUtils cellPhoneDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";
    private static final String VIEW_ITEM_PAGE = "/item-view";
    private static final String EDIT_ITEM_PAGE = "/secured/edit-item";

    public CellPhoneController() {
        this.cellPhoneDBUtils = new CellPhoneDBUtils();
    }

    /**
     * adds cellphone specs for sale
     * @param cellPhone - cellphone to insert
     * @param email - email of the seller to get the id
     */
    public void addCellPhoneForSale(CellPhone cellPhone, String email){
        CellPhone cellPhoneWithItemSpecs = new CellPhone(this.itemBean.getName(),this.itemBean.getPrice(),this.itemBean.getItemDesc()
                ,this.itemBean.getCategory(),this.itemBean.getCondition(),this.itemBean.getImg(),this.itemBean.getNumOfItems()
                ,cellPhone.getScreenSize(),cellPhone.getRam(),cellPhone.getBrand(),cellPhone.getModel(),cellPhone.getMemoryCardType()
                ,cellPhone.getOs(),cellPhone.getStorage(),cellPhone.getBatteryCapacity());
        try {
            this.addItemForSale(cellPhoneWithItemSpecs,email,this.cellPhoneDBUtils);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cellphone Wasn't Successfully added",
                    "The Cellphone " + cellPhone.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads cellphone specs for the selling list
     * @param id - books specs id
     * @return returns the cellphone found
     */
    public CellPhone loadCellPhoneForSale(Integer id){
        if(id != null){
            try {
                return this.cellPhoneDBUtils.loadCellPhoneForSale(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * loads item specs and cellphone specs for update or view page
     * @param id - cellphone specs id
     * @param itemId - item id
     * @param edit - edit or view page bool
     * @return return a string of the url to populate data to
     */
    public String loadCellPhoneForUpdate(Integer id, Integer itemId, boolean edit) {
        if (itemId != null) {
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> requestMap = externalContext.getRequestMap();
                requestMap.put("item", this.getItemDBUtils().loadItemForSale(itemId));
                requestMap.put("cellPhone", this.cellPhoneDBUtils.loadCellPhoneForSale(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(edit){
            return EDIT_ITEM_PAGE;
        }else{
            return VIEW_ITEM_PAGE;
        }
    }

    /**
     * updates a item of the category cellphone
     * @param item - item object - for specs like name and ec.
     * @param cellPhone - cellphone object - for cellphone specs
     */
    public void updateCellPhoneForSale(Item item, CellPhone cellPhone) {
        try {
            this.cellPhoneDBUtils.updateCellPhoneSpecs(cellPhone);
            this.updateItemForSale(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a cellphone that was for sale
     * @param cellPhoneId - book specs id
     * @param itemId - item id
     */
    public void deleteCellPhoneForSale(Integer cellPhoneId,Integer itemId){
        try {
            this.deleteItemForSale(itemId);
            this.cellPhoneDBUtils.deleteCellPhoneForSale(cellPhoneId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
