package Controller.Items;

import DAO.Items.MovieDBUtils;
import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Item;
import ModelManagedBeans.Items.Movie;
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
public class MovieController extends ItemController implements Serializable{
    @ManagedProperty(value = "#{item}")
    private Item itemBean;

    private MovieDBUtils movieDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";
    private static final String EDIT_ITEM_PAGE = "/secured/edit-item";


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

    public Movie loadMovieForSale(Integer id){
        if(id!=null){
            try {
                return this.movieDBUtils.loadMovieForSale(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String loadMovieForUpdate(Integer id, Integer itemId) {
        if (itemId != null) {
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> requestMap = externalContext.getRequestMap();
                requestMap.put("item", this.getItemDBUtils().loadItemForSale(itemId));
                requestMap.put("movie", this.movieDBUtils.loadMovieForSale(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return EDIT_ITEM_PAGE;
    }

    public void updateMovieForSale(Item item, Movie movie) {
        try {
            this.movieDBUtils.updateMovieSpecs(movie);
            this.updateItemForSale(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
