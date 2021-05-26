// Interfaz para aplicar patron PROTOTYPE y FACTORY METHOD
interface BaseVehiculos {
    public BaseVehiculos clonar();
    public String getReferencia();
}


// Implementacion del patron FACTORY METHOD
abstract class Vehiculos implements BaseVehiculos {

    protected final String referencia;
    protected final int velocidadMaxima;
    protected final Colores color;

    public Vehiculos(String referencia, int velocidadMaxima, Colores color) {
        this.referencia = referencia;
        this.velocidadMaxima = velocidadMaxima;
        this.color = color;
    }

    public abstract int getVelocidadMaxima();

    public abstract Colores getColor();

    public BaseVehiculos clonar() {
        return this; // Clonacion Fake
    }

    @Override
    public String toString() {
        return "Referencia: " + referencia +
                "\nVelocidad maxima: " + velocidadMaxima + " km/h" +
                "\nColor: " + color;
    }
}


class Tipado {
    
    private String referencia = "";
    private int velocidadMaxima = 0;
    private Colores color = Colores.NEGRO;
    private boolean correcto = true;

    public Tipado(boolean valido) {
        this.correcto = valido;
    }

    public Tipado(String ref, int velMax, Colores color) {
        this.referencia =  ref;
        this.velocidadMaxima = velMax;
        this.color = color;
    }

    public void setIntegridad(boolean valido) {
        this.correcto = valido;
    }

    public boolean getIntegridad() {
        return correcto;
    }

    public String getReferencia() {
        return referencia;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public Colores getColor() {
        return color;
    }
}
