package listaEquipes;

public class listaEquipesController {
    @FXML private TableView<listaEquipesModel> tableView;
    @FXML private TableColumn<listaEquipesModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<listaEquipesModel, SimpleStringProperty> tableColumn2;
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
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));

        ObservableList<listaEquipesModel> equipes = FXCollections.observableArrayList(readListaEquipes());

        tableView.setItems(equipes);
    }

    public static ArrayList<listaEquipesModel> readListaEquipes(){
        
        ArrayList<listaEquipesModel> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipe";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("equipeNome"));
                SimpleStringProperty pais = new SimpleStringProperty(resultado.getString("equipeNacionalidade"));

                listaEquipesModel equipe = new listaEquipesModel(nome, nacionalidade);

                equipes.add(equipe);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return equipes;
    }
}
