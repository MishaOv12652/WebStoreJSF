package ModelManagedBeans;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by Misha on 23/03/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class Profile implements Serializable {
    private User currentUser;

    public Profile(){}

    public Profile(User user){
        this.currentUser = user;
    }
}
