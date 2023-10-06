package com.gis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gis.dto.GpsTempData;
import com.gis.dto.LocalData;
import com.gis.dto.NoiseTempData;
import com.gis.dto.RpmTempData;

@Mapper
public interface IGisDao {
	/**
	 * Temp_GPS Table에서 차량 번호 조회
	 * @author 여수한
	 */
	public List<String> selectCarNum();
	/**
	 * 해당 차량번호에 맞는 GPS데이터를 Temp Table에서 조회
	 * @author 여수한
	 */
	public List<GpsTempData> selectTempGpsData(String carNum);
	/**
	 * 해당 차량번호에 맞는 Noise데이터를 Temp Table에서 조회
	 * @author 여수한
	 */
	public List<NoiseTempData> selectTempNoiseData(String carNum);
	/**
	 * 해당 차량번호에 맞는 rpm데이터를 Temp Table에서 조회
	 * @author 여수한
	 */
	public List<RpmTempData> selectTempRpmData(String carNum);
	/**
	 * Temp Table에 데이터를 변동 가능한 시간으로 가공후 로컬DB에 저장
	 * @author 여수한
	 */
	public void insertLocalData(@Param("localData")LocalData localData);
	/**
	 * Temp Table 데이터 삭제
	 * @author 여수한
	 */
	public void deleteTempGpsTable();
	public void deleteTempNoiseTable();
	public void deleteTempRpmTable();
	/**
	 * Temp Table 데이터 추가
	 * @author 여수한
	 */
	public void insertGpsTempData(GpsTempData gtd);
	public void insertNoiseTempData(NoiseTempData ntd);
	public void insertRpmTempData(RpmTempData rtd);
}
