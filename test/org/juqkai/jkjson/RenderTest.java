package org.juqkai.jkjson;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.juqkai.jkjson.lang.stream.StringWriter;

public class RenderTest {
	StringBuilder sb;
	Render rend;
	
	@Before
	public void init(){
		sb = new StringBuilder();
		rend = new Render(new StringWriter(sb)); 
	}
	
	@Test
	public void class2Json() throws IOException{
		rend.rend(RenderTest.class);
		assertEquals(sb.toString(), "\"org.juqkai.jkjson.RenderTest\"");
	}
}
