package com.kodilla;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;


public class GameEndBox extends BattleShips {

    GameEndBox() {
        super();
    }

    public static void display(String message, Stage primaryStage) {
        Stage stageNew = new Stage();

        stageNew.initModality(Modality.APPLICATION_MODAL);
        stageNew.setTitle("Koniec!");
        stageNew.setMinWidth(600);
        stageNew.setMinHeight(150);

        Label label = new Label();
        label.setText("Koniec gry! " + message + "/n Czy chcesz rozpocząć nową gre?");
        Button buttonYes = new Button("Tak");
        Button buttonNo = new Button("Nie");

        buttonYes.setOnAction(event -> {
            BattleShips battleShips = new BattleShips();
            battleShips.start(primaryStage);
            stageNew.close();
        });

        buttonNo.setOnAction(event -> {
            stageNew.close();
        });

        GridPane pane = new GridPane();
        pane.setHgap(20);
        pane.setVgap(20);
        GridPane.setConstraints(label, 5, 0, 20, 1);
        GridPane.setConstraints(buttonYes, 11, 1);
        GridPane.setConstraints(buttonNo, 13, 1);
        pane.getChildren().addAll(label, buttonNo, buttonYes);

        Scene scene = new Scene(pane);
        stageNew.setScene(scene);
        stageNew.showAndWait();

    }



}
