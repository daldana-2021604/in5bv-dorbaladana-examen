/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.kinal.in5bv.dorbalaldana.examen;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.kinal.in5bv.dorbalaldana.models.dao.MateriaDaoImpl;
import org.kinal.in5bv.dorbalaldana.models.domain.Materia;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class MateriaController implements Initializable {

    private final String PAQUETE_IMAGES = "org/kinal/in5bv/dorbalaldana/examen/images/";

    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView tblMaterias;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colMateria;
    @FXML
    private TableColumn colCiclo;
    @FXML
    private TableColumn colHorarioInicio;
    @FXML
    private TableColumn colHorarioFinal;
    @FXML
    private TableColumn colCatedratico;
    @FXML
    private TableColumn colSalon;
    @FXML
    private TableColumn colMaximo;
    @FXML
    private TableColumn colMinimo;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCatedratico;
    @FXML
    private JFXTimePicker tmpHorarioInicio;
    @FXML
    private JFXTimePicker tmpHorarioFinal;
    @FXML
    private TextField txtSalon;
    @FXML
    private Spinner spnMaximo;
    private SpinnerValueFactory<Integer> valueFactoyMaximo;
    @FXML
    private Spinner spnMinimo;
    private SpinnerValueFactory<Integer> valueFactoyMinimo;
    @FXML
    private TextField txtCantidadDatos;
    @FXML
    private Spinner spnCiclo;
    private SpinnerValueFactory<Integer> valueFactoyCiclo;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private ImageView imgActualizar;
    @FXML
    private ImageView imgEliminar;

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR;
    }
    private Operacion operacion = Operacion.NINGUNO;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();

        valueFactoyCiclo = new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, 2022);
        spnCiclo.setValueFactory(valueFactoyCiclo);

        valueFactoyMaximo = new SpinnerValueFactory.IntegerSpinnerValueFactory(11, 60, 20);
        spnMaximo.setValueFactory(valueFactoyMaximo);

        valueFactoyMinimo = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5);
        spnMinimo.setValueFactory(valueFactoyMinimo);
    }

    @FXML
    private void clicNuevo(ActionEvent event) {
        switch (operacion) {
            case NINGUNO:
                habilitarCampos();
                limpiarCampos();
                tblMaterias.setDisable(true);
                txtId.setDisable(true);
                txtId.setEditable(false);

                btnNuevo.setText("Guardar");
                imgNuevo.setImage(new Image(PAQUETE_IMAGES + "confirmar.png/"));

                btnModificar.setText("Cancelar");
                imgActualizar.setImage(new Image(PAQUETE_IMAGES + "cancelar.png/"));

                //Desabilitar
                btnEliminar.setDisable(true);
                //Ocultar
                btnEliminar.setVisible(false);

                //Desabilitar
                operacion = Operacion.GUARDAR;
                break;

            case GUARDAR:
                if (camposObligatoriosVacios() == false) {

                    Materia materia = new Materia();
                    materia.setNombreMateria(txtNombre.getText());
                    materia.setCiclo((int) spnCiclo.getValue());
                    materia.setHorarioInicio(tmpHorarioInicio.getValue());
                    materia.setHorarioFinal(tmpHorarioFinal.getValue());
                    materia.setCatedratico(txtCatedratico.getText());
                    materia.setSalon(txtSalon.getText());
                    materia.setCupoMaximo((int) spnMaximo.getValue());
                    materia.setCupoMinimo((int) spnMinimo.getValue());
                    System.out.println(materia);
                    int registrosInsertados = new MateriaDaoImpl().add(materia);

                    cargarDatos();
                    limpiarCampos();
                    deshabilitarCampos();
                    btnNuevo.setText("Nuevo");
                    imgNuevo.setImage(new Image(PAQUETE_IMAGES + "add.png/"));

                    btnModificar.setText("Modificar");
                    imgActualizar.setImage(new Image(PAQUETE_IMAGES + "edit.png/"));

                    btnEliminar.setVisible(true);
                    btnEliminar.setDisable(false);

                    tblMaterias.setDisable(false);
                    operacion = Operacion.NINGUNO;
                }
                break;
        }

    }

    @FXML
    private void clicModificar(ActionEvent event) {
        switch (operacion) {
            case NINGUNO:
                if (existeElementoSeleccionado()) {
                    habilitarCampos();

                    btnNuevo.setDisable(true);
                    btnNuevo.setVisible(false);
                    txtId.setEditable(false);
                    txtId.setDisable(true);

                    btnModificar.setText("Guardar");
                    imgActualizar.setImage(new Image(PAQUETE_IMAGES + "confirmar.png/"));

                    btnEliminar.setText("Cancelar");
                    imgEliminar.setImage(new Image(PAQUETE_IMAGES + "cancelar.png/"));

                    operacion = Operacion.ACTUALIZAR;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "Icono.png"));
                    alert.setTitle("Control Materia");
                    alert.setHeaderText(null);
                    alert.setContentText("Antes de continuar, selecciona un registro");
                    alert.show();
                }

                break;

            case GUARDAR: // cancelar inserción

                tblMaterias.setDisable(false);
                btnNuevo.setText("Nuevo");
                imgNuevo.setImage(new Image(PAQUETE_IMAGES + "add.png/"));

                btnModificar.setText("Modificar");
                imgActualizar.setImage(new Image(PAQUETE_IMAGES + "edit.png/"));

                btnEliminar.setVisible(true);
                btnEliminar.setDisable(false);

                limpiarCampos();
                deshabilitarCampos();

                operacion = Operacion.NINGUNO;
                break;

            case ACTUALIZAR:
                if (camposObligatoriosVacios() == false) {
                    Materia materia = new Materia();
                    materia.setNombreMateria(txtNombre.getText());
                    materia.setCiclo((int) spnCiclo.getValue());
                    materia.setHorarioInicio(tmpHorarioInicio.getValue());
                    materia.setHorarioFinal(tmpHorarioFinal.getValue());
                    materia.setCatedratico(txtCatedratico.getText());
                    materia.setSalon(txtSalon.getText());
                    materia.setCupoMaximo((int) spnMaximo.getValue());
                    materia.setCupoMinimo((int) spnMinimo.getValue());
                    materia.setIdMateria(Integer.parseInt(txtId.getText()));
                    System.out.println(materia);
                    int registrosActualizados = new MateriaDaoImpl().update(materia);

                    limpiarCampos();
                    deshabilitarCampos();
                    cargarDatos();
                    btnNuevo.setVisible(true);
                    btnNuevo.setDisable(false);

                    btnModificar.setText("Modificar");
                    imgActualizar.setImage(new Image(PAQUETE_IMAGES + "edit.png/"));

                    btnEliminar.setText("Eliminar");
                    imgEliminar.setImage(new Image(PAQUETE_IMAGES + "trash.png/"));

                    operacion = Operacion.NINGUNO;

                }
                break;

        }
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
        switch (operacion) {
            case NINGUNO:// Eliminar un registro
                if (existeElementoSeleccionado()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Control Materia");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Esta seguro que quiere eliminar el registro selecionado?");

                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "Icono.png"));

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get().equals(ButtonType.OK)) {
                        Materia materia = new Materia();
                        materia.setIdMateria(Integer.parseInt(txtId.getText()));
                        System.out.println(materia);
                        int registrosActualizados = new MateriaDaoImpl().delete(materia);
                        
                        limpiarCampos();
                        cargarDatos();
                        Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
                        Stage stageInformation = (Stage) alertInformation.getDialogPane().getScene().getWindow();
                        stageInformation.getIcons().add(new Image(PAQUETE_IMAGES + "Icono.png"));
                        alertInformation.setTitle("Control Materias");
                        alertInformation.setHeaderText(null);
                        alertInformation.setContentText("Se elimino el registro exitosamente");
                        alertInformation.show();

                    } else {
                        alert.close();
                        limpiarCampos();
                        tblMaterias.getSelectionModel().clearSelection();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Control Materias");
                    alert.setHeaderText(null);
                    alert.setContentText("Antes de continuar, seleccione un registro");
                    alert.show();
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "Icono.png"));
                }
                break;

            case ACTUALIZAR: // cancelar modificación
                btnNuevo.setVisible(true);
                btnNuevo.setDisable(false);

                btnModificar.setText("Modificar");
                imgActualizar.setImage(new Image(PAQUETE_IMAGES + "edit.png/"));

                btnEliminar.setText("Eliminar");
                imgEliminar.setImage(new Image(PAQUETE_IMAGES + "trash.png/"));

                limpiarCampos();
                deshabilitarCampos();

                tblMaterias.getSelectionModel().clearSelection();

                operacion = Operacion.NINGUNO;
                break;
        }
    }

    public void cargarDatos() {
        Materia materia = new Materia();
        ObservableList<Materia> listaMateria = new MateriaDaoImpl().getALL();
        tblMaterias.setItems(listaMateria);
        colId.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("idMateria"));
        colMateria.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombreMateria"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("ciclo"));
        colHorarioInicio.setCellValueFactory(new PropertyValueFactory<Materia, LocalTime>("horarioInicio"));
        colHorarioFinal.setCellValueFactory(new PropertyValueFactory<Materia, LocalTime>("horarioFinal"));
        colCatedratico.setCellValueFactory(new PropertyValueFactory<Materia, String>("catedratico"));
        colSalon.setCellValueFactory(new PropertyValueFactory<Materia, String>("salon"));
        colMaximo.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("cupoMaximo"));
        colMinimo.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("cupoMinimo"));
        materia.setCantidadDatos(listaMateria.size());
        txtCantidadDatos.setText(Integer.toString(materia.getCantidadDatos()));
    }

    @FXML
    private void seleccionarElemento() {
        if (existeElementoSeleccionado()) {
            txtId.setText(String.valueOf(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getIdMateria()));
            txtNombre.setText(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getNombreMateria());
            spnCiclo.getValueFactory().setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCiclo());
            tmpHorarioInicio.setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getHorarioInicio());
            tmpHorarioFinal.setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getHorarioFinal());
            txtCatedratico.setText(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCatedratico());
            txtSalon.setText(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getSalon());
            spnMaximo.getValueFactory().setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCupoMaximo());
            spnMinimo.getValueFactory().setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCupoMinimo());
        }
    }

    private boolean existeElementoSeleccionado() {
        return ((tblMaterias.getSelectionModel().getSelectedItem()) != null);
    }

    private void habilitarCampos() {
        txtNombre.setEditable(true);
        spnCiclo.setEditable(true);
        tmpHorarioInicio.setEditable(true);
        tmpHorarioFinal.setEditable(true);
        txtCatedratico.setEditable(true);
        txtSalon.setEditable(true);
        spnMaximo.setEditable(true);
        spnMinimo.setEditable(true);

        txtNombre.setDisable(false);
        spnCiclo.setDisable(false);
        tmpHorarioInicio.setDisable(false);
        tmpHorarioFinal.setDisable(false);
        txtCatedratico.setDisable(false);
        txtSalon.setDisable(false);
        spnMaximo.setDisable(false);
        spnMinimo.setDisable(false);
    }

    private void deshabilitarCampos() {
        txtId.setEditable(false);
        txtNombre.setEditable(false);
        spnCiclo.setEditable(false);
        txtCatedratico.setEditable(false);
        txtSalon.setEditable(false);
        spnMaximo.setEditable(false);
        spnMinimo.setEditable(false);

        txtId.setDisable(true);
        txtNombre.setDisable(true);
        spnCiclo.setDisable(true);
        tmpHorarioInicio.setDisable(true);
        tmpHorarioFinal.setDisable(true);
        txtCatedratico.setDisable(true);
        txtSalon.setDisable(true);
        spnMaximo.setDisable(true);
        spnMinimo.setDisable(true);

    }

    private void limpiarCampos() {
        /*txtCarne.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtNombre3.setText("");*/
        txtId.clear();
        txtNombre.clear();
        txtCatedratico.clear();
        txtSalon.clear();
    }

    private boolean camposObligatoriosVacios() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Control Accademico");
        alerta.setHeaderText(null);
        if (txtNombre.getText().isBlank()) {
            alerta.setContentText("El campo de nombre materia debe estar lleno");
            alerta.show();
            return true;
        }
        if (txtCatedratico.getText().isBlank()) {
            alerta.setContentText("El campo de catedratico debe estar lleno.");
            alerta.show();
            return true;
        }
        if (txtSalon.getText().isBlank()) {
            alerta.setContentText("El campo salon debe estar lleno");
            alerta.show();
            return true;
        }

        return false;
    }

}
