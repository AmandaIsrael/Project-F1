package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.utils.screensController;

public class Main extends Application {

    public static String screen1ID = "TelaInicial";
    public static String screen1File = "/sample/TelaInicial.fxml";
    public static String screen2ID = "Ano";
    public static String screen2File = "/ano/Ano.fxml";
    public static String screen3ID = "Pilotos";
    public static String screen3File = "/listaPilotos/ListaPilotos.fxml";
    public static String screen4ID = "Equipes";
    public static String screen4File = "/listaEquipes/ListaEquipes.fxml";
    public static String screen5ID = "Pistas";
    public static String screen5File = "/listaPista/ListaPistas.fxml";


    @Override
    public void start(Stage primaryStage) throws Exception{
        screensController mainContainer = new screensController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
        mainContainer.loadScreen(Main.screen4ID, Main.screen4File);
        mainContainer.loadScreen(Main.screen5ID, Main.screen5File);

        mainContainer.setScreen(Main.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);

        primaryStage.setTitle("Project F1");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }



}
