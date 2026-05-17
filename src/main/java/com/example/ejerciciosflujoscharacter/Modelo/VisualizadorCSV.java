package com.example.ejerciciosflujoscharacter.Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VisualizadorCSV {
    /**
     * Lee un archivo de terminación CSV (Comma-Separated-Values)
     * @param archivo Archivo a leer
     * @return ArrayList parametrizado con un arreglo cadenas, cada elemento es una columna de la tabla
     * @throws IOException Arroja un error de entrada/salida en caso de que el archivo no cargue
     */
    public ArrayList<String[]> leerArchivoCSV(File archivo) throws IOException {
        ArrayList<String[]> arregloCSV = new ArrayList<String[]>();

        try (
                BufferedReader lector = new BufferedReader(new FileReader(archivo))
                ) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    try {
                        String[] celdas = linea.split(",");
                        arregloCSV.add(celdas);
                    } catch (Exception e) {
                        System.out.println("Error en la linea: " + e.getMessage());
                    }
                }
            }
        }

        return arregloCSV;
    }
}
