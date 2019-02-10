package com.scu.sol;

public class Calculate {

	/*
	 * Method: To calculate C-factor 
	 * Input: Integer - n
	 * Output: Double - C-factor
	 */
	public double calculate_Cvalue(int n) {
		double res = 2 * calculate_Hvalue(n - 1) - (((double) 2 * (n - 1)) / n);
		return res;
	}

	/*
	 * Method: To calculate Harmonic Number 
	 * Input: Integer - i 
	 * Output: Double - Harmonic Number
	 */
	private double calculate_Hvalue(int i) {
		return Math.log(i) + 0.5772156649;
	}

	/*
	 * Method: To calculate Anomaly Score
	 * Input: Double - Average Path-length, Integer n
	 * Output: Double - Anomaly Score
	 */
	public double calculate_anomalyScore(double pathLengthAvg, int n) {
		double res = -1 * (pathLengthAvg / calculate_Cvalue(n));
		return Math.pow(2, res);
	}
}
