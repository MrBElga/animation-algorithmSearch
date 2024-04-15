package com.example.animcao.tim;


import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class TimSort extends Application {

    public double diffX,stepX;
    public int k=0, i=0,j=0,l=0;
    private Text texto,Seta,SetaA,indice,indiceA,indiceAux,titulo,end,SetaAux;
    private Text runsT,esqT,dirT;
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

        for (int i = 0; i < tamanhoVetor; i++) {
            int valorAleatorio =  random.nextInt(1000);
            vet[i] = new Button(String.valueOf(valorAleatorio));
            vet[i].setLayoutX(150 + i * 60);
            vet[i].setLayoutY(100);
            vet[i].setMinHeight(40);
            vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);

            // Adicionando o índice abaixo do botão
            Text indice = new Text(String.valueOf(i));
            indice.setFill(Color.WHITE);
            indice.setLayoutX(vet[i].getLayoutX() + 20);
            indice.setLayoutY(vet[i].getLayoutY() + 60);
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

        indiceA = new Text("indice");
        indiceA.setFill(Color.CYAN);
        indiceA.setFont(new Font(14));
        pane.getChildren().add(indiceA);

        indiceAux = new Text("indiceAux");
        indiceAux.setFill(Color.GREEN);
        indiceAux.setFont(new Font(14));
        pane.getChildren().add(indiceAux);

        end = new Text("FINALIZADO");
        end.setFont(new Font(25));


        CodigoT = new Text[13];
        alocarCodigoTim(CodigoT);
        CodigoI = new Text[12];
        alocarCodigoInsercao(CodigoI);
        CodigoM = new Text[20];
        alocarCodigoMerge(CodigoM);
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
    private int tamMin(int runs) {
        int tam=runs;
        int i = 0;
        while (tam >= runs) {
            tam /= runs;
            i++;
        }
        return tam + i;
    }
    public void printVet(int[] vetT){
        System.out.println("\nArray:");
        for (int num : vetT) {
            System.out.print(num + " ");
        }
    }
    public void move_botoes() {

        Task<Void> task = new Task<Void>(){
            int finalKt, finalIAux =0 ,iAux = 0,esq=0,pos=0,tam;
            int[] vet1, vet2;
            Button[] vetor1,vetor2;
            Text tituloVet1,tituloVet2,tituloCodigoI,Titulo,varT;
            VBox codeContainerI,codeContainerM;
            Rectangle caixaExplicacao,caixaVar;
            @Override
            protected Void call() {

                Platform.runLater(()-> {
                    pane.getChildren().remove(caixaVar);
                    pane.getChildren().remove(varT);
                    varT = new Text("VARIAVEIS USADAS");
                    pane.getChildren().add(varT);
                    varT.setLayoutY(310);
                    varT.setLayoutX(50);
                    varT.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
                    caixaVar = new Rectangle(320, 520); // largura e altura da caixa
                    caixaVar.setFill(Color.BLACK); // cor de fundo da caixa
                    caixaVar.setStroke(Color.GREEN); // cor da borda da caixa+


                    caixaVar.setLayoutX(50); // posição x da caixa
                    caixaVar.setLayoutY(330); // posição y da caixa

                    // Adicionar um rótulo com o texto da explicação
                    Label varLabel = new Label(
                            "\tO QUE É CADA VARIAVEL\n" +
                                    "runs = determina o número de \nruns(sequências ordenadas) a serem\nutilizadas no algoritmo\n" +
                                    "run = representa o tamanho mínimo de uma run\n" +
                                    "TL = tamanho do vetor\n" +
                                    "meio =undice do elemento do meio\nda parte do vetor\n\n" +
                                    "esq = indice do elemento mais à\nesquerda da parte do vetor\n\n" +
                                    "dir = indice do elemento mais à\ndireita da parte do vetor\n\n" +
                                    "vet1 = representa a metade 1\n" +
                                    "vet2 = representa a metade 2\n" +
                                    "tam1 = tamanho do vetor1\n" +
                                    "tam2 = tamanho do vetor2\n" +
                                    "tamMin() = calcula o tamanho\nmínimo de uma run\n\n" +
                                    "min() = Função que verifica\nse o valor de uma variavel X\né menor que uma Y\n\n"


                    );
                    varLabel.setLayoutX(60); // posição x do rótulo dentro da caixa
                    varLabel.setLayoutY(340); // posição y do rótulo dentro da caixa
                    varLabel.setFont(new Font(14));
                    varLabel.setTextFill(Color.YELLOW);
                    // Adicionar o retângulo e o rótulo ao painel
                    pane.getChildren().addAll(caixaVar, varLabel);
                });
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

                for (i = 0; i <vet.length ; i++) {
                    vetT[i] = Integer.parseInt(vet[i].getText());
                }

                //vai ter os valores das variveis runs, e TL

                int runs = 2;
                int TL = 14;
                tam=runs;
                i=0;
                int run = tamMin(runs);
                System.out.println(runs+" "+TL+" "+tam+" "+run);
                Platform.runLater(() -> {
                    Text tamT = new Text("tam: "+ tam);
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
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    CodigoT[2].setFill(Color.WHITE);
                    CodigoT[3].setFill(Color.RED);

                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    Titulo = new Text("EXPLICAÇÃO");
                    pane.getChildren().add(Titulo);
                    Titulo.setLayoutY(490);
                    Titulo.setLayoutX(450);
                    Titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
                    caixaExplicacao = new Rectangle(600, 200); // largura e altura da caixa
                    caixaExplicacao.setFill(Color.BLACK); // cor de fundo da caixa
                    caixaExplicacao.setStroke(Color.GREEN); // cor da borda da caixa+


                    caixaExplicacao.setLayoutX(450); // posição x da caixa
                    caixaExplicacao.setLayoutY(500); // posição y da caixa

                    // Adicionar um rótulo com o texto da explicação
                    Label explicacaoLabel = new Label(
                            "\t\t\t\tFAZENDO A INSERÇÃO DIRETA\n"+
                                    "A inserção direta é um algoritmo simples de ordenação." +
                                    "Ele divide o array em partes ordenada \ne não ordenada.\n" +
                                    "Em cada passo, um elemento da parte não ordenada é " +
                                    "selecionado e inserido na parte \nordenada, mantendo a ordem " +
                                    "crescente (ou decrescente) dos elementos\n\n\n"+"ATENÇÃO: os elementos estão sendo trocados diretamente pois estão sendo\nanalisados de 2 em 2!!"
                    );
                    explicacaoLabel.setLayoutX(460); // posição x do rótulo dentro da caixa
                    explicacaoLabel.setLayoutY(520); // posição y do rótulo dentro da caixa
                    explicacaoLabel.setFont(new Font(14));
                    explicacaoLabel.setTextFill(Color.YELLOW);
                    // Adicionar o retângulo e o rótulo ao painel
                    pane.getChildren().addAll(caixaExplicacao, explicacaoLabel);
                });
                Platform.runLater(()-> {
                            codeContainerI = new VBox();
                            codeContainerI.setStyle("-fx-background-color: #515151; -fx-border-color: #022c10; -fx-border-width: 2px; -fx-border-radius: 2px");
                            codeContainerI.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
                            DropShadow dropShadow = new DropShadow();
                            dropShadow.setOffsetX(2);
                            dropShadow.setOffsetY(2);
                            codeContainerI.setEffect(dropShadow);

                            for (int i = 0; i < CodigoI.length; i++) {
                                CodigoI[i].setFill(Color.WHITE);
                                CodigoI[i].setFont(new Font(20));
                                codeContainerI.getChildren().add(CodigoI[i]);
                            }


                            CodigoI[0].setFill(Color.RED);
                            pane.getChildren().add(codeContainerI);
                            BorderPane root = new BorderPane();
                            root.setStyle("-fx-background-color: #000000;");
                            botao_inicio.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                            titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
                            tituloCodigoI = new Text("Insercao Direta");
                            tituloCodigoI.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
                            pane.getChildren().add(tituloCodigoI);
                            AnchorPane.setTopAnchor(tituloCodigoI, 400.0);
                            AnchorPane.setRightAnchor(tituloCodigoI, 170.0);
                            AnchorPane.setRightAnchor(codeContainerI, 10.0);
                            AnchorPane.setTopAnchor(codeContainerI, 440.0);
                        });


                //insercao direta
                for ( i = 0; i < TL; i += run) {

                    int esq= i , dir=Math.min((i+runs-1), (TL - 1)), aux,j;
                    //mostrar na tela o codigo da insercao direta e as variveis esq dir pos e aux
                    Platform.runLater(() -> {



                        esqT = new Text("esq: "+ esq);
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
                        CodigoT[3].setFill(Color.WHITE);
                        CodigoT[4].setFill(Color.RED);

                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(()->{
                        CodigoI[0].setFill(Color.WHITE);
                        CodigoI[1].setFill(Color.RED);

                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(()->{
                        CodigoI[1].setFill(Color.WHITE);
                        CodigoI[2].setFill(Color.RED);

                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (j = esq + 1; j <= dir; j++) {

                        aux = vetT[j];
                        Platform.runLater(()->{
                            CodigoI[2].setFill(Color.WHITE);
                            CodigoI[3].setFill(Color.RED);
                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pos = j;
                        Platform.runLater(()->{
                            CodigoI[3].setFill(Color.WHITE);
                            CodigoI[4].setFill(Color.RED);

                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Platform.runLater(()->{
                            CodigoI[4].setFill(Color.WHITE);
                            CodigoI[5].setFill(Color.RED);

                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (pos > esq && vetT[pos - 1] > aux) {

                            Platform.runLater(()->{
                                Seta.setLayoutX(vet[pos].getLayoutX());
                                Seta.setLayoutY(vet[pos].getLayoutY() + 70);
                                SetaA.setLayoutX(vet[pos-1].getLayoutX());
                                SetaA.setLayoutY(vet[pos-1].getLayoutY() + 70);
                                indice.setText("pos");
                                indice.setLayoutX(Seta.getLayoutX());
                                indice.setLayoutY(Seta.getLayoutY() + 10);
                                indiceA.setText("pos-1");
                                indiceA.setLayoutX(SetaA.getLayoutX());
                                indiceA.setLayoutY(SetaA.getLayoutY() + 10);
                                CodigoI[5].setFill(Color.WHITE);
                                CodigoI[6].setFill(Color.RED);
                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //subindo a segunda variavel
                            for (l = 0; l < 10; l++) {
                                //subindo vetT[i]
                                Platform.runLater(() ->{ vet[pos].setLayoutY(vet[pos].getLayoutY() - 5);
                                    vet[pos-1].setLayoutY(vet[pos-1].getLayoutY() + 5);
                                });
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            diffX = vet[pos].getLayoutX() - vet[pos-1].getLayoutX();
                            stepX = diffX / 16;
                            for (int l = 0; l < 16; l++) {
                                Platform.runLater(() -> {vet[pos].setLayoutX(vet[pos].getLayoutX() - stepX);
                                    vet[pos-1].setLayoutX(vet[pos-1].getLayoutX() + stepX);
                                });
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int l = 0; l < 10; l++) {
                                //subindo vetT[i]
                                Platform.runLater(() -> {vet[pos].setLayoutY(vet[pos].getLayoutY() + 5);
                                    vet[pos-1].setLayoutY(vet[pos-1].getLayoutY() - 5);
                                });
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            vetT[pos] = vetT[pos -1];
                            Platform.runLater(()->{
                                CodigoI[6].setFill(Color.WHITE);
                                CodigoI[7].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            pos--;
                            Platform.runLater(()->{
                                CodigoI[7].setFill(Color.WHITE);
                                CodigoI[5].setFill(Color.RED);
                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        Platform.runLater(()->{
                            CodigoI[5].setFill(Color.WHITE);
                            CodigoI[9].setFill(Color.RED);


                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        vetT[pos] = aux;

                        Platform.runLater(()->{
                            CodigoI[9].setFill(Color.WHITE);
                            CodigoI[2].setFill(Color.RED);


                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {pane.getChildren().remove(esqT);
                        pane.getChildren().remove(dirT);
                    });

                    //voltando no for
                    Platform.runLater(()->{
                        for (int i = 0; i < CodigoI.length ; i++) {
                            CodigoI[i].setFill(Color.WHITE);
                        }

                        CodigoT[4].setFill(Color.WHITE);
                        CodigoT[3].setFill(Color.RED);

                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(() -> {pane.getChildren().add(esqT);
                    pane.getChildren().add(dirT);
                });

                Button[] vetNovo = new Button[vet.length]; // Criar um novo array de botões
                for (int l = 0; l < vetT.length; l++) {
                    Button botao = new Button(String.valueOf(vetT[l])); // Criar um novo botão com o texto correspondente
                    // Copiar as propriedades visuais do botão anterior (se necessário)
                    botao.setLayoutX(vet[l].getLayoutX());
                    botao.setLayoutY(vet[l].getLayoutY());
                    botao.setMinWidth(vet[l].getMinWidth());
                    botao.setMinHeight(vet[l].getMinHeight());
                    botao.setFont(vet[l].getFont());
                    vetNovo[l] = botao;
                }
                vet = vetNovo;

                Platform.runLater(()-> {
                    Text vetCopT = new Text("Vet: ");
                    vetCopT.setFill(Color.WHITE);
                    vetCopT.setLayoutX(50);
                    vetCopT.setLayoutY(120);
                    vetCopT.setFont(new Font(14));
                    pane.getChildren().add(vetCopT);
                    texto.setText("Vet Após insercao Direta: ");
                    texto.setLayoutY(210);

                    for (int l = 0; l < vet.length; l++) {
                        vet[l].setLayoutX(150 + l * 60); // Atualizar o layout do novo botão
                        vet[l].setLayoutY(220); // Atualizar o layout do novo botão
                        Text indice = new Text(String.valueOf(l));
                        indice.setFill(Color.WHITE);
                        indice.setLayoutX(vet[l].getLayoutX() + 20); // Posição do índice abaixo do botão
                        indice.setLayoutY(vet[l].getLayoutY() + 60); // Deslocamento para baixo

                        pane.getChildren().add(indice);
                        pane.getChildren().add(vet[l]);

                    }
                });

                Platform.runLater(()->{
                    pane.getChildren().remove(tituloCodigoI);
                    pane.getChildren().remove(codeContainerI);
                });
                Platform.runLater(()->{
                    CodigoT[3].setFill(Color.WHITE);
                    CodigoT[5].setFill(Color.RED);

                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(()->{
                    pane.getChildren().remove(caixaExplicacao);
                    pane.getChildren().remove(Titulo);
                    Titulo = new Text("EXPLICAÇÃO");
                    pane.getChildren().add(Titulo);
                    Titulo.setLayoutY(490);
                    Titulo.setLayoutX(450);
                    Titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
                    caixaExplicacao = new Rectangle(600, 200); // largura e altura da caixa
                    caixaExplicacao.setFill(Color.BLACK); // cor de fundo da caixa
                    caixaExplicacao.setStroke(Color.GREEN); // cor da borda da caixa+


                    caixaExplicacao.setLayoutX(450); // posição x da caixa
                    caixaExplicacao.setLayoutY(500); // posição y da caixa

                    // Adicionar um rótulo com o texto da explicação
                    Label explicacaoLabel = new Label(
                            "\t\t\t\t\tFAZENDO O MERGE\n"+
                                    "O Merge Sort é um algoritmo de ordenação eficiente que utiliza " +
                                    "a técnica de divisão\nEle divide o array em duas metades, " +
                                    "ordena cada metade separadamente e depois mescla\nas duas metades " +
                                    "já ordenadas para obter o array final ordenado.\n\n\n"+"ATENÇÃO: apenas o vetor (vet) recebe o elemento!!"

                    );
                    explicacaoLabel.setLayoutX(460); // posição x do rótulo dentro da caixa
                    explicacaoLabel.setLayoutY(520); // posição y do rótulo dentro da caixa
                    explicacaoLabel.setFont(new Font(14));
                    explicacaoLabel.setTextFill(Color.YELLOW);
                    // Adicionar o retângulo e o rótulo ao painel
                    pane.getChildren().addAll(caixaExplicacao, explicacaoLabel);
                });
                //iniciando merge
                for (tam = run; tam < TL; tam = 2 * tam) {
                    System.out.println("runs: "+runs+" TL: "+TL+" tam: "+tam+" run: "+run);
                    Platform.runLater(()->{
                        CodigoT[5].setFill(Color.WHITE);
                        CodigoT[6].setFill(Color.RED);

                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (esq = 0; esq < TL; esq += 2 * tam) {
                        Platform.runLater(()->{
                            CodigoT[6].setFill(Color.WHITE);
                            CodigoT[7].setFill(Color.RED);

                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int meio = esq + tam - 1;
                        Platform.runLater(()->{
                            CodigoT[7].setFill(Color.WHITE);
                            CodigoT[8].setFill(Color.RED);

                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int dir = Math.min((esq + 2 * tam - 1), (TL - 1));
                        System.out.println("esq: "+esq+" meio: "+meio+" dir: "+dir);
                        //merge

                        Platform.runLater(()->{
                            CodigoT[8].setFill(Color.WHITE);
                            CodigoT[9].setFill(Color.RED);

                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Platform.runLater(()->{
                            CodigoT[9].setFill(Color.WHITE);
                            CodigoT[10].setFill(Color.RED);

                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (meio < dir){
                            Platform.runLater(()-> {
                                codeContainerM = new VBox();
                                codeContainerM.setStyle("-fx-background-color: #515151; -fx-border-color: #022c10; -fx-border-width: 2px; -fx-border-radius: 2px");
                                codeContainerM.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
                                DropShadow dropShadow = new DropShadow();
                                dropShadow.setOffsetX(2);
                                dropShadow.setOffsetY(2);
                                codeContainerM.setEffect(dropShadow);

                                for (int i = 0; i < CodigoM.length; i++) {
                                    CodigoM[i].setFill(Color.WHITE);
                                    CodigoM[i].setFont(new Font(15));
                                    codeContainerM.getChildren().add(CodigoM[i]);
                                }


                                CodigoM[0].setFill(Color.RED);
                                pane.getChildren().add(codeContainerM);
                                BorderPane root = new BorderPane();
                                root.setStyle("-fx-background-color: #000000;");
                                botao_inicio.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                                titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
                                tituloCodigoI = new Text("Merge");
                                tituloCodigoI.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #4CAF50;");
                                pane.getChildren().add(tituloCodigoI);
                                AnchorPane.setTopAnchor(tituloCodigoI, 400.0);
                                AnchorPane.setRightAnchor(tituloCodigoI, 170.0);
                                AnchorPane.setRightAnchor(codeContainerM, 10.0);
                                AnchorPane.setTopAnchor(codeContainerM, 440.0);
                            });
                            int tam1 =  meio - esq + 1, tam2 = dir - meio;
                            Platform.runLater(()->{
                                CodigoM[0].setFill(Color.WHITE);
                                CodigoM[1].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int[] vet1 = new int[tam1];
                            Platform.runLater(()->{
                                CodigoM[1].setFill(Color.WHITE);
                                CodigoM[2].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int[] vet2 = new int[tam2];
                            Platform.runLater(()->{
                                CodigoM[2].setFill(Color.WHITE);
                                CodigoM[3].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            vetor1 = new Button[tam1];
                            vetor2 = new Button[tam2];;


                            Platform.runLater(()->{
                                CodigoM[3].setFill(Color.WHITE);
                                CodigoM[4].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (pos = 0; pos < tam1 ; pos++) {
                                    Platform.runLater(()->{
                                        CodigoM[4].setFill(Color.WHITE);
                                        CodigoM[5].setFill(Color.RED);

                                    });
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(() -> {
                                        tituloVet1 = new Text("vet1: ");
                                        tituloVet1.setFont(new Font(14));
                                        tituloVet1.setFill(Color.WHITE);
                                        tituloVet1.setLayoutX(450);
                                        tituloVet1.setLayoutY(350);
                                        pane.getChildren().add(tituloVet1);

                                            vetor1[pos] = new Button(String.valueOf( vetT[esq + pos]));
                                            vetor1[pos].setMinHeight(40);
                                            vetor1[pos].setMinWidth(40);
                                            vetor1[pos].setFont(new Font(14));
                                            vetor1[pos].setLayoutX(480+pos *60);
                                            vetor1[pos].setLayoutY(330);
                                            pane.getChildren().add(vetor1[pos]);
                                    });
                                Platform.runLater(()->{
                                    CodigoM[5].setFill(Color.WHITE);
                                    CodigoM[4].setFill(Color.RED);

                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                            Platform.runLater(()->{
                                CodigoM[4].setFill(Color.WHITE);
                                CodigoM[5].setFill(Color.WHITE);
                                CodigoM[6].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (pos = 0; pos < tam2 ; pos++) {
                                Platform.runLater(()->{
                                    CodigoM[6].setFill(Color.WHITE);
                                    CodigoM[7].setFill(Color.RED);

                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Platform.runLater(() -> {
                                    tituloVet2 = new Text("vet2: ");
                                    tituloVet2.setFont(new Font(14));
                                    tituloVet2.setFill(Color.WHITE);
                                    tituloVet2.setLayoutX(450);
                                    tituloVet2.setLayoutY(430);
                                    pane.getChildren().add(tituloVet2);

                                    vetor2[pos] = new Button(String.valueOf(vetT[meio + 1 + pos]));
                                    vetor2[pos].setMinHeight(40);
                                    vetor2[pos].setMinWidth(40);
                                    vetor2[pos].setFont(new Font(14));
                                    vetor2[pos].setLayoutX(480 + pos * 60);
                                    vetor2[pos].setLayoutY(400);
                                    pane.getChildren().add(vetor2[pos]);

                                });
                                Platform.runLater(()->{
                                    CodigoM[7].setFill(Color.WHITE);
                                    CodigoM[6].setFill(Color.RED);

                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int pos = 0; pos <tam1 ; pos++) {
                                vet1[pos] = vetT[esq + pos];
                            }

                            for (int pos = 0; pos <tam2 ; pos++) {
                                vet2[pos] = vetT[meio + 1 + pos];
                            }

                            printVet(vet1);
                            printVet(vet2);
                            iAux = 0;
                            j = 0;
                            k = esq;
                            Platform.runLater(()->{
                                CodigoM[6].setFill(Color.WHITE);
                                CodigoM[7].setFill(Color.WHITE);
                                CodigoM[8].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Platform.runLater(()->{
                                CodigoM[8].setFill(Color.WHITE);
                                CodigoM[9].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            while (iAux < tam1 && j < tam2) {

                                Platform.runLater(()->{
                                    CodigoM[9].setFill(Color.WHITE);
                                    CodigoM[10].setFill(Color.RED);
                                    if(vetor1.length >iAux) {
                                        Seta.setLayoutX(vetor1[iAux].getLayoutX());
                                        Seta.setLayoutY(vetor1[iAux].getLayoutY() + 70);
                                    }
                                    SetaA.setLayoutX(vet[k].getLayoutX());
                                    SetaA.setLayoutY(vet[k].getLayoutY() + 70);
                                    if(vetor2.length >j){
                                        SetaAux.setLayoutX(vetor2[j].getLayoutX());
                                        SetaAux.setLayoutY(vetor2[j].getLayoutY() + 70);
                                    }
                                    indiceAux.setText(" j");
                                    indiceAux.setLayoutX(SetaAux.getLayoutX());
                                    indiceAux.setLayoutY(SetaAux.getLayoutY() + 10);
                                    indice.setText(" i");
                                    indice.setLayoutX(Seta.getLayoutX());
                                    indice.setLayoutY(Seta.getLayoutY() + 10);
                                    indiceA.setText(" k");
                                    indiceA.setLayoutX(SetaA.getLayoutX());
                                    indiceA.setLayoutY(SetaA.getLayoutY() + 10);
                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (vet1[iAux] <= vet2[j]) {
                                    Platform.runLater(()->{
                                        CodigoM[10].setFill(Color.WHITE);
                                        CodigoM[11].setFill(Color.RED);


                                    });
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    //permutação na tela
                                    for (int i = 0; i < 10; i++) {
                                        Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 5));
                                        Platform.runLater(() -> vetor1[iAux].setLayoutY(vetor1[iAux].getLayoutY() - 5));
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    diffX = vet[k].getLayoutX() - vetor1[iAux].getLayoutX();
                                    stepX = diffX / 16;
                                    for (int i = 0; i < 16; i++) {
                                        Platform.runLater(() -> vet[k].setLayoutX(vet[k].getLayoutX() - stepX));
                                        Platform.runLater(() -> vetor1[iAux].setLayoutX(vetor1[iAux].getLayoutX() + stepX));
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    for (int i = 0; i < 10; i++) {
                                        Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 6));
                                        Platform.runLater(() -> vetor1[iAux].setLayoutY(vetor1[iAux].getLayoutY() - 6));
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    Button aux = vet[k];
                                    vet[k] = vetor1[iAux];
                                    vetor1[iAux]=aux;
                                    vetT[k] = vet1[iAux];
                                    k++;
                                    iAux++;
                                } else {
                                    Platform.runLater(()->{
                                        CodigoM[10].setFill(Color.WHITE);
                                        CodigoM[13].setFill(Color.RED);

                                    });
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    //permutação na tela
                                    for (int i = 0; i < 10; i++) {
                                        Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 5));
                                        Platform.runLater(() -> vetor2[j].setLayoutY(vetor2[j].getLayoutY() - 13));
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    diffX = vet[k].getLayoutX() - vetor2[j].getLayoutX();
                                    stepX = diffX / 16;
                                    for (int i = 0; i < 16; i++) {
                                        Platform.runLater(() -> vet[k].setLayoutX(vet[k].getLayoutX() - stepX));
                                        Platform.runLater(() -> vetor2[j].setLayoutX(vetor2[j].getLayoutX() + stepX));
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    for (int i = 0; i < 10; i++) {
                                        Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 13));
                                        Platform.runLater(() -> vetor2[j].setLayoutY(vetor2[j].getLayoutY() - 5));
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    Button aux = vet[k];
                                    vet[k] = vetor2[j];
                                    vetor2[j]=aux;
                                    vetT[k] = vet2[j];
                                    k++;
                                    j++;
                                }
                                Platform.runLater(()->{

                                    CodigoM[10].setFill(Color.WHITE);
                                    CodigoM[11].setFill(Color.WHITE);
                                    CodigoM[12].setFill(Color.WHITE);
                                    CodigoM[13].setFill(Color.WHITE);
                                    CodigoM[14].setFill(Color.WHITE);
                                    CodigoM[9].setFill(Color.RED);

                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            Platform.runLater(()->{
                                CodigoM[9].setFill(Color.WHITE);
                                CodigoM[10].setFill(Color.WHITE);
                                CodigoM[11].setFill(Color.WHITE);
                                CodigoM[12].setFill(Color.WHITE);
                                CodigoM[13].setFill(Color.WHITE);
                                CodigoM[15].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            while (iAux < tam1) {
                                Platform.runLater(()->{
                                    CodigoM[15].setFill(Color.WHITE);
                                    CodigoM[16].setFill(Color.RED);
                                    if(vetor1.length >iAux) {
                                        Seta.setLayoutX(vetor1[iAux].getLayoutX());
                                        Seta.setLayoutY(vetor1[iAux].getLayoutY() + 70);
                                    }
                                    SetaA.setLayoutX(vet[k].getLayoutX());
                                    SetaA.setLayoutY(vet[k].getLayoutY() + 70);
                                    if(vetor2.length >j){
                                        SetaAux.setLayoutX(vetor2[j].getLayoutX());
                                        SetaAux.setLayoutY(vetor2[j].getLayoutY() + 70);
                                    }
                                    indiceAux.setText(" j");
                                    indiceAux.setLayoutX(SetaAux.getLayoutX());
                                    indiceAux.setLayoutY(SetaAux.getLayoutY() + 10);
                                    indice.setText(" i");
                                    indice.setLayoutX(Seta.getLayoutX());
                                    indice.setLayoutY(Seta.getLayoutY() + 10);
                                    indiceA.setText(" k");
                                    indiceA.setLayoutX(SetaA.getLayoutX());
                                    indiceA.setLayoutY(SetaA.getLayoutY() + 10);
                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                //permutação na tela
                                for (int i = 0; i < 10; i++) {
                                    Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 5));
                                    Platform.runLater(() -> vetor1[iAux].setLayoutY(vetor1[iAux].getLayoutY() - 5));
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                diffX = vet[k].getLayoutX() - vetor1[iAux].getLayoutX();
                                stepX = diffX / 16;
                                for (int i = 0; i < 16; i++) {
                                    Platform.runLater(() -> vet[k].setLayoutX(vet[k].getLayoutX() - stepX));
                                    Platform.runLater(() -> vetor1[iAux].setLayoutX(vetor1[iAux].getLayoutX() + stepX));
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int i = 0; i < 10; i++) {
                                    Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 6));
                                    Platform.runLater(() -> vetor1[iAux].setLayoutY(vetor1[iAux].getLayoutY() - 6));
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Button aux = vet[k];
                                vet[k] = vetor1[iAux];
                                vetor1[iAux]=aux;
                                vetT[k] = vet1[iAux];
                                k++;
                                iAux++;
                                Platform.runLater(()->{
                                    CodigoM[16].setFill(Color.WHITE);
                                    CodigoM[15].setFill(Color.RED);

                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            Platform.runLater(()->{
                                CodigoM[15].setFill(Color.WHITE);
                                CodigoM[17].setFill(Color.RED);

                            });
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            while (j < tam2) {
                                Platform.runLater(()->{
                                    CodigoM[17].setFill(Color.WHITE);
                                    CodigoM[18].setFill(Color.RED);
                                    if(vetor1.length >iAux) {
                                        Seta.setLayoutX(vetor1[iAux].getLayoutX());
                                        Seta.setLayoutY(vetor1[iAux].getLayoutY() + 70);
                                    }
                                    SetaA.setLayoutX(vet[k].getLayoutX());
                                    SetaA.setLayoutY(vet[k].getLayoutY() + 70);
                                    if(vetor2.length >j){
                                        SetaAux.setLayoutX(vetor2[j].getLayoutX());
                                        SetaAux.setLayoutY(vetor2[j].getLayoutY() + 70);
                                    }
                                    indiceAux.setText(" j");
                                    indiceAux.setLayoutX(SetaAux.getLayoutX());
                                    indiceAux.setLayoutY(SetaAux.getLayoutY() + 10);
                                    indice.setText(" i");
                                    indice.setLayoutX(Seta.getLayoutX());
                                    indice.setLayoutY(Seta.getLayoutY() + 10);
                                    indiceA.setText(" k");
                                    indiceA.setLayoutX(SetaA.getLayoutX());
                                    indiceA.setLayoutY(SetaA.getLayoutY() + 10);

                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                //permutação na tela
                                for (int i = 0; i < 10; i++) {
                                    Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 5));
                                    Platform.runLater(() -> vetor2[j].setLayoutY(vetor2[j].getLayoutY() - 13));
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                diffX = vet[k].getLayoutX() - vetor2[j].getLayoutX();
                                stepX = diffX / 16;
                                for (int i = 0; i < 16; i++) {
                                    Platform.runLater(() -> vet[k].setLayoutX(vet[k].getLayoutX() - stepX));
                                    Platform.runLater(() -> vetor2[j].setLayoutX(vetor2[j].getLayoutX() + stepX));
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int i = 0; i < 10; i++) {
                                    Platform.runLater(() -> vet[k].setLayoutY(vet[k].getLayoutY() + 13));
                                    Platform.runLater(() -> vetor2[j].setLayoutY(vetor2[j].getLayoutY() - 5));
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Button aux = vet[k];
                                vet[k] = vetor2[j];
                                vetor2[j]=aux;
                                vetT[k] = vet2[j];
                                j++;
                                k++;
                                Platform.runLater(()->{
                                    CodigoM[18].setFill(Color.WHITE);
                                    CodigoM[17].setFill(Color.RED);

                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int i = 0; i < CodigoT.length ; i++)
                                CodigoT[i].setFill(Color.WHITE);
                            for (int i = 0; i <CodigoM.length ; i++)
                                    CodigoM[i].setFill(Color.WHITE);


                            for (int i = 0; i <vetor1.length; i++) {
                                int finalI = i;
                                Platform.runLater(() -> pane.getChildren().remove(vetor1[finalI]));
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int i = 0; i <vetor2.length; i++) {
                                int finalI = i;
                                Platform.runLater(() -> pane.getChildren().remove(vetor2[finalI]));
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            Platform.runLater(() ->{
                                pane.getChildren().remove(tituloVet1);
                                pane.getChildren().remove(tituloVet2);
                            });
                            System.out.println("\nvetor primeiro for ");
                            printVet(vetT);

                        }
                        System.out.println("\nvetor segundo for ");
                        printVet(vetT);
                    }


                }
                Platform.runLater(() ->{

                    pane.getChildren().removeAll(Seta,SetaA,SetaAux,indice,indiceA,indiceAux,tituloVet1,tituloVet2);
                    texto.setText("Vet ordenado apõs o Merge: ");
                });
                printVet(vetT);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}