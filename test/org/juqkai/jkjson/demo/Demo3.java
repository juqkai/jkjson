package org.juqkai.jkjson.demo;

import org.juqkai.jkjson.annotation.ToJson;

public class Demo3 {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ToJson
	public String toJson(){
		return "{id:" + id + "}";
	}
}
