package com.gis.service.fileUpload;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.gis.dao.file.IFileDao;
import com.gis.dto.gis.LocalData;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements IFileService {

	private final IFileDao iFileDao;

	@Override
	public void uploadCsv(String[] gpsFile, String[] noiseFile, String[] rpmFile) {
		LocalData localData = new LocalData();
		localData.setCarNum(gpsFile[0]);
		localData.setDate(LocalDate.parse(gpsFile[1]));
		localData.setTime(LocalTime.parse(gpsFile[2]));
		localData.setLon(Double.parseDouble(gpsFile[3]));
		localData.setLat(Double.parseDouble(gpsFile[4]));
		localData.setNoise(Integer.parseInt(noiseFile[3]));
		localData.setRpm(Integer.parseInt(rpmFile[3]));

		boolean is_done = false;

		if (Integer.parseInt(noiseFile[3]) >= 80 && Integer.parseInt(rpmFile[3]) >= 1500) {
			is_done = true;
		}
		localData.set_done(is_done);
		iFileDao.insertCsvData(localData);
	}
}
