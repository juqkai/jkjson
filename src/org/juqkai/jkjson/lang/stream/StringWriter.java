package org.juqkai.jkjson.lang.stream;

import java.io.IOException;
import java.io.Writer;

/**
 * 字符串流
 * @author juqkai(juqkai@gmail.com) 2010-10-25
 */
public class StringWriter extends Writer {
	private StringBuilder sb;

	public StringWriter(StringBuilder sb) {
		this.sb = sb;
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		if ((off < 0) || (off > cbuf.length) || (len < 0)
				|| ((off + len) > cbuf.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}
		sb.append(cbuf, off, len);
	}

}
