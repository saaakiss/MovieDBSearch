package myapp.com.moviedbsearch.contracts;

public class SearchMainContract {

    public interface View{
        void showMoviesTvShows();
        void showError();
    }

    public interface Actions{
        void getMoviewTvShows(String query);
    }
}
