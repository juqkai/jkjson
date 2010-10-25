package org.juqkai.jkjson;

import org.junit.Test;
import org.juqkai.jkjson.demo.Demo;

import static org.junit.Assert.*;

public class JsonTest {
	@Test
	public void toJson(){
		Demo demo = new Demo(1, "juqkai");
		assertEquals(Json.toJson(demo), demo.toJson());
	}
}
