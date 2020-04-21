package pa06;

import java.util.ArrayList;

/**
 * A Sample represents a vector of doubles to be used in a clustering
 * algorithm...
 * 
 * @author Team 14
 *
 */

public class Sample {
	private ArrayList<Double> sample;

	public Sample(double[] values) {
		this.sample = new ArrayList<Double>();
		for (int i = 0; i < values.length; i++) {
			sample.add(values[i]);
		}

	}

	/**
	 * the distance between two samples
	 * 
	 * @param another sample
	 * @return the distance to the other sample
	 */
	public double distanceTo(Sample sample) {
		double res = 0;
		for (int i = 0; i < this.sample.size(); i++) {
			double dif = this.sample.get(i) - sample.getElement(i);
			res += dif * dif;
		}
		return Math.sqrt(res);
	}

	/**
	 * the number of doubles in the sample
	 * 
	 * @return sample length
	 */
	public int sampleLength() {
		return this.sample.size();
	}

	/**
	 * get the double in the sample array
	 * 
	 * @param index i
	 * @return the double at index i
	 */
	public double getElement(int i) {
		return this.sample.get(i);
	}

	/**
	 * print out the sample
	 */
	public String toString() {
		String res = "{";
		if (this.sample.size() == 0) {
			// do nothing
		} else if (this.sample.size() == 1) {
			res += this.sample.get(0);
		} else {
			for (int i = 0; i < this.sample.size() - 1; i++) {
				res += (this.sample.get(i) + ", ");
			}
			res += this.sample.get(this.sample.size() - 1);
		}
		res += "}";
		return res;
	}

	public static void main(String[] args) {
		System.out.println("testing for the Sample class.");
		double[] p1 = { 1.1d, 2.1d, 3.14, 2.71 };
		double[] p2 = { 1.1, 2.1, 3.14, 1.71 };
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		System.out.println("s1 = " + s1.toString());
		System.out.println("s2 = " + s2.toString());

		// print out the distance between s1 and s2
		System.out.println("Distance between s1 and s2: " + s1.distanceTo(s2));

	}

}
