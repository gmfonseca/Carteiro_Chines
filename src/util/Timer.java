package util;

public class Timer{

    private long start;
    private long end;
    private int current_time;
    private long total_time;
    private int iteracoes;
    private int[] tempos;
    private static int i;

    public Timer(int n) {
        start = 0;
        end = 0;
        iteracoes = 0;
        total_time=0;
        tempos = new int[n];
        i=0;
    }

    public int getTime() {
        return current_time;
    }

    public long getTotalTime() {
        return total_time;
    }
    
    public long getTempoMedio() {
        return total_time/iteracoes;
    }

    public double getDesvioPadrao(){
        long media = getTempoMedio(), soma=0;

        for(int temp : tempos){
            soma += Math.pow((temp-media), 2);
        }

        double desvio = Math.sqrt(soma/(tempos.length-1));

        return desvio;
    }

    public void reset() {
        start = 0;
        end = 0;
        iteracoes++;
    }

    public void add_total(){
        total_time += current_time;
        tempos[i++] = current_time;
    }

    public void start() {
            start = System.currentTimeMillis();
    }

    public void finish() {
        end = System.currentTimeMillis();
        current_time = (int) (end-start);
        reset();
    }

    public void add_iteracoes(){
        this.iteracoes++;
    }
}
