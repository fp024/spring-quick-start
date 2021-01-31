package org.fp024.mybatistest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ExampleServiceTests {

	private ExampleService service = new ExampleService();
	
	@Test
	public void testReadOnce() throws Exception {
		assertEquals("Hello world!", service.getMessage());
	}

}
