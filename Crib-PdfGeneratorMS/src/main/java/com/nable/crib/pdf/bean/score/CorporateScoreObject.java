package com.nable.crib.pdf.bean.score;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporateScoreObject {

	private ScoreGeneralDetails scoreGeneralDetails;
	private CribScore cribScore;
	private List<ScoreObservations> ScoreObservations;
	
}
