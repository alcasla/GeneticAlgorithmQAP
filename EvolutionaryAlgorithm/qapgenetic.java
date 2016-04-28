package EvolutionaryAlgorithm;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Clase principal ejecución
 */
public class qapgenetic {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("N\u00FAmero de argumentos incorrecto");
			System.err.println("Uso: java " + qapgenetic.class.getName() + " <fichero> <tama\u00F1o poblaci\u00F3n>");
			System.exit(1);
		}
		
		String fichero = args[0];
		int tamPoblacion = Integer.parseInt(args[1]);
		ArrayList<Cromosoma> poblacion1 = new ArrayList<Cromosoma>();
		ArrayList<Cromosoma> poblacion2 = new ArrayList<Cromosoma>();
		ArrayList<Cromosoma> poblacion3 = new ArrayList<Cromosoma>();
		
		// Se crean los casos de prueba a partir del fichero
		Casos casos = null;
		try {
			casos = new Casos(fichero);
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.err.println("No se encuentra el archivo");
			System.exit(2);
		}
	
		// Se crea la poblacion aleatoriamente
		for(int i = 0; i < tamPoblacion; i++) {
			Cromosoma c = new Cromosoma(Utils.generarPermutacion(0, casos.getTamanio(), casos.getTamanio()));
			poblacion1.add(c);
			poblacion2.add(c);
			poblacion3.add(c);
		}

		AlgoritmoGenetico ag = new AlgoritmoGenetico(casos, poblacion1);
		ag.ejecutar();
		
		AlgoritmoGenetico ab = new AlgoritmoBaldwiniano(casos, poblacion2);
		ab.ejecutar();
		
		AlgoritmoGenetico al = new AlgoritmoLamarkiano(casos, poblacion3);
		al.ejecutar(); 
	}

}
