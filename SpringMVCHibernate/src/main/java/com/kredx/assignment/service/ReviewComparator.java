package com.kredx.assignment.service;

import java.util.Comparator;

import com.kredx.assignnment.model.ReviewDoc;

public class ReviewComparator implements Comparator<ReviewDoc>{

	@Override
	public int compare(ReviewDoc o1, ReviewDoc o2) {
		if(o1.getMatches() > o2.getMatches())  {
			return -1;
		}
		else if(o1.getMatches() < o2.getMatches()) {
			return 1;
		}
		else {
			if(o1.getScore() > o2.getScore()) {
				return -1;
			}
			else if(o1.getScore() < o2.getScore()) {
				return 1;
			}
			else 
				return 0;
		}
	}
	
}
