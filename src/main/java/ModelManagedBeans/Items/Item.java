package ModelManagedBeans.Items;

import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Misha on 3/24/2018.
 */
@Getter
@Setter
@ManagedBean
public class Item implements Serializable{
    private int id;
    private String name;
    private float price;
    private String itemDesc;
    private int category;
    private int condition;
    private UploadedFile img;
    private int itemSpecs;
    private int sellerId;
    private int numOfItems;

    private Hashtable<Integer, String> categories;
    private Hashtable<Integer, String> conditions;

    public Item(){}

    public Item(String name,float price, String itemDesc, int category, int condition, UploadedFile uploadedFile,int sellerId,int numOfItems){
        this.name = name;
        this.price = price;
        this.itemDesc = itemDesc;
        this.category = category;
        this.condition = condition;
        this.img = uploadedFile;
        this.sellerId = sellerId;
        this.numOfItems = numOfItems;
    }

    public void loadConsts(){
        this.categories = CommonUtils.getConstLists("dreambuy.categories", "category_name");
        this.conditions = CommonUtils.getConstLists("dreambuy.product_condition", "condition");
    }

}
