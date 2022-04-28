package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import model.Student;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Student, String> colBirthday;

    @FXML
    private TableColumn<Student, String> colFamily;

    @FXML
    private TableColumn<Student, String> colLastName;

    @FXML
    private TableColumn<Student, String> colName;

    @FXML
    private TableView<Student> tvStudent;

	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showStudent();
    }
	

	public Connection getConnection() {
		Connection connection;
		try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/education5", "root", "daniilamark");
            System.out.println("connect");
            return connection;

		}catch(Exception ex){
			System.out.println("no connect");
			System.out.println("Error: " + ex.getMessage());
			return null;
		}
	}
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public ObservableList<Student> getStudentList() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT family, name, last_name, birthday FROM student";

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Student student;
            while(rs.next()) {
                student = new Student(rs.getString("family"), rs.getString("name"), rs.getString("last_name"), rs.getString("birthday"));

                studentList.add(student);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return studentList;
    }


    public void showStudent() {
        ObservableList<Student> list = getStudentList();

        colFamily.setCellValueFactory(new PropertyValueFactory<Student, String>("family"));
        colName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<Student, String>("birthday"));

        tvStudent.setItems(list);
    }

}
