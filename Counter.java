/*
 * File: Counter.java
 * ================================================================
 * A class representing a tally counter.
 */
public class Counter {
	/* The total value tracked so far. */
	private int total;
	
	/**
	 * Constructs a new counter with the given initial value.
	 * 
	 * @param initialValue The initial value of the counter.
	 */
	public Counter(int initialValue) {
		total = initialValue;
	}
	
	/**
	 * Increments the counter.
	 */
	public void increment() {
		total ++;
	}
	
	/**
	 * Hands back the value displayed in the counter.
	 * 
	 * @return The value displayed in the counter.
	 */
	public int getTotal() {
		return total;
	}
	
	/**
	 * Produces a nice, human-readable representation of the
	 * counter.
	 * 
	 * @return A human-readable representation of the counter.
	 */
	public String toString() {
		return "Counter reads: " + total;
	}
}
