package ModelManagedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Hashtable;

@Getter
@Setter
/**
 * Created by Misha on 3/21/2018.
 */
@ManagedBean
@SessionScoped
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int phoneStart;
    private int phoneNum;
    private int city;
    private String street;
    private int streetNum;
    private int zip;
    private long creditCardNumber;
    private int creditCardComp;
    private int creditCardExpMonth;
    private int creditCardExpYear;

    //const lists
    private Hashtable<Integer, String> cities;
    private Hashtable<Integer, String> creditCardCompanies;

    @PostConstruct
    public void init() {
        this.cities = CommonUtils.getConstLists("dreambuy.city", "name");
        this.creditCardCompanies = CommonUtils.getConstLists("dreambuy.credit_companies", "name");
    }

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, int phoneStart, int phoneNum, int city, String street, int streetNum, int zip, long creditCardNumber, int creditCardComp, int creditCardExpMonth, int creditCardExpYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneStart = phoneStart;
        this.phoneNum = phoneNum;
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.zip = zip;
        this.creditCardNumber = creditCardNumber;
        this.creditCardComp = creditCardComp;
        this.creditCardExpMonth = creditCardExpMonth;
        this.creditCardExpYear = creditCardExpYear;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
