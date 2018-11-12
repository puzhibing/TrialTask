package com.puzhibing.trialtask;

import com.puzhibing.trialtask.util.ResourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrialTaskApplicationTests {

	@Test
	public void contextLoads() {
		try {
			ResourceUtil.getResourceUtil("parameter.properties").setValue("phone" , "15828353127");
			System.err.println(ResourceUtil.getResourceUtil("parameter.properties").getValue("phone"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
