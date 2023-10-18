package com.gis.dto.stats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class SelectCarDto {
	
	private String carNum;
	private String firstDate;
	private String lastDate;	
}

