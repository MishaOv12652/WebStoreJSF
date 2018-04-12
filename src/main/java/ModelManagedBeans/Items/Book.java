package ModelManagedBeans.Items;

import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Misha on 3/25/2018.
 */
@Getter
@Setter
@ManagedBean
public class Book extends Item implements Serializable {
    private int id;
    private int author;
    private int genre;
    private String series;
    private int ageLvl;

    //const Lists
    private Hashtable<Integer, String> authors;
    private Hashtable<Integer, String> genres;
    private Hashtable<Integer, String> ageLevels;

    public Book() {
        super();
    }

    public Book(int author, int genre, String series, int ageLvl) {
        this.author = author;
        this.genre = genre;
        this.series = series;
        this.ageLvl = ageLvl;
    }

    public Book(int id, int author, int genre, String series, int ageLvl, Hashtable<Integer, String> authors, Hashtable<Integer, String> genres, Hashtable<Integer, String> ageLevels) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.series = series;
        this.ageLvl = ageLvl;
        this.authors = authors;
        this.genres = genres;
        this.ageLevels = ageLevels;
    }

    public Book(String name, float price, String itemDesc, int category, int condition, Part uploadedFile, int numOfItems, float shippingPrice, int numOfItemsToBuy, int author, int genre, String series, int ageLvl) {
        super(name, price, itemDesc, category, condition,shippingPrice, uploadedFile, numOfItems,numOfItemsToBuy);
        this.author = author;
        this.genre = genre;
        this.series = series;
        this.ageLvl = ageLvl;
    }

    @PostConstruct
    public void init() {
        //get author hash from db
        this.authors = CommonUtils.getConstLists("dreamdb.known_authers", "auther_name");
        //get genres hash from db
        this.genres = CommonUtils.getConstLists("dreamdb.genres", "genre");
        //get ageLvl hash from db
        this.ageLevels = CommonUtils.getConstLists("dreamdb.age_lvl", "age_lvl");
    }

    @Override
    public String getConstValueByKey(Hashtable<Integer, String> hashtable, String key) {
        return CommonUtils.getValueByKeyFromHash(hashtable, key);
    }


}
