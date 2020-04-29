package Chess;

public class PlayingPiece {

    private int x;
    private int y;
    private boolean isAlive = true;
    private char symbol;
    private int color; // 1 - white; 2 - black;

    public PlayingPiece(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void move(int x, int y) {
        if (moveIsLegal(x, y)) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean moveIsLegal(int x, int y) {
        return !(this.x < 0 || this.x > 8 || this.y < 0 || this.y > 8 || (this.x == x && this.y == y) ||
                checkForFriendlyPiece(x, y));
    }

    private boolean checkForFriendlyPiece(int x, int y) {
        if (Board.getPiece(x, y) != null && Board.getPiece(x, y).getColor() == this.getColor()) {
            return true;
        }
        return false;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void destroy() {
        this.isAlive = false;
    }


}
