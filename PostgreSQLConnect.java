// Jonathon Sauers
// jo046326
// Object Oriented Programming, Summer 2017
// DatabaseJavaFx.java

package inputOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connects to PostgreSQL.
public class PostgreSQLConnect
{
    private Connection connect;

    /**
     *
     * @param data
     */
    public PostgreSQLConnect(ConnectionData data)
    {
        try
        {
            System.out.println("data is " + data.toString());
            Class.forName(data.getType());
            connect = DriverManager.getConnection(data.toString(), data.getLogin(), data.getPassword());
        }
        catch(ClassNotFoundException | SQLException ex) {}

        System.out.println("Opened database successfully");
    }

    /**
     *
     * @return Connection to database
     */
    public Connection getConnection()
    {
        return connect;
    }
}
