package ModelManagedBeans.Items;

import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Misha on 3/25/2018.
 */
@ManagedBean
@Setter
@Getter
@SessionScoped
public class Movie extends Item implements Serializable {
    private int id;
    private int director;
    private int length;
    private int year;
    private int knownActor;
    private int ageLvl;
    private int genre;

    //const lists
    private Hashtable<Integer, String> directors;
    private Hashtable<Integer, String> knownActors;
    private Hashtable<Integer, String> ageLvls;
    private Hashtable<Integer, String> genres;

    public Movie() {
        super();
    }

    public Movie( int id,int director, int length, int year, int ageLvl, int knownActor, int genre){
        this.id = id;
        this.director = director;
        this.length = length;
        this.year = year;
        this.ageLvl = ageLvl;
        this.knownActor = knownActor;
        this.genre = genre;
    }

    public Movie( int id,int director, int length, int year, int ageLvl, int knownActor, int genre,Hashtable<Integer, String> directors,Hashtable<Integer, String> knownActors,Hashtable<Integer, String> ageLvls,Hashtable<Integer, String> genres ){
        this.id = id;
        this.director = director;
        this.length = length;
        this.year = year;
        this.ageLvl = ageLvl;
        this.knownActor = knownActor;
        this.genre = genre;
        this.directors = directors;
        this.knownActors = knownActors;
        this.ageLvls = ageLvls;
        this.genres = genres;
    }

    public Movie(String name, float price, String itemDesc, int category, int condition, float shippingPrice,int numOfItemsToBuy,UploadedFile uploadedFile, int numOfItems,
                 int director, int length, int year, int ageLvl, int knownActor, int genre) {
        super(name, price, itemDesc, category, condition, shippingPrice, uploadedFile, numOfItems,numOfItemsToBuy);
        this.director = director;
        this.length = length;
        this.year = year;
        this.ageLvl = ageLvl;
        this.knownActor = knownActor;
        this.genre = genre;
    }

    @PostConstruct
    public void init() {
        //get directors hash from db
        this.directors = CommonUtils.getConstLists("dreambuy.directors", "name");
        //get knownActor hash from
        this.knownActors = CommonUtils.getConstLists("dreambuy.actors", "name");
        //get ageLvl hash from db
        this.ageLvls = CommonUtils.getConstLists("dreambuy.age_lvl", "age_lvl");
        //get genres hash from db
        this.genres = CommonUtils.getConstLists("dreambuy.genres", "genre");
    }

    @Override
    public String getConstValueByKey(Hashtable<Integer,String> hashtable, String key){
        return CommonUtils.getValueByKeyFromHash(hashtable,key);
    }

}
