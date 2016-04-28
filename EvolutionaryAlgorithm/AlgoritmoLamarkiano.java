package EvolutionaryAlgorithm;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Implementación algoritmo genético variante Lamarkiano
 */
public class AlgoritmoLamarkiano extends AlgoritmoGenetico {

	public AlgoritmoLamarkiano(Casos casos, List<Cromosoma> poblacion) {
		super(casos, poblacion);
	}

	public void greedy(Cromosoma cromosoma) {
		int mejorFitness = cromosoma.getFitness();
	 
            for ( int i = 0; i < cromosoma.size() - 1; i++ ) {
                for (int j=i+1; j < cromosoma.size(); j++) {
                    // Creamos un nuevo cromosoma a partir del actual
                    Cromosoma c = new Cromosoma(cromosoma);

                    // Intercambiamos los alelos
                    Collections.swap(c, i, j);

                            int nuevoFitness = QAP.fitness(c, casos.getFlujos(), casos.getDistancias());

                    // Si el nuevo fitness es mejor sustituimos el cromosoma
                    if ( nuevoFitness < mejorFitness ) {
                        cromosoma = c;
                        cromosoma.setFitness(nuevoFitness);
                        mejorFitness = nuevoFitness;
                    }
                }
            }

	}
	public void ejecutar() {
		System.out.println("AlgoritmoLamarkiano.ejecutar()");
		int generaciones = 0;
		int generacionesSinMejora = 0;
		long startTime, stopTime;
		
		startTime = System.currentTimeMillis();
		
		// Calculamos el fitness para cada cromosoma
		for (Cromosoma cromosoma : poblacion) {
			cromosoma.setFitness(QAP.fitness(cromosoma, casos.getFlujos(), casos.getDistancias()));
		}
		// Ordenamos la poblacion respecto a su fitness
		Collections.sort(poblacion);
		
		boolean parar = false;
		while(!parar) {
			generaciones++;

			Cromosoma mejorCromosomaAnterior = poblacion.get(0);

			// Aplicamos el algoritmo greedy
			for (Cromosoma cromosoma : poblacion) {
				greedy(cromosoma);
			}
			
			// Cruzamos los individuos 
			for (int i = 0; i < tamanioPoblacion-1; i++) {
				Cromosoma padre = poblacion.get(i),
				          madre = poblacion.get(i+1);

				int tamanio = padre.size();
				Random r = new Random();
				int ptoCorte1 = r.nextInt(tamanio - 1);
				int ptoCorte2 = r.nextInt(tamanio - ptoCorte1) + ptoCorte1;
				
				if (ptoCorte1 == ptoCorte2)
					ptoCorte2++;
				
				Cromosoma hijo1 = Evolucion.cruzar(padre, madre, ptoCorte1, ptoCorte2),
				          hijo2 = Evolucion.cruzar(madre, padre, ptoCorte1, ptoCorte2);

				hijo1.setFitness(QAP.fitness(hijo1, casos.getFlujos(), casos.getDistancias()));
				hijo2.setFitness(QAP.fitness(hijo1, casos.getFlujos(), casos.getDistancias()));
				
				poblacion.add(hijo1);
				poblacion.add(hijo2);
			}

			// Mutamos los individuos teniendo en cuenta la probabilidad
			for (Cromosoma cromosoma : poblacion) {
				if (Math.random() < PROB_MUTACION ) {
					Evolucion.mutar(cromosoma);
					cromosoma.setFitness(QAP.fitness(cromosoma, casos.getFlujos(), casos.getDistancias()));
				}
			}
			
			// Ordenamos la poblacion respecto al fitness y eliminamos los que sobren
			Collections.sort(poblacion);
			while(poblacion.size() > tamanioPoblacion)
				poblacion.remove(poblacion.size()-1);

			// Condicion de parada
//			if (generaciones == 1000) {
			if (mejorCromosomaAnterior.compareTo(poblacion.get(0)) <= 0) {
				generacionesSinMejora++;
//				parar = true;
			} else {
				generacionesSinMejora = 0;
			}
			
			if (generacionesSinMejora > 20 || generaciones == 1000) {
				parar = true;
			}
		}
		stopTime = System.currentTimeMillis();
		
		System.out.println("Numero de generaciones: " + generaciones);
		System.out.println("Fitness: " + poblacion.get(0).getFitness());
		System.out.println("Solucion: " + poblacion.get(0));
		System.out.println("Tiempo transcurrido: "+ (stopTime - startTime)/1000.0+" seg");
	}
}
