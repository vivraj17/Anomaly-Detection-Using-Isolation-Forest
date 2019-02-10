package com.scu.sol;

import java.util.List;

public class Analysis {
	
	/*
	 * Method: To calculate Path-Length 
	 * Input: List<Double> - Input Data Instances, TreeNode iTree, Integer current path-length
	 * Output: Double - Path-Length
	 */
	public double PathLength(List<Double> instance, TreeNode iTree, int currentPathLength) {

		if (iTree.left == null && iTree.right == null) {
			Calculate cal = new Calculate();
			
			if(iTree.size > 1) {
				
				return currentPathLength + cal.calculate_Cvalue(iTree.size); 
			}else {
				return currentPathLength;
			}
			
		} else {
			int splitattr = iTree.attribute;
			if (instance.get(splitattr) < iTree.splitFactor) {
				return PathLength(instance, iTree.left, currentPathLength + 1);
			} else {

				return PathLength(instance, iTree.right, currentPathLength + 1);
			}
		}
	}
}
