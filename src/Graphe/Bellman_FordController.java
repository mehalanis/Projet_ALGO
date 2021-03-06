package Graphe;

import Graphe.graphe.Graphe;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.GrapheNonOriente;
import Graphe.Bellman_Ford.bell_ford;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import Graphe.Forms.Cercle;
import javafx.scene.layout.Background;

public class Bellman_FordController implements Initializable {

    @FXML
    BorderPane border_Pane;
    GrapheController graphe_controller;
    @FXML
    TableView table;
    @FXML
    HBox Creer_circle_box;
    Button start, remove;
    Cercle cercle;
    ComboBox origine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cercle = new Cercle("");
        cercle.setPadding(new Insets(0, 20, 0, 0));
        Image iconremove = new Image(getClass().getResourceAsStream("iconX.png"));
        remove = new Button("");
        remove.setBackground(Background.EMPTY);
        remove.setGraphic(new ImageView(iconremove));
        remove.setPadding(new Insets(0, 20, 0, 0));
        table.setVisible(false);
        origine = new ComboBox();
        Creer_circle_box.getChildren().add(cercle);
        Creer_circle_box.getChildren().add(remove);
        Creer_circle_box.getChildren().add(origine);
        origine.setPrefSize(30, 45);
        graphe_controller = new GrapheController(cercle, remove, origine);
        border_Pane.setCenter(graphe_controller);
        start = new Button("Start");
        start.setFont(new Font(18));
        start.setPrefSize(80, 45);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setVisible(true);

                bell_ford bell = new bell_ford(graphe_controller.go, table);
                bell.BellmanFordEvaluation(origine.getSelectionModel().getSelectedIndex());
                bell.Affichar();
                graphe_controller.go.InitColorRED(origine.getSelectionModel().getSelectedIndex(), bell.distances);
            }
        });
        Creer_circle_box.getChildren().add(start);
    }

}
