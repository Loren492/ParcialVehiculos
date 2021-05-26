public class Carro extends Vehiculos implements BaseVehiculos {

    private final int numerodepuertas;

    public Carro(String referencia, int velocidadMaxima, Colores color, int numerodepuertas) {
        super(referencia, velocidadMaxima, color);
        this.numerodepuertas = numerodepuertas;
    }
    public String getReferencia() {
        return this.referencia;
    }

    public int getVelocidadMaxima() {
        return this.velocidadMaxima;
    }
    public Colores getColor() {
        return this.color;
    }

    @Override
    public BaseVehiculos clonar() {
        return new Carro(referencia, velocidadMaxima, color, numerodepuertas);
    }
    
    @Override
    public String toString() {
        return super.toString() +
                "\nNumero de puertas: " + this.numerodepuertas;
    }
}