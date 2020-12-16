package sample;

import javafx.scene.layout.AnchorPane;

public abstract class GameElements {
    double y;
    double x = 680;
    AnchorPane myRoot;

    public abstract void disappear(AnchorPane goHere, AnchorPane fromHere);
    public abstract void move(double delta, AnchorPane GamePlayRoot);
}
