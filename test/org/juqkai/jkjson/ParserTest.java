package org.juqkai.jkjson;

import java.io.Reader;

import org.junit.Test;
import static org.junit.Assert.*;
import org.juqkai.jkjson.demo.Demo3;
import org.juqkai.jkjson.lang.stream.StringReader;

/**
 * 解析测试
 * @author juqkai(juqkai@gmail.com) 2010-10-26
 */
public class ParserTest {
	@Test
	public void simpleTest(){
		String json = "{name:\"zk\"}";
		Reader reader = new StringReader(json);
		Demo3 d3 = Json.fromJson(Demo3.class, reader);
		assertNotNull(d3);
		assertEquals("zk", d3.getName());
	}
}
