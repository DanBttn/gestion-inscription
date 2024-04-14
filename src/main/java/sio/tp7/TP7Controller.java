package sio.tp7;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sio.tp7.Tools.ConnexionBDD;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TP7Controller implements Initializable {

    ConnexionBDD cnx;
    @javafx.fxml.FXML
    private ImageView imgInscription;
    @javafx.fxml.FXML
    private ImageView imgPresence;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cnx = new ConnexionBDD();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void imgInscriptionClicked(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TP7Application.class.getResource("inscription-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void imgPresenceClicked(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TP7Application.class.getResource("presence-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Présence");
        stage.setScene(scene);
        stage.show();
    }
}