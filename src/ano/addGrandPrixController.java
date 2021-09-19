package ano;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

import java.net.URL;
import java.util.ResourceBundle;

public class addGrandPrixController implements Initializable, controlledScreen {

    screensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToAno(ActionEvent event){
        myController.setScreen(Main.screen2ID);
    }
}
