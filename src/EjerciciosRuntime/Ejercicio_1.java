package EjerciciosRuntime;
// Define el paquete donde se encuentra la clase (estructura del proyecto en Java)

import java.io.BufferedReader;
import java.io.InputStreamReader;
// Importa las clases necesarias para leer la salida del proceso (comando del sistema)

/**
 * EJERCICIO 1:
 *
 * Realiza un programa en Java que ejecute el comando correspondiente con
 * el sistema operativo donde se esté ejecutando (Windows o Linux), muestre
 * la dirección IP a través de la consola y muestre su resultado por pantalla.
 */

public class Ejercicio_1 {
    public static void main(String[] args) {
        try {
            // Obtiene el nombre del sistema operativo donde se ejecuta el programa.
            // System.getProperty("os.name") devuelve algo como "Windows 10" o "Linux".
            String so = System.getProperty("os.name");
            System.out.println("Tu sistema operativo es: " + so);

            // Según el sistema operativo, elige el comando apropiado:
            // - En Windows: "ipconfig"
            // - En Linux o macOS: "ip addr show"
            String comando = "";
            if (so.contains("win")) {
                comando = "ipconfig";
            } else {
                comando = "ip addr show";
            }

            // Ejecuta el comando del sistema operativo seleccionado
            Process process = Runtime.getRuntime().exec(comando);

            // Crea un lector para leer la salida del proceso (lo que normalmente aparece en la terminal)
            BufferedReader leerComando = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String linea;
            System.out.println("\n--- Resultado del comando ---");

            // Lee la salida línea por línea y la muestra en la consola de Java
            while ((linea = leerComando.readLine()) != null) {
                System.out.println(linea);
            }

            // Cierra el lector una vez finalizada la lectura
            leerComando.close();

            // Espera a que el proceso (el comando) termine antes de continuar
            process.waitFor();

        } catch (Exception e) {
            // Si ocurre algún error (por ejemplo, comando no encontrado),
            // muestra el mensaje de error y el detalle completo de la excepción.
            System.out.println("Error al ejecutar el comando: " + e.getMessage());
            e.printStackTrace();
        }
    }
}