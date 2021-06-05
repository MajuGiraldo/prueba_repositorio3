package sample.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.PersonaException;
import sample.logic.entities.LeaderTypeEnum;
import sample.logic.entities.Persona;
import sample.logic.services.IPersonaServices;
import sample.logic.services.impl.PersonaServices;

import java.time.LocalDate;

public class BasicScene extends Application {

    private Scene scene;
    private TableView<Persona> personasTable;
    private TextField nameInput;
    private TextField lastNameInput;
    private TextField deathDateInput;
    private TextField municipalityInput;
    private TextField departmentInput;
    private TextField leaderTypeInput;
    private Button addPersona;
    private Button deletePersona;
    private Button updatePersona;

    private IPersonaServices personaServices;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setUp();
        behavior();

        primaryStage.setTitle("Lideres Sociales y violencia en Colombia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void behavior(){
        this.personaServices = new PersonaServices();
        try {
            this.personaServices.insert(new Persona("Ricardo", "Cortes Rozo", "2021-01-02", "Bogotá", "Bogotá", "Civico"));
            this.personaServices.insert(new Persona("Carlos Erlid", "González Cortés", "2021-01-10", "Buga", "Valle del Cauca", "Civico"));
            this.personaServices.insert(new Persona("Fermiliano", "Meneses", "2021-01-15", "Argelia", "Cauca", "Civico"));
            this.personaServices.insert(new Persona("William Antonio", "Rodriguez", "2021-01-16", "Cucúta", "Norte de Santander", "Civico"));
            this.personaServices.insert(new Persona("Linda", "Díaz Romero", "2021-01-19", "Cáceres", "Antioquia", "Civico"));
            this.personaServices.insert(new Persona("José", "Abadía Parra", "2021-01-20", "Pereira", "Risaralda", "Civico"));
            this.personaServices.insert(new Persona("Julian Sneider", "Muñoz", "2021-01-23", "Cali", "Valle del Cauca", "Civico"));
            this.personaServices.insert(new Persona("Giovanis", "Carranza Castillo", "2021-01-27", "Valledupar", "Cesar", "Civico"));
            this.personaServices.insert(new Persona("Yecid Andres", "Bolaño", "2021-02-08", "Barranquilla", "Atlántico", "Civico"));
            this.personaServices.insert(new Persona("José Éver", "Álvarez", "2021-02-25", "Rio de Oro", "Cesar", "Civico"));
            this.personaServices.insert(new Persona("Luis", "Hermídes Álvarez", "2021-03-03", "Río de Oro", "Cesar", "Civico"));
            this.personaServices.insert(new Persona("Karina Paola", "Cuesta Ortega", "2021-03-25", "Tierralta", "Córdoba", "Civico"));
            this.personaServices.insert(new Persona("Germán", "Medina Triviño", "2021-03-30", "Florencia", "Caquetá", "Civico"));
            this.personaServices.insert(new Persona("Alvaro Farid", "Rodríguez", "2021-04-13", "Algeciras", "Huila", "Civico"));
            this.personaServices.insert(new Persona("Luis Octavio", "Gutiérrez Montes", "2021-04-14", "Caucasia", "Antioquia", "Civico"));
            this.personaServices.insert(new Persona("Francisco", "Giacometto Gómez", "2021-04-17", "Santa Marta", "Magdalena", "Civico"));
            this.personaServices.insert(new Persona("Yovani", "Laguna", "2021-05-01", "San Miguel", "Putumayo", "Civico"));
            this.personaServices.insert(new Persona("Juan David", "García Naranjo", "2021-05-02", "San Luís", "Antioquia", "Civico"));
            this.personaServices.insert(new Persona("Cecilia", "Valderrama", "2021-05-11", "Chaparral", "Tolima", "Civico"));
            this.personaServices.insert(new Persona("Armando", "Álvarez", "2021-05-24", "Cali", "Valle del Cauca", "Civico"));

            this.personaServices.insert(new Persona("Fredman", "Herazo Padilla", "2021-01-15", "La Apartada", "Córdoba", "Afrodescendiente"));
            this.personaServices.insert(new Persona("José", "Riascos", "2021-04-08", "Nuquí", "Chocó", "Afrodescendiente"));
            this.personaServices.insert(new Persona("Margarito", "Salas", "2021-04-08", "Nuquí", "Chocó", "Afrodescendiente"));

        } catch (PersonaException e) {
            e.printStackTrace();
        }

        personasTable.setItems((ObservableList<Persona>) this.personaServices.getAll());


        addPersona.setOnAction(e -> {
            try {
                Persona p = new Persona(nameInput.getText(),lastNameInput.getText(),deathDateInput.getText(), municipalityInput.getText(), departmentInput.getText(), leaderTypeInput.getText());
                this.personaServices.insert(p);
                nameInput.clear();
                lastNameInput.clear();
                deathDateInput.clear();
                municipalityInput.clear();
                departmentInput.clear();
                leaderTypeInput.clear();
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
        });

        deletePersona.setOnAction(e -> {
            this.personaServices.delete(personasTable.getSelectionModel().getSelectedItems());
        });

        EventHandler<MouseEvent> eventHandler = e -> {
            Persona persona = this.personasTable.getSelectionModel().getSelectedItem();

            this.nameInput.setText(persona.getName());
            this.lastNameInput.setText(persona.getLastName());
            this.deathDateInput.setText(persona.getDeathDate().toString());
            this.municipalityInput.setText(persona.getMunicipality());
            this.departmentInput.setText(persona.getDepartment());
            this.leaderTypeInput.setText(persona.getLeaderType().toString());
        };

        personasTable.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        updatePersona.setOnAction(e -> {
            try {
                Persona personaModify = this.personasTable.getSelectionModel().getSelectedItem();
                Persona newPersona = new Persona(nameInput.getText(),lastNameInput.getText(),deathDateInput.getText(), municipalityInput.getText(), departmentInput.getText(), leaderTypeInput.getText());
                this.personaServices.update(personaModify, newPersona);

                this.personasTable.refresh();

                nameInput.clear();
                lastNameInput.clear();
                deathDateInput.clear();
                municipalityInput.clear();
                departmentInput.clear();
                leaderTypeInput.clear();

            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
        });

    }

    private void setUp(){
        setupCRUD();
        setupTable();
        setupInputs();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,0,5,0));
        hBox.setSpacing(5);
        hBox.getChildren().addAll(nameInput, lastNameInput, deathDateInput, municipalityInput, departmentInput, leaderTypeInput, addPersona, deletePersona, updatePersona);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(personasTable, hBox);

        scene = new Scene(layout, 800, 500);
    }

