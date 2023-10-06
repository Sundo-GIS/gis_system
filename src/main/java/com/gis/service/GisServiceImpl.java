package com.gis.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gis.dao.IGisDao;
import com.gis.dto.GpsTempData;
import com.gis.dto.LocalData;
import com.gis.dto.NoiseTempData;
import com.gis.dto.RpmTempData;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
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
		double avgX = (tgd.get(0).getX()+tgd.get(tgd.size()-1).getX())/ 2;
		double avgY = (tgd.get(0).getY()+tgd.get(tgd.size()-1).getY())/ 2;
		// 소음 데이터 추출
		List<NoiseTempData> tnd = gisDao.selectTempNoiseData(carNum);
		int totalNoise = 0;
		int avgNoise = 0;
		for(int i=0; i<tnd.size(); i++) {
			totalNoise += tnd.get(i).getNoiseLevel();
		}
		avgNoise = totalNoise/tnd.size();
		// 진동 데이터 추출
		List<RpmTempData> trd = gisDao.selectTempRpmData(carNum);
		int totalRpm = 0;
		int avgRpm = 0;
		for(int i=0; i<trd.size(); i++) {
			totalRpm += trd.get(i).getRpmLevel();
		}
		avgRpm = totalRpm/trd.size();
		// 소음 80이상 and 진동 1500이상 = true
		boolean is_done = false;
		if(avgNoise>=80&&avgRpm>=1500) {
			is_done=true;
		}
		// GPS, 소음, 진동 데이터들 로컬DB에 넣기
		LocalData localData = new LocalData();
		localData.setCar(carNum);
		localData.setDate(date);
		localData.setTime(time);
		localData.setX(avgX);
		localData.setY(avgY);
		localData.setNoiseLevel(avgNoise);
		localData.setRpmLevel(avgRpm);
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
	 * Temp Table에 데이터 넣기
	 * @author 여수한
	 */
	@Override
	public void insertGpsTempData(GpsTempData gtd) {
		String date = getCurrentDateFormatted();
		String time = getCurrentTimeFormatted();
		gtd.setDate(date);
		gtd.setTime(time);
		gisDao.insertGpsTempData(gtd);
	}
	@Override
	public void insertNoiseTempData(NoiseTempData ntd) {
		String date = getCurrentDateFormatted();
		String time = getCurrentTimeFormatted();
		ntd.setDate(date);
		ntd.setTime(time);
		gisDao.insertNoiseTempData(ntd);
	}
	@Override
	public void insertRpmTempData(RpmTempData rtd) {
		String date = getCurrentDateFormatted();
		String time = getCurrentTimeFormatted();
		rtd.setDate(date);
		rtd.setTime(time);
		gisDao.insertRpmTempData(rtd);
	}
}
