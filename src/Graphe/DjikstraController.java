/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphe;

import Graphe.Dijkstra.Djkstra;
import Graphe.graphe.Graphe;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.GrapheNonOriente;
import Graphe.Forms.Cercle;
import Graphe.Johnson.Dijkstra;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class DjikstraController implements Initializable {

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

        ToggleGroup tg = new ToggleGroup();
        RadioButton oriente = new RadioButton("Oriente");
        RadioButton Nonoriente = new RadioButton("Non Oriente");
        oriente.setToggleGroup(tg);
        Nonoriente.setToggleGroup(tg);
        tg.selectToggle(oriente);
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (oriente.isSelected()) {
                    graphe_controller = new GrapheController(cercle, remove, origine);
                    graphe_controller.go = new GrapheOriente();
                    border_Pane.setCenter(graphe_controller);
                } else {
                    graphe_controller = new GrapheController(cercle, remove, origine);
                    graphe_controller.go = new GrapheNonOriente();
                    border_Pane.setCenter(graphe_controller);

                }
            }
        };
        oriente.setOnAction(action);
        Nonoriente.setOnAction(action);
        origine = new ComboBox();
        Creer_circle_box.getChildren().add(cercle);
        Creer_circle_box.getChildren().add(remove);
        oriente.setPrefSize(80, 45);
        Creer_circle_box.getChildren().add(oriente);
        Nonoriente.setPrefSize(105, 45);
        Creer_circle_box.getChildren().add(Nonoriente);

        Creer_circle_box.getChildren().add(origine);
        origine.setPrefSize(30, 45);
        graphe_controller = new GrapheController(cercle, remove, origine);
        border_Pane.setCenter(graphe_controller);
        graphe_controller.go = new GrapheOriente();
        start = new Button("Start");
        start.setFont(new Font(18));
        start.setPrefSize(80, 45);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setVisible(true);

                int source = origine.getSelectionModel().getSelectedIndex();
                Dijkstra j = new Dijkstra(graphe_controller.go, table);

                j.dijkstra_algorithm(source);
                j.AfficherTable(source);
                /* Djkstra d = new Djkstra(table, graphe_controller.go);
                d.pcc(graphe_controller.go.getSommet(source));
                d.AfficherTable(source);*/
                graphe_controller.go.InitColorRED(source, j.distances);

            }
        });
        Creer_circle_box.getChildren().add(start);
    }

}
