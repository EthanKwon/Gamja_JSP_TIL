package JUnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import JUnit.Calculator;

public class CalculatorTest {
	private Calculator calc = new Calculator();
	
	@Before
	public void beforeTest() {
		calc = new Calculator();
		System.out.println("BeforeTest()");
	}
	
	@After
	public void afterTest() {
		System.out.println("AfterTest()");		
	}
	
	@Test
	public void addTest() {
		assertEquals(30,calc.add(10,20));
		System.out.println("addTest()");
	}
	
	@Test
	public void subTest() {
		assertEquals(10,calc.sub(20,10));
		System.out.println("subTest()");
	}
	
	@Test
	public void mulTest() {
		assertEquals(200,calc.mul(20,10));
		System.out.println("mulTest()");
	}
	
	@Test
	public void divTest() {
		assertEquals(2,calc.div(20.0,10.0),0.00001);
		System.out.println("divTest()");
	}
}
