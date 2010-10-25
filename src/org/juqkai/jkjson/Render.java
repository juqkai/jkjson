package org.juqkai.jkjson;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.juqkai.lang.Mirror;

/**
 * 对象分裂者
 * @author juqkai(juqkai@gmail.com) 2010-10-25
 */
public class Render {
	private Writer writer;
	public Render(Writer writer) {
		this.writer = writer;
	}

	/**
	 * 分裂
	 * @param obj
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 * @throws IOException 
	 */
	public void rend(Object obj) throws IOException {
		if(obj == null){
			writer.write("null");
			return;
		}
		if(obj instanceof Class<?>){
			string2Json(((Class<?>) obj).getName());
			return;
		}
		if(obj instanceof Mirror<?>){
			string2Json(((Mirror<?>)obj).getType().getName());
			return;
		}
		
		Mirror<?> me = Mirror.me(obj.getClass());
		if(me.isEnum()){
			string2Json(((Enum<?>)obj).name());
			return;
		}
		if(me.isNumber() || me.isBoolean() || me.isChar()){
			string2Json(obj.toString());
			return;
		}
		if(me.isCharSequence()){
			string2Json(obj.toString());
			return;
		}
		if(obj.getClass().isArray()){
			array2Json(obj);
			return;
		}
		if(obj instanceof Collection<?>){
			array2Json(((Collection<?>)obj).toArray());
			return;
		}
		if(obj instanceof Map<?,?>){
			map2Json((Map<?,?>)obj);
			return;
		}
		pojo2Json(obj);
	}

	/**
	 * MAP转json
	 * @param obj
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 * @throws IOException 
	 */
	private void map2Json(Map<?,?> obj) throws IOException {
		before();
		boolean temp = false;
		for(Entry<?, ?> entry : obj.entrySet()){
			if(temp){
				writer.append(',');
			}
			writeItem(entry.getKey().toString(), entry.getValue());
			temp = true;
		}
		after();
	}

	/**
	 * 数组转json
	 * @param obj
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 */
	private void array2Json(Object obj) throws IOException {
		writer.append('[');
		boolean temp = false;
		for(int i = 0; i < Array.getLength(obj); i ++){
			if(temp){
				writer.append(',');
			}
			rend(Array.get(obj, i));
			temp = true;
		}
		writer.append(']');
	}

	/**
	 * 转换一个键值对
	 * @param name
	 * @param value
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 */
	private void writeItem(String name, Object value) throws IOException {
		writer.write(name);
		writer.append(":");
		rend(value);
	}

	/**
	 * 项的结尾
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 */
	private void after() throws IOException {
		writer.append('}');
	}

	/**
	 * 项的开头
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 */
	private void before() throws IOException {
		writer.append('{');
	}
	/**
	 * 一般对象转json
	 * @param obj
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 * @throws IOException 
	 */
	private void pojo2Json(Object obj) throws IOException {
		Mirror<?> me = Mirror.me(obj.getClass());
		Field[] fs = me.getFields();
		boolean temp = false;
		
		before();
		for(Field fi : fs){
			String name = fi.getName();
			Object value = me.getValue(obj, fi.getName());
			
			if(temp){
				writer.append(",");
			}
			writeItem(name, value);
			temp = true;
		}
		after();
	}
	
	/**
	 * 字符串转成json对象
	 * @param name
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 * @throws IOException 
	 */
	private void string2Json(String str) throws IOException {
		if(str == null){
			writer.append("null");
			return;
		}
		writer.append('\"');
		for (char c : str.toCharArray()) {
			switch (c) {
			case '"':
				writer.append("\\\"");
				break;
			case '\n':
				writer.append("\\n");
				break;
			case '\t':
				writer.append("\\t");
				break;
			case '\r':
				writer.append("\\r");
				break;
			case '\\':
				writer.append("\\\\");
				break;
			default:
				writer.append(c);
			}
		}
		writer.append('\"');
	}

}
