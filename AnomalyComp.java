package com.scu.sol;

import java.util.Comparator;

public class AnomalyComp implements Comparator<Anomaly>{

	@Override
	public int compare(Anomaly o1, Anomaly o2) {
		
		return Double.compare(o2.a_score, o1.a_score);
	}
}
