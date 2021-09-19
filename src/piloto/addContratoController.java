package piloto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

import java.net.URL;
import java.util.ResourceBundle;

public class addContratoController implements Initializable, controlledScreen {

    screensController myController;

    @FXML
    TextField equipe;
    @FXML
    TextField anoInicial;
    @FXML
    TextField anoFinal;
    @FXML
    TextField salario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToPiloto(ActionEvent event){
        myController.setScreen(Main.screen8ID);
    }

    @FXML
    private void Adicionar(ActionEvent event){

        pilotoDAO.inserirContrato(pilotoMainTableController.statictabelaPilotoAtual.get(0).getNome(), pilotoMainTableController.statictabelaPilotoAtual.get(0).getSobrenome(), equipe.getText(), anoInicial.getText(), anoFinal.getText(), salario.getText());
        myController.setScreen(Main.screen8ID);
        pilotoMainTableController.refreshTabelaContrato();
    }
}
