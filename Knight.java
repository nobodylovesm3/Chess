package Chess;

public class Knight extends PlayingPiece {

    public Knight(int x, int y, int color) {
        super(x, y, color);
        if (color == 1) {
            this.setSymbol('\u2658');
        } else {
            this.setSymbol('\u265E');
        }
    }

    @Override
    public boolean moveIsLegal(int x, int y) {
        int diffX = Math.abs(this.getX() - x);
        int diffY = Math.abs(this.getY() - y);
        if (((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)) && super.moveIsLegal(x, y)) {
            return true;
        }
        return false;
    }

}

