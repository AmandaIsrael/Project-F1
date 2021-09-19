package listaEquipes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;
import javafx.scene.control.TextField;
import equipe.equipeDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class addEquipeController implements Initializable, controlledScreen {

    screensController myController;

    @FXML
    TextField nome;
    @FXML
    TextField cidade;
    @FXML
    TextField nacionalidade;
    @FXML
    TextField motorAtual;
    @FXML
    TextField anoInicioMotorAtual;
    @FXML
    TextField nacionalidadeMotorAtual;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToListaEquipe(ActionEvent event){
        myController.setScreen(Main.screen4ID);
    }

    @FXML
    private void Adicionar(ActionEvent event){

        equipeDAO.inserirEquipe(nome.getText(), cidade.getText(), nacionalidade.getText(), motorAtual.getText(), anoInicioMotorAtual.getText(), nacionalidadeMotorAtual.getText());
        myController.setScreen(Main.screen4ID);
        listaEquipesController.refreshTable();
    }
}
