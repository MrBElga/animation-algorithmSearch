package com.example.animcao.tim;

import java.util.Arrays;
import java.util.Random;
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

public class algoritimo extends Application {

    public int k=0;
    private Text K,texto,Seta,SetaA,indice,titulo,indiceText,end,SetaAux,indiceA;
    private Text runT, runsT, TLT,esqT,dirT,posT;
    AnchorPane pane;
    Button botao_inicio;
    private Button []vet;
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
        codigo[3] = new Text("     for (int i = 0; i < TL; i += run)");
        codigo[4] = new Text("        insercaoDiretaTim(i, min((i + runs - 1), (TL - 1)));");
        codigo[5] = new Text("     for (int tam = run; tam < TL; tam = 2 * tam)");
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
        texto.setLayoutY(85);
        texto.setFont(new Font(14));
        pane.getChildren().add(texto);

        // Adicionando os botões representando o vetor
        vet = new Button[tamanhoVetor];
        for (int i = 0; i < tamanhoVetor; i++) {
            int valorAleatorio = random.nextInt(1000); // Gerando número aleatório entre 0 e 99

            vet[i] = new Button(String.valueOf(valorAleatorio));
            vet[i].setLayoutX(150 + i * 60); // Espaçamento entre os botões
            vet[i].setLayoutY(60);
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

                //vai ter os valores das variveis runs, run e TL

                int runs = 2,TL = 14,run,tam=runs,i=0;
                int finalTam = tam;
                Platform.runLater(() -> {
                    Text tamT = new Text("tam: "+ finalTam);
                    runsT = new Text("runs: "+runs);
                    Text TLT = new Text("TL: "+ TL);
                    tamT.setFont(new Font(14));
                    tamT.setFill(Color.WHITE);
                    tamT.setLayoutX(50);
                    tamT.setLayoutY(150);
                    pane.getChildren().add(tamT);
                    runsT.setFont(new Font(14));
                    runsT.setFill(Color.WHITE);
                    runsT.setLayoutX(100);
                    runsT.setLayoutY(150);
                    pane.getChildren().add(runsT);
                    TLT.setFont(new Font(14));
                    TLT.setFill(Color.WHITE);
                    TLT.setLayoutX(150);
                    TLT.setLayoutY(150);
                    pane.getChildren().add(TLT);
                    CodigoT[1].setFill(Color.WHITE);
                    CodigoT[2].setFill(Color.RED);
                });

                //tamanho do run defino
                while (tam >= runs) {
                    tam /= runs;
                    i++;
                }
                run = tam + i;
                Platform.runLater(() -> {
                    runT = new Text("run: "+run);
                    runT.setFont(new Font(14));
                    runT.setFill(Color.WHITE);
                    runT.setLayoutX(200);
                    runT.setLayoutY(150);
                    pane.getChildren().add(runT);
                    CodigoT[2].setFill(Color.WHITE);
                    CodigoT[3].setFill(Color.RED);
                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //insercao direta
                for ( i = 0; i < TL; i += run) {
                    int esq=i , dir=min((i+runs-1), (TL - 1)),pos, aux,j;
                    //mostrar na tela o codigo da insercao direta e as variveis esq dir pos e aux
                    Platform.runLater(() -> {
                        esqT = new Text("esq: "+esq);
                        esqT.setFont(new Font(14));
                        esqT.setFill(Color.WHITE);
                        esqT.setLayoutX(50);
                        esqT.setLayoutY(160);
                        pane.getChildren().add(esqT);
                        dirT = new Text("dirT: "+esq);
                        dirT.setFont(new Font(14));
                        dirT.setFill(Color.WHITE);
                        dirT.setLayoutX(100);
                        dirT.setLayoutY(160);
                        pane.getChildren().add(dirT);
                        CodigoT[2].setFill(Color.WHITE);
                        CodigoT[3].setFill(Color.RED);
                    });
                    for (j = esq + 1; i <= dir; i++) {
                        aux = vetT[i];
                        pos = i;
                        while (pos > esq && vetT[pos - 1] > aux) {
                            vet[pos] = vet[pos - 1];
                            pos--;
                        }
                        vetT[pos] = aux;
                    }
                }
                //iniciando merge
                for (tam = run; tam < TL; tam = 2 * tam)
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
}