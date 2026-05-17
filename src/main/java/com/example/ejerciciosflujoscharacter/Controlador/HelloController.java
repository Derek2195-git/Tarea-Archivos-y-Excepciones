package com.example.ejerciciosflujoscharacter.Controlador;

import com.example.ejerciciosflujoscharacter.Modelo.*;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class HelloController {
    EditorNotas modeloEditor;
    EstadisticasTexto modeloEstadisticas;
    VisualizadorCSV modeloCSV;
    ClonadorImagenes modeloClonador;
    EncriptadorXOR modeloEncriptador;
    IdentificadorTiposArchivos modeloIdentificador;


    Stage stage;

    public HelloController() {
        modeloEditor = new EditorNotas();
        modeloEstadisticas = new EstadisticasTexto();
        modeloCSV = new VisualizadorCSV();
        modeloClonador = new ClonadorImagenes();
        modeloEncriptador = new EncriptadorXOR();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String cargarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de texto");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
        );

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            try {
                return modeloEditor.cargarArchivo(archivo);
            } catch (IOException error) {
                return "Error: " + error.getMessage();
            }
        }
        return null;
    }

    public boolean guardarArchivo(String texto) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo de texto");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
        );

        File archivo = fileChooser.showSaveDialog(stage);
        if (archivo != null) {
           try {
               modeloEditor.guardarArchivo(archivo, texto);
               return true;
           } catch(IOException e) {
               System.out.println(e.getMessage());
           }

        }
        return false;
    }

    public int[] analizarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de texto");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
        );

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            try {
                return modeloEstadisticas.analizarArchivo(archivo);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return null;
    }

    public ArrayList<String[]> leerCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"));

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            try {
                return modeloCSV.leerArchivoCSV(archivo);
            } catch (Exception e) {
                System.out.println("Error al leer el archivo:" + e.getMessage());
            }
        }
        return null;

    }

    public Task<Void> clonarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona la imagen a clonar");

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            return modeloClonador.crearCopiaArchivo(archivo);
        }
        return null;
    }

    public void encriptarArchivo(int clave) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo para encriptarlo");

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            modeloEncriptador.encriptarArchivo(archivo, clave);
        }

    }

    public String identificarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona el archivo a identificar");

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            modeloIdentificador = new IdentificadorTiposArchivos(archivo);
            ArrayList<Integer> bytes = modeloIdentificador.leer8bytes();
            return modeloIdentificador.verificarFormato(bytes);
        }

        return null;
    }
}
