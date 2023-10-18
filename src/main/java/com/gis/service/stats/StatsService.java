package com.gis.service.stats;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gis.dao.gis.IGisDao;
import com.gis.dao.mainView.IMainViewDao;
import com.gis.dao.stats.IStatsDao;
import com.gis.dto.gis.DateCoord;
import com.gis.dto.stats.CarDateDto;
import com.gis.dto.stats.SelectCarDto;
import com.gis.dto.stats.StatsInfoDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatsService implements IStatsService {

	private final IStatsDao statsDao;
	private final IMainViewDao mainViewDao;
	private final IGisDao gisDao;

	/**
	 * 통계 뷰 데이터 출력(모달창)
	 * 
	 * @author 고일봉
	 */
	public StatsInfoDto statsInfoDto (SelectCarDto selectCarDto) {
		
		
		StatsInfoDto sid = new StatsInfoDto();
		
		// 각 날짜에 따른 데이터값들 계산
		// 총 청소 시간
		LocalTime totalCleanTime =  LocalTime.of(0, 0, 0);
		// 총 운행 거리
		double totalDistance = 0;
		// 총 청소 거리
		double cleanDistance = 0;
		
		List<CarDateDto> cdd = statsDao.selectCar(selectCarDto);
		// 선택한 날짜
		String selectDate;
		// 선택 차량
		String selectCarNum = cdd.get(0).getCarNum();
		
		
		for (int i=0; i < cdd.size(); i++ ) {
			selectDate = cdd.get(i).getDate();
			
			// 날짜에 따른 운행 시간 계산
			 String cleanTimeString = gisDao.selectCleanTime(selectDate, selectCarNum);

			    if (cleanTimeString != null) {
			        LocalTime cleanTime = LocalTime.parse(cleanTimeString);
			        totalCleanTime = totalCleanTime.plusHours(cleanTime.getHour())
			                                     .plusMinutes(cleanTime.getMinute())
			                                     .plusSeconds(cleanTime.getSecond());
			    }
			
			// 날짜에 따른 운행거리 함수
			List<DateCoord> cleanDistanceGeom = gisDao.selectCleanDistanceGeom(selectDate, selectCarNum);
			for (int j = 0; j < cleanDistanceGeom.size() - 1; j++) {
				totalDistance += gisDao.selectCleanDistance(cleanDistanceGeom.get(j), cleanDistanceGeom.get(j + 1));
			}
			
			// 날짜에 따른 청소 운행거리 계산
			List<DateCoord> cleanDistanceResult = gisDao.selectCleanDistanceGeom(selectDate, selectCarNum);
			for (int j = 0; j < cleanDistanceResult.size() - 1; j++) {
				if (!(cleanDistanceResult.get(i).is_done() == false && cleanDistanceResult.get(i + 1).is_done() == false)) {
					cleanDistance += gisDao.selectCleanDistance(cleanDistanceResult.get(i), cleanDistanceResult.get(i + 1));
				}
			}		
			
		}
		// 달력 날짜 누르면 청소 비율 계산
		double cleanRatio = (cleanDistance / totalDistance) * 100;
		sid.setCleanTime(totalCleanTime);
		sid.setCleanRatio(cleanRatio);
		sid.setTotalDistance(totalDistance/1000);
		sid.setCleanDistance(cleanDistance/1000);	
		
		log.info(sid);
		return sid;
	}
	
}
