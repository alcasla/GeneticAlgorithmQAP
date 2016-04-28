package EvolutionaryAlgorithm;

import java.util.ArrayList;

/**
 * Clase que contiene el método de evalución para el problema
 */
public class QAP {
	public static int fitness(Cromosoma cromosoma, ArrayList<ArrayList<Integer>> flujos, ArrayList<ArrayList<Integer>> distancias) {
		int fitness = 0;
		
		for (int i = 0; i < cromosoma.size(); i++) 
			for (int j = 0; j < cromosoma.size(); j++) 
				if (i != j) {
					int fabrica1 = cromosoma.get(i);
					int fabrica2 = cromosoma.get(j);
					fitness += (distancias.get(i).get(j) * flujos.get(fabrica1).get(fabrica2));
				}

		return fitness;
	}
}
