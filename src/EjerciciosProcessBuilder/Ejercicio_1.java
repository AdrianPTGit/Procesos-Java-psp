package EjerciciosProcessBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * EJERCICIO 1:
 *      Modificar el ejercicio 1 para utilizar ProcessBuilder y que guarde el
 *      resultado en un archivo txt. Necesitarás utilizar el método redirectOutput.
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
            String [] comando;
            if (so.contains("win")) {
                comando = new String[]{"cmd.exe", "/c", "ipconfig"};
            } else {
                comando = new String[]{"bash", "-c", "ip addr show"};
            }

             // Crea un objeto File para guardar la salida en un archivo .txt
             File archivoSalida = new File("resultado_ip.txt");

             // Crea el ProcessBuilder con el comando
             ProcessBuilder pb = new ProcessBuilder(comando);

             // Redirige la salida estándar (stdout) al archivo
             pb.redirectOutput(archivoSalida);

             // También puedes redirigir los errores (stderr) al mismo archivo si quieres
             pb.redirectErrorStream(true);

             // Inicia el proceso
             Process proceso = pb.start();

             // Espera a que el proceso termine antes de continuar
             int codigoSalida = proceso.waitFor();

             if (codigoSalida == 0) {
                 System.out.println("El comando se ejecutó correctamente.");
                 System.out.println("Resultado guardado en: " + archivoSalida.getAbsolutePath());
             } else {
                 System.out.println("El comando terminó con código de salida: " + codigoSalida);
             }


        } catch (Exception e) {
            // Si ocurre algún error (por ejemplo, comando no encontrado),
            // muestra el mensaje de error y el detalle completo de la excepción.
            System.out.println("Error al ejecutar el comando: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
