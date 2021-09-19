package pista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import listaPista.listaPistasController;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;
import java.net.URL;
import java.util.ResourceBundle;

public class addTracadoController implements Initializable, controlledScreen {

    screensController myController;
    @FXML
    TextField anoAlteracao;
    @FXML
    TextField distancia;
    @FXML
    TextField numeroVoltas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToPista(ActionEvent event){
        myController.setScreen(Main.screen6ID);
    }

    @FXML
    private void Adicionar(ActionEvent event){

        pistaDAO.inserirTracado(Integer.parseInt(anoAlteracao.getText()), Integer.parseInt(distancia.getText()), Integer.parseInt(numeroVoltas.getText()), pistaMainTableController.statictabelaPistaAtual.get(0).getNome());
        myController.setScreen(Main.screen6ID);
        pistaMainTableController.refreshTabelaTracado();
    }

}
