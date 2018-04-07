package ModelManagedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import Utils.CommonUtils;
import Utils.SessionUtils;
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

    //const Lists
    private Hashtable<Integer, String> cities;
    private Hashtable<Integer, String> creditCardCompanies;

    @PostConstruct
    public void init() {
        this.cities = CommonUtils.getConstLists("dreamdb.city", "name");
        this.creditCardCompanies = CommonUtils.getConstLists("dreamdb.credit_companies", "name");
        if(SessionUtils.getSession().getAttribute("userName")!=null){
            this.firstName = (String) SessionUtils.getSession().getAttribute("userName");
        }
    }

    public User() {
    }

    /**
     * constructor for sign up
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param phoneStart
     * @param phoneNum
     * @param city
     * @param street
     * @param streetNum
     * @param zip
     * @param creditCardNumber
     * @param creditCardComp
     * @param creditCardExpMonth
     * @param creditCardExpYear
     */
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

    /**
     * constructor for login
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
