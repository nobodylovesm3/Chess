package Chess;


public class Rook extends PlayingPiece {

    public Rook(int x, int y, int color) {
        super(x, y, color);
        if (color == 1) {
            this.setSymbol('\u2656');
        } else {
            this.setSymbol('\u265C');
        }
    }

    @Override
    public boolean moveIsLegal(int x, int y) {
        if ((this.getX() == x || this.getY() == y) && super.moveIsLegal(x, y) && !hasObstacles(x, y)) {
            return true;
        }
        return false;
    }

    private boolean hasObstacles(int x, int y) {
        int diffX = this.getX() - x;
        int diffY = this.getY() - y;
        int changeX = diffX != 0 ? diffX / Math.abs(diffX) : 0;
        int changeY = diffY != 0 ? diffY / Math.abs(diffY) : 0;
        for (int i = this.getX() - changeX, j = this.getY() - changeY; i != x; i -= changeX, j -= changeY) {
            if (Board.getPiece(i, j) != null) {
                return true;
            }
        }
        return false;
    }

}

