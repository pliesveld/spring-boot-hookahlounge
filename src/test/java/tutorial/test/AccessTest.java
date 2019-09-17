package tutorial.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccessTest {

	@Test
	public void testName() throws Exception {
		Base s = new Sub();
		System.out.println(s.getX());
	}
}



class Base {
	private int x = 4;
	
	public int getX() {
		return this.x;
	}
}

class Sub extends Base {
	@Override
	public int getX() {
		return 5;
	}
}