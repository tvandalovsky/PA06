package pa06;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Blue print for the K-Means class
 * 
 * @author Team 14
 *
 */

public class KMeans {
	int k;
	/**
	 * store a list of k clusters
	 */
	Cluster[] clusters;
	Sample[] clusterPoints;
	Cluster originalData;
	/**
	 * number of times to repeat until the clusters are stable
	 */
	final int REPEATTIMES = 100;

	public KMeans(int k, String filename){
		this.k = k;
		this.readFile(filename);
		// build clusters
		clusters = new Cluster[k];
		ArrayList<Sample>[] temp = (ArrayList<Sample>[]) new ArrayList[k];
		for(int i=0; i<k; i++) {
			temp[i] = new ArrayList<Sample>();
		}
		for(int i=0; i<originalData.getSize(); i++) {
			temp[i%k].add(originalData.getSample(i));
		}
		for(int i=0; i<k; i++) {
			clusters[i] = new Cluster(temp[i], temp[i].get(0));
		}
		// build clusterPoints
		clusterPoints = new Sample[k];
		for(int i=0; i<k; i++) {
			clusterPoints[i] = clusters[i].getClusterPoint();
		}

	}
	
	/**
	 * reads a file and adds it to the originalData cluster
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */

	public void readFile(String fileName) {
		try {
			this.originalData = new Cluster();
			Scanner fileReader = new Scanner(new File(fileName));
			while (fileReader.hasNextLine()) {
				String currentLine = fileReader.nextLine();
				Scanner lineReader = new Scanner(currentLine);
				double sampleCoords[] = new double[2];
				sampleCoords[0] = lineReader.nextDouble();
				sampleCoords[1] = lineReader.nextDouble();
				Sample point = new Sample(sampleCoords);
				this.originalData.add(point);

			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	/**
	 * return the index of the cluster which sample is closest to
	 * @param the Sample to reclassify
	 * @return the index of the cluster which sample is closest to
	 */
	public int findClosestCluster(Sample s) {
		int res = 0;
		for(int i=0; i<k; i++) {
			if(s.distanceTo(clusterPoints[i])<s.distanceTo(clusterPoints[res])) {
				res = i;
			}
		}
		return res;
	}
	
	/**
	 * reclassify all Samples into a new set of k-clusters
	 */
	public void reclassify() {
		// store all original Samples into an array of Sample arraylist
		ArrayList<Sample>[] reassignedSamples = (ArrayList<Sample>[]) new ArrayList[k];
		for(int i=0; i<k; i++) {
			reassignedSamples[i] = new ArrayList<Sample>();
		}
		// get the new clusterPoints
		for(int i=0; i<k; i++) {
			clusterPoints[i] = clusters[i].findAverage();
		}
		// reassign all original Samples according to the new clusterPoints
		for(int i=0; i<originalData.getSize(); i++) {
			int temp = findClosestCluster(originalData.getSample(i));
			reassignedSamples[temp].add(originalData.getSample(i));
		}
		// build the new clusters
		for(int i=0; i<k; i++) {
			clusters[i] = new Cluster(reassignedSamples[i], clusterPoints[i]);
		}
				
	}
	
	/**
	 * reclassify the original data for REPEATTIMES(in this case, 100) times
	 * then the clusters will be stable
	 */
	public void run() {
		for(int i=0; i<REPEATTIMES; i++) {
			this.reclassify();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		//ask for the file name and the number of clusters
		Scanner in = new Scanner(System.in);
		System.out.print("Filename: ");
		String filename = in.nextLine();
		System.out.print("Num of clusters: ");
		int num_clus = in.nextInt();
		
		KMeans km = new KMeans(num_clus, filename);
/*		
		
		double[] p1 = {1,2};
		double[] p2 = {3,4};
		double[] p3 = {17,23};
		double[] p4 = {21,23};
		double[] p5 = {15,26};
		double[] p6 = {121,2};
		double[] p7 = {21,23};
		double[] p8 = {11,231};
		double[] p9 = {10,-2};
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		Sample s3 = new Sample(p3);
		Sample s4 = new Sample(p4);
		Sample s5 = new Sample(p5);
		Sample s6 = new Sample(p6);
		Sample s7 = new Sample(p7);
		Sample s8 = new Sample(p8);
		Sample s9 = new Sample(p9);
		ArrayList<Sample> samples = new ArrayList<Sample>();
		samples.add(s1);
		samples.add(s2);
		samples.add(s3);
		samples.add(s4);
		samples.add(s5);
		samples.add(s6);
		samples.add(s7);
		samples.add(s8);
		samples.add(s9);
		Cluster c = new Cluster(samples, samples.get(0));
		KMeans km = new KMeans(3, c);
		*/
		
		km.run();
		
		for(int i = 0; i<num_clus; i++){
			km.clusters[i].print();
			System.out.println();
		}
		
		//km.clusters[0].print();
		//System.out.println(" ");
		//km.clusters[1].print();
		//System.out.println(" ");
		//km.clusters[2].print();
		
		
	}

}
