package com.mf;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sun.misc.BASE64Encoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JxcApplicationTests {

	@Test
	public void contextLoads() {
		String str="测试";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			System.out.println(base64en.encode(md5.digest(str.getBytes("utf-8"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
