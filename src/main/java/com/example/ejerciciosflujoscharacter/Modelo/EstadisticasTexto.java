package com.example.ejerciciosflujoscharacter.Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EstadisticasTexto {
    /**
     *
     * @param archivo Un archivo de tipo File, dado por la vista
     * @return Retorna un arreglo donde [0] -> lineas, [1] -> palabras, [2] -> caracteres
     * @throws IOException Arroja un error de entrada/salida en caso de que el archivo no cargue
     */
    public int[] analizarArchivo(File archivo) throws IOException {
        int lineas = 0;
        int palabras = 0;
        int caracteres = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                if (!linea.trim().isEmpty()) {
                    palabras += linea.trim().split("\\s+").length;
                }
            }
        }



        return new int[]{lineas, palabras, caracteres};
    }
}
