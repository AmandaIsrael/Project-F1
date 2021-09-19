package listaPista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pista.pistaDAO;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

import java.net.URL;
import java.util.ResourceBundle;

public class addPistaController implements Initializable, controlledScreen {

    screensController myController;
    @FXML
    TextField nome;
    @FXML
    TextField pais;
    @FXML
    TextField cidade;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToListaPista(ActionEvent event){
        myController.setScreen(Main.screen5ID);
    }

    @FXML
    private void Adicionar(ActionEvent event){

        pistaDAO.inserirPista(nome.getText(), pais.getText(), cidade.getText());
        myController.setScreen(Main.screen5ID);
        listaPistasController.refreshTable();

    }
}
