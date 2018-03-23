package Controller;

import DAO.ProfileDBUtils;
import ModelManagedBeans.Profile;
import ModelManagedBeans.User;
import Utils.CommonUtils;
import Utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Misha on 23/03/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {
    private ProfileDBUtils profileDBUtils;
    private boolean editMode;
    private User currentProfile;

    private Hashtable<Integer, String> cities;
    private Hashtable<Integer, String> creditCardCompanies;


    public ProfileController() {
        this.profileDBUtils = new ProfileDBUtils();
        this.cities = CommonUtils.getConstLists("dreambuy.city", "name");
        this.creditCardCompanies = CommonUtils.getConstLists("dreambuy.credit_companies", "name");
    }


    public void loadProfileInfo() {
        HttpSession session = SessionUtils.getSession();
        try {
            this.currentProfile = this.profileDBUtils.loadProfileInfo((String) session.getAttribute("userEmail"));
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("user", this.currentProfile);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Loading Profile Error", e.getMessage()));
        }
    }

    public void toggleEditMode() {
        this.editMode = !this.editMode;
        if (this.editMode) {
            this.updateProfile(this.currentProfile);
        }
    }

    private void updateProfile(User currentProfile) {
        try {
            this.profileDBUtils.updateProfileInfo(currentProfile);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Update Profile Error", e.getMessage()));
        }
    }
}
