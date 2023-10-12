package com.gis.dto.mainView;

import lombok.Data;

@Data
public class CleaningInfoDto {

	private double lon;
	private double lat;	
	private String cleanTime;
	private double cleanRatio;
	private double totalDistance;
	private double cleanDistance;
}
