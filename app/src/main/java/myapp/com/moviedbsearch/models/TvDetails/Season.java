package myapp.com.moviedbsearch.models.TvDetails;

public class Season {

    private String id;

    private String season_number;

    private String episode_count;

    private String overview;

    private String air_date;

    private String name;

    private String poster_path;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSeason_number ()
    {
        return season_number;
    }

    public void setSeason_number (String season_number)
    {
        this.season_number = season_number;
    }

    public String getEpisode_count ()
    {
        return episode_count;
    }

    public void setEpisode_count (String episode_count)
    {
        this.episode_count = episode_count;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public String getAir_date ()
    {
        return air_date;
    }

    public void setAir_date (String air_date)
    {
        this.air_date = air_date;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
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
