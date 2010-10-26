package org.juqkai.jkjson.demo;

import java.awt.Image;

import org.juqkai.jkjson.annotation.Ignore;
import org.juqkai.util.string.Strings;

public class Demo{
	String name;
	int id;
	Demo2 demo2;
	int[] ip ;
	
	@Ignore
	Image img;
	public Demo(int id, String name){
		this.id = id;
		this.name = name;
		demo2 = new Demo2(id, name);
		ip = new int[]{8,8,8,8};
	}
	public String toJson() {
		return Strings.format("{id:\"{}\",name:\"{}\",demo2:{},ip:{}}", 
				id, name, demo2.toJson(),"[\"8\",\"8\",\"8\",\"8\"]");
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
	public Demo2 getDemo2() {
		return demo2;
	}
	public void setDemo2(Demo2 demo2) {
		this.demo2 = demo2;
	}
	
	public int[] getIp() {
		return ip;
	}
	public void setIp(int[] ip) {
		this.ip = ip;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
}
