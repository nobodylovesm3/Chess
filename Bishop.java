package Chess;

public class Bishop extends PlayingPiece {

    public Bishop(int x, int y, int color) {
        super(x, y, color);
        if (color == 1) {
            this.setSymbol('\u2657');
        } else {
            this.setSymbol('\u265D');
        }
    }

    @Override
    public boolean moveIsLegal(int x, int y) {
        if (Math.abs(this.getX() - x) == Math.abs(this.getY() - y) && super.moveIsLegal(x, y) && !hasObsticles(x, y)) {
            return true;
        }
        return false;
    }

    private boolean hasObsticles(int x, int y) {
        int diffX = this.getX() - x;
        int diffY = this.getY() - y;
        int changeX = diffX / Math.abs(diffX);
        int changeY = diffY / Math.abs(diffY);
        for (int i = this.getX() - changeX, j = this.getY() - changeY; i != x; i -= changeX, j -= changeY) {
            if (Board.getPiece(i, j) != null) {
                return true;
            }
        }
        return false;
    }

}

