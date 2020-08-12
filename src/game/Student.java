package game;


public class Student {
    private int x;
    private int y;
    private int mark = 0;
    private Direction dir = Direction.STAND;
    private Zachetka zachetka;
    private GameField field;


    public Student(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Student(GameField f) {
        field = f;
        x = f.getWidth() / 2;
        y = f.getHeight() / 2;
        zachetka = new Zachetka( x, y );
    }

    public void move() {
        int width = field.getWidth();
        int height = field.getHeight();
        Position next = dir.next(new Position(x, y));
        x = (next.getX()+width) % width;
        y = (next.getY()+height) % height;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getMark() {
        return mark;
    }

    public Student setMark(int mark) {
        this.mark = mark;
        return this;
    }

    public void catchProffesor(){
        for (int i = 0; i < field.getProfessors().size(); i++) {
            if (field.getProfessors().get( i ).getPosition().getX() == x && field.getProfessors().get( i ).getPosition().getY() == y) {
                mark += field.getProfessors().get( i ).getMark();
                field.getProfessors().remove( i );
                break;
            }
        }

    }

    public void pickUpZachetku(){
        if(zachetka.getPosition().getX() == x && zachetka.getPosition().getY() == y){
            zachetka.setThrowed( false );
        }

    }

    public void throwZachetku() {
        if( !zachetka.isThrowed() ){
                Position next = dir.next( new Position( x, y ) );
                if( dir == Direction.UP && y > 2 ){
                    zachetka.getPosition().setX( (next.getX() + field.getWidth()) % field.getWidth() );
                    zachetka.getPosition().setY( (next.getY() + field.getHeight()) % field.getHeight() - 2 );
                    zachetka.setThrowed( true );
                }
                else if( dir == Direction.DOWN  && y < field.getHeight() - 3 ){
                    zachetka.getPosition().setX( (next.getX() + field.getWidth()) % field.getWidth() );
                    zachetka.getPosition().setY( (next.getY() + field.getHeight()) % field.getHeight() + 2 );
                    zachetka.setThrowed( true );

                }
                else if( dir == Direction.RIGHT  && x < field.getWidth() - 3 ){
                    zachetka.getPosition().setX( (next.getX() + field.getWidth()) % field.getWidth() + 2 );
                    zachetka.getPosition().setY( (next.getY() + field.getHeight()) % field.getHeight() );
                    zachetka.setThrowed( true );
                }
                else if( dir == Direction.LEFT && x > 2 ){
                    zachetka.getPosition().setX( (next.getX() + field.getWidth()) % field.getWidth() - 2 );
                    zachetka.getPosition().setY( (next.getY() + field.getHeight()) % field.getHeight() );
                    zachetka.setThrowed( true );
                }
        }
    }

    public Zachetka getZachetka() {
        return zachetka;
    }

    public Position getPosition() {
        return new Position(x,y);
    }

}