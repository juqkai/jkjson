package org.juqkai.jkjson.lang.stream;

import java.io.IOException;
import java.io.Reader;

public class StringReader extends Reader {
	String str;
	int next;
	int length;

	public StringReader(String str) {
		this.str = str;
		length = str.length();
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		if (len == 0) {
			return 0;
		}
		if ((off < 0) || (off > cbuf.length) || (len < 0)
				|| ((off + len) > cbuf.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		}
		
		if (next >= length)
			return -1;
		int n = Math.min(length - next, len);
		str.getChars(next, next + n, cbuf, off);
		next += n;
		return n;
	}

}
