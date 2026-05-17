package com.example.ejerciciosflujoscharacter.Modelo;

import java.io.*;

public class EncriptadorXOR {

    public void encriptarArchivo(File archivo, int clave) {
        String destino = archivo.getParent() + "/encriptado_" + archivo.getName();
        try (FileInputStream in = new FileInputStream(archivo);
             FileOutputStream out = new FileOutputStream(destino)) {

            int bytesLeidos;
            String numeroBinario = Integer.toBinaryString(clave);
            System.out.println(numeroBinario);

            // Lee byte por byte (valor de 0 a 255)
            while ((bytesLeidos = in.read()) != -1) {
                out.write(bytesLeidos ^ clave);

            }
            System.out.println("Archivo de bytes copiado con éxito en " + destino);

        } catch (FileNotFoundException e) {
            System.err.println("Error al encontrar el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}
