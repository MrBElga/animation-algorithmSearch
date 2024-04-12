    package com.example.animcao.tim;

    import java.util.Random;

    public class timSort {
        private static int[] vet;

        public timSort(int[] arr) {
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

        public void printVet(){
            System.out.println("\nArray:");
            for (int num : vet) {
                System.out.print(num + " ");
            }
        }
        public void tim() {
            int runs = 32,TL = 14;
            int run = tamMin(runs);


            for (int i = 0; i < TL; i += run) {
                insercaoDiretaTim(i, min((i + runs - 1), (TL - 1)));
                printVet();

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
        public static void main(String[] args) {
            vet = new int[14];
            Random random = new Random();
            for (int i = 0; i < 14; i++) {
                vet[i] = random.nextInt(100);            }
            System.out.println("Array randomico:");
            for (int num : vet) {
                System.out.print(num + " ");
            }
            // Criando uma instância de timSort
            timSort sorter = new timSort(vet);

            // Ordenando o array
            sorter.tim();

            // Imprimindo o array ordenado
            System.out.println("\nArray ordenado:");
            for (int num : vet) {
                System.out.print(num + " ");
            }
        }
    }
