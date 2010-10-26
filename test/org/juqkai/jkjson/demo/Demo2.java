package org.juqkai.jkjson.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.juqkai.util.string.Strings;

public class Demo2 {
	int id;
	String name;
	List<String> books;
	Map<String, String> test;
	Demo3 de3;
	public Demo2(int id, String name){
		this.id = id;
		this.name = name;
		books = new ArrayList<String>();
		books.add("one");
		books.add("two");
		books.add("three");
		
		test = new HashMap<String, String>();
		test.put("1", "1");
		test.put("2", "2");
		
		de3 = new Demo3();
	}
	public String toJson() {
		return Strings.format("{id:\"{}\",de3:{},books:{},test:{},name:\"{}\"}", 
				id,de3.toJson(),"[\"one\",\"two\",\"three\"]", "{2:\"2\",1:\"1\"}", name);
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
	public List<String> getBooks() {
		return books;
	}
	public void setBooks(List<String> books) {
		this.books = books;
	}
	public Map<String, String> getTest() {
		return test;
	}
	public void setTest(Map<String, String> test) {
		this.test = test;
	}
	public Demo3 getDe3() {
		return de3;
	}
	public void setDe3(Demo3 de3) {
		this.de3 = de3;
	}
}
