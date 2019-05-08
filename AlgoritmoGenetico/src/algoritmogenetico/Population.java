/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author celso.alineri
 */
public class Population {

	/** The size of the tournament. */
	private static final int TOURNAMENT_SIZE = 3;
	
	/** Convenience randomizer. */
	private static final Random rand = new Random(System.currentTimeMillis());

	private float elitism;
	private float mutation;
	private float crossover;
        private float inversion;
	private Chromosome[] popArr;

	/**
	 * Default constructor.
	 * 
	 * @param size The size of the population, where size > 0.
	 * @param crossoverRatio The crossover ratio for the population during 
	 * evolution, where 0.0 <= crossoverRatio <= 1.0.
	 * @param elitismRatio The elitism ratio for the population during
	 * evolution, where 0.0 <= elitismRatio < 1.0.
	 * @param mutationRatio The mutation ratio for the population during
	 * evolution, where 0.0 <= mutationRatio <= 1.0.
	 * 
	 * @throws IllegalArgumentException Thrown if an invalid ratio is given.
	 */
	public Population(int size, float crossoverRatio, float elitismRatio, 
			float mutationRatio, float inversionRatio) {
		
		this.crossover = crossoverRatio;
		this.elitism = elitismRatio;
		this.mutation = mutationRatio;
                this.inversion = inversionRatio;
		
		// Generate an initial population
		this.popArr = new Chromosome[size];
		for (int i = 0; i < size; i++) {
			this.popArr[i] = Chromosome.generateRandom();
		}

		Arrays.sort(this.popArr);
	}

	/**
	 * Method used to evolve the population.
	 */
	public void evolve() {
		// Create a buffer for the new generation
		Chromosome[] buffer = new Chromosome[popArr.length];
		
		// Copy over a portion of the population unchanged, based on 
		// the elitism ratio.
		int idx = Math.round(popArr.length * elitism);
		System.arraycopy(popArr, 0, buffer, 0, idx);

		// Iterate over the remainder of the population and evolve as 
		// appropriate.
		while (idx < buffer.length) {
			// Check to see if we should perform a crossover. 
			if (rand.nextFloat() <= crossover) {
				
				// Select the parents and mate to get their children
				Chromosome[] parents = selectParents();
				Chromosome[] children = parents[0].mate(parents[1]);
				
				// Check to see if the first child should be mutated.
				if (rand.nextFloat() <= mutation) {
					buffer[idx++] = children[0].mutate();
				} else {
					buffer[idx++] = children[0];
				}
				
				// Repeat for the second child, if there is room.
				if (idx < buffer.length) {
					if (rand.nextFloat() <= mutation) {
						buffer[idx] = children[1].mutate();
					} else {
						buffer[idx] = children[1];
					}
				}
			} else { // No crossover, so copy verbatium.
				// Determine if mutation should occur.
				if (rand.nextFloat() <= mutation) {
					buffer[idx] = popArr[idx].mutate();
				} else {
					buffer[idx] = popArr[idx];
				}
			}
			
			// Increase our counter
			++idx;
		}

		// Sort the buffer based on fitness.
		Arrays.sort(buffer);
		
		// Reset the population
		popArr = buffer;
	}
	
	/**
	 * Method used to retrieve a copy of the current population.  This
	 * method returns a copy of the population at the time the method was
	 * called.
	 * 
	 * @return A copy of the population.
	 */
	public Chromosome[] getPopulation() {
		Chromosome[] arr = new Chromosome[popArr.length];
		System.arraycopy(popArr, 0, arr, 0, popArr.length);
		
		return arr;
	}
	
	/**
	 * Method to retrieve the elitism ratio for the population.
	 * 
	 * @return The elitism ratio.
	 */
	public float getElitism() {
		return elitism;
	}

	/**
	 * Method to retrieve the crossover ratio for the population.
	 * 
	 * @return The crossover ratio.
	 */
	public float getCrossover() {
		return crossover;
	}

	/**
	 * Method to retrieve the mutation ratio for the population.
	 * 
	 * @return The mutation ratio.
	 */
	public float getMutation() {
		return mutation;
	}

	/**
	 * A helper method that can be used to select two random parents from
	 * the population to use in crossover during evolution. 
	 * 
	 * @return Two randomly selected <code>Chromsomes</code> for crossover.
	 */
	private Chromosome[] selectParents() {
		Chromosome[] parents = new Chromosome[2];

		// Randomly select two parents via tournament selection.
		for (int i = 0; i < 2; i++) {
			parents[i] = popArr[rand.nextInt(popArr.length)];
			for (int j = 0; j < TOURNAMENT_SIZE; j++) {
				int idx = rand.nextInt(popArr.length);
				if (popArr[idx].compareTo(parents[i]) < 0) {
					parents[i] = popArr[idx];
				}
			}
		}
		
		return parents;
	}
}
