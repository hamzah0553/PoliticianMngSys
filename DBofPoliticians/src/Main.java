import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.Controller;

import java.awt.*;


public class Main extends Application {
    static Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

    @Override
    public void start(Stage primaryStage) throws Exception{
        screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.getWidth() / 1.3, screenSize.getHeight() / 1.3);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/frontPage.fxml"));
        Controller controller = new Controller(primaryStage, screenSize);
        loader.setController(controller);
        Parent root = loader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
