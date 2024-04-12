package com.example.animcao;

import java.util.Arrays;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Principal extends Application {
    public int k=0;
    private Text K,texto,Seta,SetaA,vetCT,vetBT,textoM,indice,titulo,indiceText;
    AnchorPane pane;
    Button botao_inicio;

    private Text[] Codigo;
    private Button []vet, vetC, vetB;
    public static void main(String[] args)
    {
        launch(args);
    }

    public void alocarCodigo(Text [] codigo){
        codigo[0] = new Text("public void counting(int[] vet){");
        codigo[1] = new Text(" int k=0;");
        codigo[2] = new Text(" for (int i = 0; i < vet.length ; i++) {");
        codigo[3] = new Text("   k = Math.max(k, vet[i]);");
        codigo[4] = new Text(" }");
        codigo[5] = new Text(" int []C = new int[k+1], B = new int[vet.length];");
        codigo[6] = new Text(" for (int i = 0; i < vet.length ; i++) {");
        codigo[7] = new Text("   C[vet[i]]++;");
        codigo[8] = new Text(" }");
        codigo[9] = new Text(" for (int i = 1; i <= k ; i++) {");
        codigo[10] = new Text("     C[i] += C[i-1];");
        codigo[11] = new Text(" }");
        codigo[12] = new Text(" for (int i = vet.length - 1 ; i >= 0; i--) {");
        codigo[13] = new Text("     B[C[vet[i]] - 1] = vet[i];");
        codigo[14] = new Text("     C[vet[i]]--;");
        codigo[15] = new Text(" }");
        codigo[16] = new Text(" for (int i = 0; i < vet.length; i++) {");
        codigo[17] = new Text("      vet[i] = B[i];");
        codigo[18] = new Text(" }");
        codigo[19]= new Text("}");

    }
    public void counting(int[] vet){
        int k=0;
        for (int i = 0; i < vet.length ; i++) {
            k = Math.max(k, vet[i]);
        }
        int []C = new int[k+1], B = new int[vet.length];
        for (int i = 0; i < vet.length ; i++) {
            C[vet[i]]++;
        }
        for (int i = 1; i <= k ; i++) {
            C[i] += C[i-1];
        }
        for (int i = vet.length - 1 ; i >= 0; i--) {
            B[C[vet[i]] - 1] = vet[i];
            C[vet[i]]--;
        }
        for (int i = 0; i < vet.length; i++) {
            vet[i] = B[i];
        }
    }

    public void gerarVetor(Button[] Vet) {
        Random random = new Random();
        int tamanhoVetor = 15; // Tamanho do vetor

        // Adicionando o nome do vetor
        texto = new Text("Vet:");
        texto.setFill(Color.WHITE);
        texto.setLayoutX(50);
        texto.setLayoutY(150);
        texto.setFont(new Font(14));
        pane.getChildren().add(texto);

        // Adicionando os botões representando o vetor
        vet = new Button[tamanhoVetor];
        for (int i = 0; i < tamanhoVetor; i++) {
            int valorAleatorio = random.nextInt(10); // Gerando número aleatório entre 0 e 99

            vet[i] = new Button(String.valueOf(valorAleatorio));
            vet[i].setLayoutX(150 + i * 60); // Espaçamento entre os botões
            vet[i].setLayoutY(120);
            vet[i].setMinHeight(40);
            vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);

            // Adicionando o índice abaixo do botão
            Text indice = new Text(String.valueOf(i));
            indice.setFill(Color.WHITE);
            indice.setLayoutX(vet[i].getLayoutX() + 20); // Posição do índice abaixo do botão
            indice.setLayoutY(vet[i].getLayoutY() + 60); // Deslocamento para baixo
            pane.getChildren().add(indice);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Pesquisa e Ordenacao");

        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: #222222; -fx-border-color: #7f7f7f; -fx-border-width: 2px;");
        pane.setPrefSize(1600, 900); // Definindo tamanho da tela
        botao_inicio = new Button();
        botao_inicio.setText("Iniciar");
        botao_inicio.setFont(new Font(14));
        botao_inicio.setOnAction(e -> {

            move_botoes();
        });
        pane.getChildren().add(botao_inicio);
        botao_inicio.setLayoutX(750);
        botao_inicio.setLayoutY(780);
        // Adicionando o nome do vetor
         titulo = new Text("COUNTING SORT:");

        titulo.setLayoutX(600);
        titulo.setLayoutY(50);
        titulo.setFont(new Font(20));
        titulo.setFill(Color.WHITE);
        pane.getChildren().add(titulo);



        gerarVetor(vet);

        K = new Text("K: " + k);
        K.setFill(Color.BLUE);
        K.setFont(new Font(14));
        pane.getChildren().add(K);

        Seta = new Text("↑");
        Seta.setFill(Color.RED);
        Seta.setFont(new Font(40));
        pane.getChildren().add(Seta);

        SetaA = new Text("↑");
        SetaA.setFill(Color.BLUE);
        SetaA.setFont(new Font(40));
        pane.getChildren().add(SetaA);

        vetCT = new Text("VetC:");
        vetCT.setFill(Color.WHITE);
        vetCT.setFont(new Font(14));
        pane.getChildren().add(vetCT);

        vetBT = new Text("VetB:");
        vetBT.setFill(Color.WHITE);
        vetBT.setFont(new Font(14));
        pane.getChildren().add(vetBT);

        textoM = new Text("maior");
        textoM.setFill(Color.BLUE);
        textoM.setFont(new Font(14));
        pane.getChildren().add(textoM);

        indice = new Text("indice");
        indice.setFill(Color.RED);
        indice.setFont(new Font(14));
        pane.getChildren().add(indice);
        Codigo = new Text[20];
        alocarCodigo(Codigo);

        for (int i = 0; i < Codigo.length ; i++) {
            Codigo[i].setFill(Color.WHITE);
            Codigo[i].setFont(new Font(14));
            Codigo[i].setLayoutX(1200);

            Codigo[i].setLayoutY((i+5)*15);


            pane.getChildren().add(Codigo[i]);
        }
        Scene scene = new Scene(pane,1600,900);
        stage.setScene(scene);
        stage.show();
    }


    public void move_botoes() {

        Task<Void> task = new Task<Void>(){
            int i=0;
            @Override
            protected Void call() {
                //valor de K
                int maior = 0;
                for (i = 0; i < vet.length; i++) {
                    int value = Integer.parseInt(vet[i].getText());
                    k = Math.max(k, value);
                    if (value > maior) {
                        maior = value;
                        Platform.runLater(() -> {
                            textoM.setLayoutX(150 + i * 60);
                            textoM.setLayoutY(225);
                            SetaA.setLayoutX(150 + i * 60);
                            SetaA.setLayoutY(210);
                        });
                    }

                    Platform.runLater(() -> {
                        indice.setLayoutX(150 + i * 60);
                        indice.setLayoutY(225);
                        Seta.setLayoutX(150 + i * 60);
                        Seta.setLayoutY(210);
                    });

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                k++;
                K.setText("K: "+k);
                Platform.runLater(() -> {
                    K.setLayoutX(50);
                    K.setLayoutY(200);
                    vetCT.setLayoutX(50);
                    vetCT.setLayoutY(250);
                    //pane.getChildren().removeAll(Seta, SetaA,indice,textoM);
                });


                int[] C = new int[k];
                vetC = new Button[k];
                for (i = 0; i < k; i++) {
                    vetC[i] = new Button("0");


                    Platform.runLater(() -> {
                        vetC[i].setLayoutX(150 + i * 60);
                        vetC[i].setLayoutY(230);
                        vetC[i].setMinHeight(40);
                        vetC[i].setMinWidth(40);
                        vetC[i].setFont(new Font(14));
                        pane.getChildren().add(vetC[i]);


                        indiceText = new Text(String.valueOf(i));
                        indiceText.setFill(Color.WHITE);
                        indiceText.setLayoutX(vetC[i].getLayoutX() + 20); // Posição do índice abaixo do botão
                        indiceText.setLayoutY(vetC[i].getLayoutY() + 60); // Deslocamento para baixo
                        pane.getChildren().add(indiceText);
                    });

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (i = 0; i < vet.length; i++) {
                    int valor = Integer.parseInt(vet[i].getText());
                    C[valor]++;
                    final int buttonIndex = valor;
                    Platform.runLater(() -> {
                        vetC[buttonIndex].setText(String.valueOf(C[buttonIndex]));
                        vetC[buttonIndex].setLayoutX(150 + buttonIndex * 60);
                        vetC[buttonIndex].setLayoutY(230);
                        vetC[buttonIndex].setMinHeight(40);
                        vetC[buttonIndex].setMinWidth(40);
                        vetC[buttonIndex].setFont(new Font(14));

                        indice.setText("indice");
                        indice.setFill(Color.RED);
                        indice.setLayoutX(150 + buttonIndex * 60);
                        indice.setLayoutY(320);
                        Seta.setFill(Color.RED);
                        Seta.setLayoutX(150 + buttonIndex * 60);
                        Seta.setLayoutY(310);
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 1; i <= k-1 ; i++) {
                    C[i] += C[i-1];
                    int valor = Integer.parseInt(vetC[i].getText());
                    final int buttonIndex = valor;
                    Platform.runLater(() -> {
                        indice.setText("indice");
                        indice.setFill(Color.RED);
                        indice.setLayoutX(150 + buttonIndex * 60);
                        indice.setLayoutY(320);
                        Seta.setFill(Color.RED);
                        Seta.setLayoutX(150 + buttonIndex * 60);
                        Seta.setLayoutY(310);
                        vetC[buttonIndex].setText(String.valueOf(C[buttonIndex]));
                        vetC[buttonIndex].setLayoutX(150 + buttonIndex * 60);
                        vetC[buttonIndex].setLayoutY(230);
                        vetC[buttonIndex].setMinHeight(40);
                        vetC[buttonIndex].setMinWidth(40);
                        vetC[buttonIndex].setFont(new Font(14));
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> {
                    vetBT.setLayoutX(50);
                    vetBT.setLayoutY(340);
                });

                int []B = new int[vet.length];

                vetB = new Button[vet.length];
                for (i = 0; i < vet.length; i++) {
                    final int buttonIndex = i;
                    Platform.runLater(() -> {
                        vetB[buttonIndex] = new Button("0");
                        vetB[buttonIndex].setLayoutX(150 + buttonIndex * 60);
                        vetB[buttonIndex].setLayoutY(320);
                        vetB[buttonIndex].setMinHeight(40);
                        vetB[buttonIndex].setMinWidth(40);
                        vetB[buttonIndex].setFont(new Font(14));
                        pane.getChildren().add(vetB[buttonIndex]);

                        indiceText = new Text(String.valueOf(buttonIndex));
                        indiceText.setFill(Color.WHITE);
                        indiceText.setLayoutX(vetB[buttonIndex].getLayoutX() + 20); // Posição do índice abaixo do botão
                        indiceText.setLayoutY(vetB[buttonIndex].getLayoutY() + 60); // Deslocamento para baixo
                        pane.getChildren().add(indiceText);
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

/*
                for (int i = vet.length - 1 ; i >= 0; i--) {
                    B[C[vet[i]] - 1] = vet[i];
                    C[vet[i]]--;
                }*/
                for (int i = vet.length - 1; i >= 0; i--) {
                    int index = Integer.parseInt(vet[i].getText());
                    if (C[index] - 1 >= 0 && C[index] - 1 < vetB.length) { // Verifica se o índice é válido
                        int buttonIndex = C[index] - 1;
                        B[buttonIndex] = index;
                        C[index]--;
                        Platform.runLater(() -> {
                            indice.setLayoutX(150 + buttonIndex * 60);
                            indice.setLayoutY(420);
                            Seta.setLayoutX(150 + buttonIndex * 60);
                            Seta.setLayoutY(400);
                            vetB[buttonIndex].setText(String.valueOf(B[buttonIndex]));
                            vetB[buttonIndex].setLayoutX(150 + buttonIndex * 60);
                            vetB[buttonIndex].setLayoutY(320);
                            vetB[buttonIndex].setMinHeight(40);
                            vetB[buttonIndex].setMinWidth(40);
                            vetB[buttonIndex].setFont(new Font(14));
                        });
                    } else {
                        System.out.println("Índice inválido: " + (C[index] - 1));
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                Platform.runLater(() -> {
                    vetBT.setText("Vet Ordenado: ");
                    vetBT.setLayoutX(48);
                    vetBT.setLayoutY(460);
                });
                Arrays.fill(vet, new Button("0"));
                for(i = 0; i < vet.length; i++){
                    vet[i] = new Button("0");


                    Platform.runLater(() -> {
                        vet[i].setLayoutX(150 + i * 60);
                        vet[i].setLayoutY(440);
                        vet[i].setMinHeight(40);
                        vet[i].setMinWidth(40);
                        vet[i].setFont(new Font(14));
                        pane.getChildren().add(vet[i]);


                        indiceText = new Text(String.valueOf(i));
                        indiceText.setFill(Color.WHITE);
                        indiceText.setLayoutX(vet[i].getLayoutX() + 20); // Posição do índice abaixo do botão
                        indiceText.setLayoutY(vet[i].getLayoutY() + 60); // Deslocamento para baixo
                        pane.getChildren().add(indiceText);
                    });

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                textoM.setText("indice");
                for ( i = 0; i < vet.length; i++) {
                    Platform.runLater(() -> {
                        Seta.setLayoutX(150 + i * 60);
                        Seta.setLayoutY(400);
                        SetaA.setLayoutX(150 + i * 60);
                        SetaA.setLayoutY(510);

                        textoM.setLayoutX(150 + i * 60);
                        textoM.setLayoutY(530);
                        indice.setLayoutX(150 + i * 60);
                        indice.setLayoutY(420);

                        vet[i].setText(String.valueOf(B[i]));
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    private int[] obterValoresDosBotoes() {

        int[] valores = new int[vet.length];
        for (int i = 0; i < vet.length; i++) {
            valores[i] = Integer.parseInt(vet[i].getText());
        }
        return valores;
    }
    public void limparElementosDinamicos() {
        // Limpar o conteúdo do AnchorPane dos elementos dinâmicos
        pane.getChildren().removeIf(node -> node instanceof Button || node instanceof Text);
    }

    public void limparPane() {
        // Limpar os elementos dinâmicos
        limparElementosDinamicos();

        // Reiniciar as variáveis necessárias
        pane.getChildren().addAll(K,texto,Seta,SetaA,vetCT,textoM,indice,titulo, botao_inicio);
        k = 0;
        vetC = null;

        // Chamada do método para gerar o vetor novamente
        gerarVetor(vet);
    }

}