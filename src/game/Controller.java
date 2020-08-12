package game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class Controller {
    public Text score;
    @FXML private Pane pane;
    @FXML private Canvas canvas;

    private GameField field;
    private GameView view;

    private Timeline timeline;

    @FXML
    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());

        field = GameField.create(28,15);

        view = GameView.create(field, canvas, score);

        addImages();

        timeline = new Timeline(new KeyFrame(Duration.millis(300), e->processTick()));
        timeline.setCycleCount(Animation.INDEFINITE);

        score.setText( "Score: 0" );

    }

    private void addImages() {
        for (int i = 0; i <= 9 ; i++) {
            String t = Integer.toString( i );
            view.add( "imagesIUST/"+ t +".jpg" ); //Кафедра ИУСТ
         //   view.add( "imagesPZAS/"+ t +".jpg" ); //Кафедра ПЗАС
        }
    }

    private void processTick() {
        field.tick();
        draw();
    }

    private void draw() {
        if (view!=null) {
            view.draw();
        }
    }

    public void processKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT: field.getStudent().setDir(Direction.LEFT);
                break;
            case RIGHT: field.getStudent().setDir(Direction.RIGHT);
                break;
            case UP: field.getStudent().setDir(Direction.UP);
                break;
            case DOWN: field.getStudent().setDir(Direction.DOWN);
                break;
            case SPACE : field.getStudent().setDir(Direction.STAND);
                break;
            case T: field.getStudent().throwZachetku();
        }
    }

    public void start(ActionEvent actionEvent) {
        timeline.play();
    }

    public void exit() {
        Platform.exit();
    }

    public void pause(ActionEvent actionEvent) {
        timeline.stop();
    }

}