package transport;

public class Transport {
    private String nameTransport;
    private double kg;
    private double velocidad;

    public Transport(String nameTransport, double kg, double velocidad) {
        super();
        this.nameTransport = nameTransport;
        this.kg = kg;
        this.velocidad = velocidad;
    }

    public String getNameTransport() {
        return nameTransport;
    }

    public double getKg() {
        return kg;
    }

    public double getVelocidad() {
        return velocidad;
    }


    public boolean getsInTime(double kg, double distancia, double time) {

        double div = velocidad -kg * this.kg  ;
        return time > (distancia / (div)) && div > 0;




    }
}
