package Controller.Items;

import DAO.Items.CellPhoneDBUtils;
import ModelManagedBeans.Items.CellPhone;
import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class CellPhoneController implements Serializable {
    private CellPhone cellPhone;
    private CellPhoneDBUtils cellPhoneDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";

    public CellPhoneController() {
        this.cellPhoneDBUtils = new CellPhoneDBUtils();
    }

    public void addCellPhoneForSale(CellPhone cellPhone, String email){
        try {
            int idOfUser = CommonUtils.getUserIdByEmail(email);
            int idOfItem = this.cellPhoneDBUtils.addCellPhoneForSale(cellPhone, idOfUser);
            if ( idOfItem == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cellphone Wasn't Successfully added",
                        "The Cellphone " + cellPhone.getName() + " wasn't added for sale"));

            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cellphone Added Successfully",
                        "The Cellphone " + cellPhone.getName() + " was added for sale"));
                cellPhone.setId(idOfItem);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cellphone Wasn't Successfully added",
                    "The Cellphone " + cellPhone.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
