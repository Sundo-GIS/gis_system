package com.gis.service.fileUpload;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gis.dao.file.IFileDao;
import com.gis.dto.gis.LocalData;
import com.opencsv.CSVReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements IFileService {

	private final IFileDao iFileDao;

	@Override
	public void uploadCsv(int rowCount, MultipartFile gpsFile, MultipartFile noiseFile, MultipartFile rpmFile) {
		try {
			List<String[]> gpsRecords = new ArrayList<>();
	    	List<String[]> noiseRecords = new ArrayList<>();
	    	List<String[]> rpmRecords = new ArrayList<>();
	        if (!gpsFile.isEmpty()) {
	            InputStreamReader reader = new InputStreamReader(gpsFile.getInputStream(), "EUC-KR");
	            CSVReader csvReader = new CSVReader(reader);
	            gpsRecords = csvReader.readAll();
	          
	            csvReader.close();
	            reader.close();
	        }
	        if (!noiseFile.isEmpty()) {
	            InputStreamReader reader = new InputStreamReader(noiseFile.getInputStream(), "EUC-KR");
	            CSVReader csvReader = new CSVReader(reader);
	            noiseRecords = csvReader.readAll();
	            
	            csvReader.close();
	            reader.close();
	        }
	        if (!rpmFile.isEmpty()) {
	            InputStreamReader reader = new InputStreamReader(rpmFile.getInputStream(), "EUC-KR");
	            CSVReader csvReader = new CSVReader(reader);
	            rpmRecords = csvReader.readAll();
	            
	            csvReader.close();
	            reader.close();
	        }
	        for(int i = 1; i < rowCount; i++) {
	        	double noise = 0.0;
	        	int rpm = 0;
	        	
	        	if(i+5<rowCount) {
	        		for(int j=i; j<i+6; j++) {
	        			noise += Double.parseDouble(noiseRecords.get(j)[3]);
	        			rpm += Integer.parseInt(rpmRecords.get(j)[3]);
	        		}
	        		LocalData localData = new LocalData();
	        		int noiseAvg =(int) noise/6;
	        		int rpmAvg = rpm/6;
	        		localData.setCarNum(gpsRecords.get(i+3)[2]);
	        		localData.setDate(LocalDate.parse(gpsRecords.get(i+3)[0]));
	        		localData.setTime(gpsRecords.get(i+3)[1]);
	        		localData.setLon(Double.parseDouble(gpsRecords.get(i+3)[3]));
	        		localData.setLat(Double.parseDouble(gpsRecords.get(i+3)[4]));
	        		localData.setNoise(noiseAvg);
	        		localData.setRpm(rpmAvg);
	        		boolean is_done = false;
	        		
	        		if (noiseAvg >= 80 && rpmAvg >= 1500) {
	        			is_done = true;
	        		}
	        		localData.set_done(is_done);
	        		iFileDao.insertCsvData(localData);
	        		i = i+5;
	        	} else if(i+5==rowCount){
	        		noise = Double.parseDouble(noiseRecords.get(i)[3]);
	        		rpm = Integer.parseInt(rpmRecords.get(i)[3]);
	        		LocalData localData = new LocalData();
	        		localData.setCarNum(gpsRecords.get(i)[2]);
	        		localData.setDate(LocalDate.parse(gpsRecords.get(i)[0]));
	        		localData.setTime(gpsRecords.get(i+3)[1]);
	        		localData.setLon(Double.parseDouble(gpsRecords.get(i)[3]));
	        		localData.setLat(Double.parseDouble(gpsRecords.get(i)[4]));
	        		localData.setNoise((int)noise);
	        		localData.setRpm(rpm);
	        		boolean is_done = false;
	        		
	        		if (noise >= 80 && rpm >= 1500) {
	        			is_done = true;
	        		}
	        		localData.set_done(is_done);
	        		iFileDao.insertCsvData(localData);
	        		break;
	        	} else if(i+5>rowCount) {
	        		int row = rowCount-i;
	        		for(int j=i; j<rowCount; j++) {
	        			log.info("noise1 : " + noiseRecords.get(j)[3]);
	        			log.info("noise2 : " + Double.parseDouble(noiseRecords.get(j)[3]));
	        			noise += Double.parseDouble(noiseRecords.get(j)[3]);
	        			rpm += Integer.parseInt(rpmRecords.get(j)[3]);
	        		}
	        		LocalData localData = new LocalData();
	        		int noiseAvg = ((int)noise)/row;
	        		int rpmAvg = rpm/row;
	        		localData.setCarNum(gpsRecords.get(rowCount-1)[2]);
	        		localData.setDate(LocalDate.parse(gpsRecords.get(rowCount-1)[0]));
	        		localData.setTime(gpsRecords.get(rowCount-1)[1]);
	        		localData.setLon(Double.parseDouble(gpsRecords.get(rowCount-1)[3]));
	        		localData.setLat(Double.parseDouble(gpsRecords.get(rowCount-1)[4]));
	        		localData.setNoise(noiseAvg);
	        		localData.setRpm(rpmAvg);
	        		boolean is_done = false;
	        		
	        		if (noiseAvg >= 80 && rpmAvg >= 1500) {
	        			is_done = true;
	        		}
	        		localData.set_done(is_done);
	        		iFileDao.insertCsvData(localData);
	        		break;
	        	} 
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
