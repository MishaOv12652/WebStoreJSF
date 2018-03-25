package Controller.Items;

import DAO.Items.ComputerDBUtils;
import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Computer;
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
public class ComputerController implements Serializable{
    private Computer computer;
    private ComputerDBUtils computerDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";

    public ComputerController(){
        this.computerDBUtils = new ComputerDBUtils();
    }

    public void addComputerForSale(Computer computer, String email){
        try {
            int idOfUser = CommonUtils.getUserIdByEmail(email);
            int idOfItem = this.computerDBUtils.addComputerForSale(computer, idOfUser);
            if ( idOfItem == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer Wasn't Successfully added",
                        "The Computer " + computer.getName() + " wasn't added for sale"));

            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Computer Added Successfully",
                        "The Computer " + computer.getName() + " was added for sale"));
                computer.setId(idOfItem);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer Wasn't Successfully added",
                    "The Computer " + computer.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
