package sio.tp7;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import sio.tp7.Model.Agent;
import sio.tp7.Model.AgentPresent;
import sio.tp7.Model.Formation;
import sio.tp7.Services.ServiceAgent;
import sio.tp7.Services.ServiceFormation;
import sio.tp7.Services.ServiceInscription;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PresenceController implements Initializable {
    String formationSelectionnee;
    private ServiceFormation serviceFormation;
    private  ServiceInscription serviceInscription;
    private ServiceAgent serviceAgent;
    @javafx.fxml.FXML
    private TableColumn tcNomFormation;
    @javafx.fxml.FXML
    private TableColumn tcNumeroFormation;
    @javafx.fxml.FXML
    private TableView<Formation> tvFormations;
    @javafx.fxml.FXML
    private TableView<AgentPresent> tvAgentsInscrits;
    @javafx.fxml.FXML
    private Button btnValider;
    @javafx.fxml.FXML
    private TableColumn tcNomAgentInscrit;
    @javafx.fxml.FXML
    private TableColumn tcNumeroAgentInscrit;
    @javafx.fxml.FXML
    private TableColumn tcPrenomAgentInscrit;
    @javafx.fxml.FXML
    private TableColumn<AgentPresent, Boolean> tcPresenceAgentInscrit;
    @javafx.fxml.FXML
    private Button btnDesinscrire;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            serviceFormation = new ServiceFormation();
            serviceAgent = new ServiceAgent();
            serviceInscription = new ServiceInscription();

            tcNomFormation.setCellValueFactory(new PropertyValueFactory<>("nomFormation"));
            tcNumeroFormation.setCellValueFactory(new PropertyValueFactory<>("idFormation"));

            tvFormations.setItems(FXCollections.observableArrayList(serviceFormation.GetAllFormations()));


            tcNumeroAgentInscrit.setCellValueFactory(new PropertyValueFactory<>("numeroAgent"));
            tcNomAgentInscrit.setCellValueFactory(new PropertyValueFactory<>("nomAgent"));
            tcPrenomAgentInscrit.setCellValueFactory(new PropertyValueFactory<>("prenomAgent"));
            tcPresenceAgentInscrit.setCellValueFactory(new PropertyValueFactory<>("presenceAgent"));

            tcPresenceAgentInscrit.setCellValueFactory(data ->
            {
                SimpleBooleanProperty property = new SimpleBooleanProperty();
                property.setValue(data.getValue().isPresenceAgent());
                return property;
            });


            tcPresenceAgentInscrit.setCellFactory(data ->new CheckBoxTableCell<>());

            tvAgentsInscrits.setEditable(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @javafx.fxml.FXML
    public void tvFormationsClicked(Event event) throws SQLException {
        formationSelectionnee = tvFormations.getSelectionModel().getSelectedItem().getIdFormation();
        tvAgentsInscrits.setItems(FXCollections.observableArrayList(serviceAgent.GetAllAgentsInscritsFormation(formationSelectionnee)));
    }

    @javafx.fxml.FXML
    public void btnValiderClicked(Event event) throws SQLException {
        for (AgentPresent agentPresent : tvAgentsInscrits.getSelectionModel().getSelectedItems()) {
            serviceInscription.ModifierPresence(formationSelectionnee, agentPresent.getNumeroAgent(), agentPresent.isPresenceAgent());
        }

        tvAgentsInscrits.setItems(FXCollections.observableArrayList(serviceAgent.GetAllAgentsInscritsFormation(formationSelectionnee)));
        tvAgentsInscrits.refresh();
    }






    public void btnDesinscrireClicked(Event event) throws SQLException {
        String idAgent = tvAgentsInscrits.getSelectionModel().getSelectedItem().getNumeroAgent();
        serviceInscription.DesinscrireAgentByIdAgent(idAgent);
        formationSelectionnee = tvFormations.getSelectionModel().getSelectedItem().getIdFormation();
        tvAgentsInscrits.setItems(FXCollections.observableList(serviceAgent.GetAllAgentsInscritsFormation(formationSelectionnee)));
        tvAgentsInscrits.refresh();
    }
}