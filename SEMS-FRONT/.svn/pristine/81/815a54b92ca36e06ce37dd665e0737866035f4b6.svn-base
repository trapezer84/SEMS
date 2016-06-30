package com.ktds.sems;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/educationContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/memberContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/fileContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/pcContext.xml"
		, "file:src/main/webapp/WEB-INF/spring/teamContext.xml"
		, "/rootContext.xml"})
//@PropertySource("file:src/main/webapp/WEB-INF/spring/db.properties")
public class SemsTestCase {

	@Test
	public void semsTestCaseTest() {
		assertTrue(1==1);
	}
	public void testHelper(Testable Testable) {
		Testable.preparedTest();
	}
	
}
