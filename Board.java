package Chess;

public class Board {

    private static PlayingPiece[][] board = fillBoard();
    private static int turn = 1; // 1 - white; 2 - black;
    private PlayingPiece reversablePiece;
    private static boolean isGameGoing = true;

    public static PlayingPiece[][] getBoard() {
        return board;
    }

    public static PlayingPiece getPiece(int x, int y) {
        return board[x][y];
    }

    public static boolean isGameGoing() {
        return isGameGoing;
    }

    public void play(int x, int y, int newX, int newY) {
        if (getPiece(x, y) != null && getPiece(x, y).getColor() == turn) {
            if (getPiece(x, y).moveIsLegal(newX, newY)) {
                makeMove(x, y, newX, newY);
                if (board[newX][newY] instanceof Pawn) {
                    Pawn p = new Pawn(newX, newY, turn);
                    if (p.canChange()) {
                        board[newX][newY] = Pawn.changeFigure(newX, newY, turn);
                    }
                }
                if (haveCheck(turn)) {
                    reverseMove(x, y, newX, newY);
                    System.out.println("You can't make that move, because you would be in a check!");
                    return;
                }
            } else {
                System.out.println("This move is not legal!");
                return;
            }
        } else if (getPiece(x, y) == null) {
            System.out.println("Please select a piece!");
            return;
        } else {
            System.out.println("You can only make one move with the same pieces!");
            return;
        }
        turn = turn ^ 3;
        printBoard();
        if (haveCheck(turn)) {
            if (haveMate(turn)) {
                if (turn == 1) {
                    System.out.println("Gave Over! The WHITE player has been checkmated!");
                    isGameGoing = false;
                } else {
                    System.out.println("Gave Over! The BLACK player has been checkmated!");
                    isGameGoing = false;
                }
            }
            if (turn == 1) {
                System.out.println("The WHITE player has been checked!");
            } else {
                System.out.println("The BLACK player has been checked!");
            }
        }
    }

    private void makeMove(int x, int y, int newX, int newY) {
        getPiece(x, y).move(newX, newY);
        reversablePiece = board[newX][newY];
        board[newX][newY] = getPiece(x, y);
        board[x][y] = null;
    }

    private void reverseMove(int x, int y, int newX, int newY) {
        ;
        getPiece(newX, newY).move(x, y);
        board[x][y] = getPiece(newX, newY);
        board[newX][newY] = reversablePiece;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.print("   " + i + "  ");
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print("   ");
            for (int j = 0; j < 7; j++) {
                System.out.print("-------");
            }
            System.out.println();
            System.out.print(" " + i + " ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("|     ");
                } else {
                    System.out.print("|  " + board[i][j].getSymbol() + "   ");
                }
            }
            System.out.println("| " + i);
        }
        System.out.print("   ");
        for (int j = 0; j < 7; j++) {
            System.out.print("-------");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.print("   " + i + "  ");
        }
        System.out.println("\n");
    }

    private boolean haveCheck(int color) {
        int newColor = color ^ 3;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PlayingPiece p = getPiece(i, j);
                if (p != null && p.getColor() == newColor) {
                    if (!(p instanceof King) && p.moveIsLegal(getKingX(color), getKingY(color))) {
                        return true;
                    }
                }
//				if (p != null && p.getColor() == 2) {
//					if (!(p instanceof King) && p.moveIsLegal(getKingX(1), getKingY(1))) {
//						return 1;
//					}
//				}
            }
        }
        return false;
    }

    private int getKingX(int color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getPiece(i, j) instanceof King) {
                    if (getPiece(i, j).getColor() == color) {
                        return i;
                    }
                }
            }
        }
        return -1; // It should never reach that line!
    }

    private int getKingY(int color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getPiece(i, j) instanceof King) {
                    if (getPiece(i, j).getColor() == color) {
                        return j;
                    }
                }
            }
        }
        return -1; // It should never reach that line!
    }

    private static PlayingPiece[][] fillBoard() {
        PlayingPiece[][] result = new PlayingPiece[8][8];

        result[0][4] = new King(0, 4, 2);
        result[7][4] = new King(7, 4, 1);

        result[0][3] = new Queen(0, 3, 2);
        result[7][3] = new Queen(7, 3, 1);

        for (int i = 0; i <= 7; i += 7) {
            result[0][i] = new Rook(0, i, 2);
            result[7][i] = new Rook(7, i, 1);
        }

        for (int i = 1; i <= 6; i += 5) {
            result[0][i] = new Knight(0, i, 2);
            result[7][i] = new Knight(7, i, 1);
        }

        for (int i = 2; i <= 5; i += 3) {
            result[0][i] = new Bishop(0, i, 2);
            result[7][i] = new Bishop(7, i, 1);
        }

        for (int i = 0; i < 8; i++) {
            result[1][i] = new Pawn(1, i, 2);
            result[6][i] = new Pawn(6, i, 1);
        }
        return result;
    }

    private boolean haveMate(int color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getPiece(i, j) != null && getPiece(i, j).getColor() == color) {
                    for (int k = 0; k < 8; k++) {
                        for (int h = 0; h < 8; h++) {
                            if (getPiece(i, j).moveIsLegal(k, h)) {
                                makeMove(i, j, k, h);
                                if (!haveCheck(color)) {
                                    reverseMove(i, j, k, h);
                                    return false;
                                }
                                reverseMove(i, j, k, h);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
