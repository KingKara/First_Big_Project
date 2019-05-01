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
    private String status;
    private int usersWinCount = 0;
    private int computersWinCount = 0;
    private ArrayList<Integer> usersHitFalseList = new ArrayList<>();
    private ArrayList<Integer> computerHitFalseList = new ArrayList<>();
    private ArrayList<Integer> usersHitTrueList = new ArrayList<>();
    private ArrayList<Integer> computerHitTrueList = new ArrayList<>();
    private ArrayList<Integer> usersBattleship = new ArrayList<>();
    private ArrayList<Integer> usersAircraft1 = new ArrayList<>();
    private ArrayList<Integer> usersAircraft2 = new ArrayList<>();
    private ArrayList<Integer> usersSubmarine1 = new ArrayList<>();
    private ArrayList<Integer> usersSubmarine2 = new ArrayList<>();
    private ArrayList<Integer> usersSmallShip1 = new ArrayList<>();
    private ArrayList<Integer> usersSmallShip2 = new ArrayList<>();
    private ArrayList<Integer> computersBattleship = new ArrayList<>();
    private ArrayList<Integer> computersAircraft1 = new ArrayList<>();
    private ArrayList<Integer> computersAircraft2 = new ArrayList<>();
    private ArrayList<Integer> computersSubmarine1 = new ArrayList<>();
    private ArrayList<Integer> computersSubmarine2 = new ArrayList<>();
    private ArrayList<Integer> computersSmallShip1 = new ArrayList<>();
    private ArrayList<Integer> computersSmallShip2 = new ArrayList<>();
    private HashMap<String, ArrayList<Integer>> boardLoad = new HashMap<>();
    private ArrayList<Integer> listLoad = new ArrayList<>();
    private int checkFirstShip;
    private int checkSecondShip;
    private int checkThirdShip;
    private int checkFourthShip;
    private int checkFifthShip;
    private int checkSixthShip;
    private int checkSeventhShip;
    private int checkFirstUsersShip;
    private int checkSecondUsersShip;
    private int checkThirdUsersShip;
    private int checkFourthUsersShip;
    private int checkFifthUsersShip;
    private int checkSixthUsersShip;
    private int checkSeventhUsersShip;

    @Override
    public void start(Stage primaryStage) {


//      Reading scores

        try {

            List<String> lines = Files.readAllLines(Paths.get("usersWinsCount.txt"));
            int lastline = lines.size() - 1;
            String lastLineText = lines.get(lastline);
            String cuted = lastLineText.substring(17);
            usersWinCount = Integer.parseInt(cuted);

            List<String> lines2 = Files.readAllLines(Paths.get("computersWinsCount.txt"));
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
                } else if (entry.getKey().equals("usersBattleship")) {
                    usersBattleship.addAll(entry.getValue());
                } else if (entry.getKey().equals("usersAircraft1")) {
                    usersAircraft1.addAll(entry.getValue());
                } else if (entry.getKey().equals("usersAircraft2")) {
                    usersAircraft2.addAll(entry.getValue());
                } else if (entry.getKey().equals("usersSubmarine1")) {
                    usersSubmarine1.addAll(entry.getValue());
                } else if (entry.getKey().equals("usersSubmarine2")) {
                    usersSubmarine2.addAll(entry.getValue());
                } else if (entry.getKey().equals("usersSmallShip1")) {
                    usersSmallShip1.addAll(entry.getValue());
                } else if (entry.getKey().equals("usersSmallShip2")) {
                    usersSmallShip2.addAll(entry.getValue());
                } else if (entry.getKey().equals("computersBattleship")) {
                    computersBattleship.addAll(entry.getValue());
                } else if (entry.getKey().equals("computerAircraft1")) {
                    computersAircraft1.addAll(entry.getValue());
                } else if (entry.getKey().equals("computerAircraft2")) {
                    computersAircraft2.addAll(entry.getValue());
                } else if (entry.getKey().equals("computerSubmarine1")) {
                    computersSubmarine1.addAll(entry.getValue());
                } else if (entry.getKey().equals("computerSubmarine2")) {
                    computersSubmarine2.addAll(entry.getValue());
                } else if (entry.getKey().equals("computerSmallShip1")) {
                    computersSmallShip1.addAll(entry.getValue());
                } else if (entry.getKey().equals("computerSmallShip2")) {
                    computersSmallShip2.addAll(entry.getValue());
                }
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
        }


        try {
            List<String> lines = Files.readAllLines(Paths.get("turn.txt"));
            int lastLine = lines.size() - 1;
            String moveStatus = lines.get(lastLine);
            usersMove = Boolean.getBoolean(moveStatus);

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sizesToColor.ser"));
            Object readList = ois.readObject();
            ois.close();
            if (readList instanceof ArrayList) {
                listLoad.addAll((ArrayList) readList);
            }
            if (listLoad.size() == 14) {
                checkFirstShip = listLoad.get(0);
                checkSecondShip = listLoad.get(1);
                checkThirdShip = listLoad.get(2);
                checkFourthShip = listLoad.get(3);
                checkFifthShip = listLoad.get(4);
                checkSixthShip = listLoad.get(5);
                checkSeventhShip = listLoad.get(6);
                checkFirstUsersShip = listLoad.get(7);
                checkSecondUsersShip = listLoad.get(8);
                checkThirdUsersShip = listLoad.get(9);
                checkFourthUsersShip = listLoad.get(10);
                checkFifthUsersShip = listLoad.get(11);
                checkSixthUsersShip = listLoad.get(12);
                checkSeventhUsersShip = listLoad.get(13);
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
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
        GridPane.setConstraints(computerBoard, 4, 0, 10, 1);
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


        if (!Files.exists(Paths.get("data.ser"))) {
            //InstallMovementToShips
            battleShipInstall(shipsBoard, userBoard, occupiedCells, shipsList, usersBattleship);
            buttonNext.setOnMouseClicked(event -> {
                if (shipsList.size() == 5) {
                    Node node = shipsBoard.getChildren().get(0);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    aircraftInstall(shipsBoard, userBoard, 1, occupiedCells, shipsList, usersAircraft1);
                } else if (shipsList.size() == 9) {
                    Node node = shipsBoard.getChildren().get(1);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    aircraftInstall(shipsBoard, userBoard, 2, occupiedCells, shipsList, usersAircraft2);
                } else if (shipsList.size() == 13) {
                    Node node = shipsBoard.getChildren().get(2);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    submarineInstall(shipsBoard, userBoard, 3, occupiedCells, shipsList, usersSubmarine1);
                } else if (shipsList.size() == 16) {
                    Node node = shipsBoard.getChildren().get(3);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    submarineInstall(shipsBoard, userBoard, 4, occupiedCells, shipsList, usersSubmarine2);
                } else if (shipsList.size() == 19) {
                    Node node = shipsBoard.getChildren().get(4);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    smallShipInstall(shipsBoard, userBoard, 5, occupiedCells, shipsList, usersSmallShip1);
                } else if (shipsList.size() == 21) {
                    Node node = shipsBoard.getChildren().get(5);
                    node.setOnMouseDragged(null);
                    node.setOnMouseClicked(null);
                    node.setOnMouseReleased(null);
                    smallShipInstall(shipsBoard, userBoard, 6, occupiedCells, shipsList, usersSmallShip2);
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
            board.fillComputerBoard(shipsBoard, computerShips, computersBattleship, computersAircraft1,
                    computersAircraft2, computersSubmarine1, computersSubmarine2, computersSmallShip1, computersSmallShip2);

            checkFirstShip = computersBattleship.size() - 5;
            checkSecondShip = computersAircraft1.size() - 4;
            checkThirdShip = computersAircraft2.size() - 4;
            checkFourthShip = computersSubmarine1.size() - 3;
            checkFifthShip = computersSubmarine2.size() - 3;
            checkSixthShip = computersSmallShip1.size() - 2;
            checkSeventhShip = computersSmallShip2.size() - 2;


            // Moves
            buttonStart.setOnAction(event -> {
                if (shipsList.size() == 23) {
                    for (int i = 0; i < 400; i++) {
                        Rectangle rectangle = (Rectangle) userBoard.getChildren().get(i);
                        rectangle.setFill(Color.GRAY);
                    }
                    checkFirstUsersShip = usersBattleship.size() - 5;
                    checkSecondUsersShip = usersAircraft1.size() - 4;
                    checkThirdUsersShip = usersAircraft2.size() - 4;
                    checkFourthUsersShip = usersSubmarine1.size() - 3;
                    checkFifthUsersShip = usersSubmarine2.size() - 3;
                    checkSixthUsersShip = usersSmallShip1.size() - 2;
                    checkSeventhUsersShip = usersSmallShip2.size() - 2;

                    computerBoard.setOnMouseClicked(event1 -> {

                        Random random = new Random();

                        while (usersMove) {
                            double eventX = event1.getX() - 10;
                            double eventY = event1.getY() - 10;
                            int col = (int) eventX / 25;
                            int row = (int) eventY / 25;
                            int index = row + (col * 20);
                            if (index >= 0 && index < 400) {
                                Rectangle rectangle = (Rectangle) computerBoard.getChildren().get(index);
                                if (rectangle.getFill() == Color.GRAY) {
                                    if (computerShips.contains(index)) {
                                        rectangle.setFill(Color.RED);
                                        int indexToRemove = computerShips.indexOf(index);
                                        usersHitTrueList.add(index);
                                        computerShips.remove(indexToRemove);
                                        if (computersBattleship.contains(index)) {
                                            indexToRemove = computersBattleship.indexOf(index);
                                            computersBattleship.remove(indexToRemove);
                                        } else if (computersAircraft1.contains(index)) {
                                            indexToRemove = computersAircraft1.indexOf(index);
                                            computersAircraft1.remove(indexToRemove);
                                        } else if (computersAircraft2.contains(index)) {
                                            indexToRemove = computersAircraft2.indexOf(index);
                                            computersAircraft2.remove(indexToRemove);
                                        } else if (computersSubmarine1.contains(index)) {
                                            indexToRemove = computersSubmarine1.indexOf(index);
                                            computersSubmarine1.remove(indexToRemove);
                                        } else if (computersSubmarine2.contains(index)) {
                                            indexToRemove = computersSubmarine2.indexOf(index);
                                            computersSubmarine2.remove(indexToRemove);
                                        } else if (computersSmallShip1.contains(index)) {
                                            indexToRemove = computersSmallShip1.indexOf(index);
                                            computersSmallShip1.remove(indexToRemove);
                                        } else if (computersSmallShip2.contains(index)) {
                                            indexToRemove = computersSmallShip2.indexOf(index);
                                            computersSmallShip2.remove(indexToRemove);
                                        }

                                        if (computersBattleship.size() == checkFirstShip) {
                                            for (Integer cell : computersBattleship) {
                                                if (cell >= 0 && cell < 400) {
                                                    Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                    rectangle1.setFill(Color.AQUAMARINE);
                                                }
                                            }
                                            usersHitFalseList.addAll(computersBattleship);
                                        }
                                        if (computersAircraft1.size() == checkSecondShip) {
                                            for (Integer cell : computersAircraft1) {
                                                if (cell >= 0 && cell < 400) {
                                                    Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                    rectangle1.setFill(Color.AQUAMARINE);
                                                }
                                            }
                                            usersHitFalseList.addAll(computersAircraft1);
                                        }

                                        if (computersAircraft2.size() == checkThirdShip) {
                                            for (Integer cell : computersAircraft2) {
                                                if (cell >= 0 && cell < 400) {
                                                    Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                    rectangle1.setFill(Color.AQUAMARINE);
                                                }
                                            }
                                            usersHitFalseList.addAll(computersAircraft2);
                                        }
                                        if (computersSubmarine1.size() == checkFourthShip) {
                                            for (Integer cell : computersSubmarine1) {
                                                if (cell >= 0 && cell < 400) {
                                                    Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                    rectangle1.setFill(Color.AQUAMARINE);
                                                }
                                            }
                                            usersHitFalseList.addAll(computersSubmarine1);
                                        }
                                        if (computersSubmarine2.size() == checkFifthShip) {
                                            for (Integer cell : computersSubmarine2) {
                                                if (cell >= 0 && cell < 400) {
                                                    Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                    rectangle1.setFill(Color.AQUAMARINE);
                                                }
                                            }
                                            usersHitFalseList.addAll(computersSubmarine2);
                                        }
                                        if (computersSmallShip1.size() == checkSixthShip) {
                                            for (Integer cell : computersSmallShip1) {
                                                if (cell >= 0 && cell < 400) {
                                                    Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                    rectangle1.setFill(Color.AQUAMARINE);
                                                }
                                            }
                                            usersHitFalseList.addAll(computersSmallShip1);
                                        }
                                        if (computersSmallShip2.size() == checkSeventhShip) {
                                            for (Integer cell : computersSmallShip2) {
                                                if (cell >= 0 && cell < 400) {
                                                    Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                    rectangle1.setFill(Color.AQUAMARINE);
                                                }
                                            }
                                            usersHitFalseList.addAll(computersSmallShip2);
                                        }


                                        if (computerShips.size() > 0) {
                                            return;
                                        } else {
                                            status = "Gratulacje! Wygrałeś";
                                            computerBoard.setOnMouseClicked(null);
                                            usersWinCount++;
                                            try {
                                                BufferedWriter usersWriter = new BufferedWriter(new FileWriter("usersWinsCount.txt", true));
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
                                            if (usersBattleship.contains(usersBoardIndex)) {
                                                indexToRemove2 = usersBattleship.indexOf(usersBoardIndex);
                                                usersBattleship.remove(indexToRemove2);
                                            } else if (usersAircraft1.contains(usersBoardIndex)) {
                                                indexToRemove2 = usersAircraft1.indexOf(usersBoardIndex);
                                                usersAircraft1.remove(indexToRemove2);
                                            } else if (usersAircraft2.contains(usersBoardIndex)) {
                                                indexToRemove2 = usersAircraft2.indexOf(usersBoardIndex);
                                                usersAircraft2.remove(indexToRemove2);
                                            } else if (usersSubmarine1.contains(usersBoardIndex)) {
                                                indexToRemove2 = usersSubmarine1.indexOf(usersBoardIndex);
                                                usersSubmarine1.remove(indexToRemove2);
                                            } else if (usersSubmarine2.contains(usersBoardIndex)) {
                                                indexToRemove2 = usersSubmarine2.indexOf(usersBoardIndex);
                                                usersSubmarine2.remove(indexToRemove2);
                                            } else if (usersSmallShip1.contains(usersBoardIndex)) {
                                                indexToRemove2 = usersSmallShip1.indexOf(usersBoardIndex);
                                                usersSmallShip1.remove(indexToRemove2);
                                            } else if (usersSmallShip2.contains(usersBoardIndex)) {
                                                indexToRemove2 = usersSmallShip2.indexOf(usersBoardIndex);
                                                usersSmallShip2.remove(indexToRemove2);
                                            }
                                            usersMove = checkNeighbour(usersBoardIndex, userBoard, shipsList, nodesToCheck, computerHitTrueList, computerHitFalseList, usersBattleship,
                                                    usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                                    checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
                                            return;

                                        } else {
                                            computerHitFalseList.add(usersBoardIndex);
                                            rectangle2.setFill(Color.AQUAMARINE);
                                            usersMove = true;
                                        }
                                    } else {
                                        return;
                                    }
                                } else if (nodesToCheck.size() == 3) {
                                    usersMove = stageTwo(3, nodesToCheck, userBoard, shipsList, originalNode, computerHitTrueList, computerHitFalseList, usersBattleship,
                                            usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                            checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
                                    return;
                                } else if (nodesToCheck.size() == 2) {
                                    usersMove = stageTwo(2, nodesToCheck, userBoard, shipsList,  originalNode, computerHitTrueList, computerHitFalseList, usersBattleship,
                                            usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                            checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
                                    return;
                                } else if (nodesToCheck.size() == 1) {
                                    usersMove = stageTwo(1, nodesToCheck, userBoard, shipsList, originalNode, computerHitTrueList, computerHitFalseList, usersBattleship,
                                            usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                            checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
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


                                                if (usersBattleship.contains(usersBoardIndex2)) {
                                                    indexToRemove2 = usersBattleship.indexOf(usersBoardIndex2);
                                                    usersBattleship.remove(indexToRemove2);
                                                } else if (usersAircraft1.contains(usersBoardIndex2)) {
                                                    indexToRemove2 = usersAircraft1.indexOf(usersBoardIndex2);
                                                    usersAircraft1.remove(indexToRemove2);
                                                } else if (usersAircraft2.contains(usersBoardIndex2)) {
                                                    indexToRemove2 = usersAircraft2.indexOf(usersBoardIndex2);
                                                    usersAircraft2.remove(indexToRemove2);
                                                } else if (usersSubmarine1.contains(usersBoardIndex2)) {
                                                    indexToRemove2 = usersSubmarine1.indexOf(usersBoardIndex2);
                                                    usersSubmarine1.remove(indexToRemove2);
                                                } else if (usersSubmarine2.contains(usersBoardIndex2)) {
                                                    indexToRemove2 = usersSubmarine2.indexOf(usersBoardIndex2);
                                                    usersSubmarine2.remove(indexToRemove2);
                                                } else if (usersSmallShip1.contains(usersBoardIndex2)) {
                                                    indexToRemove2 = usersSmallShip1.indexOf(usersBoardIndex2);
                                                    usersSmallShip1.remove(indexToRemove2);
                                                } else if (usersSmallShip2.contains(usersBoardIndex2)) {
                                                    indexToRemove2 = usersSmallShip2.indexOf(usersBoardIndex2);
                                                    usersSmallShip2.remove(indexToRemove2);
                                                }

                                                if (usersBattleship.size() == checkFirstUsersShip) {
                                                    for (Integer cell : usersBattleship) {
                                                        if (cell >= 0 && cell < 400) {
                                                            Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                            rectangle1.setFill(Color.AQUAMARINE);
                                                        }
                                                    }
                                                    nodesToCheck.clear();
                                                    computerHitFalseList.addAll(usersBattleship);
                                                }
                                                if (usersAircraft1.size() == checkSecondUsersShip) {
                                                    for (Integer cell : usersAircraft1) {
                                                        if (cell >= 0 && cell < 400) {
                                                            Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                            rectangle1.setFill(Color.AQUAMARINE);
                                                        }
                                                    }
                                                    nodesToCheck.clear();
                                                    computerHitFalseList.addAll(usersAircraft1);
                                                }

                                                if (usersAircraft2.size() == checkThirdUsersShip) {
                                                    for (Integer cell : usersAircraft2) {
                                                        if (cell >= 0 && cell < 400) {
                                                            Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                            rectangle1.setFill(Color.AQUAMARINE);
                                                        }
                                                    }
                                                    nodesToCheck.clear();
                                                    computerHitFalseList.addAll(usersAircraft2);
                                                }
                                                if (usersSubmarine1.size() == checkFourthUsersShip) {
                                                    for (Integer cell : usersSubmarine1) {
                                                        if (cell >= 0 && cell < 400) {
                                                            Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                            rectangle1.setFill(Color.AQUAMARINE);
                                                        }
                                                    }
                                                    nodesToCheck.clear();
                                                    computerHitFalseList.addAll(usersSubmarine1);
                                                }
                                                if (usersSubmarine2.size() == checkFifthUsersShip) {
                                                    for (Integer cell : usersSubmarine2) {
                                                        if (cell >= 0 && cell < 400) {
                                                            Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                            rectangle1.setFill(Color.AQUAMARINE);
                                                        }
                                                    }
                                                    nodesToCheck.clear();
                                                    computerHitFalseList.addAll(usersSubmarine2);
                                                }
                                                if (usersSmallShip1.size() == checkSixthUsersShip) {
                                                    for (Integer cell : usersSmallShip1) {
                                                        if (cell >= 0 && cell < 400) {
                                                            Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                            rectangle1.setFill(Color.AQUAMARINE);
                                                        }
                                                    }
                                                    nodesToCheck.clear();
                                                    computerHitFalseList.addAll(usersSmallShip1);
                                                }
                                                if (usersSmallShip2.size() == checkSeventhUsersShip) {
                                                    for (Integer cell : usersSmallShip2) {
                                                        if (cell >= 0 && cell < 400) {
                                                            Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                            rectangle1.setFill(Color.AQUAMARINE);
                                                        }
                                                    }
                                                    nodesToCheck.clear();
                                                    computerHitFalseList.addAll(usersSmallShip2);
                                                }

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
                                    BufferedWriter computersWriter = new BufferedWriter(new FileWriter("computersWinsCount.txt", true));
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
                }
            });


        } else {
            buttonStart.setOnAction(event -> {
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

                computerBoard.setOnMouseClicked(event1 -> {

                    Random random = new Random();

                    while (usersMove) {
                        double eventX = event1.getX() - 10;
                        double eventY = event1.getY() - 10;
                        int col = (int) eventX / 25;
                        int row = (int) eventY / 25;
                        int index = row + (col * 20);
                        if (index >= 0 && index < 400) {
                            Rectangle rectangle = (Rectangle) computerBoard.getChildren().get(index);
                            if (rectangle.getFill() == Color.GRAY) {
                                if (computerShips.contains(index)) {
                                    rectangle.setFill(Color.RED);
                                    int indexToRemove = computerShips.indexOf(index);
                                    usersHitTrueList.add(index);
                                    computerShips.remove(indexToRemove);
                                    if (computersBattleship.contains(index)) {
                                        indexToRemove = computersBattleship.indexOf(index);
                                        computersBattleship.remove(indexToRemove);
                                    } else if (computersAircraft1.contains(index)) {
                                        indexToRemove = computersAircraft1.indexOf(index);
                                        computersAircraft1.remove(indexToRemove);
                                    } else if (computersAircraft2.contains(index)) {
                                        indexToRemove = computersAircraft2.indexOf(index);
                                        computersAircraft2.remove(indexToRemove);
                                    } else if (computersSubmarine1.contains(index)) {
                                        indexToRemove = computersSubmarine1.indexOf(index);
                                        computersSubmarine1.remove(indexToRemove);
                                    } else if (computersSubmarine2.contains(index)) {
                                        indexToRemove = computersSubmarine2.indexOf(index);
                                        computersSubmarine2.remove(indexToRemove);
                                    } else if (computersSmallShip1.contains(index)) {
                                        indexToRemove = computersSmallShip1.indexOf(index);
                                        computersSmallShip1.remove(indexToRemove);
                                    } else if (computersSmallShip2.contains(index)) {
                                        indexToRemove = computersSmallShip2.indexOf(index);
                                        computersSmallShip2.remove(indexToRemove);
                                    }

                                    if (computersBattleship.size() == checkFirstShip) {
                                        for (Integer cell : computersBattleship) {
                                            if (cell >= 0 && cell < 400) {
                                                Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                rectangle1.setFill(Color.AQUAMARINE);
                                            }
                                        }
                                        usersHitFalseList.addAll(computersBattleship);
                                    }
                                    if (computersAircraft1.size() == checkSecondShip) {
                                        for (Integer cell : computersAircraft1) {
                                            if (cell >= 0 && cell < 400) {
                                                Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                rectangle1.setFill(Color.AQUAMARINE);
                                            }
                                        }
                                        usersHitFalseList.addAll(computersAircraft1);
                                    }

                                    if (computersAircraft2.size() == checkThirdShip) {
                                        for (Integer cell : computersAircraft2) {
                                            if (cell >= 0 && cell < 400) {
                                                Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                rectangle1.setFill(Color.AQUAMARINE);
                                            }
                                        }
                                        usersHitFalseList.addAll(computersAircraft2);
                                    }
                                    if (computersSubmarine1.size() == checkFourthShip) {
                                        for (Integer cell : computersSubmarine1) {
                                            if (cell >= 0 && cell < 400) {
                                                Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                rectangle1.setFill(Color.AQUAMARINE);
                                            }
                                        }
                                        usersHitFalseList.addAll(computersSubmarine1);
                                    }
                                    if (computersSubmarine2.size() == checkFifthShip) {
                                        for (Integer cell : computersSubmarine2) {
                                            if (cell >= 0 && cell < 400) {
                                                Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                rectangle1.setFill(Color.AQUAMARINE);
                                            }
                                        }
                                        usersHitFalseList.addAll(computersSubmarine2);
                                    }
                                    if (computersSmallShip1.size() == checkSixthShip) {
                                        for (Integer cell : computersSmallShip1) {
                                            if (cell >= 0 && cell < 400) {
                                                Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                rectangle1.setFill(Color.AQUAMARINE);
                                            }
                                        }
                                        usersHitFalseList.addAll(computersSmallShip1);
                                    }
                                    if (computersSmallShip2.size() == checkSeventhShip) {
                                        for (Integer cell : computersSmallShip2) {
                                            if (cell >= 0 && cell < 400) {
                                                Rectangle rectangle1 = (Rectangle) computerBoard.getChildren().get(cell);
                                                rectangle1.setFill(Color.AQUAMARINE);
                                            }
                                        }
                                        usersHitFalseList.addAll(computersSmallShip2);
                                    }


                                    if (computerShips.size() > 0) {
                                        return;
                                    } else {
                                        status = "Gratulacje! Wygrałeś";
                                        computerBoard.setOnMouseClicked(null);
                                        usersWinCount++;
                                        try {
                                            BufferedWriter usersWriter = new BufferedWriter(new FileWriter("usersWinsCount.txt", true));
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
                                        if (usersBattleship.contains(usersBoardIndex)) {
                                            indexToRemove2 = usersBattleship.indexOf(usersBoardIndex);
                                            usersBattleship.remove(indexToRemove2);
                                        } else if (usersAircraft1.contains(usersBoardIndex)) {
                                            indexToRemove2 = usersAircraft1.indexOf(usersBoardIndex);
                                            usersAircraft1.remove(indexToRemove2);
                                        } else if (usersAircraft2.contains(usersBoardIndex)) {
                                            indexToRemove2 = usersAircraft2.indexOf(usersBoardIndex);
                                            usersAircraft2.remove(indexToRemove2);
                                        } else if (usersSubmarine1.contains(usersBoardIndex)) {
                                            indexToRemove2 = usersSubmarine1.indexOf(usersBoardIndex);
                                            usersSubmarine1.remove(indexToRemove2);
                                        } else if (usersSubmarine2.contains(usersBoardIndex)) {
                                            indexToRemove2 = usersSubmarine2.indexOf(usersBoardIndex);
                                            usersSubmarine2.remove(indexToRemove2);
                                        } else if (usersSmallShip1.contains(usersBoardIndex)) {
                                            indexToRemove2 = usersSmallShip1.indexOf(usersBoardIndex);
                                            usersSmallShip1.remove(indexToRemove2);
                                        } else if (usersSmallShip2.contains(usersBoardIndex)) {
                                            indexToRemove2 = usersSmallShip2.indexOf(usersBoardIndex);
                                            usersSmallShip2.remove(indexToRemove2);
                                        }
                                        usersMove = checkNeighbour(usersBoardIndex, userBoard, shipsList, nodesToCheck, computerHitTrueList, computerHitFalseList, usersBattleship,
                                                usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                                checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
                                        return;

                                    } else {
                                        computerHitFalseList.add(usersBoardIndex);
                                        rectangle2.setFill(Color.AQUAMARINE);
                                        usersMove = true;
                                    }
                                } else {
                                    return;
                                }
                            } else if (nodesToCheck.size() == 3) {
                                usersMove = stageTwo(3, nodesToCheck, userBoard, shipsList, originalNode, computerHitTrueList, computerHitFalseList, usersBattleship,
                                        usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                        checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
                                return;
                            } else if (nodesToCheck.size() == 2) {
                                usersMove = stageTwo(2, nodesToCheck, userBoard, shipsList,  originalNode, computerHitTrueList, computerHitFalseList, usersBattleship,
                                        usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                        checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
                                return;
                            } else if (nodesToCheck.size() == 1) {
                                usersMove = stageTwo(1, nodesToCheck, userBoard, shipsList, originalNode, computerHitTrueList, computerHitFalseList, usersBattleship,
                                        usersAircraft1, usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, checkFirstUsersShip, checkSecondUsersShip,
                                        checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip, checkSeventhUsersShip);
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


                                            if (usersBattleship.contains(usersBoardIndex2)) {
                                                indexToRemove2 = usersBattleship.indexOf(usersBoardIndex2);
                                                usersBattleship.remove(indexToRemove2);
                                            } else if (usersAircraft1.contains(usersBoardIndex2)) {
                                                indexToRemove2 = usersAircraft1.indexOf(usersBoardIndex2);
                                                usersAircraft1.remove(indexToRemove2);
                                            } else if (usersAircraft2.contains(usersBoardIndex2)) {
                                                indexToRemove2 = usersAircraft2.indexOf(usersBoardIndex2);
                                                usersAircraft2.remove(indexToRemove2);
                                            } else if (usersSubmarine1.contains(usersBoardIndex2)) {
                                                indexToRemove2 = usersSubmarine1.indexOf(usersBoardIndex2);
                                                usersSubmarine1.remove(indexToRemove2);
                                            } else if (usersSubmarine2.contains(usersBoardIndex2)) {
                                                indexToRemove2 = usersSubmarine2.indexOf(usersBoardIndex2);
                                                usersSubmarine2.remove(indexToRemove2);
                                            } else if (usersSmallShip1.contains(usersBoardIndex2)) {
                                                indexToRemove2 = usersSmallShip1.indexOf(usersBoardIndex2);
                                                usersSmallShip1.remove(indexToRemove2);
                                            } else if (usersSmallShip2.contains(usersBoardIndex2)) {
                                                indexToRemove2 = usersSmallShip2.indexOf(usersBoardIndex2);
                                                usersSmallShip2.remove(indexToRemove2);
                                            }

                                            if (usersBattleship.size() == checkFirstUsersShip) {
                                                for (Integer cell : usersBattleship) {
                                                    if (cell >= 0 && cell < 400) {
                                                        Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                        rectangle1.setFill(Color.AQUAMARINE);
                                                    }
                                                }
                                                nodesToCheck.clear();
                                                computerHitFalseList.addAll(usersBattleship);
                                            }
                                            if (usersAircraft1.size() == checkSecondUsersShip) {
                                                for (Integer cell : usersAircraft1) {
                                                    if (cell >= 0 && cell < 400) {
                                                        Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                        rectangle1.setFill(Color.AQUAMARINE);
                                                    }
                                                }
                                                nodesToCheck.clear();
                                                computerHitFalseList.addAll(usersAircraft1);
                                            }

                                            if (usersAircraft2.size() == checkThirdUsersShip) {
                                                for (Integer cell : usersAircraft2) {
                                                    if (cell >= 0 && cell < 400) {
                                                        Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                        rectangle1.setFill(Color.AQUAMARINE);
                                                    }
                                                }
                                                nodesToCheck.clear();
                                                computerHitFalseList.addAll(usersAircraft2);
                                            }
                                            if (usersSubmarine1.size() == checkFourthUsersShip) {
                                                for (Integer cell : usersSubmarine1) {
                                                    if (cell >= 0 && cell < 400) {
                                                        Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                        rectangle1.setFill(Color.AQUAMARINE);
                                                    }
                                                }
                                                nodesToCheck.clear();
                                                computerHitFalseList.addAll(usersSubmarine1);
                                            }
                                            if (usersSubmarine2.size() == checkFifthUsersShip) {
                                                for (Integer cell : usersSubmarine2) {
                                                    if (cell >= 0 && cell < 400) {
                                                        Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                        rectangle1.setFill(Color.AQUAMARINE);
                                                    }
                                                }
                                                nodesToCheck.clear();
                                                computerHitFalseList.addAll(usersSubmarine2);
                                            }
                                            if (usersSmallShip1.size() == checkSixthUsersShip) {
                                                for (Integer cell : usersSmallShip1) {
                                                    if (cell >= 0 && cell < 400) {
                                                        Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                        rectangle1.setFill(Color.AQUAMARINE);
                                                    }
                                                }
                                                nodesToCheck.clear();
                                                computerHitFalseList.addAll(usersSmallShip1);
                                            }
                                            if (usersSmallShip2.size() == checkSeventhUsersShip) {
                                                for (Integer cell : usersSmallShip2) {
                                                    if (cell >= 0 && cell < 400) {
                                                        Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                                        rectangle1.setFill(Color.AQUAMARINE);
                                                    }
                                                }
                                                nodesToCheck.clear();
                                                computerHitFalseList.addAll(usersSmallShip2);
                                            }

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
                                BufferedWriter computersWriter = new BufferedWriter(new FileWriter("computersWinsCount.txt", true));
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
        }



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
                saveGame.saveBoards(usersHitFalseList, computerHitFalseList, usersHitTrueList, computerHitTrueList, shipsList, computerShips, usersBattleship, usersAircraft1,
                        usersAircraft2, usersSubmarine1, usersSubmarine2, usersSmallShip1, usersSmallShip2, computersBattleship, computersAircraft1, computersAircraft2,
                        computersSubmarine1, computersSubmarine2, computersSmallShip1, computersSmallShip2);
                saveGame.saveTurn(usersMove);
                saveGame.saveSizez(checkFirstShip, checkSecondShip, checkThirdShip, checkFourthShip, checkFifthShip, checkSixthShip, checkSeventhShip,
                        checkFirstUsersShip, checkSecondUsersShip, checkThirdUsersShip, checkFourthUsersShip, checkFifthUsersShip, checkSixthUsersShip,
                        checkSeventhUsersShip);
                stageClose.close();
                primaryStage.close();
            });

            buttonNo.setOnAction(event -> {
                try {
                    Path path1 = Paths.get("turn.txt");
                    Path path2 = Paths.get("data.ser");
                    Path path3 = Paths.get("sizesToColor.ser");
                    Files.delete(path1);
                    Files.delete(path2);
                    Files.delete(path3);
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



