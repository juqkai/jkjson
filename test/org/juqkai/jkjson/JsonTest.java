package org.juqkai.jkjson;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonTest {
	@Test
	public void toJson(){
		Demo demo = new Demo(1, "juqkai");
		assertEquals(Json.toJson(demo), "{\"id\":1,\"name\":\"juqkai\"}");
	}
}
class Demo{
	private String name;
	private int id;
	public Demo(int id, String name){
		this.id = id;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
