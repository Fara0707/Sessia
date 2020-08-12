package game;

import java.util.Objects;

public class Professor {
    private Position position;
    private int num;
    private Direction dir = Direction.STAND;
    private int mark;
    private GameField field;
    private  boolean Zachet = false;

    public boolean isZachet() {
        return Zachet;
    }

    public Professor setZachet(boolean zachet) {
        Zachet = zachet;
        return this;
    }

    public int getNum() {
        return num;
    }

    public void move() {
        int width = field.getWidth();
        int height = field.getHeight();
        Position next = dir.next(new Position(position.getX(), position.getY()));
        position.setX( (next.getX()+width) % width );
        position.setY( (next.getY()+height) % height );
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Position getPosition() {
        return position;
    }

    public int getMark() {
        return mark;
    }

    public Professor(int x, int y, int num, int mark, GameField field) {
        this.position = new Position( x, y );
        this.num = num;
        this.mark = mark;
        this.field = field;
    }


    @Override
    public boolean equals(Object o) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Professor professor = (Professor) o;
        return mark == professor.mark &&
                Objects.equals( position, professor.position );
    }

    @Override
    public int hashCode() {
        return Objects.hash( position, mark );
    }
}
