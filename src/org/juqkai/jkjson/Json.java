package org.juqkai.jkjson;

import java.io.IOException;
import java.io.Writer;

import org.juqkai.jkjson.exception.JsonException;
import org.juqkai.jkjson.lang.stream.StringWriter;

/**
 * JSON 转换工具
 * @author juqkai(juqkai@gmail.com) 2010-10-25
 */
public class Json {
	/**
	 * 转换成JSON字符串
	 * @param obj 待转换对象
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 */
	public static String toJson(Object obj) {
		StringBuilder sb = new StringBuilder();
		toJson(new StringWriter(sb), obj);
		return sb.toString();
	}
	/**
	 * 转换成JSON字符串
	 * @param writer 输入流,这个地方之所以使用输入流,原因在于希望它能在各种流中直接转换,
	 * 如文件流,字符串流,网络流,终端流等
	 * @param obj 待转换对象
	 * @author juqkai(juqkai@gmail.com) 2010-10-25
	 */
	public static void toJson(Writer writer, Object obj) {
		String str = "{\"id\":1,\"name\":\"juqkai\"}";
		try {
			writer.write(str);
		} catch (IOException e) {
			throw new JsonException("json 转换失败");
		}
	}
}
