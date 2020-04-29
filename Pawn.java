package Chess;

import java.util.Scanner;

public class Pawn extends PlayingPiece {

    private boolean hasMoved;

    public Pawn(int x, int y, int color) {
        super(x, y, color);
        if (color == 1) {
            this.setSymbol('\u2659');
        } else {
            this.setSymbol('\u265F');
        }
    }

    @Override
    public boolean moveIsLegal(int x, int y) {
        if (canTake(x, y) && super.moveIsLegal(x, y)) {
            return true;
        }
        if (!hasMoved) {
            if (this.getColor() == 1) {
                if (this.getY() == y && (this.getX() - x == 2 || this.getX() - x == 1) && super.moveIsLegal(x, y)
                        && !hasObstacles(x, y)) {
                    this.hasMoved = true;
                    return true;
                }
            } else {
                if (this.getY() == y && (this.getX() - x == -2 || this.getX() - x == -1) && super.moveIsLegal(x, y)
                        && !hasObstacles(x, y)) {
                    this.hasMoved = true;
                    return true;
                }
            }
        } else {
            if (this.getColor() == 1) {
                if (this.getY() == y && this.getX() - x == 1 && super.moveIsLegal(x, y) && !hasObstacles(x, y)) {
                    return true;
                }
            } else {
                if (this.getY() == y && this.getX() - x == -1 && super.moveIsLegal(x, y) && !hasObstacles(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasObstacles(int x, int y) {
        int diffX = this.getX() - x;
        int changeX = diffX / Math.abs(diffX);
        if (diffX == 1) {
            if (Board.getPiece(this.getX() - changeX, y) != null) {
                return true;
            }
        } else {
            if (Board.getPiece(this.getX() - changeX, y) != null && Board.getPiece(x, y) != null) {
                return true;
            }
        }
        return false;
    }

    private boolean canTake(int x, int y) {
        if (this.getColor() == 1) {
            if (this.getX() - x == 1 && Math.abs(this.getY() - y) == 1) {
                if (Board.getPiece(x, y) != null) {
                    return true;
                }
            }
        } else {
            if (this.getX() - x == -1 && Math.abs(this.getY() - y) == 1) {
                if (Board.getPiece(x, y) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canChange() {
        if (this.getColor() == 1) {
            if (this.getX() == 0) {
                return true;
            }
        } else {
            if (this.getX() == 7) {
                return true;
            }
        }
        return false;
    }

    public static PlayingPiece changeFigure(int x, int y, int color) {
        System.out.print("Please choose an option: 1 - Rook, 2 - Knight, 3 - Bishop, 4 - Queen");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        input.close();
        switch (option) {
            case 1:
                return new Rook(x, y, color);
            case 2:
                return new Knight(x, y, color);
            case 3:
                return new Bishop(x, y, color);
            case 4:
                return new Queen(x, y, color);
            default:
                System.out.println("You wasted your chance!");
        }
        return null;
    }

}

