import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

public class ProjectF1 extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listaPista/ListaPistas.fxml"));
        Parent root = fxmlLoader.load();
        Scene screen = new Scene(root);

        primaryStage.setTitle("F1");
        primaryStage.setScene(screen);
        primaryStage.show();
    }
}