package org.juqkai.jkjson.demo;

import java.util.ArrayList;
import java.util.List;

import org.juqkai.util.string.Strings;

public class Demo2 {
	int id;
	String name;
	List<String> books;
	public Demo2(int id, String name){
		this.id = id;
		this.name = name;
		books = new ArrayList<String>();
		books.add("one");
		books.add("two");
		books.add("three");
		
	}
	public String toJson() {
		return Strings.format("{id:\"{}\",books:{},name:\"{}\"}", id,"[\"one\",\"two\",\"three\"]", name);
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
	
}
