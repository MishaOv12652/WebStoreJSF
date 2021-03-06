package ModelManagedBeans.Items;

import Utils.CommonUtils;
import Utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Misha on 3/24/2018.
 */
@Getter
@Setter
@ManagedBean(name = "item")
@SessionScoped
public class Item implements Serializable {
    private int id;
    private String name;
    private float price;
    private String itemDesc;
    private int category;
    private int condition;
    private Part img;
    private float shippingPrice;

    private Integer bookSpecs;
    private Integer movieSpecs;
    private Integer cellSpecs;
    private Integer compSpecs;

    private int sellerId;
    private int numOfItems;
    private int numOfItemsToBuy;

    private Hashtable<Integer, String> categories;
    private Hashtable<Integer, String> conditions;

    public Item() {
    }

    public Item(String name, float price, String itemDesc, int category, int condition, float shippingPrice, Part uploadedFile, int numOfItems,int numOfItemsToBuy) {
        this.name = name;
        this.price = price;
        this.itemDesc = itemDesc;
        this.category = category;
        this.condition = condition;
        this.img = uploadedFile;
        this.numOfItems = numOfItems;
        this.shippingPrice = shippingPrice;
        this.numOfItemsToBuy = numOfItemsToBuy;
    }


    public Item(int id, String name, float price, String itemDesc, int category, int condition, float shippingPrice, Part uploadedFile, int numOfItems, Integer bookSpecs, Integer movieSpecs, Integer cellSpecs, Integer compSpecs) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.itemDesc = itemDesc;
        this.category = category;
        this.condition = condition;
        this.img = uploadedFile;
        this.numOfItems = numOfItems;
        this.shippingPrice = shippingPrice;
        this.bookSpecs = bookSpecs;
        this.movieSpecs = movieSpecs;
        this.cellSpecs = cellSpecs;
        this.compSpecs = compSpecs;
    }

    public Item(int id, String name, float price, String itemDesc, int category, int condition, float shippingPrice, Part uploadedFile, int numOfItems, Integer bookSpecs, Integer movieSpecs, Integer cellSpecs, Integer compSpecs, Hashtable<Integer, String> conditions, Hashtable<Integer, String> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.itemDesc = itemDesc;
        this.category = category;
        this.condition = condition;
        this.img = uploadedFile;
        this.numOfItems = numOfItems;
        this.shippingPrice = shippingPrice;
        this.bookSpecs = bookSpecs;
        this.movieSpecs = movieSpecs;
        this.cellSpecs = cellSpecs;
        this.compSpecs = compSpecs;
        this.conditions = conditions;
        this.categories = categories;
    }

    @PostConstruct
    public void init() {
        this.categories = CommonUtils.getConstLists("dreamdb.categories", "category_name");
        this.conditions = CommonUtils.getConstLists("dreamdb.product_condition", "condition");
        this.category = 5;
    }

    public String getConstValueByKey(Hashtable<Integer, String> hashtable, String key) {
        return CommonUtils.getValueByKeyFromHash(hashtable, key);
    }

}
