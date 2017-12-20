// Jonathon Sauers
// jo046326
// Object Oriented Programming, Summer 2017
// DatabaseJavaFx.java

package databasejavafx;

import dataModel.FilmDAO;
import inputOutput.ConnectionData;
import inputOutput.PostgreSQLConnect;
import inputOutput.XmlParser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;

public class DatabaseJavaFx extends Application
{
    private static final Logger LOGGER = Logger.getLogger(DatabaseJavaFx.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Sets up the tableView.
        TableView tableView = new TableView();
        tableView.setEditable(true);
        final Label label = new Label("Film");
        label.setFont(new Font("Arial", 20));

        // Four instances of TableColumn for each column of the database table.
        TableColumn title = new TableColumn("Title");
        title.setMinWidth(200);
        title.setCellValueFactory(new PropertyValueFactory<>("filmName"));

        TableColumn description = new TableColumn("Description");
        description.setMinWidth(700);
        description.setCellValueFactory(new PropertyValueFactory<>("filmDescription"));

        TableColumn rate = new TableColumn("Rental Rate");
        rate.setMinWidth(100);
        rate.setCellValueFactory(new PropertyValueFactory<>("filmPrice"));

        TableColumn rating = new TableColumn("Rating");
        rating.setMinWidth(100);
        rating.setCellValueFactory(new PropertyValueFactory<>("filmRating"));

        // Add the four columns to tableView.
        tableView.getColumns().addAll(title, description, rate, rating);

        // Fetches films from database.
        final Button fetchData = new Button("Fetch films from database");
        fetchData.setOnAction((ActionEvent event) -> {
            fetchData(tableView);
        });

        // Create a scene.
        Scene scene = new Scene(new Group());
        final VBox vbox = new VBox();
        vbox.setPrefHeight(500);
        vbox.setStyle("-fx-background-color: cornsilk; -fx-adding: 50;");
        vbox.getChildren().addAll(label, tableView);
        ((Group)scene.getRoot()).getChildren().addAll(vbox, fetchData);

        // Setup and create the UI.
        stage.setTitle("Films for Rent");
        stage.setScene(scene);
        stage.show();
    }
    private void fetchData(TableView tableView)
    {
        // getConnection connects to the database.
        try(Connection con = getConnection())
        {
            // Populate UI by calling method fetchData().
            tableView.setItems(fetchFilms(con));
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    private Connection getConnection() throws ClassNotFoundException, SQLException
    {
        LOGGER.info("Getting a database connection");

        // Read in properties from XML and passes the location as an argument.
        XmlParser xml = new XmlParser("inputOutput/properties.xml");
        ConnectionData data = xml.getConnectionData();

        // Creates the connnection.
        PostgreSQLConnect connect = new PostgreSQLConnect(data);
        Connection databaseConnect = connect.getConnection();

        return databaseConnect;
    }

    private ObservableList<FilmDAO> fetchFilms(Connection con) throws SQLException
    {
        LOGGER.info("fetching film from database");
        ObservableList<FilmDAO> films = FXCollections.observableArrayList();

        String select = "Select title, rental_rate, rating, description " + "from film " + "order by title";
        LOGGER.log(Level.INFO, "Select statement {0}", select);

        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(select);

        while(result.next())
        {
            // Creates the DAO.
            FilmDAO film = new FilmDAO();
            film.setFilmName(result.getString("title"));
            film.setFilmRating(result.getString("rating"));
            film.setFilmDescription(result.getString("description"));
            film.setFilmPrice(result.getDouble("rental_rate"));
            films.add(film);
        }

        LOGGER.log(Level.INFO, "Found {0} films", films.size());

        return films;
    }
}
