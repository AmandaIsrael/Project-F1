package listaPilotos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import piloto.pilotoDAO;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

import java.net.URL;
import java.util.ResourceBundle;

public class addPilotoController implements Initializable, controlledScreen {

    screensController myController;

    @FXML
    TextField nome;
    @FXML
    TextField sobrenome;
    @FXML
    TextField numero;
    @FXML
    TextField abrev;
    @FXML
    TextField nascimento;
    @FXML
    TextField cidade;
    @FXML
    TextField nacionalidade;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToListaPilotos(ActionEvent event){
        myController.setScreen(Main.screen3ID);
    }

    @FXML
    private void Adicionar(ActionEvent event){

        pilotoDAO.inserirPiloto(nome.getText(), sobrenome.getText(), numero.getText(), abrev.getText(), nascimento.getText(), cidade.getText(), nacionalidade.getText());
        myController.setScreen(Main.screen3ID);
        listaPilotosController.refreshTable();

    }
}
