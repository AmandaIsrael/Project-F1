package equipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

import java.net.URL;
import java.util.ResourceBundle;

public class addNomeAntigoController implements Initializable, controlledScreen {

    screensController myController;
    @FXML
    TextField nome;
    @FXML
    TextField anoInicial;
    @FXML
    TextField anoFinal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToEquipe(ActionEvent event){
        myController.setScreen(Main.screen7ID);
    }

    @FXML
    private void Adicionar(ActionEvent event){

        equipeDAO.inserirNomeAntigo(nome.getText(), equipeMainTableController.statictabelaEquipeAtual.get(0).getNome(), anoInicial.getText(), anoFinal.getText());
        myController.setScreen(Main.screen7ID);
        equipeMainTableController.refreshTabelaNomeAntigo();
    }
}
