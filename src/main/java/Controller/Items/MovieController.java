package Controller.Items;

import DAO.Items.MovieDBUtils;
import ModelManagedBeans.Items.Movie;
import Utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@ManagedBean
@SessionScoped
public class MovieController implements Serializable{
    private Movie movie;
    private MovieDBUtils movieDBUtils;
    private static final String PROFILE_PAGE_REDIRECT_SELLING_LIST =
            "/NewSadna_war_exploded/secured/profile-selling-items.xhtml";


    public MovieController(){
        this.movieDBUtils = new MovieDBUtils();
    }

    public void addMovieForSale(Movie movie,String email){
        try {
            int idOfUser = CommonUtils.getUserIdByEmail(email);
            int idOfItem = this.movieDBUtils.addMovieForSale(movie, idOfUser);
            if ( idOfItem == -1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Movie Wasn't Successfully added",
                        "The Movie " + movie.getName() + " wasn't added for sale"));

            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Movie Added Successfully",
                        "The Movie " + movie.getName() + " was added for sale"));
                movie.setId(idOfItem);
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect(PROFILE_PAGE_REDIRECT_SELLING_LIST);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Movie Wasn't Successfully added",
                    "The Movie " + movie.getName() + " wasn't added for sale"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
