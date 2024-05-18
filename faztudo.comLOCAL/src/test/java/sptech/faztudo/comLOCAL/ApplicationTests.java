package sptech.faztudo.comLOCAL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;

import java.util.Base64;
import java.util.Date;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	void createTokenResetPassword() throws Exception {
//		KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
//		service.setServerSecret("SECRET123");
//		service.setServerInteger(16);
//		service.setSecureRandom(new SecureRandomFactoryBean().getObject());
//
//		Token token = service.allocateToken("wallace.mota@sptech.school");
//
//		System.out.println(token.getExtendedInformation());
//		System.out.println(new Date(token.getKeyCreationTime()));
//		System.out.println(token.getKey());
//
//		//Sun Feb 25 10:17:08 BRT 2024
//		//MTcwODg2NzAyODk3OTowYzhhNTgxN2RlYjM3OTAxZjg1YjhkZjYyY2M1YTBjYTc5ODQ5YjkzNjE5M2Q2OTExZTJhYzhlNjRkYjk5NTRmOndhbGxhY2UubW90YUBzcHRlY2guc2Nob29sOjRiOGIyYzg2YTNiNTYyNDU2MzM1NTUyODJhZDkxMDM4Mjk4ZjZjMDg3YTI1YjA3MzA0MzgxYmU1NzJiNzZkNDFkODFhZmZjMDMzMWEyYmFiODEwNDI1ZmE3MzFhYTkxZmFkYTkyMjI1YzIwYzNmYzlmZDA4MTNkMjdjYmYyMzA0
//	}
//
//	@Test
//	public void readToken() throws Exception{
//		KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
//		service.setServerSecret("SECRET123");
//		service.setServerInteger(16);
//		service.setSecureRandom(new SecureRandomFactoryBean().getObject());
//
//		String rawToken = "MTcwODg2NzAyODk3OTowYzhhNTgxN2RlYjM3OTAxZjg1YjhkZjYyY2M1YTBjYTc5ODQ5YjkzNjE5M2Q2OTExZTJhYzhlNjRkYjk5NTRmOndhbGxhY2UubW90YUBzcHRlY2guc2Nob29sOjRiOGIyYzg2YTNiNTYyNDU2MzM1NTUyODJhZDkxMDM4Mjk4ZjZjMDg3YTI1YjA3MzA0MzgxYmU1NzJiNzZkNDFkODFhZmZjMDMzMWEyYmFiODEwNDI1ZmE3MzFhYTkxZmFkYTkyMjI1YzIwYzNmYzlmZDA4MTNkMjdjYmYyMzA0";
//
//		Token token = service.verifyToken(rawToken);
//
//		System.out.println(token.getExtendedInformation());
//		System.out.println(new Date(token.getKeyCreationTime()));
//		System.out.println(token.getKey());
//
//	}
//
//	@Test
//	public void readPublicToken() throws Exception {
//		String rawToken = "MTcwODg2NzAyODk3OTowYzhhNTgxN2RlYjM3OTAxZjg1YjhkZjYyY2M1YTBjYTc5ODQ5YjkzNjE5M2Q2OTExZTJhYzhlNjRkYjk5NTRmOndhbGxhY2UubW90YUBzcHRlY2guc2Nob29sOjRiOGIyYzg2YTNiNTYyNDU2MzM1NTUyODJhZDkxMDM4Mjk4ZjZjMDg3YTI1YjA3MzA0MzgxYmU1NzJiNzZkNDFkODFhZmZjMDMzMWEyYmFiODEwNDI1ZmE3MzFhYTkxZmFkYTkyMjI1YzIwYzNmYzlmZDA4MTNkMjdjYmYyMzA0";
//
//		byte[] bytes = Base64.getDecoder().decode(rawToken);
//		String rawTokenDecoded = new String(bytes);
//
//		System.out.println(rawTokenDecoded);
//
//		String[] tokenParts = rawTokenDecoded.split(":");
//
//		Long timestamp = Long.parseLong(tokenParts[0]);
//		System.out.println(new Date(timestamp));
//
//		System.out.println(tokenParts[2]);
//
//	}

}
