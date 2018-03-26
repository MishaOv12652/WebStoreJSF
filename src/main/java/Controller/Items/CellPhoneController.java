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
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

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

    public CellPhoneController() {
        this.cellPhoneDBUtils = new CellPhoneDBUtils();
    }

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

}
