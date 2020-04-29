package Chess;


public class King extends PlayingPiece {

    public King(int x, int y, int color) {
        super(x, y, color);
        if (color == 1) {
            this.setSymbol('\u2654');
        } else {
            this.setSymbol('\u265A');
        }
    }

    @Override
    public boolean moveIsLegal(int x, int y) {
        if (Math.abs(this.getX() - x) < 2 && Math.abs(this.getY() - y) < 2 && super.moveIsLegal(x, y)) {
            return true;
        }
        return false;
    }

}
