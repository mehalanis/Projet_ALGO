package Graphe;

import Graphe.graphe.Graphe;
import Graphe.graphe.GrapheOriente;
import Graphe.graphe.GrapheNonOriente;
import Graphe.Forms.Cercle;
import Graphe.FloydWarshal.FloydWarshal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javax.swing.JTable;

public class FloydWarshalController implements Initializable {

    @FXML
    BorderPane border_Pane;
    GrapheController graphe_controller;
    @FXML
    TableView table;
    @FXML
    HBox Creer_circle_box;
    Button start, remove;
    Cercle cercle;

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

        remove.setPadding(new Insets(0, 20, 0, 0));
        Creer_circle_box.getChildren().add(cercle);
        Creer_circle_box.getChildren().add(remove);
        graphe_controller = new GrapheController(cercle, remove);
        border_Pane.setCenter(graphe_controller);
        start = new Button("Start");
        start.setFont(new Font(18));
        start.setPrefSize(80, 45);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setVisible(true);

                FloydWarshal m = new FloydWarshal(graphe_controller.go, table);
                m.printSolution(m.listreult.size() - 1);
            }
        });
        Creer_circle_box.getChildren().add(start);

    }

}
