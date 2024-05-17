
package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.fxml.Initializable;

public class DocumentController implements Initializable {

	@FXML
	private AnchorPane main_form;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginBtn;

	@FXML
	private Button closeBtn;

	public void close() {
		System.exit(0);
	}

	public void login() {
	    String nom = username.getText();
	    String motdepasse = password.getText();

	    if (nom.isEmpty()) {
	        showAlert(AlertType.ERROR, "Form Error!", "Please enter your name");
	    } else if (motdepasse.isEmpty()) {
	        showAlert(AlertType.ERROR, "Form Error!", "Please enter your password");
	    } else {
	        showAlert(AlertType.INFORMATION, "Authentification successful!", "Welcome " + nom);
	        authenticate(nom, motdepasse);
	        loginBtn.getScene().getWindow().hide();
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
	        BorderPane root = new BorderPane();
            loader.setRoot(root);
	        try {
	            root = loader.load();
	            Stage stage = new Stage();
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


	private void authenticate(String nom, String motdepasse) {
	        try {
	            BufferedReader bf = new BufferedReader(new FileReader("D:\\2éme annéé\\S2\\java\\PetShopManagement\\fichiers\\admin.txt"));
	            String chaine;
	            boolean authentificationReussie = false;
	            try {
	                while ((chaine = bf.readLine()) != null) {
	                    String[] parts = chaine.split(" ");
	                    String loginFichier = parts[0];
	                    String motDePasseFichier = parts[1];

	                    if (nom.equals(loginFichier) && motdepasse.equals(motDePasseFichier)) {
	                        authentificationReussie = true;
	                        break;
	                    }
	                }
	            } catch (IOException e) {
	                showAlert(AlertType.ERROR, "Authentication Error!", "An error occurred while authenticating");
	                e.printStackTrace();
	            }
	            if (!authentificationReussie){
	                showAlert(AlertType.ERROR, "Authentication Error!", "Invalid username or password");
	            }
	        } catch (FileNotFoundException e) {
	            showAlert(AlertType.ERROR, "Authentication Error!", "An error occurred while authenticating");
	            e.printStackTrace();
	        }
	    }

	private void showAlert(AlertType alertType, String title, String content) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.show();
	    }

	@Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    }
}