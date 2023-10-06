package com.gis.service;

import java.util.List;

import com.gis.dto.GpsTempData;
import com.gis.dto.NoiseTempData;
import com.gis.dto.RpmTempData;

public interface IGisService {
	/**
	 * Temp Table에서 차량 번호 조회
	 * @author 여수한
	 */
	List<String> selectCarNumber();
	/**
	 * Temp Table에서 GPS, 소음, 진동 데이터 불러와 평균내기
	 * @author 여수한
	 */
	void selectTempData(String carNum);
	/**
	 * Temp Table 비우기
	 * @author 여수한
	 */
	void deleteTempTable();
	/**
	 * Temp Table 넣기
	 * @author 여수한
	 */
	void insertGpsTempData(GpsTempData gtd);
	void insertNoiseTempData(NoiseTempData ntd);
	void insertRpmTempData(RpmTempData rtd);
}
