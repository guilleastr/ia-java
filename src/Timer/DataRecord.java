package Timer;

public class DataRecord {


    private String algorithm;

    private boolean bestAnswer;

    private double timeElapsed;

    private int result;

    private int bestResult;
    private String origin;
    private String destin;

    public DataRecord(String algorithm, double timeElapsed, int result, int bestResult, String origin, String destin) {
        this.algorithm = algorithm;
        this.bestAnswer = result == bestResult;
        this.timeElapsed = timeElapsed;
        this.result = result;
        this.bestResult = bestResult;
        this.origin=origin;
        this.destin=destin;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public boolean isBestAnswer() {
        return bestAnswer;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }




    public String toExcel(){
        return bestAnswer +"; "+timeElapsed +
                "; " + result +
                ";  " + bestResult +
                "; " + origin +
                "; " + destin +"; "; 
    }

    @Override
    public String toString() {
        if (bestAnswer) {
            return "DataRecord{" +
                    "algorithm='" + algorithm + '\'' +
                    ", bestAnswer=" + bestAnswer +
                    ", timeElapsed(ns)=" + timeElapsed +
                    ", result=" + result +
                    ", origin='" + origin + '\'' +
                    ", destin='" + destin + '\'' +
                    '}';
        }
        return "DataRecord{" +
                "algorithm='" + algorithm + '\'' +
                ", bestAnswer=" + bestAnswer +
                ", timeElapsed(ns)=" + timeElapsed +
                ", result=" + result +
                ", bestResult= " + bestResult +
                ", origin='" + origin + '\'' +
                ", destin='" + destin + '\'' +
                '}';

    }


}
