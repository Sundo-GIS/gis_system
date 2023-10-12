package com.gis.controller.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gis.dto.gis.LocalData;
import com.gis.service.file.IFileService;
import com.opencsv.exceptions.CsvException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FileController {

	private final IFileService iFileService;
	
	/**
	 * 파일 업로드(opencsv라이브러리 사용) 
	 * @author 임연서
	 */
    @PostMapping("/uploadCsv")
    public ResponseEntity<String> uploadCsv(@RequestParam("gpsfile") MultipartFile gpsFile,
                            @RequestParam("noisefile") MultipartFile noiseFile,
                            @RequestParam("rpmfile") MultipartFile rpmFile) throws IOException, CsvException {
    	try(BufferedReader br = new BufferedReader(new InputStreamReader(gpsFile.getInputStream()))) {
    		if (gpsFile.isEmpty() || noiseFile.isEmpty() || rpmFile.isEmpty()) {
                return ResponseEntity.badRequest().body("누락된 파일이 있습니다.");
            }
    		// 파일 행 개수 구하기
    		int rowCount=0;
        	String line;
        	while((line = br.readLine()) != null) {
        		rowCount++;
        	}
        	iFileService.uploadCsv(rowCount, gpsFile, noiseFile, rpmFile);
        	
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
        } catch(Exception e) { 
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("잘못된 요청입니다: " + e.getMessage());
        } 
    	
    }
    
	/**
	 * 파일 다운로드(opencsv라이브러리 사용) : coord 테이블에서 데이터 조회하여 파일로 저장 
	 * @author 임연서
	 */
    @GetMapping("/downloadCsv")
    public void downloadCsv(@RequestParam("date") String date,
                            @RequestParam("carNum") String carNum,
                            HttpServletResponse response) throws IOException {

    	// 데이터베이스에서 데이터를 가져오기
        List<LocalData> dataList = iFileService.selectLocalData(date, carNum);

        // HTTP 응답 헤더 및 파일 이름 설정
        response.setContentType("text/csv; charset=UTF-8"); // UTF-8 설정
        response.setHeader("Content-Disposition", "attachment; filename=data.csv"); // 파일 이름 설정

        // CSV 파일을 생성 후 출력
        try (PrintWriter writer 
        		= new PrintWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
        	writer.write("\uFEFF"); // 해당 문자가 있으면 BOM형식임을 인식
        	iFileService.createCsvFile(dataList, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
