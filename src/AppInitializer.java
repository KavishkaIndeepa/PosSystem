import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = this.getClass().getResource("/lk/ijse/riceMil/view/WelcomePage.fxml");
        Parent window = FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.setTitle("");
//        primaryStage.setMaxHeight(454);
//        primaryStage.setMaxWidth(684);


        primaryStage.show();
    }
}
