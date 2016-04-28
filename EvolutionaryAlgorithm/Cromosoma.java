package EvolutionaryAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela un cromosoma.
 */
public class Cromosoma extends ArrayList<Integer> implements Comparable<Cromosoma>{
	private static final long serialVersionUID = 1L;
	
	//Fitness del cromosoma.
	private int fitness = Integer.MAX_VALUE;
	//Estado anterior del Cromosoma. Utilizado en el algoritmo Baldwiniano.
	private Cromosoma estadoAnterior;

	/**
	 * Constructor por defecto.
	 */
	public Cromosoma() { }
	
	/**
	 * Construye un cromosoma de un determinado tamanio.
	 * 
	 * @param tamanio Tamanio del cromosoma
	 */
	public Cromosoma(int tamanio) {
		for (int i = 0; i < tamanio; i++)
			this.add(null);
	}
	
	/**
	 * Constructor de copia. Crea un cromosoma igual.
	 * 
	 * @param cromosoma Cromosoma a copiar
	 */
	public Cromosoma(Cromosoma cromosoma) {
		super(cromosoma);
	}
	
	/**
	 * Construye un cromosoma a partir de una lista de alelos.
	 * 
	 * @param alelos Lista de alelos
	 */
	public Cromosoma(List<Integer> alelos) {
		super(alelos);
	}
	
	/**
	 * Construye un cromosoma a partir de los alelos introducidos
	 * como parametros del constructor.
	 * 
	 * @param alelos Alelos del cromosoma
	 */
	public Cromosoma(int... alelos) {
		for (int i : alelos)
			this.add(i);
	}
	
	@Override
	public String toString() {
		String resultado = "";
		for (Integer alelo : this) {
			resultado += alelo + " ";
		}
		return resultado;
	}
	
	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
	public Cromosoma getEstadoAnterior() {
		return estadoAnterior;
	}
	
	public void actualizarEstadoAnterior() {
		estadoAnterior = (Cromosoma) this.clone();
	}

	@Override
	public int compareTo(Cromosoma o) {
		return this.fitness - o.fitness;
	}
}
