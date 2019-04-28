package com.kodilla;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class InstallListener {

    public void resetColor(GridPane userBoard, Color color, ArrayList<Integer> occupiedCells, ArrayList<Integer> shipCells) {
        for (int i = 0; i < 400; i++) {
            Rectangle rectangle = (Rectangle) userBoard.getChildren().get(i);
            if (rectangle.getFill() == color) {
                rectangle.setFill(Color.GRAY);
            }
        }
        occupiedCells.removeAll(shipCells);
    }

    public void setShipZeroRotation(int index, ArrayList<Integer> battleshipList, ArrayList<Integer> cells, int col, int row, int size) {
        cells.add(index);
        cells.add(index + 20);
        if (size == 3) {
            cells.add(index + 40);
        } else if (size == 4) {
            cells.add(index + 40);
            cells.add(index + 60);
        } else if (size == 5) {
            cells.add(index + 40);
            cells.add(index + 60);
            cells.add(index + 80);
        }


        battleshipList.add(index);
        battleshipList.add(index + 20);
        if (size == 3) {
            battleshipList.add(index + 40);
        } else if (size == 4) {
            battleshipList.add(index + 40);
            battleshipList.add(index + 60);
        } else if (size == 5) {
            battleshipList.add(index + 40);
            battleshipList.add(index + 60);
            battleshipList.add(index + 80);
        }

        if (row == 0) {

            cells.add(index + 1);
            cells.add(index + 21);
            if (size == 3) {
                cells.add(index + 41);
            } else if (size == 4) {
                cells.add(index + 41);
                cells.add(index + 61);
            } else if (size == 5) {
                cells.add(index + 41);
                cells.add(index + 61);
                cells.add(index + 81);
            }

            if (size == 5) {
                if (col == 0) {
                    cells.add(index + 100);
                    cells.add(index + 101);
                } else if (col == 15) {
                    cells.add(index - 20);
                    cells.add(index - 19);
                } else if (col > 0 && col < 15) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index + 100);
                    cells.add(index + 101);
                }
            } else if (size == 4) {
                if (col == 0) {
                    cells.add(index + 80);
                    cells.add(index + 81);
                } else if (col == 16) {
                    cells.add(index - 20);
                    cells.add(index - 19);
                } else if (col > 0 && col < 16) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index + 80);
                    cells.add(index + 81);
                }
            } else if (size == 3) {
                if (col == 0) {
                    cells.add(index + 60);
                    cells.add(index + 61);
                } else if (col == 17) {
                    cells.add(index - 20);
                    cells.add(index - 19);
                } else if (col > 0 && col < 17) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index + 60);
                    cells.add(index + 61);
                }
            } else if (size == 2) {
                if (col == 0) {
                    cells.add(index + 40);
                    cells.add(index + 41);
                } else if (col == 18) {
                    cells.add(index - 20);
                    cells.add(index - 19);
                } else if (col > 0 && col < 18) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index + 40);
                    cells.add(index + 41);
                }
            }
        } else if (row == 19) {
            cells.add(index - 1);
            cells.add(index + 19);
            if (size == 3) {
                cells.add(index + 39);
            } else if (size == 4) {
                cells.add(index + 39);
                cells.add(index + 59);
            } else if (size == 5) {
                cells.add(index + 39);
                cells.add(index + 59);
                cells.add(index + 79);
            }

            if (size == 5) {
                if (col == 0) {

                    cells.add(index + 100);
                    cells.add(index + 99);
                } else if (col == 15) {
                    cells.add(index - 21);
                    cells.add(index - 20);

                } else if (col > 0 && col < 15) {
                    cells.add(index + 100);
                    cells.add(index + 99);
                    cells.add(index - 21);
                    cells.add(index - 20);

                }
            } else if (size == 4) {
                if (col == 0) {
                    cells.add(index + 80);
                    cells.add(index + 79);
                } else if (col == 16) {
                    cells.add(index - 21);
                    cells.add(index - 20);

                } else if (col > 0 && col < 16) {
                    cells.add(index + 80);
                    cells.add(index + 79);
                    cells.add(index - 21);
                    cells.add(index - 20);
                }
            } else if (size == 3) {
                if (col == 0) {
                    cells.add(index + 60);
                    cells.add(index + 59);
                } else if (col == 17) {
                    cells.add(index - 21);
                    cells.add(index - 20);

                } else if (col > 0 && col < 17) {
                    cells.add(index + 60);
                    cells.add(index + 59);
                    cells.add(index - 21);
                    cells.add(index - 20);

                }
            } else if (size == 2) {
                if (col == 0) {
                    cells.add(index + 40);
                    cells.add(index + 39);
                } else if (col == 18) {
                    cells.add(index - 21);
                    cells.add(index - 20);

                } else if (col > 0 && col < 18) {
                    cells.add(index + 40);
                    cells.add(index + 39);
                    cells.add(index - 21);
                    cells.add(index - 20);

                }
            }

        } else if (row > 0 && row < 19) {
            cells.add(index - 1);
            cells.add(index + 1);
            cells.add(index + 19);
            cells.add(index + 21);
            if (size == 3) {
                cells.add(index + 39);
                cells.add(index + 41);
            } else if (size == 4) {
                cells.add(index + 39);
                cells.add(index + 41);
                cells.add(index + 59);
                cells.add(index + 61);
            } else if (size == 5) {
                cells.add(index + 39);
                cells.add(index + 41);
                cells.add(index + 59);
                cells.add(index + 61);
                cells.add(index + 79);
                cells.add(index + 81);
            }
            if (size == 5) {
                if (col == 0) {
                    cells.add(index + 99);
                    cells.add(index + 100);
                    cells.add(index + 101);
                } else if (col == 15) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                } else if (col > 0 && col < 15) {
                    cells.add(index + 99);
                    cells.add(index + 100);
                    cells.add(index + 101);
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                }
            } else if (size == 4) {
                if (col == 0) {
                    cells.add(index + 79);
                    cells.add(index + 80);
                    cells.add(index + 81);
                } else if (col == 16) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                } else if (col > 0 && col < 16) {
                    cells.add(index + 79);
                    cells.add(index + 80);
                    cells.add(index + 81);
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                }
            } else if (size == 3) {
                if (col == 0) {
                    cells.add(index + 59);
                    cells.add(index + 60);
                    cells.add(index + 61);
                } else if (col == 17) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                } else if (col > 0 && col < 17) {
                    cells.add(index + 59);
                    cells.add(index + 60);
                    cells.add(index + 61);
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                }
            } else if (size == 2) {
                if (col == 0) {
                    cells.add(index + 39);
                    cells.add(index + 40);
                    cells.add(index + 41);
                } else if (col == 18) {
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                } else if (col > 0 && col < 18) {
                    cells.add(index + 39);
                    cells.add(index + 40);
                    cells.add(index + 41);
                    cells.add(index - 19);
                    cells.add(index - 20);
                    cells.add(index - 21);
                }
            }
        }

    }

    public void setRotatedShip(int index, ArrayList<Integer> battleshipList, ArrayList<Integer> cells, int col, int row, int size) {
        cells.add(index);
        cells.add(index + 1);
        if (size == 3) {
            cells.add(index + 2);
        } else if (size == 4) {
            cells.add(index + 2);
            cells.add(index + 3);
        } else if (size == 5) {
            cells.add(index + 2);
            cells.add(index + 3);
            cells.add(index + 4);
        }

        battleshipList.add(index);
        battleshipList.add(index + 1);
        if (size == 3) {
            battleshipList.add(index + 2);
        } else if (size == 4) {
            battleshipList.add(index + 2);
            battleshipList.add(index + 3);
        } else if (size == 5) {
            battleshipList.add(index + 2);
            battleshipList.add(index + 3);
            battleshipList.add(index + 4);
        }

        if (col == 0) {

            cells.add(index + 20);
            cells.add(index + 21);
            if (size == 3) {
                cells.add(index + 22);
            } else if (size == 4) {
                cells.add(index + 22);
                cells.add(index + 23);
            } else if (size == 5) {
                cells.add(index + 22);
                cells.add(index + 23);
                cells.add(index + 24);
            }

            if (size == 5) {
                if (row == 0) {
                    cells.add(index + 5);
                    cells.add(index + 25);
                } else if (row == 15) {
                    cells.add(index - 1);
                    cells.add(index + 19);
                } else if (row > 0 && row < 15) {
                    cells.add(index + 5);
                    cells.add(index + 25);
                    cells.add(index - 1);
                    cells.add(index + 19);
                }
            } else if (size == 4) {
                if (row == 0) {
                    cells.add(index + 4);
                    cells.add(index + 24);
                } else if (row == 16) {
                    cells.add(index - 1);
                    cells.add(index + 19);
                } else if (row > 0 && row < 16) {
                    cells.add(index + 4);
                    cells.add(index + 24);
                    cells.add(index - 1);
                    cells.add(index + 19);
                }
            } else if (size == 3) {
                if (row == 0) {
                    cells.add(index + 3);
                    cells.add(index + 23);
                } else if (row == 17) {
                    cells.add(index - 1);
                    cells.add(index + 19);
                } else if (row > 0 && row < 17) {
                    cells.add(index + 3);
                    cells.add(index + 23);
                    cells.add(index - 1);
                    cells.add(index + 19);
                }
            } else if (size == 2) {
                if (row == 0) {
                    cells.add(index + 2);
                    cells.add(index + 22);
                } else if (row == 18) {
                    cells.add(index - 1);
                    cells.add(index + 19);
                } else if (row > 0 && row < 18) {
                    cells.add(index + 2);
                    cells.add(index + 22);
                    cells.add(index - 1);
                    cells.add(index + 19);
                }
            }


        } else if (col == 19) {
            cells.add(index - 20);
            cells.add(index - 19);
            if (size == 3) {
                cells.add(index - 18);
            } else if (size == 4) {
                cells.add(index - 18);
                cells.add(index - 17);
            } else if (size == 5) {
                cells.add(index - 18);
                cells.add(index - 17);
                cells.add(index - 16);
            }

            if (size == 5) {
                if (row == 0) {

                    cells.add(index + 5);
                    cells.add(index - 15);
                } else if (row == 15) {
                    cells.add(index - 1);
                    cells.add(index - 21);

                } else if (row > 0 && row < 15) {
                    cells.add(index + 5);
                    cells.add(index - 15);
                    cells.add(index - 1);
                    cells.add(index - 21);

                }
            } else if (size == 4) {
                if (row == 0) {
                    cells.add(index + 4);
                    cells.add(index - 16);
                } else if (row == 16) {
                    cells.add(index - 1);
                    cells.add(index - 21);

                } else if (row > 0 && row < 16) {
                    cells.add(index + 4);
                    cells.add(index - 16);
                    cells.add(index - 1);
                    cells.add(index - 21);

                }
            } else if (size == 3) {
                if (row == 0) {
                    cells.add(index + 3);
                    cells.add(index - 17);
                } else if (row == 17) {
                    cells.add(index - 1);
                    cells.add(index - 21);

                } else if (row > 0 && row < 17) {
                    cells.add(index + 3);
                    cells.add(index - 17);
                    cells.add(index - 1);
                    cells.add(index - 21);

                }
            } else if (size == 2) {

                if (row == 0) {
                    cells.add(index + 2);
                    cells.add(index - 18);
                } else if (row == 18) {
                    cells.add(index - 1);
                    cells.add(index - 21);

                } else if (row > 0 && row < 18) {
                    cells.add(index + 2);
                    cells.add(index - 18);
                    cells.add(index - 1);
                    cells.add(index - 21);

                }
            }
        } else if (col > 0 && col < 19) {
            cells.add(index - 20);
            cells.add(index + 20);
            cells.add(index - 19);
            cells.add(index + 21);
            if (size == 3) {
                cells.add(index - 18);
                cells.add(index + 22);
            } else if (size == 4) {
                cells.add(index - 18);
                cells.add(index + 22);
                cells.add(index - 17);
                cells.add(index + 23);
            } else if (size == 5) {
                cells.add(index - 18);
                cells.add(index + 22);
                cells.add(index - 17);
                cells.add(index + 23);
                cells.add(index - 16);
                cells.add(index + 24);
            }

            if (size == 5) {
                if (row == 0) {
                    cells.add(index + 5);
                    cells.add(index - 15);
                    cells.add(index + 25);
                } else if (row == 15) {
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);
                } else if (row > 0 && row < 15) {
                    cells.add(index + 5);
                    cells.add(index - 15);
                    cells.add(index + 25);
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);
                }

            } else if (size == 4) {
                if (row == 0) {
                    cells.add(index + 4);
                    cells.add(index - 16);
                    cells.add(index + 24);
                } else if (row == 16) {
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);
                } else if (row > 0 && row < 16) {
                    cells.add(index + 4);
                    cells.add(index - 16);
                    cells.add(index + 24);
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);


                }
            } else if (size == 3) {
                if (row == 0) {
                    cells.add(index + 3);
                    cells.add(index - 17);
                    cells.add(index + 23);
                } else if (row == 17) {
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);
                } else if (row > 0 && row < 17) {
                    cells.add(index + 3);
                    cells.add(index - 17);
                    cells.add(index + 23);
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);


                }
            } else if (size == 2) {
                if (row == 0) {
                    cells.add(index + 2);
                    cells.add(index - 18);
                    cells.add(index + 22);
                } else if (row == 18) {
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);
                } else if (row > 0 && row < 18) {
                    cells.add(index + 2);
                    cells.add(index - 18);
                    cells.add(index + 22);
                    cells.add(index - 1);
                    cells.add(index - 21);
                    cells.add(index + 19);


                }
            }
        }
    }
}

