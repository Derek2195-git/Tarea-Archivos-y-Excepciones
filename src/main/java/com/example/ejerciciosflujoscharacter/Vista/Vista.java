package com.example.ejerciciosflujoscharacter.Vista;

import com.example.ejerciciosflujoscharacter.Controlador.Controlador;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Vista extends Application {
    /**
     * Crea el menú principal
     * @param stage Ventana donde se mostrará todo
     * @throws IOException Arroja un error de entrada/salida en caso de que el archivo no cargue
     */
    @Override
    public void start(Stage stage) throws IOException {
        Label labelMenu = new Label("Manejo de archivos");
        Button botonEditor = new Button();
        Button botonEstadisticas = new Button();
        Button botonCSV = new Button();
        Button botonClonador = new Button();
        Button botonEncriptador = new Button();
        Button botonDetector = new Button();


        labelMenu.getStyleClass().add("label-menu");
        botonEditor.getStyleClass().add("tile-button");
        botonEstadisticas.getStyleClass().add("tile-button");
        botonCSV.getStyleClass().add("tile-button");
        botonClonador.getStyleClass().add("tile-button");
        botonEncriptador.getStyleClass().add("tile-button");
        botonDetector.getStyleClass().add("tile-button");
        ImageView imgEditor = new ImageView(new Image(getClass().getResourceAsStream("/com/example/ejerciciosflujoscharacter/EditorTexto.png")));
        ImageView imgEstadisticas = new ImageView(new Image(getClass().getResourceAsStream("/com/example/ejerciciosflujoscharacter/EstadisticasArchivo.png")));
        ImageView imgEncriptador = new ImageView(new Image(getClass().getResourceAsStream("/com/example/ejerciciosflujoscharacter/Encriptador.png")));
        ImageView imgDetector = new ImageView(new Image(getClass().getResourceAsStream("/com/example/ejerciciosflujoscharacter/Identificador.png")));
        ImageView imgCSV = new ImageView(new Image(getClass().getResourceAsStream("/com/example/ejerciciosflujoscharacter/VisualizadorCSV.png")));
        ImageView imgClonador = new ImageView(new Image(getClass().getResourceAsStream("/com/example/ejerciciosflujoscharacter/ClonadorImagen.png")));

        botonEditor.setGraphic(imgEditor);
        botonEstadisticas.setGraphic(imgEstadisticas);
        botonEncriptador.setGraphic(imgEncriptador);
        botonDetector.setGraphic(imgDetector);
        botonCSV.setGraphic(imgCSV);
        botonClonador.setGraphic(imgClonador);


        botonEditor.setOnAction(e -> abrirEditorNotas(stage));
        botonEstadisticas.setOnAction(e -> abrirEstadisticas(stage));
        botonCSV.setOnAction(e -> abrirVisualizadorCSV(stage));
        botonClonador.setOnAction(e -> abrirClonador(stage));
        botonEncriptador.setOnAction(e -> abrirEncriptador(stage));
        botonDetector.setOnAction(e -> abrirIdentificador(stage));


        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(20);

        grid.add(botonEditor, 0, 0);
        grid.add(botonEstadisticas, 0, 1);
        grid.add(botonEncriptador, 1, 0, 1, 2);
        grid.add(botonDetector, 2, 0, 1, 2);
        grid.add(botonCSV, 0, 2, 2, 1);
        grid.add(botonClonador, 2, 2);
        grid.setAlignment(Pos.CENTER);

        VBox contenedor = new VBox(15, labelMenu, grid);

        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(30));

        Scene scene = new Scene(contenedor, 600, 480);
        scene.getStylesheets().add(getClass().getResource("/com/example/ejerciciosflujoscharacter/estilos.css").toExternalForm());
        stage.setTitle("Flujos de Character");
        stage.setScene(scene);
        stage.show();

    }



    /**
     * Ventana del editor de notas
     * @param stage Ventana usada en el menu principal
     */
    public void abrirEditorNotas(Stage stage) {
        Controlador controlador = new Controlador();
        controlador.setStage(stage);

        VBox contenedorVertical = new VBox(30);

        TextArea areaTexto = new TextArea();
        areaTexto.setPromptText("Carga un archivo o escribe aqui");
        areaTexto.setWrapText(true);
        areaTexto.setPrefHeight(400);
        areaTexto.setPrefWidth(500);
        areaTexto.setMaxWidth(400);

        Button botonCargar = new Button("Abrir archivo");
        Button botonGuardar = new Button("Guardar archivo");
        Button botonVolver = new Button("Volver al menú principal");

        HBox barraHerramientas = new HBox(10, botonCargar, botonGuardar, botonVolver);
        barraHerramientas.setPadding(new Insets(10));
        barraHerramientas.setAlignment(Pos.CENTER);

        Label labelArchivo = new Label("Ningun archivo cargado.");
        labelArchivo.setPadding(new Insets(10));

        botonCargar.setOnAction(e -> {
            String contenido = controlador.cargarArchivo();
            if (contenido != null) {
                areaTexto.setText(contenido);
                labelArchivo.setText("Archivo cargado!");
            }
        });

        botonGuardar.setOnAction(e -> {
            boolean exito = controlador.guardarArchivo(areaTexto.getText());
            labelArchivo.setText(exito ? "Archivo guardado correctamente!" : "Error al guardar!");
        });

        botonVolver.setOnAction(e -> {
            try {
                start(stage);
            } catch (IOException error) {
                System.out.println("Error al abrir el menu principal: " + error.getMessage());
            }
        });


        contenedorVertical.getChildren().addAll(barraHerramientas, areaTexto, labelArchivo);
        contenedorVertical.setAlignment(Pos.CENTER);

        Scene scene = new Scene(contenedorVertical, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/ejerciciosflujoscharacter/estilos.css").toExternalForm());

        stage.setTitle("Editor de texto");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Ventana del mostrador de estadisticas
     * @param stage Ventana usada en el menu principal
     */
    public void abrirEstadisticas(Stage stage) {
        Controlador controlador = new Controlador();
        controlador.setStage(stage);

        Button botonCargar = new Button("Cargar archivo");
        Button botonVolver = new Button("Volver al menú principal");
        Label labelArchivo = new Label("Ningun archivo cargado.");

        TableColumn<int[], String> colMetrica = new TableColumn<>("Metrica");
        TableColumn<int[], String> colValor = new TableColumn<>("Valor");

        colMetrica.setCellValueFactory(data -> {
            return new SimpleStringProperty(
                    data.getValue()[0] == -1 ? "Lineas" :
                    data.getValue()[0] == -2 ? "Palabras" : "Caracteres"
            );
        });

        colValor.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue()[1]))
        );

        TableView<int[]> tabla = new TableView<>();
        tabla.getColumns().addAll(colMetrica, colValor);

        tabla.setFixedCellSize(40);
        tabla.setPrefHeight(50 * 3 + 30);
        tabla.setPrefWidth(50 * 3 + 30);

        colMetrica.setPrefWidth(175);
        colValor.setPrefWidth(175);
        tabla.setMaxWidth(350);

        botonCargar.setOnAction(e -> {
            int[] stats = controlador.analizarArchivo();
            if (stats != null) {
                tabla.getItems().clear();
                tabla.getItems().add(new int[]{-1, stats[0]}); // lineas
                tabla.getItems().add(new int[]{-2, stats[1]}); // palabras
                tabla.getItems().add(new int[]{-3, stats[2]}); // caracteres
                labelArchivo.setText("Archivo analizado!");
            }
        });

        botonVolver.setOnAction(e -> {
            try {
                start(stage);
            } catch (IOException error) {
                System.out.println("Error al abrir el menu principal: " + error.getMessage());
            }
        });

        VBox contenedor = new VBox(15, botonVolver, botonCargar, tabla, labelArchivo);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(20));

        Scene scene = new Scene(contenedor, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/ejerciciosflujoscharacter/estilos.css").toExternalForm());
        stage.setTitle("Estadísticas de Texto");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Ventana del visualizador de archivos CSV
     * @param stage Ventana usada en los ejercicios anteriores
     */
    public void abrirVisualizadorCSV(Stage stage) {
        Controlador controlador = new Controlador();
        controlador.setStage(stage);

        Button botonCargar = new Button("Cargar archivo");
        Button botonVolver = new Button("Volver al menú principal");
        Label labelArchivo = new Label("Ningun archivo cargado");

        TableView<String[]> tabla = new TableView<>();

        botonCargar.setOnAction(e -> {
            ArrayList<String[]> filas = controlador.leerCSV();
            if (filas != null && !filas.isEmpty()) {
                tabla.getItems().clear();
                tabla.getColumns().clear();

                String[] encabezados = filas.get(0);

                for (int i = 0; i < encabezados.length; i++) {
                    int indice = i;
                    TableColumn<String[], String> columna = new TableColumn<>(encabezados[indice]);
                    columna.setCellValueFactory(datos ->{
                        String[] fila = datos.getValue();
                        if (fila == null || fila.length <= indice) {
                            return new SimpleStringProperty("");
                        }
                        return new SimpleStringProperty(fila[indice]);

                    });
                    tabla.getColumns().add(columna);

                }

                for (int i = 1; i < filas.size(); i++) {
                    tabla.getItems().add(filas.get(i));
                }

                labelArchivo.setText("Archivo cargado: Se mostraron un total de " + filas.size() + " filas");
            }
        });

        botonVolver.setOnAction(e -> {
            try {
                start(stage);
            } catch (IOException error) {
                System.out.println("Error al abrir el menu principal: " + error.getMessage());
            }
        });

        VBox contenedorVertical = new VBox(15, botonVolver, botonCargar, tabla, labelArchivo);
        contenedorVertical.setAlignment(Pos.CENTER);
        contenedorVertical.setPadding(new Insets(20));

        Scene scene = new Scene(contenedorVertical, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/ejerciciosflujoscharacter/estilos.css").toExternalForm());


        stage.setTitle("Visualizador CSV");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Ventana del clonador de imagenes
     * @param stage Ventana usada en el menú principal
     */
    public void abrirClonador(Stage stage) {
        Controlador controlador = new Controlador();
        controlador.setStage(stage);

        ProgressBar barraDeProgreso = new ProgressBar();
        Label labelProgreso = new Label("");
        Label labelEstado = new Label("");

        Button botonSeleccionar = new Button("Seleccionar un archivo");
        Button botonVolver = new Button("Volver al menú principal");

        botonSeleccionar.setOnAction(e -> {
            Task<Void> tarea = controlador.clonarArchivo();
            if (tarea != null) {
                barraDeProgreso.progressProperty().bind(tarea.progressProperty());
                tarea.progressProperty().addListener((observable, viejo, nuevo) ->  {
                    labelProgreso.setText((int) (nuevo.doubleValue() * 100) + "%");
                });
                tarea.setOnSucceeded(s -> labelEstado.setText("¡Archivo copiado con éxito!"));
                new Thread(tarea).start();
            }
        });

        botonVolver.setOnAction(e -> {
            try {start(stage);} catch (IOException error) {error.printStackTrace();}
        });

        VBox contenedor = new VBox(15, botonSeleccionar, barraDeProgreso, labelProgreso, labelEstado, botonVolver);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(30));

        Scene scene = new Scene(contenedor, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/com/example/ejerciciosflujoscharacter/estilos.css").toExternalForm());
        stage.setTitle("Clonador de imágenes");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Ventana del encriptador de archivos
     * @param stage Ventana usada en el menú principal
     */
    public void abrirEncriptador(Stage stage) {
        Controlador controlador = new Controlador();
        controlador.setStage(stage);

        Label labelClave = new Label("Ingresa una clave del 0 al 255 para encriptar un archivo");
        TextField campoClave = new TextField();
        campoClave.setMaxWidth(100);

        Label labelEstado = new Label("");

        Button botonSeleccionar = new Button("Seleccionar un archivo");
        Button botonVolver = new Button("Volver al menú principal");

        botonSeleccionar.setOnAction(e -> {
            String texto = campoClave.getText();
            try {
                int clave = Integer.parseInt(texto);
                if (clave < 0 || clave > 255) {
                    labelEstado.setText("La clave debe ser un valor entre 0 y 255");
                    return;
                }
                controlador.encriptarArchivo(clave);
                labelEstado.setText("Archivo encriptado con exito!");
            } catch (NumberFormatException error) {
                labelEstado.setText("Ingresa un numero valido");
            }
        });

        botonVolver.setOnAction(e -> {
            try {start(stage);} catch (IOException error) {error.printStackTrace();}
        });

        VBox contenedor = new VBox(15, labelClave, campoClave, botonSeleccionar, labelEstado, botonVolver);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(30));

        Scene scene = new Scene(contenedor, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/com/example/ejerciciosflujoscharacter/estilos.css").toExternalForm());
        stage.setTitle("Encriptador XOR");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Ventana del identificador de tipo de un archivo
     * @param stage Ventana usada en el menú principal
     */
    public void abrirIdentificador(Stage stage) {
        Controlador controlador = new Controlador();
        controlador.setStage(stage);

        Label labelResultado = new Label("");
        Button botonSeleccionar = new Button("Seleccionar un archivo");
        Button botonVolver = new Button("Volver al menú principal");

        botonSeleccionar.setOnAction(e -> {
            String formato = controlador.identificarArchivo();
            if (formato != null) {
                labelResultado.setText("El formato del archivo es: " + formato.trim());
            }
        });

        botonVolver.setOnAction(e -> {
            try {start(stage);} catch (IOException error) {error.printStackTrace();}
        });

        VBox contenedor = new VBox(15, botonSeleccionar, labelResultado, botonVolver);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(30));

        Scene scene = new Scene(contenedor, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/com/example/ejerciciosflujoscharacter/estilos.css").toExternalForm());
        stage.setTitle("Identificador de archivos");
        stage.setScene(scene);
        stage.show();

    }
}
