package cn.sina.weibo.xycoding.checkj;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassMeta {
	private String className;
	private String location;
	private Integer classModifier;
	private Method[] methods;
	@SuppressWarnings("rawtypes")
	private Constructor[] constructors;
	private Field[] fields;
	
	
	public ClassMeta(String className) {
		this.className = className;
	}
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getClassModifier() {
		return classModifier;
	}
	public void setClassModifier(Integer classModifier) {
		this.classModifier = classModifier;
	}
	public Method[] getMethods() {
		return methods;
	}
	public void setMethods(Method[] methods) {
		this.methods = methods;
	}
	@SuppressWarnings("rawtypes")
	public Constructor[] getConstructors() {
		return constructors;
	}
	@SuppressWarnings("rawtypes")
	public void setConstructors(Constructor[] constructors) {
		this.constructors = constructors;
	}
	public Field[] getFields() {
		return fields;
	}
	public void setFields(Field[] fields) {
		this.fields = fields;
	}
	
//	@Override
//	public boolean equals(Object o){
//		return false;
//	}
//	
//	@Override
//	public int hashCode(){
//		return 0;
//	}
}
