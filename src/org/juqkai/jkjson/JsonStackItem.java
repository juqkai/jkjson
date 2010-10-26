package org.juqkai.jkjson;

/**
 * json栈
 * @author juqkai(juqkai@gmail.com) 2010-10-26
 */
public class JsonStackItem {
	//起点标示
	private char mark;
	private char end;
	private StringBuilder name;
	private Object value;
	private boolean startValue = false;
	
	public JsonStackItem(char mark) {
		this.mark = mark;
		switch(mark){
		case '{':
			end = '}';
			break;
		case '\"':
			end = '\"';
			break;
		case '[':
			end = ']';
			break;
		}
	}
	
	public boolean append(char c){
		if(c == ':'){
			return false;
		}
		if(c == end){
			return false;
		}
		
		name.append(c);
		return true;
	}
	public boolean startValue() {
		return startValue;
	}
	
	public char getMark() {
		return mark;
	}
	public char getEnd() {
		return end;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Object getValue(){
		return value;
	}
}
