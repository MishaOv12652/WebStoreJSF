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
import javax.faces.bean.RequestScoped;
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

    }

    /**
     * loads consts of the profile - for getting the string value
     */
    public void loadConsts(){
        this.cities = CommonUtils.getConstLists("dreamdb.city", "name");
        this.creditCardCompanies = CommonUtils.getConstLists("dreamdb.credit_companies", "name");
    }

    /**
     * loads user info
     */
    public void loadProfileInfo() {
        HttpSession session = SessionUtils.getSession();
        try {
            this.currentProfile = this.profileDBUtils.loadProfileInfo((String) session.getAttribute("userEmail"));
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("user", this.currentProfile);
            SessionUtils.getSession().setAttribute("userName",this.currentProfile.getFirstName());
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Loading Profile Error", e.getMessage()));
        }
    }

    /**
     * toggles edit mode mode on and off
     */
    public void toggleEditMode() {
        this.editMode = !this.editMode;
    }

    /**
     * cancels edit mode
     */
    public void cancelEditMode() {
        this.editMode = false;
    }

    /**
     * updates the profile info
     * @param currentProfile - profile user object with the data to update
     */
    public void updateProfile(User currentProfile) {
        try {
            this.profileDBUtils.updateProfileInfo(currentProfile);
            this.editMode = false;
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Update Profile Error", e.getMessage()));
        }
    }

    /**
     * gets string value by key toshow the actual value
     * @param hashtable - hash table of const values
     * @param key - key of the value
     * @return - the actual value
     */
    public String getConstValueByKey(Hashtable<Integer,String> hashtable, String key){
        return CommonUtils.getValueByKeyFromHash(hashtable,key);
    }
}
