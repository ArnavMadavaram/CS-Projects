package lab;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.Random;

public class RandomCircle extends Circle {
    private boolean captured = false;

    public RandomCircle(double paneWidth, double paneHeight, boolean isExtraMode) {
        super();
        Random random = new Random();
        setRadius(20 + random.nextInt(30));
        setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        double startX = random.nextDouble() * (paneWidth - 2 * getRadius()) + getRadius();
        double startY = random.nextDouble() * (paneHeight - 2 * getRadius()) + getRadius();
        setCenterX(startX);
        setCenterY(startY);
        double endX = random.nextDouble() * (paneWidth - 2 * getRadius()) + getRadius();
        double endY = random.nextDouble() * (paneHeight - 2 * getRadius()) + getRadius();
        Line path = new Line(startX, startY, endX, endY);
        PathTransition animation = new PathTransition();
        animation.setNode(this);
        animation.setPath(path);
        animation.setDuration(Duration.seconds(2));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setAutoReverse(true);
        if (isExtraMode) {
            animation.setOnFinished(e -> {
                double newEndX = random.nextDouble() * (paneWidth - 2 * getRadius()) + getRadius();
                double newEndY = random.nextDouble() * (paneHeight - 2 * getRadius()) + getRadius();
                path.setEndX(newEndX);
                path.setEndY(newEndY);
                animation.playFromStart();
            });
        }
        animation.play();
        setOnMouseClicked(event -> {
            if (!captured) {
                animation.stop();
                setCenterX(paneWidth / 2);
                setCenterY(paneHeight / 2);
                captured = true;
            }
        });
    }

    public boolean isCaptured() {
        return captured;
    }
}
