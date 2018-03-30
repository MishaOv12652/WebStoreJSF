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
//@RequestScoped
@Getter
@Setter
public class BookController extends ItemController implements Serializable {
    @ManagedProperty(value = "#{item}")
    private Item itemBean;

    private Book book;
    private BookDBUtils bookDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";

    public BookController() {
        this.bookDBUtils = new BookDBUtils();
    }

    public void addBookForSale(Book book, String email) {
        Book bookWithItemSpecs = new Book(this.itemBean.getName(), this.itemBean.getPrice(), this.itemBean.getItemDesc()
                , this.itemBean.getCategory(), this.itemBean.getCondition(), this.itemBean.getImg(), this.itemBean.getNumOfItems()
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

    public Book loadBookForSale(Integer id) {
        if (id != null) {
            try {
                Book book = this.bookDBUtils.loadBooksForSale(id);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> requestMap = externalContext.getRequestMap();
                if (requestMap.containsKey("book")) {
                    requestMap.remove("book");
                }
                requestMap.put("book", book);
                return book;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void updateBookForSale(Item item, Book book) {
        try {
            this.bookDBUtils.updateBookSpecs(book);

            this.updateItemForSale(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setItemBean(Item itemBean) {
        this.itemBean = itemBean;
    }
}
