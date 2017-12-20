// Jonathon Sauers
// jo046326
// Object Oriented Programming, Summer 2017
// DatabaseJavaFx.java

package dataModel;

public class FilmDAO
{
    private String filmName;
    private String filmRating;
    private String filmDescription;
    private Double filmPrice;

    // Data Access Object
    public FilmDAO() {}

    public FilmDAO(String name, String rating, String description, Double price)
    {
        this.filmName = name;
        this.filmRating = rating;
        this.filmDescription = description;
        this.filmPrice = price;
    }

    /**
     * @return the filmName
     */
    public String getFilmName()
    {
        return filmName;
    }

    /**
     * @param filmName the filmName to set
     */
    public void setFilmName(String filmName)
    {
        this.filmName = filmName;
    }

    /**
     * @return the filmRating
     */
    public String getFilmRating()
    {
        return filmRating;
    }

    /**
     * @param filmRating the filmRating to set
     */
    public void setFilmRating(String filmRating)
    {
        this.filmRating = filmRating;
    }

    /**
     * @return the filmDescription
     */
    public String getFilmDescription()
    {
        return filmDescription;
    }

    /**
     * @param filmDescription the filmDescription to set
     */
    public void setFilmDescription(String filmDescription)
    {
        this.filmDescription = filmDescription;
    }

    /**
     * @return the filmPrice
     */
    public Double getFilmPrice()
    {
        return filmPrice;
    }

    /**
     * @param filmPrice the filmPrice to set
     */
    public void setFilmPrice(Double filmPrice)
    {
        this.filmPrice = filmPrice;
    }
}
