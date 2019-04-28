package com.kodilla;


import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;
import java.util.Random;


public class Board {


    public Board() {

    }

    public GridPane createUserBoard() {
        GridPane board = new GridPane();
        BoardCell boardCell = new BoardCell(25, 25);
        for (int x = 0; x < 500; x += 25) {
            for (int y = 0; y < 500; y += 25) {
                board.add(boardCell.createCell(), x, y);
            }
        }
        return board;
    }

    public void fillComputerBoard(GridPane shipsBoard, ArrayList<Integer> computerShips) {
        Random random = new Random();
        ArrayList<Integer> rotation = new ArrayList<>();
        ArrayList<Integer> computerOccupiedList = new ArrayList<>();
        rotation.add(0);
        rotation.add(90);
        for (Node node : shipsBoard.getChildren()) {
            Integer rotationRand = random.nextInt(2);
            Integer rotate = rotation.get(rotationRand);
            Bounds bounds = node.getBoundsInLocal();
            Integer index;


            if (bounds.getWidth() == 125) {
                if (rotate == 0) {
                    index = random.nextInt(320);
                    Integer index2 = index + 20;
                    Integer index3 = index + 40;
                    Integer index4 = index + 60;
                    Integer index5 = index + 80;

                    int row = getRow(index);
                    int col = getCol(index);

                    while (col>15) {
                        index = random.nextInt(320);
                         index2 = index + 20;
                         index3 = index + 40;
                         index4 = index + 60;
                         index5 = index + 80;

                         row = getRow(index);
                        col = getCol(index);
                    }


                    computerShips.add(index);
                    computerShips.add(index2);
                    computerShips.add(index3);
                    computerShips.add(index4);
                    computerShips.add(index5);

                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);
                    computerOccupiedList.add(index3);
                    computerOccupiedList.add(index4);
                    computerOccupiedList.add(index5);

                    if (row == 0) {

                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 41);
                        computerOccupiedList.add(index + 61);
                        computerOccupiedList.add(index + 81);

                        if (col == 0) {
                            computerOccupiedList.add(10, index + 100);
                            computerOccupiedList.add(11, index + 101);
                        } else if (col == 15) {
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 19);
                        } else if (col > 0 && col < 15) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index + 100);
                            computerOccupiedList.add(index + 101);
                        }

                    } else if (row == 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 19);
                        computerOccupiedList.add(index + 39);
                        computerOccupiedList.add(index + 59);
                        computerOccupiedList.add(index + 79);
                        if (col == 0) {

                            computerOccupiedList.add(index + 100);
                            computerOccupiedList.add(index + 99);
                        } else if (col == 15) {
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        } else if (col > 0 && col < 15) {
                            computerOccupiedList.add(index + 100);
                            computerOccupiedList.add(index + 99);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        }

                    } else if (row > 0 && row < 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 19);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 39);
                        computerOccupiedList.add(index + 41);
                        computerOccupiedList.add(index + 59);
                        computerOccupiedList.add(index + 61);
                        computerOccupiedList.add(index + 79);
                        computerOccupiedList.add(index + 81);

                        if (col == 0) {
                            computerOccupiedList.add(index + 99);
                            computerOccupiedList.add(index + 100);
                            computerOccupiedList.add(index + 101);
                        } else if (col == 15) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        } else if (col > 0 && col < 15) {
                            computerOccupiedList.add(index + 99);
                            computerOccupiedList.add(index + 100);
                            computerOccupiedList.add(index + 101);
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        }
                    }

                } else if (rotate == 90) {
                    index = random.nextInt(396);
                    Integer index2 = index + 1;
                    Integer index3 = index + 2;
                    Integer index4 = index + 3;
                    Integer index5 = index + 4;

                    int row = getRow(index);
                    int col = getCol(index);

                    while (row>15) {
                        index = random.nextInt(320);
                        index2 = index + 20;
                        index3 = index + 40;
                        index4 = index + 60;
                        index5 = index + 80;

                        row = getRow(index);
                        col = getCol(index);
                    }

                    computerShips.add(index);
                    computerShips.add(index2);
                    computerShips.add(index3);
                    computerShips.add(index4);
                    computerShips.add(index5);

                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);
                    computerOccupiedList.add(index3);
                    computerOccupiedList.add(index4);
                    computerOccupiedList.add(index5);


                    if (col == 0) {

                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 22);
                        computerOccupiedList.add(index + 23);
                        computerOccupiedList.add(index + 24);

                        if (row == 0) {
                            computerOccupiedList.add(index + 5);
                            computerOccupiedList.add(index + 25);
                        } else if (row == 15) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 15) {
                            computerOccupiedList.add(index + 5);
                            computerOccupiedList.add(index + 25);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        }

                    } else if (col == 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index - 19);
                        computerOccupiedList.add(index - 18);
                        computerOccupiedList.add(index - 17);
                        computerOccupiedList.add(index - 16);
                        if (row == 0) {

                            computerOccupiedList.add(index + 5);
                            computerOccupiedList.add(index - 15);
                        } else if (row == 15) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        } else if (row > 0 && row < 15) {
                            computerOccupiedList.add(index + 5);
                            computerOccupiedList.add(index - 15);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        }

                    } else if (col > 0 && col < 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index - 19);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index - 18);
                        computerOccupiedList.add(index + 22);
                        computerOccupiedList.add(index - 17);
                        computerOccupiedList.add(index + 23);
                        computerOccupiedList.add(index - 16);
                        computerOccupiedList.add(index + 24);

                        if (row == 0) {
                            computerOccupiedList.add(index + 5);
                            computerOccupiedList.add(index - 15);
                            computerOccupiedList.add(index + 25);
                        } else if (row == 15) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 15) {
                            computerOccupiedList.add(index + 5);
                            computerOccupiedList.add(index - 15);
                            computerOccupiedList.add(index + 25);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);
                        }
                    }

                }

            } else if (bounds.getWidth() == 100) {
                if (rotate == 0) {
                    index = random.nextInt(340);
                    Integer index2 = index + 20;
                    Integer index3 = index + 40;
                    Integer index4 = index + 60;
                    int row = getRow(index);
                    int col = getCol(index);

                    while (computerOccupiedList.contains(index) || computerOccupiedList.contains(index2) || computerOccupiedList.contains(index3) ||
                            computerOccupiedList.contains(index4) || col>16) {
                        index = random.nextInt(340);
                        index2 = index + 20;
                        index3 = index + 40;
                        index4 = index + 60;
                       row = getRow(index);
                        col = getCol(index);
                    }

                    computerShips.add(index);
                    computerShips.add(index2);
                    computerShips.add(index3);
                    computerShips.add(index4);

                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);
                    computerOccupiedList.add(index3);
                    computerOccupiedList.add(index4);

                    if (row == 0) {

                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 41);
                        computerOccupiedList.add(index + 61);

                        if (col == 0) {
                            computerOccupiedList.add(index + 80);
                            computerOccupiedList.add(index + 81);
                        } else if (col == 16) {
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 19);
                        } else if (col > 0 && col < 16) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index + 80);
                            computerOccupiedList.add(index + 81);
                        }

                    } else if (row == 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 19);
                        computerOccupiedList.add(index + 39);
                        computerOccupiedList.add(index + 59);
                        if (col == 0) {
                            computerOccupiedList.add(index + 80);
                            computerOccupiedList.add(index + 79);
                        } else if (col == 16) {
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        } else if (col > 0 && col < 16) {
                            computerOccupiedList.add(index + 80);
                            computerOccupiedList.add(index + 79);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        }

                    } else if (row > 0 && row < 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 19);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 39);
                        computerOccupiedList.add(index + 41);
                        computerOccupiedList.add(index + 59);
                        computerOccupiedList.add(index + 61);

                        if (col == 0) {
                            computerOccupiedList.add(index + 79);
                            computerOccupiedList.add(index + 80);
                            computerOccupiedList.add(index + 81);
                        } else if (col == 16) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        } else if (col > 0 && col < 16) {
                            computerOccupiedList.add(index + 79);
                            computerOccupiedList.add(index + 80);
                            computerOccupiedList.add(index + 81);
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        }
                    }
                } else if (rotate == 90) {
                    index = random.nextInt(397);
                    Integer index2 = index + 1;
                    Integer index3 = index + 2;
                    Integer index4 = index + 3;
                    int row = getRow(index);
                    int col = getCol(index);

                    while (computerOccupiedList.contains(index) || computerOccupiedList.contains(index2) || computerOccupiedList.contains(index3) ||
                            computerOccupiedList.contains(index4)||row>16) {
                        index = random.nextInt(340);
                        index2 = index + 1;
                        index3 = index + 2;
                        index4 = index + 3;

                        row = getRow(index);
                        col = getCol(index);
                    }

                    computerShips.add(index);
                    computerShips.add(index2);
                    computerShips.add(index3);
                    computerShips.add(index4);

                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);
                    computerOccupiedList.add(index3);
                    computerOccupiedList.add(index4);

                    if (col == 0) {

                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 22);
                        computerOccupiedList.add(index + 23);


                        if (row == 0) {
                            computerOccupiedList.add(index + 4);
                            computerOccupiedList.add(index + 24);
                        } else if (row == 16) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 16) {
                            computerOccupiedList.add(index + 4);
                            computerOccupiedList.add(index + 24);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        }

                    } else if (col == 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index - 19);
                        computerOccupiedList.add(index - 18);
                        computerOccupiedList.add(index - 17);
                        if (row == 0) {
                            computerOccupiedList.add(index + 4);
                            computerOccupiedList.add(index - 16);
                        } else if (row == 16) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        } else if (row > 0 && row < 16) {
                            computerOccupiedList.add(index + 4);
                            computerOccupiedList.add(index - 16);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        }

                    } else if (col > 0 && col < 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index - 19);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index - 18);
                        computerOccupiedList.add(index + 22);
                        computerOccupiedList.add(index - 17);
                        computerOccupiedList.add(index + 23);


                        if (row == 0) {
                            computerOccupiedList.add(index + 4);
                            computerOccupiedList.add(index - 16);
                            computerOccupiedList.add(index + 24);
                        } else if (row == 16) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 16) {
                            computerOccupiedList.add(index + 4);
                            computerOccupiedList.add(index - 16);
                            computerOccupiedList.add(index + 24);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);


                        }
                    }
                }

            } else if (bounds.getWidth() == 75) {
                if (rotate == 0) {
                    index = random.nextInt(360);
                    Integer index2 = index + 20;
                    Integer index3 = index + 40;
                    int row = getRow(index);
                    int col = getCol(index);

                    while (computerOccupiedList.contains(index) || computerOccupiedList.contains(index2) || computerOccupiedList.contains(index3)|| col>17) {
                        index = random.nextInt(340);
                        index2 = index + 20;
                        index3 = index + 40;

                        row = getRow(index);
                        col = getCol(index);
                    }

                    computerShips.add(index);
                    computerShips.add(index2);
                    computerShips.add(index3);

                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);
                    computerOccupiedList.add(index3);

                    if (row == 0) {

                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 41);

                        if (col == 0) {
                            computerOccupiedList.add(index + 60);
                            computerOccupiedList.add(index + 61);
                        } else if (col == 17) {
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 19);
                        } else if (col > 0 && col < 17) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index + 60);
                            computerOccupiedList.add(index + 61);
                        }

                    } else if (row == 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 19);
                        computerOccupiedList.add(index + 39);

                        if (col == 0) {
                            computerOccupiedList.add(index + 60);
                            computerOccupiedList.add(index + 59);
                        } else if (col == 17) {
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        } else if (col > 0 && col < 17) {
                            computerOccupiedList.add(index + 60);
                            computerOccupiedList.add(index + 59);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        }

                    } else if (row > 0 && row < 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 19);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 39);
                        computerOccupiedList.add(index + 41);


                        if (col == 0) {
                            computerOccupiedList.add(index + 59);
                            computerOccupiedList.add(index + 60);
                            computerOccupiedList.add(index + 61);
                        } else if (col == 17) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        } else if (col > 0 && col < 17) {
                            computerOccupiedList.add(index + 59);
                            computerOccupiedList.add(index + 60);
                            computerOccupiedList.add(index + 61);
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        }
                    }
                } else if (rotate == 90) {
                    index = random.nextInt(398);
                    Integer index2 = index + 1;
                    Integer index3 = index + 2;
                    int row = getRow(index);
                    int col = getCol(index);

                    while (computerOccupiedList.contains(index) || computerOccupiedList.contains(index2) || computerOccupiedList.contains(index3)|| row>17) {
                        index = random.nextInt(340);
                        index2 = index + 1;
                        index3 = index + 2;

                        row = getRow(index);
                        col = getCol(index);
                    }

                    computerShips.add(index);
                    computerShips.add(index2);
                    computerShips.add(index3);


                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);
                    computerOccupiedList.add(index3);


                    if (col == 0) {

                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index + 22);


                        if (row == 0) {
                            computerOccupiedList.add(index + 3);
                            computerOccupiedList.add(index + 23);
                        } else if (row == 17) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 17) {
                            computerOccupiedList.add(index + 3);
                            computerOccupiedList.add(index + 23);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        }

                    } else if (col == 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index - 19);
                        computerOccupiedList.add(index - 18);
                        if (row == 0) {
                            computerOccupiedList.add(index + 3);
                            computerOccupiedList.add(index - 17);
                        } else if (row == 17) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        } else if (row > 0 && row < 17) {
                            computerOccupiedList.add(index + 3);
                            computerOccupiedList.add(index - 17);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        }

                    } else if (col > 0 && col < 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index - 19);
                        computerOccupiedList.add(index + 21);
                        computerOccupiedList.add(index - 18);
                        computerOccupiedList.add(index + 22);


                        if (row == 0) {
                            computerOccupiedList.add(index + 3);
                            computerOccupiedList.add(index - 17);
                            computerOccupiedList.add(index + 23);
                        } else if (row == 17) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 17) {
                            computerOccupiedList.add(index + 3);
                            computerOccupiedList.add(index - 17);
                            computerOccupiedList.add(index + 23);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);


                        }
                    }
                }
            } else if (bounds.getWidth() == 50) {

                if (rotate == 0) {
                    index = random.nextInt(380);
                    Integer index2 = index + 20;
                    int row = getRow(index);
                    int col = getCol(index);

                    while (computerOccupiedList.contains(index) || computerOccupiedList.contains(index2)|| col>18) {
                        index = random.nextInt(340);
                        index2 = index + 20;
                        row = getRow(index);
                        col = getCol(index);
                    }

                    computerShips.add(index);
                    computerShips.add(index2);

                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);

                    if (row == 0) {

                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 21);


                        if (col == 0) {
                            computerOccupiedList.add(index + 40);
                            computerOccupiedList.add(index + 41);
                        } else if (col == 18) {
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 19);
                        } else if (col > 0 && col < 18) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index + 40);
                            computerOccupiedList.add(index + 41);
                        }

                    } else if (row == 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 19);

                        if (col == 0) {
                            computerOccupiedList.add(index + 40);
                            computerOccupiedList.add(index + 39);
                        } else if (col == 18) {
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        } else if (col > 0 && col < 18) {
                            computerOccupiedList.add(index + 40);
                            computerOccupiedList.add(index + 39);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index - 20);

                        }

                    } else if (row > 0 && row < 19) {
                        computerOccupiedList.add(index - 1);
                        computerOccupiedList.add(index + 1);
                        computerOccupiedList.add(index + 19);
                        computerOccupiedList.add(index + 21);


                        if (col == 0) {
                            computerOccupiedList.add(index + 39);
                            computerOccupiedList.add(index + 40);
                            computerOccupiedList.add(index + 41);
                        } else if (col == 18) {
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        } else if (col > 0 && col < 18) {
                            computerOccupiedList.add(index + 39);
                            computerOccupiedList.add(index + 40);
                            computerOccupiedList.add(index + 41);
                            computerOccupiedList.add(index - 19);
                            computerOccupiedList.add(index - 20);
                            computerOccupiedList.add(index - 21);
                        }
                    }

                } else if (rotate == 90) {
                    index = random.nextInt(399);
                    Integer index2 = index + 1;
                    int row = getRow(index);
                    int col = getCol(index);

                    while (computerOccupiedList.contains(index) || computerOccupiedList.contains(index2)||row>18) {
                        index = random.nextInt(340);
                        index2 = index + 1;
                        row = getRow(index);
                        col = getCol(index);

                    }

                    computerShips.add(index);
                    computerShips.add(index2);


                    computerOccupiedList.add(index);
                    computerOccupiedList.add(index2);

                    if (col == 0) {

                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index + 21);


                        if (row == 0) {
                            computerOccupiedList.add(index + 2);
                            computerOccupiedList.add(index + 22);
                        } else if (row == 18) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 18) {
                            computerOccupiedList.add(index + 2);
                            computerOccupiedList.add(index + 22);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index + 19);
                        }

                    } else if (col == 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index - 19);

                        if (row == 0) {
                            computerOccupiedList.add(index + 2);
                            computerOccupiedList.add(index - 18);
                        } else if (row == 18) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        } else if (row > 0 && row < 18) {
                            computerOccupiedList.add(index + 2);
                            computerOccupiedList.add(index - 18);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);

                        }

                    } else if (col > 0 && col < 19) {
                        computerOccupiedList.add(index - 20);
                        computerOccupiedList.add(index + 20);
                        computerOccupiedList.add(index - 19);
                        computerOccupiedList.add(index + 21);


                        if (row == 0) {
                            computerOccupiedList.add(index + 2);
                            computerOccupiedList.add(index - 18);
                            computerOccupiedList.add(index + 22);
                        } else if (row == 18) {
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);
                        } else if (row > 0 && row < 18) {
                            computerOccupiedList.add(index + 2);
                            computerOccupiedList.add(index - 18);
                            computerOccupiedList.add(index + 22);
                            computerOccupiedList.add(index - 1);
                            computerOccupiedList.add(index - 21);
                            computerOccupiedList.add(index + 19);


                        }
                    }
                }

            }

        }
    }


    private int getCol(int index) {
        int col = (index / 20) - 1;
        return col;
    }

    private int getRow(int index) {

        int row = -1;
        if (index == 19 || index == 39 || index == 59 || index == 79 || index == 99 || index == 119 || index == 139 || index == 159 || index == 179 || index == 199 || index == 219
                || index == 239 || index == 259|| index == 279 || index == 299|| index == 319 || index == 339 || index == 359 || index == 379 || index == 399) {
            row = 19;

        } else if (index == 18 || index == 38 || index == 58 || index == 78 || index == 98 || index == 118 || index == 138 || index == 158 || index == 178 || index == 198 || index == 218
                || index == 238 || index == 258 || index == 278 || index == 298|| index == 318 || index == 338|| index == 358 || index == 378 || index == 398) {
            row = 18;
        } else if (index == 17 || index == 37 || index == 57 || index == 77 || index == 97 || index == 117 || index == 137 || index == 157 || index == 177 || index == 197 || index == 217
                || index == 237 || index == 257 || index == 277 || index == 297 || index == 317 || index == 337 || index == 357 || index == 377 || index == 397) {
            row = 17;
        } else if (index == 16 || index == 36 || index == 56 || index == 76 || index == 96 || index == 116 || index == 136 || index == 156 || index == 176 || index == 196 || index == 216
                || index == 236 || index == 256 || index == 276 || index == 296 || index == 316 || index == 336 || index == 356 || index == 376 || index == 396) {
            row = 16;
        } else if (index == 15 || index == 35 || index == 55|| index == 75 || index == 95 || index == 115 || index == 135 || index == 155 || index == 175 || index == 195 || index == 215
                || index == 235|| index == 255 || index == 275 || index == 295 || index == 315 || index == 335|| index == 355 || index == 375 || index == 395) {
            row = 15;
        } else if (index == 14 || index == 34 || index == 54 || index == 74 || index == 94 || index == 114 || index == 134 || index == 154 || index == 174 || index == 194 || index == 214
                || index == 234 || index == 254 || index == 274 || index == 294 || index == 314 || index == 334 || index == 354 || index == 374 || index == 394) {
            row = 14;
        } else if (index == 13 || index == 33|| index == 53 || index == 73 || index == 93 || index == 113 || index == 133 || index == 153 || index == 173 || index == 193 || index == 213
                || index == 233 || index == 253 || index == 273 || index == 293 || index == 313 || index == 333 || index == 353 || index == 373 || index == 393) {
            row = 13;
        } else if (index == 12 || index == 32 || index == 52 || index == 72 || index == 92 || index == 112 || index == 132 || index == 152 || index == 172 || index == 192 || index == 212
                || index == 232 || index == 252 || index == 272 || index == 292 || index == 312 || index == 332 || index == 352 || index == 372 || index == 392){
            row = 12;
        } else if (index == 11 || index == 31 || index == 51 || index == 71 || index == 91 || index == 111 || index == 131 || index == 151 || index == 171 || index == 191 || index == 211
                || index == 231 || index == 251 || index == 271 || index == 291 || index == 311 || index == 331 || index == 351 || index == 371 || index == 391) {
            row = 11;
        } else if (index == 10 || index == 30 || index == 50 || index == 70 || index == 90 || index == 110 || index == 130 || index == 150 || index == 170 || index == 190 || index == 210
                || index == 230 || index == 250 || index == 270 || index == 290 || index == 310 || index == 330 || index == 350 || index == 370 || index == 390) {
            row = 10;
        } else if (index == 9 || index == 29 || index == 49 || index == 69 || index == 89 || index == 109 || index == 129 || index == 149 || index == 169 || index == 189 || index == 209
                || index == 229 || index == 249 || index == 269 || index == 289 || index == 309 || index == 329 || index == 349 || index == 369 || index == 389) {
            row = 9;
        } else if (index == 8 || index == 28 || index == 48 || index == 68 || index == 88 || index == 108 || index == 128 || index == 148 || index == 168 || index == 188 || index == 208
                || index == 228 || index == 248 || index == 268 || index == 288 || index == 308 || index == 328 || index == 348 || index == 368 || index == 388) {
            row = 8;
        } else if (index == 7 || index == 27 || index == 47 || index == 67 || index == 87 || index == 107 || index == 127 || index == 147 || index == 167 || index == 187 || index == 207
                || index == 227 || index == 247 || index == 267 || index == 287 || index == 307 || index == 327 || index == 347 || index == 367 || index == 387) {
            row = 7;
        } else if (index == 6 || index == 26 || index == 46 || index == 66 || index == 86 || index == 106 || index == 126 || index == 146 || index == 166 || index == 186 || index == 206
                || index == 226 | index == 246 || index == 266 || index == 286 || index == 306 || index == 326 || index == 346 || index == 366 || index == 386) {
            row = 6;
        } else if (index == 5 || index == 25 || index == 45 || index == 65 || index == 85 || index == 105 || index == 1325 || index == 145 || index == 165 || index == 185 || index == 205
                || index == 225 || index == 245 || index == 265 || index == 285 || index == 305 || index == 325 || index == 345 || index == 365 || index == 385) {
            row = 5;
        } else if (index == 4 || index == 24 || index == 44 || index == 64 || index == 84 || index == 104 || index == 124 || index == 144 || index == 164 || index == 184 || index == 204
                || index == 224 || index == 244 || index == 264 || index == 284 || index == 304 || index == 324 || index == 344 || index == 364 || index == 384) {
            row = 4;
        } else if (index == 3 || index == 23 || index == 43 || index == 63 || index == 83 || index == 103 || index == 123 || index == 143 || index == 163 || index == 183 || index == 203
                || index == 223 || index == 243 || index == 263 || index == 283 || index == 303 || index == 323 || index == 343 || index == 363 || index == 383) {
            row = 3;
        } else if (index == 2 || index == 22 || index == 42 || index == 62 || index == 82 || index == 102 || index == 122 || index == 142 || index == 162 || index == 182 || index == 202
                || index == 222 || index == 242 || index == 262 || index == 282 || index == 302 || index == 322 || index == 342 || index == 362 || index == 382) {
            row = 2;
        } else if (index == 1 || index == 21 || index == 41 || index == 61 || index == 81 || index == 101 || index == 121 || index == 141 || index == 161 || index == 181 || index == 201
                || index == 221 || index == 241 || index == 261 || index == 281 || index == 301 || index == 321 || index == 341 || index == 361 || index == 381) {
            row = 1;
        } else if (index == 0 || index == 20 || index == 40 || index == 60 || index == 80 || index == 100 || index == 120 || index == 140 || index == 160 || index == 180 || index == 200
                || index == 220 || index == 240 || index == 260 || index == 280 || index == 300 || index == 320 || index == 340 || index == 360 || index == 380) {
            row = 0;
        }
        return row;
    }
}





