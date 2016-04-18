package testAcumulaSumas;

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

/**
 * TestAcumulaSumas
 * 
 * Este programa realiza sumas acumuladas de enteros bajo los siguientes
 * requisitos:Recuerda identificarlo todo correctamente Paquete
 * nombreApellido1Apellido2AcumulaSumas en javadoc Inicialmente se parte del
 * entero almacenado en un fichero txt. Si el fichero no existe, se parte desde
 * 0. El programa continúa pidiendo al usuario números enteros y los suma, hasta
 * que el usuario introduce en número 0. Una vez hecha la suma, el resultado se
 * almacenará en el mismo fichero txt Detalles del programa: No utilices la
 * clase Teclado En el caso en que el usuario introduzca por teclado un valor no
 * válido (no es un entero) se le informa del error y se solicita de nuevo
 * (utiliza excepciones) En el caso en que al leer del fichero el dato no sea
 * correcto (no es un entero) se informa del error y se comienza desde 0. Junto
 * con el programa, entrega un "flujosE-S.pdf" que contenga: Flujos de E/S
 * utilizados Clases y métodos que se utilizan
 * 
 * @author Antonio Luque Bravo
 * @version 1.0
 *
 */
public class TestAcumulaSumas {
	private static File file = new File("suma.txt");

	public static void main(String[] args) throws IOException {
		int suma = 0;
		suma = comprobarSiNullSiNoExiste(suma);
		do {
		} while (!pedirEnteros(suma));
	}

	private static int comprobarSiNullSiNoExiste(int suma) {
		if (!file.exists() || file == null) {
			System.out.println("El archivo no existe.. La suma se inicializará a 0.");
			suma = 0;
		} else {
			suma = recuperarSuma(suma);
		}
		return suma;
	}

	private static int recuperarSuma(int suma) {
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			System.out.println("Recuperando suma..");
			suma = in.readInt();
			System.out.println("Suma almacenada: " + suma);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return suma;
	}

	private static boolean pedirEnteros(int suma) {
		try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			Scanner scanner = new Scanner(System.in);
			int acumulador;
			System.out.println(
					"Introduce los números que quieras y se almacenará la suma de los mismos en un archivo txt.");
			do {
				acumulador = scanner.nextInt();
				suma += acumulador;
			} while (acumulador != 0);
			out.writeInt(suma);
			return true;
		} catch (InputMismatchException | IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
