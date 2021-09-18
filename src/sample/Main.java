package sample;

import javafx.application.Application;
import javafx.scene.Group;
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
    public static String screen6ID = "Pista";
    public static String screen6File = "/pista/pista.fxml";
    public static String screen7ID = "Equipe";
    public static String screen7File = "/equipe/equipe.fxml";
    public static String screen8ID = "Piloto";
    public static String screen8File = "/piloto/piloto.fxml";
    public static String screen9ID = "WCC";
    public static String screen9File = "/WCC/WCC.fxml";
    public static String screen10ID = "WDC";
    public static String screen10File = "/WDC/WDC.fxml";
    public static String screen11ID = "Resumo Grand Prix";
    public static String screen11File = "/grandPrix/grandPrixResumo/grandPrixResumo.fxml";
    public static String screen12ID = "Grand Prix Corrida";
    public static String screen12File = "/grandPrix/grandPrixCorrida/grandPrixCorrida.fxml";
    public static String screen13ID = "Grand Prix Qualificatório";
    public static String screen13File = "/grandPrix/grandPrixQuali/grandPrixQuali.fxml";
    public static String screen14ID = "addTracado";
    public static String screen14File = "/pista/addTracado.fxml";
    public static String screen15ID = "addPista";
    public static String screen15File = "/listaPista/addPista.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        screensController mainContainer = new screensController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
        mainContainer.loadScreen(Main.screen4ID, Main.screen4File);
        mainContainer.loadScreen(Main.screen5ID, Main.screen5File);
        mainContainer.loadScreen(Main.screen6ID, Main.screen6File);
        mainContainer.loadScreen(Main.screen7ID, Main.screen7File);
        mainContainer.loadScreen(Main.screen8ID, Main.screen8File);
        mainContainer.loadScreen(Main.screen9ID, Main.screen9File);
        mainContainer.loadScreen(Main.screen10ID, Main.screen10File);
        mainContainer.loadScreen(Main.screen11ID, Main.screen11File);
        mainContainer.loadScreen(Main.screen12ID, Main.screen12File);
        mainContainer.loadScreen(Main.screen13ID, Main.screen13File);
        mainContainer.loadScreen(Main.screen14ID, Main.screen14File);
        mainContainer.loadScreen(Main.screen15ID, Main.screen15File);

        mainContainer.setScreen(Main.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);

        primaryStage.setTitle("Project F1");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }



}
