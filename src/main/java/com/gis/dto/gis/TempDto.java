package com.gis.dto.gis;

import lombok.Data;

@Data
public class TempDto {
	private GpsTempData gtd;
	private NoiseTempData ntd;
	private RpmTempData rtd;
}
