package EvolutionaryAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que modela los casos de prueba para el algoritmo genetico.
 */
public class Casos {
	private String nombreFichero;
	private int tamanio;

	private ArrayList<ArrayList<Integer>> flujos;
	private ArrayList<ArrayList<Integer>> distancias;
	
	public Casos(String nombreFichero) throws FileNotFoundException {
		this.nombreFichero = nombreFichero;
		flujos = new ArrayList<ArrayList<Integer>>();
		distancias = new ArrayList<ArrayList<Integer>>();
		
		// Leemos el archivo de datos
		Scanner sc = new Scanner(new File(nombreFichero));
		
		// Leemos el tamanio del problema
		tamanio = sc.nextInt();
		
		// Leemos la matriz de flujos
		for (int i = 0; i < tamanio; i++) {
			flujos.add(new ArrayList<Integer>());
			for (int j = 0; j < tamanio; j++)
				flujos.get(i).add(sc.nextInt());
		}
		
		// Leemos la matriz de distancias
		for (int i = 0; i < tamanio; i++) {
			distancias.add(new ArrayList<Integer>());
			for (int j = 0; j < tamanio; j++)
				distancias.get(i).add(sc.nextInt());
		}

		sc.close();
	}

	public int getTamanio() {
		return tamanio;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public ArrayList<ArrayList<Integer>> getFlujos() {
		return flujos;
	}

	public ArrayList<ArrayList<Integer>> getDistancias() {
		return distancias;
	}
}
