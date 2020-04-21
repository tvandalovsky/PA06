package pa06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A cluster is a cluster point (which is itself a sample) and a list of Samples
 * (the one's closest to that sample point, ideally).
 * 
 * @author Team 14
 *
 */
public class Cluster {
	/**
	 * the center of the Cluster 
	 */
	private Sample clusterPoint;
	/**
	 * all the Samples in the cluster
	 */
	ArrayList<Sample> cluster;

	public Cluster(ArrayList<Sample> samples, Sample clusterPoint) {
		this.cluster = samples;
		this.clusterPoint = clusterPoint;
	}
	
	public Cluster() {
		this.cluster = new ArrayList<Sample>();
	}

	/**
	 * choose k items from the Sample list
	 * 
	 * @param the number of samples to pick
	 * @return a list of elements which are randomly chosen
	 */
	public Sample[] randomPick(int num) {
		Collections.shuffle(cluster);
		Sample[] res = new Sample[num];
		for (int i = 0; i < num; i++) {
			res[i] = cluster.get(i);
		}
		return res;
	}
	
	public Sample getClusterPoint() {
		return this.clusterPoint;
	}
	
	public Sample getSample(int i) {
		return cluster.get(i);
	}
	
	public int getSize() {
		return cluster.size();
	}

	public void add(Sample sample) {
		this.cluster.add(sample);
	}

	/**
	 * prints contents of cluster
	 */
	public void print() {
		for (int i = 0; i < this.cluster.size(); i++) {
			System.out.print("{");
			for (int j = 0; j < this.cluster.get(i).sampleLength(); j++) {
				System.out.print(this.cluster.get(i).getElement(j));
				if (j < this.cluster.get(i).sampleLength() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("}");
		}
	}
	
	/**
	 * find the new Sample which is the average value of all Samples in the cluster
	 * @return the Sample with average value
	 */
	public Sample findAverage() {
		double[] res = new double[clusterPoint.sampleLength()];
		for(int i=0; i<res.length; i++) {
			double temp = 0;
			for(Sample s: cluster) {
				temp += s.getElement(i);
			}
			double avgTemp = temp/cluster.size();
			res[i] = avgTemp;
		}
		return new Sample(res);
	}
	
	
//	/**
//	 * return the closest Sample to the targetSample
//	 * @param targetSample
//	 * @return the closest Sample to the targetSample
//	 */
    public Sample closestSamplePoint(Sample targetSample) {
	     Sample result = this.cluster.get(0);		
     	 for (Sample i : cluster) {
		 if (result.distanceTo(targetSample) > i.distanceTo(targetSample)) {
				result = i;
			}
		 }
		 return result;
	}

	public static void main(String[] args) {
		double[] p1 = { 1.1d, 2.1d, 3.14, 2.71};
		double[] p2 = { 1.1, 2.1, 3.14, 1.71};
		double[] p3 = { 1.1, 2.1, 3.14, 1.71};
		double[] p4 = { 10, 20, 30, 10};
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		Sample s3 = new Sample(p3);
		Sample s4 = new Sample(p4);

		ArrayList<Sample> samples = new ArrayList<Sample>();
		samples.add(s1);
		samples.add(s2);
		samples.add(s3);
		samples.add(s4);

		Cluster cluster = new Cluster(samples, samples.get(0));
		cluster.print();
		System.out.println(cluster.findAverage().toString());
		
	}

}
