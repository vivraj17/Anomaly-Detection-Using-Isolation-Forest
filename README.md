# Anomaly-Detection-Using-Isolation-Forest

Implementation of Isolation Forest Algorithm for anomaly detection in Java

Input - input from stdin with first row taking an integer value that denotes the number of anomalies to be returned in output followed by data points.

Output - Set of anomalies with their data points and anomalyScore.

The anomaly score s(x, n) = 2^(-E(h(x))/c(n)) where c(n) = 2H(n-1) – 2(n-1)/n is the average of h(x) given n, H(i) is the harmonic number as ln(i) + 0.5772156649 (Euler’s constant), and h(x) is the path length. E(h(x)) is the average h(x) of from a collection of isolation trees. 0 < s ≤ 1 for 0 < h(x) ≤ n -1. If s close to 1, then they are definitely anomalies, if s much smaller than 0.5, they are normal instances, and if all instances return s ≈ 0.5, then the entire sample doesn’t really have any anomaly

File Descriptions:

Main.java : Main method
Data Structure Objects : Forest.java, TreeNode.java, Data.java, Anomaly.java, AnomalyComp.java
Mathematical Calculations : Calculate.java

Algorithms.java
The important methods implementing the crucs of the algorithm are theoretically mentioned below:
                            iForest - Function takes input - data, number of trees and sub sampling size
                            and returns a list of trees. Each tree is created for a random sub sample size of input data.
                       
                            iTree - Function takes input - random sub sample input (X), current tree height (e), and height limit (l). 
                            Returns a tree for X. The external node for the tree holds the size of remaining input.

Analysis.Java
The file includes a method to analyse the data point to detect if anomaly or not.
                            PathLength - Method: To calculate Path-Length 
                                         Input: List<Double> - Input Data Instances, TreeNode iTree, Integer current path-length
                                         Output: Double - Path-Length
                            
                            
To run the code, Run the main method and give input as described above with each data point on a new line with comma separated values. Press CTRL-D on Windows(CMD-C/CTRL-C on MAC/Linux) to stop adding input data.

Sample Inputs:

10                      // Top # of anomalies you want to detect
-1.222, 3.234, 3.222    // Data dimension can be unlimited but shoul be constant throughout
-4.232, 4.2332, 3.25    // Data Range should be in DOUBLE range defined by Java
8.222, 6.2332, 6.27     // Can have huge set of data (1000000..) , gives best result with large data set.
-5.222, 2.2332, 3.522
.
.
.
.

Output:
Top K anomalies score and data points.
Usually score above 75% can be considered as anomalies.
