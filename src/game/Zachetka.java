package game;

public class Zachetka {
    private Position position;
    boolean throwed = false;

    public Zachetka(int x, int y) {
        this.position = new Position( x, y );
    }

    public Position getPosition() {
        return position;
    }

    public Zachetka setPosition(Position position) {
        this.position = position;
        return this;
    }

    public boolean isThrowed() {
        return throwed;
    }

    public Zachetka setThrowed(boolean throwed) {
        this.throwed = throwed;
        return this;
    }
}
