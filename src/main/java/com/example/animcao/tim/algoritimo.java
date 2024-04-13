package com.example.animcao.tim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class algoritimo extends Application {

    public double diffX,stepX;
    public int k=0;
    private Text K,texto,Seta,SetaA,indice,titulo,indiceText,end,SetaAux,indiceA;
    private Text runT, runsT, TLT,esqT,dirT,posT;
    AnchorPane pane;
    Button botao_inicio;
    private Button []vet,vetCopia;
    private Text[] CodigoT, CodigoI, CodigoM;
    private VBox codeContainer;
    private ComboBox<String> codeSelector;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void alocarCodigoMerge(Text [] codigo){
        codigo[0] = new Text("private void mergeTim(int esq, int meio, int dir) {");
        codigo[1] = new Text("    int tam1 = meio - esq + 1, tam2 = dir - meio;");
        codigo[2] = new Text("    int[] vet1 = new int[tam1];");
        codigo[3] = new Text("    int[] vet2 = new int[tam2];");
        codigo[4] = new Text("    for (int pos = 0; pos < tam1; pos++)");
        codigo[5] = new Text("         vet1[pos] = vet[esq + pos];");
        codigo[6] = new Text("    for (int pos = 0; pos < tam2; pos++)");
        codigo[7] = new Text("         vet2[pos] = vet[meio + 1 + pos];");
        codigo[8] = new Text("    int i = 0,j = 0,k = esq;");
        codigo[9] = new Text("    while (i < tam1 && j < tam2) {)");
        codigo[10] = new Text("         if (vet1[i] <= vet2[j])");
        codigo[11] = new Text("             vet[k++] = vet1[i++];");
        codigo[12] = new Text("         else");
        codigo[13] = new Text("             vet[k++] = vet2[j++];");
        codigo[14] = new Text("    }");
        codigo[15] = new Text("    while (i < tam1)");
        codigo[16] = new Text("          vet[k++] = vet1[i++];");
        codigo[17] = new Text("    while (j < tam2)");
        codigo[18] = new Text("          vet[k++] = vet2[j++];");
        codigo[19] = new Text("}");

    }

    public void alocarCodigoTim(Text [] codigo){
        codigo[0] = new Text("public void tim() {");
        codigo[1] = new Text("     int runs = 32,TL = 14;");
        codigo[2] = new Text("     int run = tamMin(runs);");
        codigo[3] = new Text("     for (int i = 0; i < TL; i += runs)");
        codigo[4] = new Text("        insercaoDiretaTim(i, min((i + runs - 1), (TL - 1)));");
        codigo[5] = new Text("     for (int tam = runs; tam < TL; tam = 2 * tam)");
        codigo[6] = new Text("         for (int esq = 0; esq < TL; esq += 2 * tam) {");
        codigo[7] = new Text("            int meio = esq + tam - 1;");
        codigo[8] = new Text("            int dir = min((esq + 2 * tam - 1), (TL - 1));");
        codigo[9] = new Text("            if (meio < dir)");
        codigo[10] = new Text("               mergeTim(esq, meio, dir);");
        codigo[11] = new Text("        }");
        codigo[12] = new Text("}");
    }

    public void alocarCodigoInsercao(Text [] codigo){
        codigo[0] = new Text("private void insercaoDiretaTim(int esq, int dir){");
        codigo[1] = new Text("     int pos, aux;");
        codigo[2] = new Text("     for (int i = esq + 1; i <= dir; i++) {");
        codigo[3] = new Text("          aux = vet[i];");
        codigo[4] = new Text("          pos = i;");
        codigo[5] = new Text("          while (pos > esq && vet[pos-1] > aux) {");
        codigo[6] = new Text("                vet[pos] = vet[pos-1];");
        codigo[7] = new Text("                pos--;");
        codigo[8] = new Text("          }");
        codigo[9] = new Text("          vet[pos] = aux;");
        codigo[10] = new Text("    }");
        codigo[11] = new Text("}");
    }

    public void gerarVetor(Button[] Vet) {
        Random random = new Random();
        int tamanhoVetor = 14; // Tamanho do vetor

        // Adicionando o nome do vetor
        texto = new Text("Vet:");
        texto.setFill(Color.WHITE);
        texto.setLayoutX(50);
        texto.setLayoutY(120);
        texto.setFont(new Font(14));
        pane.getChildren().add(texto);

        // Adicionando os botões representando o vetor
        vet = new Button[tamanhoVetor];
        int valorAleatorio = 14;
        for (int i = 0; i < tamanhoVetor; i++) {

                    //random.nextInt(1000); // Gerando número aleatório entre 0 e 99

            vet[i] = new Button(String.valueOf(valorAleatorio));
            valorAleatorio--;
            vet[i].setLayoutX(150 + i * 60); // Espaçamento entre os botões
            vet[i].setLayoutY(100);
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
        pane.setPrefSize(1600, 900);
        botao_inicio = new Button();
        botao_inicio.setText("Iniciar");
        botao_inicio.setFont(new Font(14));
        botao_inicio.setOnAction(e -> move_botoes());
        pane.getChildren().add(botao_inicio);
        botao_inicio.setLayoutX(750);
        botao_inicio.setLayoutY(780);

        titulo = new Text("TIM SORT:");
        titulo.setLayoutX(600);
        titulo.setLayoutY(50);
        titulo.setFont(new Font(20));
        titulo.setFill(Color.GREEN);
        pane.getChildren().add(titulo);



        Seta = new Text("↑");
        Seta.setFill(Color.RED);
        Seta.setFont(new Font(40));
        pane.getChildren().add(Seta);

        SetaAux = new Text("↑");
        SetaAux.setFill(Color.GREEN);
        SetaAux.setFont(new Font(40));
        pane.getChildren().add(SetaAux);

        SetaA = new Text("↑");
        SetaA.setFill(Color.CYAN);
        SetaA.setFont(new Font(40));
        pane.getChildren().add(SetaA);

        indice = new Text("indice");
        indice.setFill(Color.RED);
        indice.setFont(new Font(14));
        pane.getChildren().add(indice);
        end = new Text("FINALIZADO");
        end.setFont(new Font(25));

        
        CodigoT = new Text[13];
        alocarCodigoTim(CodigoT);
        CodigoI = new Text[12];
        alocarCodigoInsercao(CodigoI);
        CodigoM = new Text[20];
        alocarCodigoMerge(CodigoM);

        // Container para o código

        VBox codeContainer = new VBox();
        codeContainer.setStyle("-fx-background-color: #515151; -fx-border-color: #022c10; -fx-border-width: 2px; -fx-border-radius: 2px");
        codeContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        codeContainer.setEffect(dropShadow);

        for (int i = 0; i < CodigoT.length; i++) {
            CodigoT[i].setFill(Color.WHITE);
            CodigoT[i].setFont(new Font(20));
            codeContainer.getChildren().add(CodigoT[i]);
        }

        CodigoT[0].setFill(Color.RED);

        pane.getChildren().add(codeContainer);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #000000;");
        botao_inicio.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
        Text tituloCodigo = new Text("Código");
        tituloCodigo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
        pane.getChildren().add(tituloCodigo);
        AnchorPane.setTopAnchor(tituloCodigo, 10.0);
        AnchorPane.setRightAnchor(tituloCodigo, 170.0);
        AnchorPane.setRightAnchor(codeContainer, 10.0);
        AnchorPane.setTopAnchor(codeContainer, 50.0);
        // Configuração da janela
        Scene scene = new Scene(pane, 1600, 900);
        stage.setTitle("Pesquisa e Ordenacao");
        stage.setScene(scene);
        stage.show();
    }

    public void move_botoes() {

        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call() {
                int[] vetT = new int[14];
                Platform.runLater(() -> {
                    gerarVetor(vet);
                    CodigoT[0].setFill(Color.WHITE);
                    CodigoT[1].setFill(Color.RED);
                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i <vet.length ; i++) {
                    vetT[i] = Integer.parseInt(vet[i].getText());
                }

                //vai ter os valores das variveis runs, e TL

                int runs = 5,TL = 14,tam=runs,i=0;
                int finalTam = tam;
                Platform.runLater(() -> {
                    Text tamT = new Text("tam: "+ finalTam);
                    runsT = new Text("runs: "+runs);
                    Text TLT = new Text("TL: "+ TL);
                    tamT.setFont(new Font(14));
                    tamT.setFill(Color.WHITE);
                    tamT.setLayoutX(50);
                    tamT.setLayoutY(50);
                    pane.getChildren().add(tamT);
                    runsT.setFont(new Font(14));
                    runsT.setFill(Color.WHITE);
                    runsT.setLayoutX(100);
                    runsT.setLayoutY(50);
                    pane.getChildren().add(runsT);
                    TLT.setFont(new Font(14));
                    TLT.setFill(Color.WHITE);
                    TLT.setLayoutX(150);
                    TLT.setLayoutY(50);
                    pane.getChildren().add(TLT);
                    CodigoT[1].setFill(Color.WHITE);
                    CodigoT[2].setFill(Color.RED);
                });



                //insercao direta
                for ( i = 0; i < TL; i += runs) {
                    int esq=i , dir=min((i+runs-1), (TL - 1)),pos, aux,j;
                    System.out.println(esq);
                    System.out.println(dir);
                    //mostrar na tela o codigo da insercao direta e as variveis esq dir pos e aux
                    Platform.runLater(() -> {
                        esqT = new Text("esq: "+esq);
                        esqT.setFont(new Font(14));
                        esqT.setFill(Color.WHITE);
                        esqT.setLayoutX(50);
                        esqT.setLayoutY(75);
                        pane.getChildren().add(esqT);

                        dirT = new Text("dirT: "+dir);
                        dirT.setFont(new Font(14));
                        dirT.setFill(Color.WHITE);
                        dirT.setLayoutX(100);
                        dirT.setLayoutY(75);
                        pane.getChildren().add(dirT);
                        CodigoT[2].setFill(Color.WHITE);
                        CodigoT[3].setFill(Color.RED);
                    });
        /*
                    //permutação na tela
                    for (int i = 0; i < 10; i++) {
                        Platform.runLater(() -> vet[0].setLayoutY(vet[0].getLayoutY() + 5));
                        Platform.runLater(() -> vet[1].setLayoutY(vet[1].getLayoutY() - 5));
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 16; i++) {
                        Platform.runLater(() -> vet[0].setLayoutX(vet[0].getLayoutX() + 5));
                        3
                        Platform.runLater(() -> vet[1].setLayoutX(vet[1].getLayoutX() - 5));
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 10; i++) {
                        Platform.runLater(() -> vet[0].setLayoutY(vet[0].getLayoutY() - 5));
                        Platform.runLater(() -> vet[1].setLayoutY(vet[1].getLayoutY() + 5));
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }*/

                    for (j = esq + 1; j <= dir; j++) {
                        aux = vetT[j];
                        pos = j;
                        while (pos > esq && vetT[pos - 1] > aux) {
                            //subindo a segunda variavel
                            int finalI = pos;
                            for (int l = 0; l < 10; l++) {

                                //subindo vetT[i]
                                Platform.runLater(() -> vet[finalI].setLayoutY(vet[finalI].getLayoutY() - 5));
                                Platform.runLater(() -> vet[finalI-1].setLayoutY(vet[finalI-1].getLayoutY() + 5));
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            diffX = vet[finalI].getLayoutX() - vet[finalI-1].getLayoutX();
                            stepX = diffX / 16;
                            for (int l = 0; l < 16; l++) {

                                Platform.runLater(() -> vet[finalI].setLayoutX(vet[finalI].getLayoutX() - stepX));
                                Platform.runLater(() -> vet[finalI-1].setLayoutX(vet[finalI-1].getLayoutX() + stepX));
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            for (int l = 0; l < 10; l++) {

                                //subindo vetT[i]
                                Platform.runLater(() -> vet[finalI].setLayoutY(vet[finalI].getLayoutY() + 5));
                                Platform.runLater(() -> vet[finalI-1].setLayoutY(vet[finalI-1].getLayoutY() - 5));
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                            vetT[pos] = vetT[pos -1];
                            pos--;
                        }
                        vetT[pos] = aux;
                    }
                    Platform.runLater(() -> pane.getChildren().remove(esqT));
                    Platform.runLater(() -> pane.getChildren().remove(dirT));
                }
                Platform.runLater(() -> pane.getChildren().add(esqT));
                Platform.runLater(() -> pane.getChildren().add(dirT));

                Button[] vetNovo = new Button[vet.length]; // Criar um novo array de botões

                for (int l = 0; l < vetT.length; l++) {
                    String valorBotao = String.valueOf(vetT[l]); // Converter o valor inteiro para uma string
                    Button botao = new Button(valorBotao); // Criar um novo botão com o texto correspondente
                    // Copiar as propriedades visuais do botão anterior (se necessário)
                    botao.setLayoutX(vet[l].getLayoutX());
                    botao.setLayoutY(vet[l].getLayoutY());
                    botao.setMinWidth(vet[l].getMinWidth());
                    botao.setMinHeight(vet[l].getMinHeight());
                    botao.setFont(vet[l].getFont());
                    // Adicionar o novo botão ao array
                    vetNovo[l] = botao;
                }
                vetCopia = vet;
                vet = vetNovo;

                printVetB(vet);
                printVet(vetT);

                int finalI1 = i;

                Platform.runLater(() -> {
                    Text vetCopT = new Text("Vet: ");
                    vetCopT.setFill(Color.WHITE);
                    vetCopT.setLayoutX(50);
                    vetCopT.setLayoutY(120);
                    vetCopT.setFont(new Font(14));
                    pane.getChildren().add(vetCopT);

                    texto.setText("Vet Após insercao Direta: ");
                    texto.setLayoutY(210);
                    int deslocamentoY = 120;

                    for (int l = 0; l < vet.length; l++) {
                        vet[l].setLayoutX(150 + l * 60); // Atualizar o layout do novo botão
                        Text indiceCop = new Text(String.valueOf(l));
                        indiceCop.setFill(Color.WHITE);
                        indiceCop.setLayoutX(vet[l].getLayoutX() + 20);
                        indiceCop.setLayoutY(vet[l].getLayoutY() + 60);
                        pane.getChildren().add(indiceCop);

                        vet[l].setLayoutY(100 + deslocamentoY); // Atualizar o layout do novo botão
                        Text indice = new Text(String.valueOf(l));
                        indice.setFill(Color.WHITE);
                        indice.setLayoutX(vet[l].getLayoutX() + 20); // Posição do índice abaixo do botão
                        indice.setLayoutY(vet[l].getLayoutY() + 60); // Deslocamento para baixo
                        pane.getChildren().add(indice);
                        pane.getChildren().add(vet[l]);
                    }
                });


                //iniciando merge
                for (tam = runs; tam < TL; tam = 2 * tam)
                    for (int esq = 0; esq < TL; esq += 2 * tam) {
                        int meio = esq + tam - 1;
                        int dir = min((esq + 2 * tam - 1), (TL - 1));
                        //merge
                        if (meio < dir){
                            int tam1 = meio - esq + 1, tam2 = dir - meio;
                            int[] vet1 = new int[tam1];
                            int[] vet2 = new int[tam2];
                            for (int pos = 0; pos < tam1; pos++)
                                vet1[pos] = vetT[esq + pos];

                            for (int pos = 0; pos < tam2; pos++)
                                vet2[pos] = vetT[meio + 1 + pos];

                           int j = 0,k = esq;
                            i = 0;
                            while (i < tam1 && j < tam2) {
                                if (vet1[i] <= vet2[j])
                                    vetT[k++] = vet1[i++];
                                else
                                    vetT[k++] = vet2[j++];
                            }
                            while (i < tam1)
                                vetT[k++] = vet1[i++];
                            while (j < tam2)
                                vetT[k++] = vet2[j++];
                        }
                    }
                printVet(vetT);

                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
    private int min(int i, int j) {
        return (i <= j) ? i : j;
    }
    public void printVet(int[] vetT){
        System.out.println("\nArray:");
        for (int num : vetT) {
            System.out.print(num + " ");
        }
    }


    public void printVetB(Button[] vetT){
        System.out.println("\nArray:");
        for (Button button : vetT) {
            // Se você quiser imprimir o texto do botão, use getText()
            System.out.print(button.getText() + " ");
            // Ou se você quiser imprimir outra propriedade, use o método apropriado
        }
    }


}