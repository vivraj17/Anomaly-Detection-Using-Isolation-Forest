import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

	public static void main(String[] args) {

		BufferedReader br_con = null;
		String line = "";
		String cvsSplitBy = ",";
		int numberOfAnomalies = 0;

		List<Data> X = new ArrayList<Data>();

		Calculate calculate = new Calculate();

		/*
		 * Reading Data from console
		 */
		System.out.println("Please enter the instances: ");
		br_con = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			line = br_con.readLine();
			if (line == null || line.trim().isEmpty()) {
				System.out.println("Invalid Input! Try ...!");
				return;
			}
			
			numberOfAnomalies = Integer.valueOf(line.trim());
			int size = 0;
			while ((line = br_con.readLine()) != null) {

				String[] inputs = line.split(cvsSplitBy);

				List<Double> values = new ArrayList<Double>();

				for (String input : inputs) {
					values.add(Double.valueOf(input.trim()));
				}
				
				if (size == 0) {
					Data data = new Data(values);
					X.add(data);
					size = values.size();
				} else if(values.size() == size){
					Data data = new Data(values);
					X.add(data);
				}else {
					throw new Exception();
				}
			}
			
			
		} catch (NumberFormatException | IOException e) {
			System.out.println("Invalid Input! Try Again....!");
			e.getMessage();
			return;
		} catch (Exception e) {
			System.out.println("Invalid Input! Try Again....!");
			e.getMessage();
			return;
		}

		Algorithms alg = new Algorithms();
		Forest iforestRes = alg.iForest(X, X.size()/5, X.size()/10);

		List<Double> anomalyScore = new ArrayList<Double>();
		List<Anomaly> anomalies = new ArrayList<Anomaly>();
		int index = 0;

		/*
		 *Calculating anomaly score for all instances 
		 */
		for (Data data : X) {

			double pathLengthSum = 0;
			for (TreeNode itree : iforestRes.Trees) {

				Analysis analysis = new Analysis();
				pathLengthSum = pathLengthSum + analysis.PathLength(data.values, itree, 0);
			}

			double pathLengthAvg = (pathLengthSum / iforestRes.Trees.size());

			anomalyScore.add(calculate.calculate_anomalyScore(pathLengthAvg, X.size()));

			Anomaly anomaly = new Anomaly();
			anomaly.a_score = calculate.calculate_anomalyScore(pathLengthAvg, X.size());
			anomaly.index = index;
			anomaly.values = data.values;
			anomalies.add(anomaly);
			index++;
		}

		/*
		 * Searching top k anomalies
		 */
		if (anomalies.size() >= numberOfAnomalies) {
			
			System.out.println("\nTop " + numberOfAnomalies + " anomalies of the given set are: ");
			Collections.sort(anomalies, new AnomalyComp());
			for (int i = 0; i < numberOfAnomalies; i++) {
				
				System.out.print(i + 1 + ") ");
				Anomaly anomaly = anomalies.get(i);
				for (Double val : anomaly.values) {
					System.out.print(val + ", ");
				}
				System.out.print("Score: " + anomaly.a_score);
				System.out.println();
			}
		} else {
			System.out.println("Invalid Input: Please enter atleast " + numberOfAnomalies + " instances");
		}

	}

}
