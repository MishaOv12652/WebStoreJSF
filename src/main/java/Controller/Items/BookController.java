package Controller.Items;

import DAO.Items.BookDBUtils;
import ModelManagedBeans.Items.Book;
import Utils.CommonUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by Misha on 3/25/2018.
 */
@ManagedBean
@SessionScoped
public class BookController extends ItemController implements Serializable {
    private BookDBUtils bookDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";

    public BookController(){
        this.bookDBUtils = new BookDBUtils();
    }

    public void addBookForSale(Book book,String email){
        try {
            int idOfUser = CommonUtils.getUserIdByEmail(email);
            int idOfItem = this.bookDBUtils.addBookForSale(book, idOfUser);
            if ( idOfItem == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Book Wasn't Successfully added",
                        "The Book " + book.getName() + " wasn't added for sale"));

            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Added Successfully",
                        "The Book " + book.getName() + " was added for sale"));
                book.setId(idOfItem);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Book Wasn't Successfully added",
                    "The Book " + book.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
