package Controller.Items;

import DAO.Items.MovieDBUtils;
import ModelManagedBeans.Items.Item;
import ModelManagedBeans.Items.Movie;
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
public class MovieController extends ItemController implements Serializable{
    @ManagedProperty(value = "#{item}")
    private Item itemBean;

    private MovieDBUtils movieDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";


    public MovieController(){
        this.movieDBUtils = new MovieDBUtils();
    }

    public void addMovieForSale(Movie movie,String email){
        Movie movieWithItemSpecs = new Movie(this.itemBean.getName(),this.itemBean.getPrice(),this.itemBean.getItemDesc()
                ,this.itemBean.getCategory(),this.itemBean.getCondition(),this.itemBean.getImg(),this.itemBean.getNumOfItems()
                ,movie.getDirector(),movie.getLength(),movie.getYear(),movie.getAgeLvl(),movie.getKnownActor(),movie.getGenre());
        try {
           this.addItemForSale(movieWithItemSpecs,email,this.movieDBUtils);
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Movie Wasn't Successfully added",
                    "The Movie " + movie.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
