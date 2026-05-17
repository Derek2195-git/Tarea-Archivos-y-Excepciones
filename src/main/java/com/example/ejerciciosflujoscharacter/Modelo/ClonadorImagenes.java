package com.example.ejerciciosflujoscharacter.Modelo;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.IntConsumer;

public class ClonadorImagenes {

    public Task<Void> crearCopiaArchivo(File archivo) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                File destino = new File(archivo.getParent(), "copia_" + archivo.getName());

                try (FileInputStream in = new FileInputStream(archivo);
                     FileOutputStream out = new FileOutputStream(destino)) {
                    byte[] bytes = new byte[1024];
                    int bytesLeidos;
                    long totalBytesLeidos = 0;
                    long totalBytes = archivo.length();

                    while ((bytesLeidos = in.read(bytes)) != -1) {

                        out.write(bytes, 0, bytesLeidos);
                        totalBytesLeidos += bytesLeidos;

                        int progreso = (int) ((totalBytesLeidos * 100) / totalBytes);

                        updateProgress(totalBytesLeidos, totalBytes);
                    }
                    System.out.println("Archivo de bytes copiado con éxito.");


                } catch (IOException e) {
                    System.err.println("Error de E/S: " + e.getMessage());
                }
                return null;
            }
        };

    }


}
