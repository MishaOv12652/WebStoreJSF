package Controller;

import DAO.ProfileDBUtils;
import ModelManagedBeans.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Misha on 23/03/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable{
    private Profile profile;
    private ProfileDBUtils profileDBUtils;

    public ProfileController(){
        this.profileDBUtils = new ProfileDBUtils();
    }

    public void loadProfile(){

    }
}
