package util;

public class Comparison {

    private long comparacao;
    private long total_comparacoes;
    private long media_comparacoes;
    private long iteracoes;
    private long[] comparacoes;
    private static int i;

    public Comparison(int n) {
        total_comparacoes=0;
        iteracoes=0;
        comparacao = 0;
        comparacoes = new long[n];
        i=0;
    }

    public long getComparacoes() {
        return comparacao;
    }

    public long getTotalComparacoes(){
        return total_comparacoes;
    }

    public long getMediaComparacoes() {
        media_comparacoes = total_comparacoes/iteracoes;
        return media_comparacoes;
    }

    public double getDesvioPadrao(){
        long media = getMediaComparacoes(), soma=0;

        for(long comp : comparacoes){
            soma += Math.pow((comp-media), 2);
        }

        double desvio = Math.sqrt(soma/(comparacoes.length-1));

        return desvio;
    }

    public void reset() {
        comparacao = 0;
        iteracoes++;
    }

    public void add_total(){
        total_comparacoes += comparacao;
        comparacoes[i++] = comparacao;
    }

    public boolean add() {
        comparacao++;
        return true;
    }
}