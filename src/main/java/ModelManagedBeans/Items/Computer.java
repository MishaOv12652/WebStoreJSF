package ModelManagedBeans.Items;

import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Misha on 3/25/2018.
 */
@ManagedBean
@SessionScoped
@Getter
@Setter
public class Computer extends Item implements Serializable {
    private int id;
    private String type;
    private String model;
    private int os;
    private int cpu;
    private double cpuSpeed;
    private int memory;
    private int gpu;
    private int brand;
    private double screenSize;
    private int releaseYear;
    private int hdd;
    private int ssd;

    //const Lists
    private Hashtable<Integer, String> osSys;
    private Hashtable<Integer, String> cpuList;
    private Hashtable<Integer, String> gpuList;
    private Hashtable<Integer, String> storageCaps;
    private Hashtable<Integer, String> brandList;

    public Computer() {
        super();
    }

    public Computer(int id,String type, String model, int os, int cpu, double cpuSpeed, int memory, int gpu, int brand, double screenSize,
                    int releaseYear, int hdd, int ssd) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.os = os;
        this.cpu = cpu;
        this.cpuSpeed = cpuSpeed;
        this.memory = memory;
        this.gpu = gpu;
        this.brand = brand;
        this.screenSize = screenSize;
        this.releaseYear = releaseYear;
        this.hdd = hdd;
        this.ssd = ssd;
    }

    public Computer(int id,String type, String model, int os, int cpu, double cpuSpeed, int memory, int gpu, int brand, double screenSize,
                    int releaseYear, int hdd, int ssd, Hashtable<Integer, String> osSys, Hashtable<Integer, String> cpuList,
                    Hashtable<Integer, String> gpuList, Hashtable<Integer, String> storageCaps, Hashtable<Integer, String> brandList) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.os = os;
        this.cpu = cpu;
        this.cpuSpeed = cpuSpeed;
        this.memory = memory;
        this.gpu = gpu;
        this.brand = brand;
        this.screenSize = screenSize;
        this.releaseYear = releaseYear;
        this.hdd = hdd;
        this.ssd = ssd;
        this.osSys = osSys;
        this.cpuList = cpuList;
        this.gpuList = gpuList;
        this.storageCaps = storageCaps;
        this.brandList = brandList;
    }

    public Computer(String name, float price, String itemDesc, int category, int condition, float shippingPrice, int numOfItemsToBuy, Part uploadedFile, int numOfItems,
                    String type, String model, int os, int cpu, double cpuSpeed, int memory, int gpu, int brand, double screenSize, int releaseYear, int hdd, int ssd) {
        super(name, price, itemDesc, category, condition, shippingPrice,uploadedFile, numOfItems,numOfItemsToBuy);
        this.type = type;
        this.model = model;
        this.os = os;
        this.cpu = cpu;
        this.cpuSpeed = cpuSpeed;
        this.memory = memory;
        this.gpu = gpu;
        this.brand = brand;
        this.screenSize = screenSize;
        this.releaseYear = releaseYear;
        this.hdd = hdd;
        this.ssd = ssd;
    }

    @PostConstruct
    public void init() {
        //get os const list
        this.osSys = CommonUtils.getConstLists("dreamdb.os_systems", "os");
        //get cpu const list
        this.cpuList = CommonUtils.getConstLists("dreamdb.cpu_list", "cpu");
        //get gpu const list
        this.gpuList = CommonUtils.getConstLists("dreamdb.gpu_list", "gpu");
        //get storage capacity  const list
        this.storageCaps = CommonUtils.getConstLists("dreamdb.storage_capacity", "capacity");
        //get brand cont list
        this.brandList = CommonUtils.getConstLists("dreamdb.cellphone_brands", "brand");
    }

    @Override
    public String getConstValueByKey(Hashtable<Integer, String> hashtable, String key) {
        return CommonUtils.getValueByKeyFromHash(hashtable, key);
    }

}
