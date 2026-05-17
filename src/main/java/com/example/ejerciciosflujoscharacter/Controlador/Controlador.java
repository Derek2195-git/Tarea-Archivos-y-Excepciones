package com.example.ejerciciosflujoscharacter.Controlador;

import com.example.ejerciciosflujoscharacter.Modelo.*;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Controlador {
    EditorNotas modeloEditor;
    EstadisticasTexto modeloEstadisticas;
    VisualizadorCSV modeloCSV;
    ClonadorImagenes modeloClonador;
    EncriptadorXOR modeloEncriptador;
    IdentificadorTiposArchivos modeloIdentificador;


    Stage stage;

    /**
     * Constructor del controlador
     */
    public Controlador() {
        modeloEditor = new EditorNotas();
        modeloEstadisticas = new EstadisticasTexto();
        modeloCSV = new VisualizadorCSV();
        modeloClonador = new ClonadorImagenes();
        modeloEncriptador = new EncriptadorXOR();
    }

    /**
     * Setter de la ventana del usuario
     * @param stage Ventana que está usando el usuario
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Este metodo llama al editor de notas para que cargue un archivo
     * @return Texto del archivo seleccionado
     */
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

    /**
     * Este metodo llama al editor de notas para que guarde un archivo
     * @param texto Texto a guardar en el archivo
     * @return Bandera que indica si el guardado fue exitoso o no
     */
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

    /**
     * Metodo que llama al analizador de archivos
     * @return Arreglo con las lineas, palabras y caracteres escritos en el archivo
     */
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

    /**
     * Este metodo llama al visualizador de archivos CSV
     * @return ArrayList parametrizado con un arreglo de cadenas, cada elemento es una columna de la tabla
     */
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

    /**
     * Este metodo llama al clonador de imagenes para que pueda empezar con el copiado
     * @return Retorna la tarea que clona un archivo definida en el modelo
     */
    public Task<Void> clonarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona la imagen a clonar");

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            return modeloClonador.crearCopiaArchivo(archivo);
        }
        return null;
    }

    /**
     * Este metodo llama al encriptador de archivos
     * @param clave Numero del 0 al 255 el cual servirá como una clave para encriptarse
     */
    public void encriptarArchivo(int clave) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo para encriptarlo");

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            modeloEncriptador.encriptarArchivo(archivo, clave);
        }

    }

    /**
     * Este metodo llama al identificador de archivos para obtener el arreglo de bytes
     * y verificar el formato del archivo dado por el usuario
     * @return Cadena con el formato del archivo
     */
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

    public String getRutaArchivo(File archivo) {
        return archivo.getPath();
    }
}
