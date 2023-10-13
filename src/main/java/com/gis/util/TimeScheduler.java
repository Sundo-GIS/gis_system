package com.gis.util;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.gis.service.gis.IGisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class TimeScheduler {

	private final IGisService gisService;
	private ScheduledExecutorService scheduler;

	public void startScheduler(int time) {
		stopScheduler();
		log.info("시작");
		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(this::insertLocalDB, 0, time, TimeUnit.SECONDS);
	}
	public void stopScheduler() {
		if (scheduler != null && !scheduler.isShutdown()) {
			gisService.deleteTempTable();
			scheduler.shutdown();
			try {
				if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
					scheduler.shutdownNow();
				}
			} catch (InterruptedException e) {
				scheduler.shutdownNow();
				Thread.currentThread().interrupt();
			}
			log.info("종료");
		}
	}
	/**
	 * 변동 가능한 시간의 스케줄러 실행
	 * @author 여수한
	 */
	public void insertLocalDB() {
		List<String> carNums = gisService.selectCarNumber();
		for (int i = 0; i < carNums.size(); i++) {
			gisService.selectTempData(carNums.get(i));
		}
		gisService.deleteTempTable();
	}
}
