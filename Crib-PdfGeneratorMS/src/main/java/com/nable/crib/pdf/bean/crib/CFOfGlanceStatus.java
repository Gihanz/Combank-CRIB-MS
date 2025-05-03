package com.nable.crib.pdf.bean.crib;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CFOfGlanceStatus {

	private String status;
	private String arrearsDays0;
	private String arrearsDays1_30;
	private String arrearsDays31_60;
	private String arrearsDays61_90;
	private String arrearsDaysOver90;
	
}
