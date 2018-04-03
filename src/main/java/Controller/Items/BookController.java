package Controller.Items;

import DAO.Items.BookDBUtils;
import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Misha on 3/25/2018.
 */
@ManagedBean
@ViewScoped
@SessionScoped
@Getter
@Setter
public class BookController extends ItemController implements Serializable {
    @ManagedProperty(value = "#{item}")
    private Item itemBean;

    private Book book;
    private BookDBUtils bookDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";
    private static final String EDIT_ITEM_PAGE = "/secured/edit-item";
    private static final String VIEW_ITEM_PAGE = "/item-view";

    public BookController() {
        this.bookDBUtils = new BookDBUtils();
    }

    /**
     * adds book specs for sale
     * @param book - book to insert
     * @param email - email of the seller to get the id
     */
    public void addBookForSale(Book book, String email) {
        Book bookWithItemSpecs = new Book(this.itemBean.getName(), this.itemBean.getPrice(), this.itemBean.getItemDesc()
                , this.itemBean.getCategory(), this.itemBean.getCondition(), this.itemBean.getImg(), this.itemBean.getNumOfItems(),this.itemBean.getShippingPrice()
                , book.getAuthor(), book.getGenre(), book.getSeries(), book.getAgeLvl());
        try {
            this.addItemForSale(bookWithItemSpecs, email, this.bookDBUtils);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Book Wasn't Successfully added",
                    "The Book " + book.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads book specs for the selling list
     * @param id - books specs id
     * @return returns the book found
     */
    public Book loadBookForSale(Integer id) {
        if (id != null) {
            try {
                return this.bookDBUtils.loadBooksForSale(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * loads item specs and book specs for update or view page
     * @param id - book specs id
     * @param itemId - item id
     * @param edit - edit or view page bool
     * @return return a string of the url to populate data to
     */
    public String loadBookForUpdate(Integer id, Integer itemId,boolean edit) {
        if (itemId != null) {
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> requestMap = externalContext.getRequestMap();
                requestMap.put("item", this.getItemDBUtils().loadItemForSale(itemId));
                requestMap.put("book", this.bookDBUtils.loadBooksForSale(id));
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

    /**
     * updates a item of the category book
     * @param item - item object - for specs like name and ec.
     * @param book - book object - for book specs
     */
    public void updateBookForSale(Item item, Book book) {
        try {
            this.bookDBUtils.updateBookSpecs(book);
            this.updateItemForSale(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a book that was for sale
     * @param bookId - book specs id
     * @param itemId - item id
     */
    public void deleteBookForSale(Integer bookId,Integer itemId){
        try {
            this.deleteItemForSale(itemId);
            this.bookDBUtils.deleteBookForSale(bookId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void setItemBean(Item itemBean) {
        this.itemBean = itemBean;
    }
}
