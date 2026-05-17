package com.example.ejerciciosflujoscharacter.Modelo;

import java.io.*;

public class EditorNotas {
    /**
     * Constructor vacio
     */
    public EditorNotas() {
    }

    /**
     * Carga un archivo dado por la vista
     * @param archivo Archivo de la clase File
     * @return Retorna una cadena con el contenido del archivo de texto
     * @throws IOException Arroja un error de entrada y salida en caso de que haya error al leer el archivo
     */
    public String cargarArchivo(File archivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e){
            System.out.println("Error al leer: " + e.getMessage());
        }
        return contenido.toString();
    }

    /**
     * Guarda el contenido de un texto a un archivo con terminacion .txt
     * @param archivo Archivo de la clase File dado por la vista
     * @param texto Cadena con lo que se va a guardar en el archivo
     * @throws IOException Arroja un error de entrada y salida en caso de que haya error al leer el archivo
     */
    public void guardarArchivo(File archivo, String texto) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));) {
            escritor.write(texto);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
