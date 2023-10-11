package com.gis.service.gis;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gis.dao.gis.IGisDao;
import com.gis.dto.gis.DateCoord;
import com.gis.dto.gis.GpsTempData;
import com.gis.dto.gis.LocalData;
import com.gis.dto.gis.NoiseTempData;
import com.gis.dto.gis.RpmTempData;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GisServiceImpl implements IGisService {

	private final IGisDao gisDao;
	
	/**
	 * Temp Table에서 차량 번호 조회
	 * @author 여수한
	 */
	@Override
	public List<String> selectCarNumber() {
		List<String> carNum = gisDao.selectCarNum();
		return carNum;
	}
	/**
	 * 현재 날짜를 원하는 형식(yyyy-MM-dd)으로 포맷하려면 아래와 같이 사용할 수 있습니다.
	 * @author 여수한
	 */
	public String getCurrentDateFormatted() {
	    LocalDate currentDate = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String formattedDate = currentDate.format(formatter);
	    return formattedDate;
	}
	/**
	 * 현재 시간을 원하는 형식(HH:mm:ss)으로 포맷하려면 아래와 같이 사용할 수 있습니다.
	 * @author 여수한
	 */
	public String getCurrentTimeFormatted() {
	    LocalTime currentTime = LocalTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String formattedTime = currentTime.format(formatter);
	    return formattedTime;
	}
	/**
	 * Temp Table에서 GPS, 소음, 진동 데이터 불러와 평균내기
	 * @author 여수한
	 */
	@Override
	public void selectTempData(String carNum) {
		String date = getCurrentDateFormatted();
		String time = getCurrentTimeFormatted();
		// gps 데이터 추출 
		List<GpsTempData> tgd = gisDao.selectTempGpsData(carNum);
		double avgX = (tgd.get(0).getLon()+tgd.get(tgd.size()-1).getLon())/ 2;
		double avgY = (tgd.get(0).getLat()+tgd.get(tgd.size()-1).getLat())/ 2;
		// 소음 데이터 추출
		List<NoiseTempData> tnd = gisDao.selectTempNoiseData(carNum);
		int totalNoise = 0;
		int avgNoise = 0;
		int tndSize = 0;
		int lowNoise = 60;
		int highNoise = 140;
		for(int i=0; i<tnd.size(); i++) {
			if(tnd.get(i).getNoise()>lowNoise&&tnd.get(i).getNoise()<highNoise) {
				totalNoise += tnd.get(i).getNoise();	
				tndSize++;
			}
		}
		avgNoise = totalNoise/tndSize;
		// 진동 데이터 추출
		List<RpmTempData> trd = gisDao.selectTempRpmData(carNum);
		int totalRpm = 0;
		int avgRpm = 0;
		int trdSize = 0;
		int lowRpm = 1000;
		int highRpm = 2500;
		for(int i=0; i<trd.size(); i++) {
			if(trd.get(i).getRpm()>lowRpm && trd.get(i).getRpm() < highRpm) {
				totalRpm += trd.get(i).getRpm();
				trdSize++;
			}
		}
		avgRpm = totalRpm/trdSize;
		// 소음 80이상 and 진동 1500이상 = true
		boolean is_done = false;
		if(avgNoise>=80&&avgRpm>=1500) {
			is_done=true;
		}
		// GPS, 소음, 진동 데이터들 로컬DB에 넣기
		LocalData localData = new LocalData();
		localData.setCarNum(carNum);
		localData.setDate(date);
		localData.setTime(time);
		localData.setLon(avgX);
		localData.setLat(avgY);
		localData.setNoise(avgNoise);
		localData.setRpm(avgRpm);
		localData.set_done(is_done);
		gisDao.insertLocalData(localData);
	}
	/**
	 * Temp Table 비우기
	 * @author 여수한
	 */
	@Override
	public void deleteTempTable() {
		gisDao.deleteTempGpsTable();
		gisDao.deleteTempNoiseTable();
		gisDao.deleteTempRpmTable();
	}
	/**
	 * Temp Table에 GPS, Noise, Rpm 데이터 넣기
	 * @author 여수한
	 */
	@Override
	public void insertGpsTempData(GpsTempData gtd) {
//		String date = getCurrentDateFormatted();
//		String time = getCurrentTimeFormatted();
//		gtd.setDate(date);
//		gtd.setTime(time);
		gisDao.insertGpsTempData(gtd);
	}
	@Override
	public void insertNoiseTempData(NoiseTempData ntd) {
//		String date = getCurrentDateFormatted();
//		String time = getCurrentTimeFormatted();
//		ntd.setDate(date);
//		ntd.setTime(time);
		gisDao.insertNoiseTempData(ntd);
	}
	@Override
	public void insertRpmTempData(RpmTempData rtd) {
//		String date = getCurrentDateFormatted();
//		String time = getCurrentTimeFormatted();
//		rtd.setDate(date);
//		rtd.setTime(time);
		gisDao.insertRpmTempData(rtd);
	}
	/**
	 * 달력 날짜 누르면 운행 시간 계산
	 * @author 여수한
	 */
	@Override
	public String selectDateCleanTime(String date) {
		String cleanTime = gisDao.selectCleanTime(date);
		log.info(cleanTime);
		return cleanTime;
	}
	/**
	 * 달력 날짜 누르면 청소 비율 계산
	 * @author 여수한
	 */
	@Override
	public int selectDateCleanRatio(String date) {
		int cleanRatio = gisDao.selectCleanRatio();
		return cleanRatio;
	}
	/**
	 * 달력 날짜 누르면 전체 운행거리 계산
	 * @author 여수한
	 */
	@Override
	public int selectDateTotalDistance(String date) {
		int totalDistance = gisDao.selectTotalDistance();
		return totalDistance;
	}
	/**
	 * 달력 날짜 누르면 청소 운행거리 계산
	 * @author 여수한
	 */
	@Override
	public int selectDateCleanDistance(String date) {
		int cleanDistance = gisDao.selectCleanDistance();
		return cleanDistance;
	}
	/**
	 * 달력 날짜 누르면 해당 날짜의 좌표 데이터 조회
	 * @author 여수한
	 */
	@Override
	public DateCoord selectDateCoord(String date, String carNum) {
		log.info(date + carNum);
		DateCoord dc = gisDao.selectDateCoord(date, carNum);
		log.info(dc);
		return dc;
	}
}
