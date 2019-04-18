/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.Random;

/**
 *
 * @author celso.alineri
 */
public class Chromosome implements Comparable<Chromosome> {
	private final String gene;
	private final int fitness;
	
	/** The target gene, converted to an array for convenience. */
	private static final char[] TARGET_GENE = "Hello, world!".toCharArray();

	/** Convenience randomizer. */
	private static final Random rand = new Random(System.currentTimeMillis());
	
	/**
	 * Default constructor.
	 *
	 * @param gene The gene representing this <code>Chromosome</code>.
	 */
	public Chromosome(String gene) {
		this.gene    = gene;
		this.fitness = calculateFitness(gene);
	}
	
	/**
	 * Method to retrieve the gene for this <code>Chromosome</code>.
	 *
	 * @return The gene for this <code>Chromosome</code>.
	 */
	public String getGene() {
		return gene;
	}
	
	/**
	 * Method to retrieve the fitness of this <code>Chromosome</code>.  Note
	 * that a lower fitness indicates a better <code>Chromosome</code> for the
	 * solution.
	 *
	 * @return The fitness of this <code>Chromosome</code>.
	 */
	public int getFitness() {
		return fitness;
	}
	
	/**
	 * Helper method used to calculate the fitness for a given gene.  The
	 * fitness is defined as being the sum of the absolute value of the 
	 * difference between the current gene and the target gene.
	 * 
	 * @param gene The gene to calculate the fitness for.
	 * 
	 * @return The calculated fitness of the given gene.
	 */
        
        
        
        /**
	 *COMPLETAR \o/
         *############################################################ 
	 */
        
	private static int calculateFitness(String gene) {
		int fitness = 0;
		char[] arr  = gene.toCharArray();
		for (int i = 0; i < arr.length; i++) {
                    fitness = fitness + Math.abs((int)arr[i] - (int)TARGET_GENE[i]);
                    //Completar cógido, calcular fitness (somar todos os genes e subtrair do target(tamanho da string alvo))
			
		}
		
		return fitness;
	}

	/**
	 * Method to generate a new <code>Chromosome</code> that is a random
	 * mutation of this <code>Chromosome</code>.  This method randomly
	 * selects one character in the <code>Chromosome</code>s gene, then
	 * replaces it with another random (but valid) character.  Note that
	 * this method returns a new <code>Chromosome</code>, it does not
	 * modify the existing <code>Chromosome</code>.
	 * 
	 * @return A mutated version of this <code>Chromosome</code>.
	 */
        
        
        /**
	 *COMPLETAR \o/
         *############################################################ 
	 */
        
        
	public Chromosome mutate() {
                
		char[] arr  = gene.toCharArray();
		//mudar um gene aleatório para um valor aleatório
                //lembrando que esse aleatório pode ser qualquer letra ou caracter especial, além de ser case sensitive
                int i = rand.nextInt(arr.length);
                arr[i] = (char) rand.nextInt(256);
                
		return new Chromosome(String.valueOf(arr));
	}

	/**
	 * Method used to mate this <code>Chromosome</code> with another.  The
	 * resulting child <code>Chromosome</code>s are returned.
	 * 
	 * @param mate The <code>Chromosome</code> to mate with.
	 * 
	 * @return The resulting <code>Chromosome</code> children.
	 */
	public Chromosome[] mate(Chromosome mate) {
		// Convert the genes to arrays to make thing easier.
		char[] arr1  = gene.toCharArray();
		char[] arr2  = mate.gene.toCharArray();
		
		// Select a random pivot point for the mating
		int pivot    = rand.nextInt(arr1.length);
		
		// Provide a container for the child gene data
		char[] child1 = new char[gene.length()];
		char[] child2 = new char[gene.length()];
		
		// Copy the data from each gene to the first child.
		/**
	 *COMPLETAR \o/
         * fazer o filho 1
         *############################################################ 
	 */
		
		// Repeat for the second child, but in reverse order.
		/**
                 * 
	 *COMPLETAR \o/
         * fazer o filho 2
         *############################################################ 
	 */

		return new Chromosome[] { new Chromosome(String.valueOf(child1)), 
				new Chromosome(String.valueOf(child2))}; 
	}
	
	/**
	 * A convenience method to generate a randome <code>Chromosome</code>.
	 * 
	 * @return A randomly generated <code>Chromosome</code>.
	 */
	/* package */ static Chromosome generateRandom() {
		char[] arr = new char[TARGET_GENE.length];
		for (int i = 0; i < arr.length; i++) {
                    // dica de como gerar um cromossomo de string com caracteres aleatorios
			arr[i] = (char) (rand.nextInt(90) + 32);
		}

		return new Chromosome(String.valueOf(arr));
	}

	/**
	 * Method to allow for comparing <code>Chromosome</code> objects with
	 * one another based on fitness.  <code>Chromosome</code> ordering is 
	 * based on the natural ordering of the fitnesses of the
	 * <code>Chromosome</code>s.  
	 */
	@Override
	public int compareTo(Chromosome c) {
		if (fitness < c.fitness) {
			return -1;
		} else if (fitness > c.fitness) {
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Chromosome)) {
			return false;
		}
		
		Chromosome c = (Chromosome) o;
		return (gene.equals(c.gene) && fitness == c.fitness);
	}
	
	/**
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {		
		return new StringBuilder().append(gene).append(fitness)
				.toString().hashCode();
	}
}
