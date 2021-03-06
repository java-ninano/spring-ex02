package org.zerock.service;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTxServiceTests {

	@Setter(onMethod_= @Autowired)
	private SampleTxService service;
	
	@Test
	public void testLong() {
		String str = "01234567890012345678900123456789001234567890012345678900123456789001234567890";
		//String str ="0123456789";
		service.addData(str);
	}

}
