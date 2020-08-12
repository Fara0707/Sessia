package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameField {
    private int width;
    private int height;

    private Student student;
    private List<Professor> professors = new ArrayList<>(  );
    private Random random = new Random(  );
    private static int maxProfessor = 10;

    private GameField(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static GameField create(int width, int height) {
        GameField gameField = new GameField(width, height);
        gameField.student = new Student(gameField);
        return gameField;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Student getStudent() {
        return student;
    }

    public void tick() {
        student.move();

        if(professors.size() != 0){
            goProfessorsGo();
        }

        if (random.nextInt(5) == 1 && maxProfessor >= professors.size()){
            addProfessor();
        }
    }

    private void goProfessorsGo() {
        for (Professor professor : professors) {
            if(professor.getPosition().equals( student.getZachetka().getPosition() ) ){
                professor.setZachet( true );
            }
            if(!professor.isZachet()){
                professor.setDir( goProfessors() );
                professor.move();
            }
        }
    }

    private Direction goProfessors() {
        int n = random.nextInt( 4 );

        if(n == 0) return Direction.LEFT;
        else if(n == 1) return Direction.RIGHT;
        else if(n == 2) return Direction.UP;
        else if(n == 3 ) return Direction.DOWN;
        else return Direction.STAND;
    }

    private void addProfessor() {
        Position p;
        do {
            p = new Position(random.nextInt(width), random.nextInt(height));
        } while (!isValid(p));
        professors.add(new Professor(p.getX(), p.getY(), random.nextInt( 8 )  + 2, random.nextInt( 6 ) + 1, this));
    }

    private boolean isValid(Position p) {
        boolean b = professors.stream().map(Professor::getPosition).noneMatch(pos -> pos.equals(p));
        return !student.getPosition().equals(p) && b;
    }


    public List<Professor> getProfessors() {
        return professors;
    }
}