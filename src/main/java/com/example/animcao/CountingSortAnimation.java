package com.example.animcao;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.Arrays;

public class CountingSortAnimation extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Rectangle[] rectangles;
    private final int SIZE = 10; // Tamanho do array
    private final int MAX_VALUE = 100; // Valor máximo para os elementos

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Counting Sort Animation");
        pane = new AnchorPane();
        botao_inicio = new Button();
        botao_inicio.setLayoutX(10); botao_inicio.setLayoutY(100);
        botao_inicio.setText("Inicia...");
        botao_inicio.setOnAction(e->{ move_botoes();});
        pane.getChildren().add(botao_inicio);
        generateRectangles();
        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void move_botoes() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                int[] arr = Arrays.stream(rectangles)
                        .mapToInt(rectangle -> Integer.parseInt(rectangle.getId()))
                        .toArray();

                // Counting Sort
                int[] count = new int[MAX_VALUE + 1];
                for (int num : arr) {
                    count[num]++;
                }

                int index = 0;
                for (int i = 0; i < count.length; i++) {
                    for (int j = 0; j < count[i]; j++) {
                        final int finalIndex = index; // Criando uma variável final local
                        int finalI = i;
                        Platform.runLater(() -> rectangles[finalIndex].setX(finalI * 60)); // Usando a variável finalIndex
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        index++;
                    }
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    private void generateRectangles() {
        rectangles = new Rectangle[SIZE];
        for (int i = 0; i < SIZE; i++) {
            Rectangle rectangle = new Rectangle(50, 20, Color.BLUE);
            rectangle.setX(100 + i * 60);
            rectangle.setY(200);
            rectangle.setId(String.valueOf((int) (Math.random() * MAX_VALUE)));
            rectangles[i] = rectangle;
            pane.getChildren().add(rectangle);
        }
    }
}