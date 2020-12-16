package sample;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public abstract class Obstacle extends GameElements{
    Group group;
    double rotateAngle = 0;
    double rotateSpeed;
    Star star;
    colorSwitch cs;
    public void disappear(AnchorPane goHere, AnchorPane fromHere) {
        fromHere.getChildren().remove(this.group);
        goHere.getChildren().add(this.group);
    }

    public abstract void stopRotate();
}
