package com.gis.service.gis;

import java.math.BigDecimal;
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
	 * Car테이블에서 차량 번호 조회
	 * @author 여수한
	 */
	@Override
	public List<String> selectCar() {
		List<String> carList = gisDao.selectCar();
    	return carList;
	}
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
	public LocalDate getCurrentDateFormatted() {
	    LocalDate currentDate = LocalDate.now();
	    return currentDate;
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
		LocalDate date = getCurrentDateFormatted();
		String time = getCurrentTimeFormatted();
		// gps 데이터 추출 
		List<GpsTempData> tgd = gisDao.selectTempGpsData(carNum);
		double avgX = (tgd.get(0).getLon()+tgd.get(tgd.size()-1).getLon())/ 2;
		double avgY = (tgd.get(0).getLat()+tgd.get(tgd.size()-1).getLat())/ 2;
		// 소음 데이터 추출
		List<NoiseTempData> tnd = gisDao.selectTempNoiseData(carNum);
		BigDecimal totalNoise = new BigDecimal(0);
		int avgNoise = 0;
		int tndSize = 0;
		BigDecimal lowNoise = new BigDecimal(60);
		BigDecimal highNoise = new BigDecimal(140);
		for(int i=0; i<tnd.size(); i++) {
			if(tnd.get(i).getNoise().compareTo(lowNoise)>0&&highNoise.compareTo(tnd.get(i).getNoise())>0) {
				totalNoise = totalNoise.add(tnd.get(i).getNoise());
				tndSize++;
			}
		}
		avgNoise = totalNoise.intValue()/tndSize;
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
		localData.setRpm(avgRpm);
		localData.setNoise(avgNoise);
		localData.set_done(is_done);
		gisDao.insertLocalData(localData);
		gisDao.insertLiveData(localData);
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
		gisDao.insertGpsTempData(gtd);
	}
	@Override
	public void insertNoiseTempData(NoiseTempData ntd) {
		gisDao.insertNoiseTempData(ntd);
	}
	@Override
	public void insertRpmTempData(RpmTempData rtd) {
		gisDao.insertRpmTempData(rtd);
	}
	/**
	 * 달력 날짜 누르면 운행 시간 계산
	 * @author 여수한
	 */
	@Override
	public String selectDateCleanTime(String date, String carNum) {
		String cleanTime = gisDao.selectCleanTime(date,carNum);
		return cleanTime;
	}
	/**
	 * 달력 날짜 누르면 청소 비율 계산
	 * @author 여수한
	 */
	@Override
	public double selectDateCleanRatio(String date, String carNum) {
		double cleanRatio = gisDao.selectCleanRatio(date, carNum);
		return cleanRatio;
	}
	/**
	 * 달력 날짜 누르면 전체 운행거리 계산
	 * @author 여수한
	 */
	@Override
	public double selectDateTotalDistance(String date, String carNum) {
		List<DateCoord> cleanDistanceGeom = gisDao.selectCleanDistanceGeom(date, carNum);
		double totalDistance = 0;
		for(int i=0; i<cleanDistanceGeom.size()-1; i++) {
			totalDistance += gisDao.selectCleanDistance(cleanDistanceGeom.get(i), cleanDistanceGeom.get(i+1));
		}
		return totalDistance/1000;
	}
	/**
	 * 달력 날짜 누르면 청소 운행거리 계산
	 * @author 여수한
	 */
	@Override
	public double selectDateCleanDistance(String date, String carNum) {
		List<DateCoord> cleanDistanceGeom = gisDao.selectCleanDistanceGeom(date, carNum);
		double cleanDistance = 0;
		for(int i=0; i<cleanDistanceGeom.size()-1; i++) {
			if(!(cleanDistanceGeom.get(i).is_done() == false && cleanDistanceGeom.get(i+1).is_done() == false)) {
				cleanDistance += gisDao.selectCleanDistance(cleanDistanceGeom.get(i), cleanDistanceGeom.get(i+1));
			}
		}
		return cleanDistance/1000;
	}
	/**
	 * 달력 날짜 누르면 해당 날짜의 좌표 데이터 조회
	 * @author 여수한
	 */
	@Override
	public DateCoord selectDateCoord(String date, String carNum) {
		DateCoord dc = gisDao.selectDateCoord(date, carNum);
		return dc;
	}
	/**
	 * 라이브 좌표 조회
	 * @author 여수한
	 */
	@Override
	public DateCoord selectLiveCoord() {
		DateCoord liveCoord = gisDao.selectLiveCoord();
		return liveCoord;
	}
}
