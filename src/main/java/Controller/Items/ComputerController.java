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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

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
    private static final String VIEW_ITEM_PAGE = "/item-view";
    private static final String EDIT_ITEM_PAGE = "/secured/edit-item";

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

    public Computer loadComputerForSale(Integer id){
        if(id != null){
            try {
                return this.computerDBUtils.loadComputerForSale(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String loadComputerForUpdate(Integer id, Integer itemId, boolean edit) {
        if (itemId != null) {
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> requestMap = externalContext.getRequestMap();
                requestMap.put("item", this.getItemDBUtils().loadItemForSale(itemId));
                requestMap.put("computer", this.computerDBUtils.loadComputerForSale(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(edit){
            return EDIT_ITEM_PAGE;
        }else{
            return VIEW_ITEM_PAGE;
        }
    }

    public void updateComputerForSale(Item item, Computer computer) {
        try {
            this.computerDBUtils.updateCOmputerSpecs(computer);
            this.updateItemForSale(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteComputerForSale(Integer compId,Integer itemId){
        try {
            this.deleteItemForSale(itemId);
            this.computerDBUtils.deleteComputerForSale(compId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
