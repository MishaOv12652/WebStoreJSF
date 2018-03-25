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
@SessionScoped
@Getter
@Setter
public class Computer extends Item implements Serializable {

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

    //const lists
    private Hashtable<Integer, String> osSys;
    private Hashtable<Integer, String> cpuList;
    private Hashtable<Integer, String> gpuList;
    private Hashtable<Integer, String> storageCaps;
    private Hashtable<Integer, String> brandList;

    public Computer() {
        super();
    }

    public Computer(String name, float price, String itemDesc, int category, int condition, UploadedFile uploadedFile, int numOfItems,
                    String type, String model, int os, int cpu, double cpuSpeed, int memory, int gpu, int brand, double screenSize, int releaseYear, int hdd, int ssd) {
        super(name, price, itemDesc, category, condition, uploadedFile, numOfItems);
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
        this.osSys = CommonUtils.getConstLists("dreambuy.os_systems", "os");
        //get cpu const list
        this.cpuList = CommonUtils.getConstLists("dreambuy.cpu_list", "cpu");
        //get gpu const list
        this.gpuList = CommonUtils.getConstLists("dreambuy.gpu_list", "gpu");
        //get storage capacity  const list
        this.storageCaps = CommonUtils.getConstLists("dreambuy.storage_capacity", "capacity");
        //get brand cont list
        this.brandList = CommonUtils.getConstLists("dreambuy.cellphone_brands", "brand");


    }
}
