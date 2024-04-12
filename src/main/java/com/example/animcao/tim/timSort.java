package com.example.animcao.tim;

public class timSort {
    private int[] vet;
    private int TL = 14;
    private int tamMin(int runs) {
        int tam=runs;
        int i = 0;
        while (tam >= runs) {
            tam /= runs;
            i++;
        }
        return tam + i;
    }

    private void insercaoDiretaTim(int esq, int dir){
        int pos, aux;
        for (int i = esq + 1; i <= dir; i++) {
            aux = vet[i];
            pos = i;
            while (pos > esq && vet[pos-1] > aux) {
                vet[pos] = vet[pos-1];
                pos--;
            }
            vet[pos] = aux;
        }
    }

    private void mergeTim(int esq, int meio, int dir){
        int tam1 = meio - esq + 1, tam2 = dir - meio;
        int[] vet1 = new int[tam1];
        int[] vet2 = new int[tam2];
        for (int pos = 0; pos < tam1; pos++)
            vet1[pos] = vet[esq + pos];

        for (int pos = 0; pos < tam2; pos++)
            vet2[pos] = vet[meio + 1 + pos];

        int i = 0,j = 0,k = esq;
        while (i < tam1 && j < tam2) {
            if (vet1[i] <= vet2[j])
                vet[k++] = vet1[i++];
            else
                vet[k++] = vet2[j++];
        }
        while (i < tam1)
            vet[k++] = vet1[i++];
        while (j < tam2)
            vet[k++] = vet2[j++];
    }
    private int min(int i, int j) {
        return (i <= j) ? i : j;
    }
    public void tim(int runs) {
        int run = tamMin(runs);

        for (int i = 0; i < TL; i += run) {
            insercaoDiretaTim(i, min((i + runs - 1), (TL - 1)));
        }

        for (int tam = run; tam < TL; tam = 2 * tam) {
            for (int esq = 0; esq < TL; esq += 2 * tam) {
                int meio = esq + tam - 1;
                int dir = min((esq + 2 * tam - 1), (TL - 1));
                if (meio < dir)
                    mergeTim(esq, meio, dir);
            }
        }
    }
}
