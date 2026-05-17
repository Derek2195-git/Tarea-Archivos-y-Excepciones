package com.example.ejerciciosflujoscharacter.Modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class IdentificadorTiposArchivos {
    File archivo;

    /**
     * Constructor del identificador
     * @param archivo Archivo dado por el usuario
     */
    public IdentificadorTiposArchivos(File archivo) {
        this.archivo = archivo;
    }

    /**
     * Este metodo lee los primeros 8 bytes del archivo dado
     * @return ArrayList con los primeros 8 bytes del archivo
     */
    public ArrayList<Integer> leer8bytes() {
        int unByte = -1;
        int contador = 0;
        ArrayList<Integer> ochoBytes = new ArrayList<>();
        FileInputStream in = null;
        try {
            in = new FileInputStream(archivo);
            while ((unByte = in.read()) != -1 && (contador < 8)) {
                ochoBytes.add(unByte);
                contador++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ochoBytes;
    }

    /**
     * Este metodo verifica el formato del archivo a partir de los primeros 8 bytes de este
     * @param ochoBytes Arreglo con los primeros 8 bytes del archivo
     * @return Cadena con el formato del archivo
     */
    public String verificarFormato(ArrayList<Integer> ochoBytes) {
        String formato = " ";
        ArrayList<Integer> formatoPDF = new ArrayList<>();
        formatoPDF.add(Integer.parseInt("25", 16));
        formatoPDF.add(Integer.parseInt("50", 16));
        formatoPDF.add(Integer.parseInt("44", 16));
        formatoPDF.add(Integer.parseInt("46", 16));
        formatoPDF.add(Integer.parseInt("2D", 16));

        ArrayList<Integer> formatoJPEG = new ArrayList<>();
        formatoJPEG.add(Integer.parseInt("FF", 16));
        formatoJPEG.add(Integer.parseInt("D8", 16));
        formatoJPEG.add(Integer.parseInt("FF", 16));
        formatoJPEG.add(Integer.parseInt("E0", 16));
        formatoJPEG.add(Integer.parseInt("E1", 16));

        // 89 50 4E 47 0D 0A 1A 0A
        ArrayList<Integer> formatoPNG = new ArrayList<>();
        formatoPNG.add(Integer.parseInt("89", 16));
        formatoPNG.add(Integer.parseInt("50", 16));
        formatoPNG.add(Integer.parseInt("4E", 16));
        formatoPNG.add(Integer.parseInt("47", 16));
        formatoPNG.add(Integer.parseInt("0D", 16));
        formatoPNG.add(Integer.parseInt("0A", 16));
        formatoPNG.add(Integer.parseInt("1A", 16));
        formatoPNG.add(Integer.parseInt("0A", 16));

        // ZIP / DOCX / XLSX / JAR
        // 50 4B 03 04
        ArrayList<Integer> formatoZIP = new ArrayList<>();
        formatoZIP.add(Integer.parseInt("50", 16));
        formatoZIP.add(Integer.parseInt("4B", 16));
        formatoZIP.add(Integer.parseInt("03", 16));
        formatoZIP.add(Integer.parseInt("04", 16));

        //  66 74 79 70
        ArrayList<Integer> formatoMP4 = new ArrayList<>();
        formatoMP4.add(Integer.parseInt("66", 16));
        formatoMP4.add(Integer.parseInt("74", 16));
        formatoMP4.add(Integer.parseInt("79", 16));
        formatoMP4.add(Integer.parseInt("70", 16));

        // 47 49 46 38
        ArrayList<Integer> formatoGIF = new ArrayList<>();
        formatoGIF.add(Integer.parseInt("47", 16));
        formatoGIF.add(Integer.parseInt("49", 16));
        formatoGIF.add(Integer.parseInt("46", 16));
        formatoGIF.add(Integer.parseInt("38", 16));

        int valor = ochoBytes.getFirst();

        boolean formatoEncontrado = false;
        if (valor == formatoPDF.getFirst()) {
            formato = "PDF";
            formatoEncontrado = true;
        }
        if (valor == formatoJPEG.getFirst()) {
            formato = "JPEG";
            formatoEncontrado = true;
        }
        if (valor == formatoPNG.getFirst()) {
            formato = "PNG";
            formatoEncontrado = true;
        }
        if (valor == formatoZIP.getFirst()) {
            formato = "ZIP";
            formatoEncontrado = true;
        }
        if (valor == formatoMP4.get(3)) {
            formato = "MP4";
            formatoEncontrado = true;
        }
        if (valor == formatoGIF.getFirst()) {
            formato = "GIF";
            formatoEncontrado = true;
        }
        if (!formatoEncontrado) formato = "El formato del archivo introducido no se encontró.";

        return formato;
    }
}