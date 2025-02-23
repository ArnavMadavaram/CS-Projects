

/**
 * Name: Arnav Madavaram
 * Username: madaa01
 */

package lab;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Pane mainPane;

    @FXML
    private Button playButton;

    @FXML
    private Text titleText;

    @FXML
    private MenuItem normalModeMenuItem;

    @FXML
    private MenuItem extraModeMenuItem;

    private final ArrayList<RandomCircle> circles = new ArrayList<>();
    private Timeline extraModeTimeline;
    private Timeline gameCheckTimeline;
    private static final int NUM_CIRCLES = 10;
    private boolean isExtraMode = false;

    @FXML
    public void setNormalMode(ActionEvent event) {
        isExtraMode = false;
    }

    @FXML
    public void setExtraMode(ActionEvent event) {
        isExtraMode = true;
    }

    @FXML
    public void initialize() {
        titleText.setText("Welcome to Circle Catcher!");
        playButton.setText("Play");
    }

    @FXML
    public void startGame(ActionEvent event) {
        mainPane.getChildren().clear();
        circles.clear();
        playButton.setVisible(false);
        titleText.setText("Catch the circles!");

        if (isExtraMode) {
            startExtraMode();
        } else {
            startNormalMode();
        }
        if (!isExtraMode) {
            startGameCheckTimer();
        }
    }

    private void startNormalMode() {
        for (int i = 0; i < NUM_CIRCLES; i++) {
            RandomCircle circle = new RandomCircle(mainPane.getWidth(), mainPane.getHeight(), false);
            circles.add(circle);
            mainPane.getChildren().add(circle);
        }
    }

    private void startExtraMode() {
        extraModeTimeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            RandomCircle circle = new RandomCircle(mainPane.getWidth(), mainPane.getHeight(), true);
            circles.add(circle);
            mainPane.getChildren().add(circle);
        }));
        extraModeTimeline.setCycleCount(Timeline.INDEFINITE);
        extraModeTimeline.play();
    }

    private void stopExtraMode() {
        if (extraModeTimeline != null) {
            extraModeTimeline.stop();
        }
    }

    private void startGameCheckTimer() {
        gameCheckTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> checkGameStatus()));
        gameCheckTimeline.setCycleCount(Timeline.INDEFINITE);
        gameCheckTimeline.play();
    }

    private void checkGameStatus() {
        if (!circles.isEmpty() && circles.stream().allMatch(RandomCircle::isCaptured)) {
            endGame();
        }
    }

    private void endGame() {
        if (gameCheckTimeline != null) {
            gameCheckTimeline.stop();
        }
        stopExtraMode();
        titleText.setText("You Win! Try Again?");
        playButton.setText("Try Again");
        playButton.setVisible(true);
    }
}






