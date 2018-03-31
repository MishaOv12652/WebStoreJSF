package ModelManagedBeans.Items;

import Utils.CommonUtils;
import Utils.SessionUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Blob;
import java.util.Hashtable;

/**
 * Created by Misha on 3/25/2018.
 */
@ManagedBean
@SessionScoped
@Getter
@Setter
public class CellPhone extends Item {

    private double screenSize;
    private int ram;
    private int brand;
    private String model;
    private int colour;
    private int memoryCardType;
    private int os;
    private int storage;
    private int batteryCapacity;

    //const lists
    private Hashtable<Integer, String> brands;
    private Hashtable<Integer, String> colours;
    private Hashtable<Integer, String> osSys;
    private Hashtable<Integer, String> storageTypes;
    private Hashtable<Integer, String> storageCaps;

    public CellPhone() {
        super();
    }

    public CellPhone(double screenSize, int ram, int brand, String model, int memoryCardType, int os, int storage, int batteryCapacity){
        this.screenSize = screenSize;
        this.ram = ram;
        this.brand = brand;
        this.model = model;
        this.memoryCardType = memoryCardType;
        this.os = os;
        this.storage = storage;
        this.batteryCapacity = batteryCapacity;
    }

    public CellPhone(double screenSize, int ram, int brand, String model, int memoryCardType, int os, int storage, int batteryCapacity,
                     Hashtable<Integer, String> brands,Hashtable<Integer, String> osSys,Hashtable<Integer, String> storageTypes,
                     Hashtable<Integer, String> storageCaps){
        this.screenSize = screenSize;
        this.ram = ram;
        this.brand = brand;
        this.model = model;
        this.memoryCardType = memoryCardType;
        this.os = os;
        this.storage = storage;
        this.batteryCapacity = batteryCapacity;
        this.brands = brands;
        this.osSys = osSys;
        this.storageTypes = storageTypes;
        this.storageCaps = storageCaps;
    }

    public CellPhone(String name, float price, String itemDesc, int category, int condition, UploadedFile uploadedFile, int numOfItems,
                     double screenSize, int ram, int brand, String model, int memoryCardType, int os, int storage, int batteryCapacity) {
        super(name, price, itemDesc, category, condition, uploadedFile, numOfItems);
        this.screenSize = screenSize;
        this.ram = ram;
        this.brand = brand;
        this.model = model;
        this.memoryCardType = memoryCardType;
        this.os = os;
        this.storage = storage;
        this.batteryCapacity = batteryCapacity;
    }

    @PostConstruct
    public void init() {
        //get hash of brands options
        this.brands = CommonUtils.getConstLists("dreambuy.cellphone_brands", "brand");
        //get hash of colours options
        this.colours = CommonUtils.getConstLists("dreambuy.colours", "colour");
        //get has of os options
        this.osSys = CommonUtils.getConstLists("dreambuy.os_systems", "os");
        //get storage type - memory card type
        this.storageTypes = CommonUtils.getConstLists("dreambuy.storage_type", "storage_type");
        //get hash of storage capacity options
        this.storageCaps = CommonUtils.getConstLists("dreambuy.storage_capacity", "capacity");
    }

    @Override
    public String getConstValueByKey(Hashtable<Integer,String> hashtable, String key){
        return CommonUtils.getValueByKeyFromHash(hashtable,key);
    }

}
