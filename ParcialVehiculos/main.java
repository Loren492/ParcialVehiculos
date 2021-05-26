import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class main {

    static Scanner entrada = new Scanner(System.in); // Defino la entrada del escaner como statico para usarlo en todas las funciones

    public static void main(String[] args) {

        int opcion, entradasUsuario;
        String opcionVehiculo;
        Tipado datos; //Almecenar datos basicos del vehiculo registrado
        ConstructorYate creadorYate = new ConstructorYate();
        ArrayList<Vehiculos> garaje = new ArrayList();  // Definino un arrayList de objetos
                                                        // para almacenar los diferentes objetos (tipos de vehiculos)

        do {
            mensaje("Deseas agregar un vehiculo\n 1. Sí\n 2. Mostrar garaje\n 3. Encontrar vehiculo con la mayor velocidad maxima\n 4. Encontrar vehiculo con la menor velocidad maxima\n 5. Ordenar vehiculos por velocidad maxima\n 6. Solo vehiculos de color NEGRO o AZUL\n 7. Concatenar en un solo String las referencias de los vehiculos de color BLANCO o VERDE\n 8.Salir  ");
            try {   
                opcion = Integer.parseInt(entrada.nextLine());
            } catch (Exception e) {
                mensaje("Entrada invalida [EM1n]");
                opcion = 0;
                limpiarPantalla(1);
                continue;
            }
            
            switch (opcion) {
                case 1:
                    do{
                        if (garaje.size() >= 10){
                            mensajeAlerta("El garaje esta lleno");
                            break;
                        }
                        limpiarPantalla((float)0.001);
                        mensaje("Elija una opción: \n A. Avion\n B. Yate\n C. Carro\n D. Cancelar");
                        opcionVehiculo = entrada.nextLine().toUpperCase();

                        switch (opcionVehiculo) {

                            case "A":
                                datos = informacionVehiculo("Avion");

                                if (datos.getIntegridad()) {
                                    entradasUsuario = validarNumeroEntero("Ingrese altura maxima: ");
                                    if (entradasUsuario > 0) {
                                        garaje.add(new Avion(datos.getReferencia(), datos.getVelocidadMaxima(), datos.getColor(), entradasUsuario));
                                        mensajeAlerta("Vehiculo Agregado");
                                    } else {
                                        mensajeAlerta("Operacion Cancelada");
                                    }
                                } else {
                                    mensajeAlerta("Operacion Cancelada");
                                }
                                opcionVehiculo = "D";
                                break;

                            case "B":
                                datos = informacionVehiculo("Yate");

                                if (datos.getIntegridad()) {
                                    entradasUsuario = validarNumeroEntero("Ingrese peso: ");
                                    if (entradasUsuario > 0) {
                                        // Aplicación del patron BUILDER
                                        creadorYate.referencia(datos.getReferencia());
                                        creadorYate.velocidadMaxima(datos.getVelocidadMaxima());
                                        creadorYate.color(datos.getColor()).pesoMaximo(entradasUsuario); // Paso multiples constructores

                                        garaje.add(new Yate(creadorYate)); // Para crear Yate paso como parametro el constructor

                                        mensajeAlerta("Vehiculo Agregado");
                                    } else {
                                        mensajeAlerta("Operacion Cancelada");
                                    }
                                } else {
                                    mensajeAlerta("Operacion Cancelada");
                                }
                                opcionVehiculo = "D";
                                break;

                            case "C":
                                datos = informacionVehiculo("Carro");

                                if (datos.getIntegridad()) {
                                    entradasUsuario = validarNumeroEntero("Ingrese numero de puertas: ");
                                    if (entradasUsuario > 0) {
                                        garaje.add(new Carro(datos.getReferencia(), datos.getVelocidadMaxima(), datos.getColor(), entradasUsuario));
                                        mensajeAlerta("Vehiculo Agregado");
                                    } else {
                                        mensajeAlerta("Operacion Cancelada");
                                    }
                                } else {
                                    mensajeAlerta("Operacion Cancelada");
                                }
                                opcionVehiculo = "D";
                                break;

                            case "D":
                                mensajeAlerta("Operacion cancelada");
                                opcionVehiculo = "D";
                                break;
    
                            default:
                                mensaje("Ingreso una opcion incorrecta [M2c]");
                                continue;
                        }
                    } while (opcionVehiculo != "D");
                    limpiarPantalla(2);
                    break;

                case 2:
                    limpiarPantalla((float) 0.5);
                    if (garaje.size() == 0){
                        mensajeAlerta("El garaje esta vacio");
                    } else {
                        mensaje("Los vehiculos en el garaje son:\n");
                        for (int i = 0; i < garaje.size(); i++){
                            mensaje(garaje.get(i).getClass().getName() + "\t[ID: " + garaje.get(i).hashCode() + "]");
                            mensaje(garaje.get(i)+"");
                            mensaje("-----------------------");
                        }
                        // Utlizacion de la accion CLONAR (patron PROTOTYPE)
                        mensaje("Ingrese [S] para clonar un vehiculo o [Enter] para volvel al menu");
                        if (entrada.nextLine().trim().toUpperCase().equals("S")) {
                            if (garaje.size() >= 10){
                                mensajeAlerta("El garaje esta lleno");
                            } else {
                                try {
                                    garaje.add((Vehiculos)garaje.get(validarNumeroEntero("\nSeleccione el vehiculo a clonar")-1).clonar());
                                    mensajeAlerta("Vehiculo clonado corectamente");
                                } catch (Exception e) {
                                    mensajeAlerta("Error");
                                    mensaje("\n" + e);
                                }
                            }
                        }
                    }
                    limpiarPantalla(2);
                    break;

                case 3:
                    if (garaje.size() < 1){
                        mensajeAlerta("No hay vehiculos en el garaje");
                        break;
                    }else{
                        limpiarPantalla((float)0.001);

                        Vehiculos maxVehicle = Collections.max(garaje, new Comparator<Vehiculos>(){
                            @Override
                            public int compare(Vehiculos arg0, Vehiculos arg1) {
                                return Integer.compare(arg0.getVelocidadMaxima(), arg1.getVelocidadMaxima());
                            }
                        });
                        mensajeAlerta("Vehiculo con mayor velocidad maxima: "+maxVehicle.getReferencia());
                    }
                    break;
                case 4:
                    if (garaje.size() < 1){
                        mensajeAlerta("No hay vehiculos en el garaje");
                        break;
                    }else{
                        limpiarPantalla((float)0.001);

                        Vehiculos minVehicle = Collections.min(garaje, new Comparator<Vehiculos>(){
                            @Override
                            public int compare(Vehiculos arg0, Vehiculos arg1) {
                                return Integer.compare(arg0.getVelocidadMaxima(), arg1.getVelocidadMaxima());
                            }
                        });
                        mensajeAlerta("Vehiculo con menor velocidad maxima: "+minVehicle.getReferencia());
                    }
                    break;

                case 5:
                    limpiarPantalla((float) 0.5);
                    if (garaje.size() == 0){
                        mensajeAlerta("El garaje esta vacio");
                    } else {
                        mensaje("Los vehiculos en el garaje ordenados por su velocidad maxima son:\n");
                        List<Vehiculos> sortedList = garaje.stream().sorted(Comparator.comparing(Vehiculos::getVelocidadMaxima).reversed()).collect(Collectors.toList());

                        for(Vehiculos item : sortedList){
                            mensaje(item.getClass().getName() + "\t[ID: " + item.hashCode() + "]");
                            mensaje(item+"");
                            mensaje("-----------------------");
                        }
                    }
                    limpiarPantalla(2);
                    break;
                case 6:
                    limpiarPantalla((float) 0.5);
                    if (garaje.size() == 0){
                        mensajeAlerta("El garaje esta vacio");
                    } else {
                        mensaje("Los vehiculos en el garaje filtrados por color NEGRO o AZUL son:\n");

                        List<Vehiculos> filteredList = garaje.stream().filter(vehiculo -> vehiculo.getColor().name().equals("BLACK") || vehiculo.getColor().name().equals("AZUL")).collect(Collectors.toList());

                        for(Vehiculos item : filteredList){
                            mensaje(item.getClass().getName() + "\t[ID: " + item.hashCode() + "]");
                            mensaje(item+"");
                            mensaje("-----------------------");
                        }
                    }
                    limpiarPantalla(2);
                    break;
                case 7:
                    limpiarPantalla((float) 0.5);
                    if (garaje.size() == 0){
                        mensajeAlerta("El garaje esta vacio");
                    } else {
                        mensaje("Los vehiculos en el garaje filtrados por color BLANCO o VERDE son:\n");
                        StringBuilder result = new StringBuilder();
                        List<Vehiculos> filteredList = garaje.stream().filter(vehiculo -> vehiculo.getColor().name().equals("BLANCO") || vehiculo.getColor().name().equals("VERDE")).collect(Collectors.toList());
                        
                        for(Vehiculos item : filteredList){
                            result.append(item.referencia +" - ");
                        }

                        mensaje("-----------Resultado------------");
                        mensaje(result.toString());
                    }
                    limpiarPantalla(2);
                    break;
                case 8:
                    mensajeAlerta("Hasta luego!");
                    break;

                default:
                    mensaje("Digito una opcion incorrecta [M1n]");
                    limpiarPantalla(1);
            }
        } while (opcion != 3);
        entrada.close();
    }

    public static Tipado informacionVehiculo(String tipo) {
        
        boolean error = false;
        String referencia;
        int selector, velocidadMax = 0;
        Colores color  = Colores.NEGRO;

        mensaje("Ingrese los datos del " + tipo);

        do {
            error = false;
            mensaje("Ingrese referencia:");
            referencia = entrada.nextLine().trim();
            if (referencia.length() > 0) {
                if (referencia.equals("0")) {
                    error = true;
                }
                break;
            } else {
                mensaje("Entrada invalida (ingrese 0 para cancelar): ");
                limpiarPantalla(1);
            }
        } while (true);
        
        if (!error) {
            velocidadMax = validarNumeroEntero("Ingrese velocidad maxima: ");
            if (velocidadMax <= 0) {
                error = true;
            }
        }

        if (!error) {
            do {
                error = false;
                mensaje("Ingrese color: ");
                for (Colores colorIndice:Colores.values()) {
                    mensaje("   " + (colorIndice.ordinal() + 1) + ". "+colorIndice.toString());
                }
                selector = validarNumeroEntero("");
                if (selector == 0) {
                    error = true;
                    break;
                } else {
                    try {
                        color = Colores.values()[selector-1];
                    }
                    catch (Exception er) {
                        mensaje("Seleccion invalida (ingrese 0 para cancelar)");
                        limpiarPantalla(1);
                        error = true;
                    }
                    if (!error) break;
                }
            } while (true);
        }

        if (!error) {
            return new Tipado(referencia, velocidadMax, color);
        } else {
            return new Tipado(false);
        }
    }

    public static int validarNumeroEntero(String mensaje) {
        boolean salir;
        int numero = -1; 
        do {
            salir = true;
            mensaje(mensaje);
            try {
                numero = -1;
                numero = Integer.parseInt(entrada.nextLine());
            } catch (Exception e) {
                mensaje("Entrada invalida (ingrese 0 para cancelar)\n" + e);
                salir = false;
                limpiarPantalla(1);
            }
            if (numero < 0) {
                salir = false;
            }
        } while (!salir);
        return numero;
    }

    public static void mensajeAlerta(String msj) {
        limpiarPantalla((float)0.01);
        mensaje(new String(new char[msj.length() + 4]).replace("\0", "="));
        mensaje("| " + msj + " |");
        mensaje(new String(new char[msj.length() + 4]).replace("\0", "="));
    }

    public static void mensaje(String msj) {
        System.out.println(msj);
    }

    //Codigo de terceros para liempiar la consola
    public static void limpiarPantalla(float tiempo) {
        try {
            Thread.sleep((int)(tiempo * 1000));
            if (System.getProperty("os.name").contains("Windows")) {new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
            else {Runtime.getRuntime().exec("clear");}
        } catch (Exception error) {
            System.out.print(error);
        }//*/
        
    }
}