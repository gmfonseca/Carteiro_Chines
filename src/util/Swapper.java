package util;

public class Swapper {

    private long troca;
    private long[] trocas;
    private long total_trocas;
    private int iteracoes;
    private static int i;

    public Swapper(int n) {
        total_trocas=0;
        troca = 0;
        iteracoes=0;
        trocas = new long[n];
        i=0;
    }

    public long getTrocas(){
        return troca;
    }

    public long getTotalTrocas(){
        return total_trocas;
    }

    public long getMediaTrocas(){
        return total_trocas/iteracoes;
    }

    public double getDesvioPadrao(){
        long media = getMediaTrocas(), soma=0;

        for(long swap : trocas){
            soma += Math.pow((swap-media), 2);
        }

        double desvio = Math.sqrt(soma/(trocas.length-1));

        return desvio;
    }

    public boolean add(){
        troca++;
        return true;
    }

    public void reset(){
        troca = 0;
        iteracoes++;
    }

    public void add_total(){
        total_trocas += troca;
        trocas[i++] = troca;
    }


}