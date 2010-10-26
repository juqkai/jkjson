package org.juqkai.jkjson;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.juqkai.jkjson.annotation.Ignore;
import org.juqkai.jkjson.annotation.ToJson;
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
	 * 项与项之间的分隔符
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-10-26
	 */
	private String itemSeparator(){
		return ",";
	}
	
	/**
	 * MAP转json
	 * @param map
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 * @throws IOException 
	 */
	private void map2Json(Map<?,?> map) throws IOException {
		before();
		String temp = "";
		for(Entry<?, ?> entry : map.entrySet()){
			writer.append(temp);
			writeItem(entry.getKey().toString(), entry.getValue());
			temp = itemSeparator();
		}
		after();
	}

	/**
	 * 数组转json
	 * @param array
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 */
	private void array2Json(Object array) throws IOException {
		writer.append('[');
		String temp = "";
		for(int i = 0; i < Array.getLength(array); i ++){
			writer.append(temp);
			rend(Array.get(array, i));
			temp = itemSeparator();
		}
		writer.append(']');
	}
	/**
	 * 一般对象转json
	 * @param obj
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 * @throws IOException 
	 */
	private void pojo2Json(Object obj) throws IOException {
		if(run2JsonMethod(obj)){
			return;
		}
		
		before();
		Mirror<?> me = Mirror.me(obj.getClass());
		String temp = "" ;
		for(Field fi : me.getFields()){
			try{
				if(fi.getAnnotation(Ignore.class) != null){
					continue;
				}
				writer.append(temp);
				Object value = me.getValue(obj, fi.getName());
				
				writeItem(fi.getName(), value);
				temp = itemSeparator();
			} catch (Exception e){
				//如果抛出异常,说明不能获取该属性的值,那么直接忽略该属性
			}
		}
		after();
	}
	/**
	 * 执行对象身上自带的toJson方法
	 * @param obj
	 * @return 执行成功返回true
	 * @author juqkai(juqkai@gmail.com) 2010-10-26
	 */
	private boolean run2JsonMethod(Object obj){
		Mirror<?> me = Mirror.me(obj.getClass());
		for(Method meth : me.getMethods()){
			if(meth.getAnnotation(ToJson.class) != null){
				try {
					meth.invoke(obj);
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
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
