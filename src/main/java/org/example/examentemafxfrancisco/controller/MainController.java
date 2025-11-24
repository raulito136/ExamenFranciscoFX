package org.example.examentemafxfrancisco.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.examentemafxfrancisco.model.Usuario;
import org.example.examentemafxfrancisco.util.JavaFXUtil;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Clase controlador de la ventana principal
 * @author Raúl López Palomo
 */

public class MainController implements Initializable {

    @FXML
    private TableColumn<Usuario,String> tcRol;
    @FXML
    private Spinner<Integer> snVersion;
    @FXML
    private TableColumn<Usuario,String> tcCorreo;
    @FXML
    private TableColumn<Usuario,String> tcFecha;
    @FXML
    private TextField tfCorreo;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableColumn<Usuario,String> tcPlataforma;
    @FXML
    private ComboBox<String> cbPlataforma;
    @FXML
    private CheckBox ckAdministrador;
    @FXML
    private TableView<Usuario> tabla;
    @FXML
    private Button btnAnadir;
    @FXML
    private TableColumn<Usuario,String> tcVersion;

    /**
     * Metodo que se se inicia al abrir la ventana
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcCorreo.setCellValueFactory(row->{
            return new SimpleStringProperty(row.getValue().getCorreo());
        });
        tcPlataforma.setCellValueFactory(row->{
            return new SimpleStringProperty(row.getValue().getPlataforma());
        });
        tcRol.setCellValueFactory(row->{
            return new SimpleStringProperty(row.getValue().getRol());
        });
        tcVersion.setCellValueFactory(row->{
            return new SimpleStringProperty(row.getValue().getVersion().toString());
        });
        tcFecha.setCellValueFactory(row->{
            return new SimpleStringProperty(row.getValue().getFecha());
        });
        cbPlataforma.getItems().addAll("Windows","Linux","MacOS");
        SpinnerValueFactory<Integer> spinnerValueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,1);
        snVersion.setValueFactory(spinnerValueFactory);
        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue)->{
            JavaFXUtil.showModal(Alert.AlertType.INFORMATION,"Informacion Usuario","Informacion del Usuario","Correo: "+newValue.getCorreo()+"\nPlataforma: "+newValue.getPlataforma()+"\nRol: "+newValue.getRol()+"\nVersion: "+newValue.getVersion()+"\nFecha y hora: "+newValue.getFecha());
        });
    }

    /**
     * Metodo para añadir un usuario
     * @param actionEvent
     */
    @FXML
    public void anadirUsuario(ActionEvent actionEvent) {
        Usuario usuario=new Usuario();
        if (tfCorreo.getText().isEmpty() || cbPlataforma.getValue()==null || snVersion.getValue()==null){
            JavaFXUtil.showModal(Alert.AlertType.ERROR,"ERROR","Error: Faltan Datos","Necesita rellenar todos los campos");
        }else{
            usuario.setCorreo(tfCorreo.getText());
            usuario.setPlataforma(cbPlataforma.getValue());
            if (ckAdministrador.isSelected()){
                usuario.setRol("Administrador");
            }else{
                usuario.setRol("Usuario");
            }
            usuario.setVersion(snVersion.getValue());
            usuario.setFecha(LocalDateTime.now().toString());
            tabla.getItems().add(usuario);
            tfCorreo.clear();
            ckAdministrador.setSelected(false);
            snVersion.getValueFactory().setValue(1);
            cbPlataforma.setValue(cbPlataforma.getItems().getFirst());
        }
    }

    /**
     * Metodo para borrar todos los datos de la tabla
     * @param actionEvent
     */
    @FXML
    public void borrarDatos(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Borrado");
        alert.setHeaderText("¿Estas seguro?");
        alert.setContentText("¿Quieres borrar todos los datos de la tabla?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                tabla.getItems().clear();
            }
        });
    }


}