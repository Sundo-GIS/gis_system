package com.gis.util.member;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil {
	
	/**
	 * 비밀번호 암호화 : SHA-256 + salt 
	 * @author 임연서
	 */
    public static String hashPassword(String password, String salt) {
        try {
            // 비밀번호와 솔트를 결합
            String passwordWithSalt = password + salt;
            
        	// MessageDigest 객체 생성 : SHA-256 해시함수를 사용
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));

            // bytes를 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            
            // 해시된 비밀번호
            for (byte hashByte : encodedHash) {
                String hex = Integer.toHexString(0xff & hashByte); 
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
            // 알고리즘 이름이 잘못된 경우  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String generateRandomSalt() {
        // 16 바이트 길이의 솔트(배열) 생성
        byte[] saltBytes = new byte[16];

        // SecureRandom을 사용하여 무작위 바이트로 채움
        SecureRandom random = new SecureRandom();
        random.nextBytes(saltBytes);

        // 생성된 바이트 배열을 Base64 인코딩하여 문자열로 반환
        return Base64.getEncoder().encodeToString(saltBytes);
    }

}
