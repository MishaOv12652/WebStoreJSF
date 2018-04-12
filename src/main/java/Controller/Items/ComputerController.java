package Controller.Items;

import DAO.Items.ComputerDBUtils;
import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Computer;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.RedirectHelper;
import Utils.SessionUtils;
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

    public ComputerController(){
        this.computerDBUtils = new ComputerDBUtils();
    }

    /**
     * adds computer specs for sale
     * @param computer - computer to insert
     * @param email - email of the seller to get the id
     */
    public void addComputerForSale(Computer computer, String email){
        Computer computerWithItemSpecs = new Computer(this.itemBean.getName(),this.itemBean.getPrice(),this.itemBean.getItemDesc()
                ,this.itemBean.getCategory(),this.itemBean.getCondition(),this.itemBean.getShippingPrice(),this.itemBean.getNumOfItemsToBuy(),this.itemBean.getImg(),this.itemBean.getNumOfItems()
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

    /**
     * loads computer specs for the selling list
     * @param id - computer specs id
     * @return returns the computer found
     */
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

    /**
     * loads item specs and computer specs for update or view page
     * @param id - computer specs id
     * @param itemId - item id
     * @param edit - edit or view page bool
     * @return return a string of the url to populate data to
     */
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
        return this.checkIfEdit(edit,itemId);
    }

    /**
     * updates a item of the category computer
     * @param item - item object - for specs like name and ec.
     * @param computer - computer object - for computer specs
     */
    public void updateComputerForSale(Item item, Computer computer) {
        try {
            this.computerDBUtils.updateCOmputerSpecs(computer);
            this.updateItemForSale(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a computer that was for sale
     * @param compId - computer specs id
     * @param itemId - item id
     */
    public void deleteComputerForSale(Integer compId,Integer itemId){
        try {
            this.deleteItemForSale(itemId);
            this.computerDBUtils.deleteComputerForSale(compId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
