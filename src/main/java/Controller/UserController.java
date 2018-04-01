package Controller;

import DAO.UserDBUtils;
import ModelManagedBeans.User;
import Utils.SessionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Misha on 3/21/2018.
 */
@ManagedBean
@SessionScoped
public class UserController {
    private static final String PROFILE_PAGE_REDIRECT =
            "/NewSadna_war_exploded/secured/profile-personal-info.xhtml";
    private static final String LOGOUT_PAGE_REDIRECT =
            "/logout.xhtml?faces-redirect=true";
    private UserDBUtils userDBUtils;

    public UserController() {
        this.userDBUtils = new UserDBUtils();
    }

    //sign Up
    public void signUp(User user) {
        try {
            this.userDBUtils.signUp(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful Registration", "You Have Registered Successfully"));
            SessionUtils.getSession().setAttribute("userEmail", user.getEmail());
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unsuccessful Registration", "Something Went Wrong On Registration"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //login
    public void login(User user){
        try {
            if(this.userDBUtils.login(user)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful Login", "You Have Logged in Successfully"));
                SessionUtils.getSession().setAttribute("userEmail", user.getEmail());
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT);
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unsuccessful Login", "Something Went Wrong On Login"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Unsuccessful Login", e.getMessage()));
        }
    }

    public boolean checkIfUserLoggedIn(){
        HttpSession httpSession = SessionUtils.getSession();
        return httpSession.getAttribute("email") != null;
    }

    public void logOut(){
        HttpSession httpSession = SessionUtils.getSession();
        httpSession.removeAttribute("email");
    }
}
