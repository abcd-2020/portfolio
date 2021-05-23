package com.study.homepage;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomepageApplicationTests {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSqlSession() throws Exception {
		System.out.println(sqlsession);
	}
}
