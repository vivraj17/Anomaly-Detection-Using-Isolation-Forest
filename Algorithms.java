package com.scu.sol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algorithms {
	
	/*
	 * Method: To generate an iForest of given instances
	 * Inputs: List<Data> - input data, integer - number of trees, integer – sub-sampling size 
	 * Output: Forest : a set of iTree
	 */
	public Forest iForest(List<Data> inputData, int numberOfTrees, int subsamplingSize) {

		List<TreeNode> iTrees = new ArrayList<TreeNode>();

		int heightLimit = (int) Math.ceil(Math.log(subsamplingSize) / Math.log(2));
		for (int i = 0; i < numberOfTrees; i++) {

			List<Data> Xi = new ArrayList<Data>();
			Random randomGenerator = new Random();
			for (int j = 0; j < subsamplingSize; j++) {
				Xi.add(inputData.get(randomGenerator.nextInt(inputData.size())));
			}

			iTrees.add(iTree(Xi, 0, heightLimit));
		}

		Forest iforest = new Forest(iTrees);
		return iforest;

	}

	/*
	 * Method: To generate an iTree of given instances
	 * Inputs: List<Data>  - input data, integer - current tree height, integer – height limit
	 * Output: TreeNode : an iTree
	 */
	private TreeNode iTree(List<Data> inputData, int currentTreeHeight, int heightLimit) {
		if ((currentTreeHeight >= heightLimit) || (inputData.size() <= 1)) {
			
			TreeNode itree = new TreeNode();
			itree.size = inputData.size();
			return itree;
		} else {

			Random randomGenerator = new Random();
			int q = randomGenerator.nextInt(inputData.get(0).values.size());
			List<Double> max_min_ip = new ArrayList<Double>();
			for (Data dt : inputData) {
				max_min_ip.add((Double) dt.values.get(q));
			}

			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;

			for (int i = 0; i < max_min_ip.size(); i++) {
				if (max_min_ip.get(i) > max) {
					max = max_min_ip.get(i);
				}
				if (max_min_ip.get(i) < min) {
					min = max_min_ip.get(i);
				}
			}

			double p = min + (max - min) * randomGenerator.nextDouble();

			List<Data> XL = new ArrayList<Data>();
			List<Data> XR = new ArrayList<Data>();

			for (Data data : inputData) {

				if (data.values.get(q) < p) {
					XL.add(data);
				} else {
					XR.add(data);
				}
			}
			TreeNode itree = new TreeNode();
			itree.attribute = q;
			itree.splitFactor = p;
			itree.left = iTree(XL, currentTreeHeight + 1, heightLimit);
			itree.right = iTree(XR, currentTreeHeight + 1, heightLimit);

			return itree;
		}

	}

}
