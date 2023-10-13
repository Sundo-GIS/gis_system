package com.gis.dao.gis;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gis.dto.gis.DateCoord;
import com.gis.dto.gis.GpsTempData;
import com.gis.dto.gis.LocalData;
import com.gis.dto.gis.NoiseTempData;
import com.gis.dto.gis.RpmTempData;

@Mapper
public interface IGisDao {
	/**
	 * Car테이블에서 차량 번호 조회
	 * @author 여수한
	 */
	public List<String> selectCar();
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
	public void insertGpsTempData(@Param("gtd")GpsTempData gtd);
	public void insertNoiseTempData(@Param("ntd")NoiseTempData ntd);
	public void insertRpmTempData(@Param("rtd")RpmTempData rtd);
	/**
	 * 달력 날짜 누르면 운행 시간 계산
	 * @author 여수한
	 */
	public String selectCleanTime(@Param("date")String date, @Param("carNum")String carNum);
	/**
	 * 달력 날짜 누르면 청소 비율 계산
	 * @author 여수한
	 * @param carNum 
	 * @param date 
	 */
	public double selectCleanRatio(@Param("date")String date, @Param("carNum") String carNum);
	/**
	 * 달력 날짜 누르면 전체 운행거리 계산
	 * @author 여수한
	 */
	public List<DateCoord> selectCleanDistanceGeom(@Param("date")String date,@Param("carNum") String carNum);
	public double selectCleanDistance(@Param("start")DateCoord start, @Param("end")DateCoord end);
	/**
	 * 달력 날짜 누르면 해당 날짜의 좌표 데이터 조회
	 * @author 여수한
	 */
	public DateCoord selectDateCoord(@Param("date")String date, @Param("carNum")String carNum);
	/**
	 * 라이브 좌표 조회
	 * @author 여수한
	 */
	public DateCoord selectLiveCoord();
	public void insertLiveData(@Param("localData")LocalData localData);
	/**
	 * 실시간 좌표 조회 끝
	 * @author 여수한
	 */
	public void deleteLiveCoord();
	public List<LocalData> selectCoord(@Param("date")LocalDate date);
	public void insertCleanLine(@Param("start")LocalData start, @Param("end")LocalData end, @Param("isDone") boolean is_done);
}
