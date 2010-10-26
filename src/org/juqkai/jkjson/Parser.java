package org.juqkai.jkjson;

import java.io.IOException;
import java.io.Reader;

import org.juqkai.jkjson.exception.JsonException;

/**
 * 解析器
 * @author juqkai(juqkai@gmail.com) 2010-10-26
 */
public class Parser {
	Reader reader;
	public Parser(Reader reader) {
		this.reader = reader;
	}
	
	public <T> T parse(Class<T> type) {
		parse2Stack();
		return null;
	}

	private char next(){
		try {
			return (char)reader.read();
		} catch (IOException e) {
			throw new JsonException("JSON 读取失败!");
		}
	}
	/**
	 * 解析到栈
	 * 
	 * @author juqkai(juqkai@gmail.com) 2010-10-26
	 * @return 
	 */
	private JsonStackItem parse2Stack() {
		JsonStackItem jsi = new JsonStackItem(next());
		
		while(jsi.append(next())){
			if(jsi.startValue()){
				jsi.setValue(parse2Stack());
			}
		}
		return jsi;
	}
	
}
