package com.gis.controller.fileUpload;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gis.dao.file.IFileDao;
import com.gis.dto.gis.LocalData;
import com.gis.service.fileUpload.IFileService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FileUploadController {

	private final IFileService iFileService;
	
    @PostMapping("/uploadCsv")
    public ResponseEntity<String> uploadCsv(@RequestParam("gpsfile") MultipartFile gpsFile,
                            @RequestParam("noisefile") MultipartFile noiseFile,
                            @RequestParam("rpmfile") MultipartFile rpmFile) throws IOException, CsvException {
    	try(BufferedReader br = new BufferedReader(new InputStreamReader(gpsFile.getInputStream()))) {
    		// 파일 행 개수 구하기
    		int rowCount=0;
        	String line;
        	while((line = br.readLine()) != null) {
        		rowCount++;
        	}
        	// 파일 읽기 : opencsv 라이브러리 사용
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
            	iFileService.uploadCsv(gpsRecords.get(i), noiseRecords.get(i), rpmRecords.get(i));
            }
            return ResponseEntity.ok("데이터가 성공적으로 추가되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        } catch(ArrayIndexOutOfBoundsException e) { 
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일을 다시 확인해주세요");
        } catch(IndexOutOfBoundsException e) { 
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("누락된 파일이 있습니다");
        }
    	
    }

    
}
