package Controller;

import DAO.UserDBUtils;
import ModelManagedBeans.User;
import Utils.SessionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;

/**
 * Created by Misha on 3/21/2018.
 */
@ManagedBean
@SessionScoped
public class UserController {
    private static final String PROFILE_PAGE_REDIRECT =
            "/JSFR_war_exploded/secured/profile.xhtml?faces-redirect=true";
    private static final String LOGOUT_PAGE_REDIRECT =
            "/logout.xhtml?faces-redirect=true";
    private UserDBUtils userDBUtils;

    public UserController(){
        this.userDBUtils = new UserDBUtils();
    }
    //sign Up
    public String signUp(User user){
        try {
            this.userDBUtils.signUp(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful Registration", "You Have Registered Successfully"));
            SessionUtils.getSession().setAttribute("userEmail",user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unsuccessful Registration", "Something Went Wrong On Registration"));
            return null;
        }
        return PROFILE_PAGE_REDIRECT;
    }
}
