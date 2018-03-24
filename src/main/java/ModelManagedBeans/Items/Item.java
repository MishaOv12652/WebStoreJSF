package ModelManagedBeans.Items;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;
import java.io.Serializable;

/**
 * Created by Misha on 3/24/2018.
 */
@Getter
@Setter
@ManagedBean
public class Item implements Serializable{
    private int id;
    private String name;
    private double price;
    private String itemDesc;
    private int category;
    private int condition;
    private Part uploadedFile;

    public Item(){}

    public Item(int id,String name,double price, String itemDesc, int category, int condition, Part uploadedFile){
        this.id = id;
        this.name = name;
        this.price = price;
        this.itemDesc = itemDesc;
        this.category = category;
        this.condition = condition;
        this.uploadedFile = uploadedFile;
    }

}
