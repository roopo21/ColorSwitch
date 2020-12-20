package sample;

import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;

public abstract class GameElements {
    double y;
    double x = 680;
    AnchorPane GamePlayRoot;
    AnchorPane invisiRoot;

    public abstract void disappear() throws FileNotFoundException;
    public abstract void move(double delta, AnchorPane GamePlayRoot) throws FileNotFoundException ;
}
