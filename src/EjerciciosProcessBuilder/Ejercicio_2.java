package EjerciciosProcessBuilder;
/**
 * Utilizando ProcessBuilder, realiza un programa en Java que admite como
 * parámetro de entrada el comando a ejecutar en la consola del sistema
 * operativo y muestre en pantalla el resultado. (Not all the command might
 * work)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Ejercicio_2 {
    public static void main(String[] args) {
        try {
            // Verifica que se haya pasado al menos un argumento (el comando)
            if (args.length == 0) {
                System.out.println("Debes indicar un comando a ejecutar como parámetro.");
                System.out.println("Ejemplo: java EjecutarComando ls");
                return;
            }

            // Une todos los argumentos en un solo comando (por si incluye parámetros)
            // Ejemplo: java EjecutarComando ping google.com
            String comando = String.join(" ", args);

            // Detectar el sistema operativo
            String so = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (so.contains("win")) {
                // En Windows los comandos deben ejecutarse dentro de "cmd /c"
                pb = new ProcessBuilder("cmd.exe", "/c", comando);
            } else {
                // En Linux o macOS se usa "bash -c"
                pb = new ProcessBuilder("bash", "-c", comando);
            }

            // Redirige errores al mismo flujo de salida
            pb.redirectErrorStream(true);

            // Inicia el proceso
            Process proceso = pb.start();

            // Lee la salida del comando
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;

            System.out.println("\n--- Resultado del comando ---");
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

            // Cierra el lector y espera a que el proceso finalice
            lector.close();
            int codigoSalida = proceso.waitFor();

            System.out.println("\n--- Proceso finalizado con código: " + codigoSalida + " ---");

        } catch (Exception e) {
            System.out.println("Error al ejecutar el comando: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