    private void setupCRUD() {

        addPersona = new Button();
        addPersona.setText("Add");
        addPersona.setMinWidth(72);

        deletePersona = new Button();
        deletePersona.setText("Delete");
        deletePersona.setMinWidth(71);

        updatePersona = new Button("update");
        updatePersona.setMinWidth(70);
    }

    private void setupInputs() {
        nameInput = new TextField();
        nameInput.setPromptText("nombre");
        nameInput.setMinWidth(70);

        lastNameInput = new TextField();
        lastNameInput.setPromptText("appelido");
        lastNameInput.setMinWidth(70);

        deathDateInput = new TextField();
        deathDateInput.setPromptText("fecha de fallecimiento");
        deathDateInput.setMinWidth(120);

        municipalityInput = new TextField();
        municipalityInput.setPromptText("municipio");
        municipalityInput.setMinWidth(70);

        departmentInput = new TextField();
        departmentInput.setPromptText("departamento");
        departmentInput.setMinWidth(70);

        leaderTypeInput = new TextField();
        leaderTypeInput.setPromptText("tipo de lider");
        leaderTypeInput.setMinWidth(70);

    }

    private void setupTable(){

        TableColumn<Persona, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Persona, String> lastNameColumn = new TableColumn<>("Apellido");
        lastNameColumn.setMaxWidth(200);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Persona, LocalDate> deathDateColumn = new TableColumn<>("Fecha de Fallecimiento");
        deathDateColumn.setMaxWidth(200);
        deathDateColumn.setCellValueFactory(new PropertyValueFactory<>("deathDate"));

        TableColumn<Persona, String> municipalityColumn = new TableColumn<>("Municipio");
        municipalityColumn.setMaxWidth(200);
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipality"));

        TableColumn<Persona, String> departmentColumn = new TableColumn<>("Departamento");
        departmentColumn.setMaxWidth(200);
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<Persona, String> leaderTypeColumn = new TableColumn<>("Tipo de Lider");
        leaderTypeColumn.setMaxWidth(200);
        leaderTypeColumn.setCellValueFactory(new PropertyValueFactory<>("leaderType"));

        personasTable = new TableView<>();
        personasTable.getColumns().addAll(nameColumn, lastNameColumn, deathDateColumn, municipalityColumn, departmentColumn, leaderTypeColumn);

    }



    public static void main(String[] args) {
        launch(args);
    }
}
