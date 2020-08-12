package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameView {
    private GameField field;
    private Canvas canvas;
    private Text score;
    private List<Image> images = new ArrayList<>();


    private GameView(GameField field, Canvas canvas, Text score) {
        this.field = field;
        this.canvas = canvas;
        this.score = score;
    }

    public void add(String fileName){
        images.add( getImage( fileName ) );
    }

    private Image getImage(String fileName) {
        InputStream in = getClass().getResourceAsStream(fileName);
        return new Image( in );
    }

    public static GameView create(GameField field, Canvas canvas, Text score) {
        GameView view = new GameView(field, canvas, score);
        return view;
    }

    public void draw() {

        GraphicsContext g2 = canvas.getGraphicsContext2D();

        g2.setFill(Color.WHITESMOKE);
        g2.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        g2.setStroke(Color.BLUEVIOLET);
        double cellWidth = canvas.getWidth() / (double) field.getWidth();
        double cellHeight = canvas.getHeight() / (double) field.getHeight();
        double cellSize = Double.min(cellHeight, cellWidth);


        for (int i = 0; i <= field.getWidth(); i++) {
            g2.strokeLine(i*cellSize, 0, i*cellSize, field.getHeight()*cellSize);
        }
        for (int i = 0; i <=field.getHeight() ; i++) {
            g2.strokeLine(0, i*cellSize, field.getWidth()*cellSize, i*cellSize);
        }
        score.setText( "Score: " + field.getStudent().getMark() );

        for (int i = 0; i < field.getProfessors().size() ; i++) {
            int x = field.getProfessors().get( i ).getPosition().getX();
            int y = field.getProfessors().get( i ).getPosition().getY();
            g2.drawImage( images.get( field.getProfessors().get( i ).getNum()), x*cellSize+2, y*cellSize+2, cellSize-4, cellSize-4 );

        }

        int x = field.getStudent().getX();
        int y = field.getStudent().getY();
        g2.drawImage( images.get( 0 ), x*cellSize+2, y*cellSize+2, cellSize-4, cellSize-4);

        field.getStudent().pickUpZachetku();
        field.getStudent().catchProffesor();

        if(field.getStudent().getZachetka().isThrowed()){
            x = field.getStudent().getZachetka().getPosition().getX();
            y = field.getStudent().getZachetka().getPosition().getY();
            g2.setGlobalAlpha( 0.5 );
            g2.drawImage( images.get( 1 ), x*cellSize + 4, y*cellSize + 4, cellSize - 8 , cellSize -8);
        }
        g2.setGlobalAlpha( 1 );

    }
}