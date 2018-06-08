package com.troyboot.java;

import com.troyboot.java.system.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TroybootApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
		System.out.println("###########");
		userDao.findById(1);
		userDao.findById(1);
		System.out.println("###########");
	}

}
