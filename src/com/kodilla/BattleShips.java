package com.kodilla;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static com.kodilla.AircraftListener.aircraftInstall;
import static com.kodilla.BattleshipListener.battleShipInstall;
import static com.kodilla.ComputersSmartMove.checkNeighbour;
import static com.kodilla.ComputersSmartMove.stageTwo;
import static com.kodilla.GameEndBox.display;
import static com.kodilla.SmallShipListener.smallShipInstall;
import static com.kodilla.SubmarineListener.submarineInstall;


public class BattleShips extends Application {

    private Image imageback = new Image("file:resources/Background.jpg");
    private Ships ships;
    private ArrayList<Integer> occupiedCells = new ArrayList<>();
    private ArrayList<Integer> shipsList = new ArrayList<>();
    private ArrayList<Integer> computerShips = new ArrayList<>();
    private boolean usersMove;
    private ArrayList<Integer> nodesToCheck = new ArrayList<>();
    private int originalNode = -1;
    private int hitNode = -1;
    private String status;
    private int usersWinCount = 0;
    private int computersWinCount = 0;
    private ArrayList<Integer> usersHitFalseList = new ArrayList<>();
    private ArrayList<Integer> computerHitFalseList = new ArrayList<>();
    private ArrayList<Integer> usersHitTrueList = new ArrayList<>();
    private ArrayList<Integer> computerHitTrueList = new ArrayList<>();
    private HashMap<String, ArrayList<Integer>> boardLoad = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {


//      Reading scores
        Path usersPath = Paths.get("C:/Users/Kinga/Documents/Development/Projects/BattleShips/usersWinsCount.txt");
        Path computersPath = Paths.get("C:/Users/Kinga/Documents/Development/Projects/BattleShips/computersWinsCount.txt");


        try {

            List<String> lines = Files.readAllLines(usersPath);
            int lastline = lines.size() - 1;
            String lastLineText = lines.get(lastline);
            String cuted = lastLineText.substring(17);
            usersWinCount = Integer.parseInt(cuted);

            List<String> lines2 = Files.readAllLines(computersPath);
            String lastLineText2 = lines2.get((lines2.size() - 1));
            String cuted2 = lastLineText2.substring(19);
            computersWinCount = Integer.parseInt(cuted2);


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //Load last game
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"));
            Object readMap = ois.readObject();
            if (readMap instanceof HashMap) {
                boardLoad.putAll((HashMap) readMap);
            }
            ois.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        for (Map.Entry<String, ArrayList<Integer>> entry : boardLoad.entrySet()) {
            if (entry.getKey().equals("usersHitFalseList")) {
                usersHitFalseList.addAll(entry.getValue());
            } else if (entry.getKey().equals("computerHitFalseList")) {
                computerHitFalseList.addAll(entry.getValue());
            } else if (entry.getKey().equals("usersHitTrueList")) {
                usersHitTrueList.addAll(entry.getValue());
            } else if (entry.getKey().equals("computerHitTrueList")) {
                computerHitTrueList.addAll(entry.getValue());
            } else if (entry.getKey().equals("shipsList")) {
                shipsList.addAll(entry.getValue());
            } else if (entry.getKey().equals("computerShips")) {
                computerShips.addAll(entry.getValue());
            }
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get("C:/Users/Kinga/Documents/Development/Projects/BattleShips/turn.txt"));
            int lastLine = lines.size() - 1;
            String lastLineText = lines.get(lastLine);
            usersMove = Boolean.getBoolean(lastLineText);

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }


        GridPane game = new GridPane();
        game.setHgap(50);
        game.setVgap(20);

        Scene scene = new Scene(game, 1800, 900, Color.BLACK);


        //Setting background
        BackgroundSize backgroundSize = new BackgroundSize(1, 1, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        game.setBackground(background);


        //Creating boards
        game.setPadding(new Insets(50.0, 0, 0, 50.0));
        Board board = new Board();
        GridPane userBoard = board.createUserBoard();
        GridPane.setConstraints(userBoard, 1, 0);
        game.getChildren().add(userBoard);

        GridPane computerBoard = board.createUserBoard();
        GridPane.setConstraints(computerBoard, 4, 0);
        game.getChildren().add(computerBoard);

        //Add scores and buttons to view
        Rectangle rectangleBackground = new Rectangle(350, 100);
        rectangleBackground.setFill(Color.WHITE);
        game.add(rectangleBackground, 4, 2, 2, 1);
        Text actualScore = new Text("Aktualna historia: \nLiczba wygranych: " + usersWinCount + " liczba przegranych: " + computersWinCount + ".\nStan na dzień: "
                + LocalDate.now() + ".");
        actualScore.setFont(Font.font("TimesNewRoman", 15));
        game.add(actualScore, 4, 2, 2, 1);


        Button buttonNext = new Button("Next ship");
        game.add(buttonNext, 2, 1);
        Button buttonStart = new Button("Start game");
        game.add(buttonStart, 2, 2);

        //Creating ships
        ships = new Ships();
        GridPane shipsBoard = ships.createShips();
        game.add(shipsBoard, 0, 0);
        if (!Files.exists(Paths.get("C:/Users/Kinga/Documents/Development/Projects/BattleShips/data.ser"))) {

            //InstallMovementToShips
            battleShipInstall(shipsBoard, userBoard, occupiedCells, shipsList);
            buttonNext.setOnMouseClicked(event -> {
                if (shipsList.size() == 5) {
                    Node node = shipsBoard.getChildren().get(0);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    aircraftInstall(shipsBoard, userBoard, 1, occupiedCells, shipsList);
                } else if (shipsList.size() == 9) {
                    Node node = shipsBoard.getChildren().get(1);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    aircraftInstall(shipsBoard, userBoard, 2, occupiedCells, shipsList);
                } else if (shipsList.size() == 13) {
                    Node node = shipsBoard.getChildren().get(2);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    submarineInstall(shipsBoard, userBoard, 3, occupiedCells, shipsList);
                } else if (shipsList.size() == 16) {
                    Node node = shipsBoard.getChildren().get(3);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    submarineInstall(shipsBoard, userBoard, 4, occupiedCells, shipsList);
                } else if (shipsList.size() == 19) {
                    Node node = shipsBoard.getChildren().get(4);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    smallShipInstall(shipsBoard, userBoard, 5, occupiedCells, shipsList);
                } else if (shipsList.size() == 21) {
                    Node node = shipsBoard.getChildren().get(5);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    smallShipInstall(shipsBoard, userBoard, 6, occupiedCells, shipsList);
                } else if (shipsList.size() == 23) {
                    for (Node node : shipsBoard.getChildren()) {
                        node.setOnMouseReleased(null);
                        node.setOnMouseClicked(null);
                        node.setOnMouseDragged(null);
                        node.setCursor(Cursor.DEFAULT);
                    }
                }

            });

            //Install computer ships
            board.fillComputerBoard(shipsBoard, computerShips);

        }


        // Moves
        buttonStart.setOnAction(event -> {
            if (!Files.exists(Paths.get("C:/Users/Kinga/Documents/Development/Projects/BattleShips/data.ser"))) {
                for (int i = 0; i < 400; i++) {
                    Rectangle rectangle = (Rectangle) userBoard.getChildren().get(i);
                    rectangle.setFill(Color.GRAY);
                }
            }else {
                for (Integer index : usersHitTrueList) {
                    Rectangle rectangle = (Rectangle) computerBoard.getChildren().get(index);
                    rectangle.setFill(Color.RED);

                }
                for (Integer index : usersHitFalseList) {
                    Rectangle rectangle = (Rectangle) computerBoard.getChildren().get(index);
                    rectangle.setFill(Color.AQUAMARINE);

                }
                for (Integer index : computerHitFalseList) {
                    Rectangle rectangle = (Rectangle) userBoard.getChildren().get(index);
                    rectangle.setFill(Color.AQUAMARINE);

                }
                for (Integer index : computerHitTrueList) {
                    Rectangle rectangle = (Rectangle) userBoard.getChildren().get(index);
                    rectangle.setFill(Color.RED);

                }
            }




                computerBoard.setOnMouseClicked(event1 -> {

                    Random random = new Random();

                    while (usersMove) {
                        double eventX = event1.getSceneX() - 1000;
                        double eventY = event1.getSceneY() - 60;
                        int col = (int) eventX / 25;
                        int row = (int) eventY / 25;
                        int index = row + (col * 20);

                        Rectangle rectangle = (Rectangle) computerBoard.getChildren().get(index);
                        if (rectangle.getFill() == Color.GRAY) {

                            if (computerShips.contains(index)) {
                                rectangle.setFill(Color.RED);
                                int indexToRemove = computerShips.indexOf(index);
                                computerShips.remove(indexToRemove);
                                usersHitTrueList.add(index);
                                if (computerShips.size() > 0) {
                                    return;
                                } else {
                                    status = "Gratulacje! Wygrałeś";
                                    computerBoard.setOnMouseClicked(null);
                                    usersWinCount++;
                                    try {
                                        BufferedWriter usersWriter = new BufferedWriter(new FileWriter("C:/Users/Kinga/Documents/Development/Projects/BattleShips/usersWinsCount.txt", true));
                                        usersWriter.append("Liczba wygranych " + usersWinCount);
                                        usersWriter.newLine();
                                        usersWriter.flush();
                                    } catch (IOException e) {
                                        System.out.println("wystąpił błąd: " + e);
                                    }
                                    display(status, primaryStage);
                                    break;
                                }

                            } else {
                                usersHitFalseList.add(index);
                                rectangle.setFill(Color.AQUAMARINE);
                                usersMove = false;
                            }

                        } else {
                            return;
                        }

                    }


                    while (!usersMove) {

                        if (shipsList.size() > 0) {

                            if (nodesToCheck.size() == 0) {
                                int usersBoardIndex = random.nextInt(400);
                                Rectangle rectangle2 = (Rectangle) userBoard.getChildren().get(usersBoardIndex);
                                if (rectangle2.getFill() == Color.GRAY) {
                                    if (shipsList.contains(usersBoardIndex)) {
                                        originalNode = usersBoardIndex;
                                        rectangle2.setFill(Color.RED);
                                        int indexToRemove2 = shipsList.indexOf(usersBoardIndex);
                                        shipsList.remove(indexToRemove2);
                                        computerHitTrueList.add(usersBoardIndex);
                                        usersMove = checkNeighbour(usersBoardIndex, userBoard, shipsList, nodesToCheck, computerHitTrueList, computerHitFalseList);
                                        return;
                                    } else {
                                        rectangle2.setFill(Color.AQUAMARINE);
                                        usersMove = true;
                                    }
                                } else {
                                    return;
                                }
                            } else if (nodesToCheck.size() == 3) {
                                usersMove = stageTwo(3, nodesToCheck, userBoard, shipsList, hitNode, originalNode, computerHitTrueList, computerHitFalseList);
                                return;
                            } else if (nodesToCheck.size() == 2) {
                                usersMove = stageTwo(2, nodesToCheck, userBoard, shipsList, hitNode, originalNode, computerHitTrueList, computerHitFalseList);
                                return;
                            } else if (nodesToCheck.size() == 1) {
                                usersMove = stageTwo(1, nodesToCheck, userBoard, shipsList, hitNode, originalNode, computerHitTrueList, computerHitFalseList);
                                return;
                            } else {
                                int hit = random.nextInt(2);
                                int usersBoardIndex2 = nodesToCheck.get(hit);
                                if (usersBoardIndex2 >= 0 && usersBoardIndex2 < 399) {
                                    Rectangle rectangle3 = (Rectangle) userBoard.getChildren().get(usersBoardIndex2);
                                    if (rectangle3.getFill() == Color.GRAY) {
                                        if (shipsList.contains(usersBoardIndex2)) {
                                            rectangle3.setFill(Color.RED);
                                            int indexToRemove2 = shipsList.indexOf(usersBoardIndex2);
                                            computerHitTrueList.add(usersBoardIndex2);
                                            shipsList.remove(indexToRemove2);
                                            nodesToCheck.remove(hit);
                                            return;
                                        } else {
                                            computerHitFalseList.add(usersBoardIndex2);
                                            rectangle3.setFill(Color.AQUAMARINE);
                                            usersMove = true;
                                        }
                                    } else {
                                        nodesToCheck.remove(hit);
                                        return;
                                    }
                                } else {
                                    nodesToCheck.remove(hit);
                                    return;
                                }
                            }


                        } else if (shipsList.size() == 0) {
                            status = "Przegrałeś";
                            computersWinCount++;
                            computerBoard.setOnMouseClicked(null);
                            try {
                                BufferedWriter computersWriter = new BufferedWriter(new FileWriter("C:/Users/Kinga/Documents/Development/Projects/BattleShips/computersWinsCount.txt", true));
                                computersWriter.append("Liczba przegranych " + computersWinCount);
                                computersWriter.newLine();
                                computersWriter.flush();
                            } catch (IOException e) {
                                System.out.println("wystąpił błąd: " + e);
                            }
                            display(status, primaryStage);
                            break;
                        }
                    }

                });


        });


        primaryStage.setOnCloseRequest(e -> {

            SaveGame saveGame = new SaveGame();
            Stage stageClose = new Stage();
            stageClose.initModality(Modality.APPLICATION_MODAL);
            stageClose.setMinWidth(400);
            stageClose.setMinHeight(150);

            Label label = new Label();
            label.setText("Czy chcesz zapisać stan gry?");
            Button buttonYes = new Button("Tak");
            Button buttonNo = new Button("Nie");

            buttonYes.setOnAction(event -> {
                saveGame.saveBoards(usersHitFalseList, computerHitFalseList, usersHitTrueList, computerHitTrueList, shipsList, computerShips);
                saveGame.saveTurn(usersMove);
                stageClose.close();
                primaryStage.close();
            });

            buttonNo.setOnAction(event -> {
                try {
                    Path path1 = Paths.get("C:/Users/Kinga/Documents/Development/Projects/BattleShips/moveStatus.txt");
                    Path path2 = Paths.get("C:/Users/Kinga/Documents/Development/Projects/BattleShips/data.ser");
                    Files.delete(path1);
                    Files.delete(path2);
                } catch (IOException e2) {
                    System.out.println("Error: " + e2);
                }
                stageClose.close();
                primaryStage.close();
            });

            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(label, buttonYes, buttonNo);
            Scene scene2 = new Scene(vbox);
            stageClose.setScene(scene2);
            stageClose.showAndWait();
        });


        primaryStage.setTitle("BattleShips");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}



