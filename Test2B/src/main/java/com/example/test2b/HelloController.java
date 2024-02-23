package com.example.test2b;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;


public class HelloController {
    @FXML
    private Label welcomeText;

    // @FXML
    //protected void onHelloButtonClick() {
    //  welcomeText.setText("Welcome to JavaFX Application!");
    //}

    public TextField eTitle;
    public TextField eAuthor;
    public TextField eGenre;
    public TextField eBookID;


    @FXML
    private TableView<Library_Books> tableView;
    @FXML
    private TableColumn<Library_Books, Integer> BookID;
    @FXML
    private TableColumn<Library_Books, String> Title;
    @FXML
    private TableColumn<Library_Books, String> Author;
    @FXML
    private TableColumn<Library_Books, String> Genre;

    ObservableList<Library_Books> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookID.setCellValueFactory(new
                PropertyValueFactory<Library_Books, Integer>("BookID"));
        Title.setCellValueFactory(new
                PropertyValueFactory<Library_Books, String>("Title"));
        Author.setCellValueFactory(new
                PropertyValueFactory<Library_Books, String>("Author"));
        Genre.setCellValueFactory(new
                PropertyValueFactory<Library_Books, String>("Genre"));

        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }

    public void populateTable() {

        list.clear();


// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `Library_Books`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int BookID = resultSet.getInt("BookID");
                String Title = resultSet.getString("Title");
                String Author = resultSet.getString("Author");
                String Genre = resultSet.getString("Genre");

                tableView.getItems().add(new Library_Books(BookID, Title, Author, Genre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(ActionEvent actionEvent) {

        String ETitle = eTitle.getText();
        String EAuthor = eAuthor.getText();
        String EGenre = eGenre.getText();


        InsertTable(ETitle, EAuthor, EGenre);
    }


    public void InsertTable(String eTitle, String eAuthor, String eGenre) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `Library_Books`( `Title`, `Author`, `Genre`) VALUES ('" + eTitle + "','" + eAuthor + "','" + eGenre + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteData(ActionEvent actionEvent) {


        String Eid = eBookID.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `tbl_employee` WHERE id='" + Eid + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void UpdateData(ActionEvent actionEvent) {


        String ETitle = eTitle.getText();
        String EAuthor = eAuthor.getText();
        String EGenre = eGenre.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `Library_Books` SET `Title`='" + ETitle + "',`Author`='" + EAuthor + "',`Genre`='" + EGenre + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void LoadData(ActionEvent actionEvent) {


        String Eid = eBookID.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `Library_Books` WHERE id='" + Eid + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String employee_name = resultSet.getString("employee_name");
                int salary = resultSet.getInt("salary");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");


                eTitle.setText(String.valueOf(Title));
                eAuthor.setText(String.valueOf(Author));
                eGenre.setText(String.valueOf(Genre));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}