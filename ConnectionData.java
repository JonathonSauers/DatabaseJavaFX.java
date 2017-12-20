// Jonathon Sauers
// jo046326
// Object Oriented Programming, Summer 2017
// DatabaseJavaFx.java

package inputOutput;

public class ConnectionData
{
    private String type;
    private String url;
    private String ipaddress;
    private String port;
    private String database;
    private String login;
    private String password;

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return the ip address
     */
    public String getIpaddress()
    {
        return ipaddress;
    }

    /**
     * @param ipaddress the ip address to set
     */
    public void setIpaddress(String ipaddress)
    {
        this.ipaddress = ipaddress;
    }

    /**
     * @return the port
     */
    public String getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port)
    {
        this.port = port;
    }

    /**
     * @return the database
     */
    public String getDatabase()
    {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database)
    {
        this.database = database;
    }

    /**
     * @return the login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return url + "://" + ipaddress + ":" + port + "/" + database;
    }
}
