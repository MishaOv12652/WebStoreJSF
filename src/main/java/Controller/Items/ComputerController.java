package Controller.Items;

import DAO.Items.ComputerDBUtils;
import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Computer;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by Misha on 3/25/2018.
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class ComputerController extends ItemController implements Serializable{
    @ManagedProperty(value = "#{item}")
    private Item itemBean;

    private ComputerDBUtils computerDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";

    public ComputerController(){
        this.computerDBUtils = new ComputerDBUtils();
    }

    public void addComputerForSale(Computer computer, String email){
        Computer computerWithItemSpecs = new Computer(this.itemBean.getName(),this.itemBean.getPrice(),this.itemBean.getItemDesc()
                ,this.itemBean.getCategory(),this.itemBean.getCondition(),this.itemBean.getImg(),this.itemBean.getNumOfItems()
                ,computer.getType(),computer.getModel(),computer.getOs(),computer.getCpu(),computer.getCpuSpeed()
                ,computer.getMemory(),computer.getGpu(),computer.getBrand(),computer.getScreenSize(),computer.getReleaseYear()
                ,computer.getHdd(),computer.getSsd());
        try {
            this.addItemForSale(computerWithItemSpecs,email,this.computerDBUtils);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Computer Wasn't Successfully added",
                    "The Computer " + computer.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
