package EvolutionaryAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que contiene funciones de utilidad independientes de clases.
 */
public class Utils {
	
	/**
	 * Genera una permutacion cuyos elementos estan dentro de un rango y de un
	 * tamanio concreto.
	 * @param min     Valor minimo de los elementos
	 * @param max     Valor maximo de los elementos
	 * @param tamanio Tamanio de la permutacion
	 * @return La permutacion generada
	 */
	public static List<Integer> generarPermutacion(int min, int max, int tamanio) {
		List<Integer> permutacion = new ArrayList<Integer>();
		Random r = new Random();
		int numero;
		
		while (permutacion.size() < tamanio) {
			numero = r.nextInt(max - min) + min;
			
			if (!permutacion.contains(numero))
				permutacion.add(numero);
		}
		
		return permutacion;
	}
	
	/**
	 * Imprime por la salida estandar los elementos de una lista.
	 * @param lista Lista a imprimir
	 */
	public static void print(List<?> lista) {
		for (Object elem : lista) {
			System.out.println(elem);
		}
	}
}
