package pista;

public class pistaMainTableController implements Initializable /*, controlledScreen */{
    @FXML private TableView<pistaMainTableModel> tableView;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn3;
    private static Connection con = ConnectPostgre.ConnectDatabase();
    private String nomePista;

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
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pais"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));

        ObservableList<pistaMainTableModel> pistas = FXCollections.observableArrayList(readListaPistas());

    public String getNomePista() {
        return this.nomePista;
    }

    public void setNomePista(String nomePista) {
        this.nomePista = nomePista;
    }

        tableView.setItems(pistas);
    }

    public static ArrayList<pistaMainTableModel> readListaPistas(){
        
        ArrayList<pistaMainTableModel> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pista WHERE nomPista = "+nomePista;

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomepista"));
                SimpleStringProperty pais = new SimpleStringProperty(resultado.getString("paispista"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("cidadepista"));

                pistaMainTableModel pista = new pistaMainTableModel(nome, pais, cidade);

                pistas.add(pista);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return pistas;
    }
}
