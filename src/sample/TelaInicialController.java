package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import pista.pistaMainTableController;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

public class TelaInicialController implements Initializable, controlledScreen {

    screensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToAno(ActionEvent event){
        myController.setScreen(Main.screen2ID);
    }

    @FXML
    private void goToPilotos(ActionEvent event){
        myController.setScreen(Main.screen3ID);
    }

    @FXML
    private void goToEquipes(ActionEvent event){
        myController.setScreen(Main.screen4ID);
    }

    @FXML
    private void goToPistas(ActionEvent event) throws IOException {
        myController.setScreen(Main.screen5ID);
        /*
        URL url = getClass().getResource(Main.screen6File);
        FXMLLoader myLoader = new FXMLLoader(url);
        myLoader.load();
        //controlledScreen myScreenController = myLoader.getController();
        pistaMainTableController pistaController = myLoader.getController();
        myController.setScreen(Main.screen6ID);
        System.out.println(pistaController.getNomePista());
        pistaController.populateTable("Hungaroring");
        System.out.println(pistaController.getNomePista());

         */

    }

}
