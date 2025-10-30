package EjerciciosRuntime;
/*
EJERCICIO 2:
        Utiliza el objeto Runtime para obtener información del equipo donde se
        ejecuta el programa. Muestra la información acerca del número de
        procesadores disponibles.
* */
public class Ejercicio_2 {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        // Numero de nucleos CPU
        int numNucleos = runtime.availableProcessors();

        System.out.println("El PC tiene: " + numNucleos + " nucleos.");
    }
}
