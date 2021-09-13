package equipe;

public class equipeLiderController {
    @FXML private TableView<equipeLiderModel> tableView;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn3;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn4;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn5;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn6;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn7;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn8;
    private String equipeNome;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

    /*
    @Override
    public void setScreenParent(screensController screenPage) {}
    */

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tableColumn6.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        tableColumn7.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        tableColumn8.setCellValueFactory(new PropertyValueFactory<>("anoFim"));

        ObservableList<equipeLiderModel> lideres = FXCollections.observableArrayList(readListaLideres());

        tableView.setItems(lideres);
    }

    public static ArrayList<equipeLiderModel> readListaLideres(){
        
        ArrayList<equipeLiderModel> lideres = new ArrayList<>();
        String sql = "SELECT liderNome, liderSobrenome, liderNacionalidade, liderCidade, liderNacimento, cargo, lideradaAnoInicio, lideradaAnoFim FROM lider, liderada, liderRegistro, equipeRegistro WHERE lideradaLiderID = liderID AND liderRegistroNome = liderNome AND liderRegistroSobrenome = liderSobrenome AND lideradaEquipeID=equipeID AND equipeRegistroNome="+equipeNome;

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("liderNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("liderSobrenome"));
                SimpleStringProperty cargo = new SimpleStringProperty(resultado.getString("cargo"));
                SimpleStringProperty nascimento = new SimpleStringProperty(resultado.getString("liderNascimento"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("liderCidade "));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("liderNacionalidade"));
                SimpleStringProperty anoInicio = new SimpleStringProperty(resultado.getString("lideradaAnoInicio"));
                SimpleStringProperty anoFim = new SimpleStringProperty(resultado.getString("lideradaAnoFim"));

                equipeLiderModel lider = new equipeLiderModel(nome, sobrenome, cargo, nascimento, cidade, nacionalidade, anoInicio, anoFim);

                lideres.add(lider);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return lideres;
    }
}
