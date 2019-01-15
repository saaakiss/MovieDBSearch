package myapp.com.moviedbsearch.models.TvDetails;

import myapp.com.moviedbsearch.models.MovieDetails.Genres;
import myapp.com.moviedbsearch.models.MovieDetails.ProductionCompany;

public class TvDetails {

    private String vote_average;

    private Genres[] genres;

    private String[] episode_run_time;

    private String type;

    private Object next_episode_to_air;

    private String id;

    private String[] languages;

    private String number_of_seasons;

    private String last_air_date;

    private String in_production;

    private String name;

    private String popularity;

    private CreatedBy[] created_by;

    private Network[] networks;

    private String backdrop_path;

    private String status;

    private String number_of_episodes;

    private LastEpisodeToAir last_episode_to_air;

    private String original_name;

    private String homepage;

    private String first_air_date;

    private String[] origin_country;

    private String original_language;

    private String overview;

    private ProductionCompany[] production_companies;

    private Season[] seasons;

    private String vote_count;

    private String poster_path;

    public String getVote_average ()
    {
        return vote_average;
    }

    public void setVote_average (String vote_average)
    {
        this.vote_average = vote_average;
    }

    public Genres[] getGenres ()
    {
        return genres;
    }

    public void setGenres (Genres[] genres)
    {
        this.genres = genres;
    }

    public String[] getEpisode_run_time ()
    {
        return episode_run_time;
    }

    public void setEpisode_run_time (String[] episode_run_time)
    {
        this.episode_run_time = episode_run_time;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public Object getNext_episode_to_air ()
    {
        return next_episode_to_air;
    }

    public void setNext_episode_to_air (Object next_episode_to_air)
    {
        this.next_episode_to_air = next_episode_to_air;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getLanguages ()
    {
        return languages;
    }

    public void setLanguages (String[] languages)
    {
        this.languages = languages;
    }

    public String getNumber_of_seasons ()
    {
        return number_of_seasons;
    }

    public void setNumber_of_seasons (String number_of_seasons)
    {
        this.number_of_seasons = number_of_seasons;
    }

    public String getLast_air_date ()
    {
        return last_air_date;
    }

    public void setLast_air_date (String last_air_date)
    {
        this.last_air_date = last_air_date;
    }

    public String getIn_production ()
    {
        return in_production;
    }

    public void setIn_production (String in_production)
    {
        this.in_production = in_production;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    public CreatedBy[] getCreated_by ()
    {
        return created_by;
    }

    public void setCreated_by (CreatedBy[] created_by)
    {
        this.created_by = created_by;
    }

    public Network[] getNetworks ()
    {
        return networks;
    }

    public void setNetworks (Network[] networks)
    {
        this.networks = networks;
    }

    public String getBackdrop_path ()
    {
        return backdrop_path;
    }

    public void setBackdrop_path (String backdrop_path)
    {
        this.backdrop_path = backdrop_path;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getNumber_of_episodes ()
    {
        return number_of_episodes;
    }

    public void setNumber_of_episodes (String number_of_episodes)
    {
        this.number_of_episodes = number_of_episodes;
    }

    public LastEpisodeToAir getLast_episode_to_air ()
    {
        return last_episode_to_air;
    }

    public void setLast_episode_to_air (LastEpisodeToAir last_episode_to_air)
    {
        this.last_episode_to_air = last_episode_to_air;
    }

    public String getOriginal_name ()
    {
        return original_name;
    }

    public void setOriginal_name (String original_name)
    {
        this.original_name = original_name;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getFirst_air_date ()
    {
        return first_air_date;
    }

    public void setFirst_air_date (String first_air_date)
    {
        this.first_air_date = first_air_date;
    }

    public String[] getOrigin_country ()
    {
        return origin_country;
    }

    public void setOrigin_country (String[] origin_country)
    {
        this.origin_country = origin_country;
    }

    public String getOriginal_language ()
    {
        return original_language;
    }

    public void setOriginal_language (String original_language)
    {
        this.original_language = original_language;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public ProductionCompany[] getProduction_companies ()
    {
        return production_companies;
    }

    public void setProduction_companies (ProductionCompany[] production_companies)
    {
        this.production_companies = production_companies;
    }

    public Season[] getSeasons ()
    {
        return seasons;
    }

    public void setSeasons (Season[] seasons)
    {
        this.seasons = seasons;
    }

    public String getVote_count ()
    {
        return vote_count;
    }

    public void setVote_count (String vote_count)
    {
        this.vote_count = vote_count;
    }

    public String getPoster_path ()
    {
        return poster_path;
    }

    public void setPoster_path (String poster_path)
    {
        this.poster_path = poster_path;
    }
}
