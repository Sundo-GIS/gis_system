package com.gis.service.mainView;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gis.dao.gis.IGisDao;
import com.gis.dao.mainView.IMainViewDao;
import com.gis.dto.gis.DateCoord;
import com.gis.dto.mainView.CarDto;
import com.gis.dto.mainView.CarNumListDto;
import com.gis.dto.mainView.CleaningInfoDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MainViewService implements IMainViewService {

	private final IMainViewDao mainViewDao;
	private final IGisDao gisDao;

	/**
	 * 모달창 차량 추가
	 * 
	 * @author 고일봉
	 */
	public void addCar(CarDto car) {
		mainViewDao.addCar(car);
	}

	/**
	 * 모달창 차량 삭제
	 * 
	 * @author 고일봉
	 */
	public void deleteCar(String carNum) {
		mainViewDao.deleteCar(carNum);
	}

	/**
	 * 차량 데이터 삭제
	 * 
	 * @author 고일봉
	 */
	public void deleteCarData(String carNum) {
		mainViewDao.deleteCarData(carNum);
	}

	/**
	 * 차량 리스트 출력
	 * 
	 * @author 고일봉
	 */
	public List<CarNumListDto> carNumList() {
		List<CarDto> cdList = mainViewDao.carNumList();
		List<CarNumListDto> cnldList = new ArrayList<>();

		for (CarDto cd : cdList) {
			CarNumListDto cnld = new CarNumListDto();

			cnld.setCarNum(cd.getCarNum());
			cnldList.add(cnld);
		}
		return cnldList;
	}

	/**
	 * 차량 별 청소 날짜 출력
	 * 
	 * @author 고일봉
	 */
	public List<String> selectedDate(String carNum) {
		List<String> cdd = mainViewDao.selectedDate(carNum);
		return cdd;
	}

	/**
	 * 차량, 날짜 선택시 청소정보 및 달력에 대한 좌표 갖고오기
	 * 
	 * @author 고일봉
	 */
	public CleaningInfoDto cleaningInfo(String carNum, String date) {

		// CleaningInfoDto에 담기 위한 생성사 생성
		CleaningInfoDto cid = new CleaningInfoDto();

		// 달력 날짜 누르면 운행 시간 계산
		String cleanTime = gisDao.selectCleanTime(date, carNum);

		// 달력 날짜 누르면 전체 운행거리 계산
		List<DateCoord> cleanDistanceGeom = gisDao.selectCleanDistanceGeom(date, carNum);
		double totalDistance = 0;
		for (int i = 0; i < cleanDistanceGeom.size() - 1; i++) {
			totalDistance += gisDao.selectCleanDistance(cleanDistanceGeom.get(i), cleanDistanceGeom.get(i + 1));
		}

		// 달력 날짜 누르면 청소 운행거리 계산
		List<DateCoord> cleanDistanceResult = gisDao.selectCleanDistanceGeom(date, carNum);
		double cleanDistance = 0;
		for (int i = 0; i < cleanDistanceResult.size() - 1; i++) {
			if (!(cleanDistanceResult.get(i).is_done() == false && cleanDistanceResult.get(i + 1).is_done() == false)) {
				cleanDistance += gisDao.selectCleanDistance(cleanDistanceResult.get(i), cleanDistanceResult.get(i + 1));
			}
		}

		// 달력 날짜 누르면 청소 비율 계산
		double cleanRatio = (cleanDistance / totalDistance) * 100;

		// 달력 날짜 누르면 해당 날짜의 좌표 데이터 조회
		DateCoord dc = gisDao.selectDateCoord(date, carNum);

		cid.setCleanTime(cleanTime);
		cid.setCleanRatio(cleanRatio);
		cid.setTotalDistance(totalDistance / 1000);
		cid.setCleanDistance(cleanDistance / 1000);
		cid.setLat(dc.getLat());
		cid.setLon(dc.getLon());

		return cid;
	}
}
