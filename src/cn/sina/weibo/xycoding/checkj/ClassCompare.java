package cn.sina.weibo.xycoding.checkj;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassCompare {
	private ClassMeta source;
	private ClassMeta target;
	
	public ClassCompare(ClassMeta source, ClassMeta target){
		this.source = source;
		this.target = target;
	}

	public ClassMeta getSource() {
		return source;
	}

	public void setSource(ClassMeta source) {
		this.source = source;
	}

	public ClassMeta getTarget() {
		return target;
	}

	public void setTarget(ClassMeta target) {
		this.target = target;
	}
	
	@SuppressWarnings("rawtypes")
	private ClassMeta initMeta(ClassMeta cm){
		try {
			URL url = new URL("file:" + cm.getLocation());
			URLClassLoader cl = new URLClassLoader(new URL[] { url }, Thread
					.currentThread().getContextClassLoader().getParent());
//			Class clazz = Class.forName(cm.getClassName(), true, cl);
			Class clazz = cl.loadClass(cm.getClassName());
			cm.setFields(clazz.getFields());
			cm.setMethods(clazz.getMethods());
			cm.setClassModifier(clazz.getModifiers());
			cm.setConstructors(clazz.getConstructors());
		} catch(Exception e){
//			System.out.println("--999-------");

		}catch (Error e) {
//			System.out.println("---------");
//			e.printStackTrace();
			return null;
		}
		return cm;
	}
	
	private boolean compareFields(ClassMeta source, ClassMeta target){
		if(null==source.getFields() && null==target.getFields()){
			return true;
		}else if(null==source.getFields() || null==target.getFields()){
			return false;
		}
		Field[] sFs = source.getFields();
		Field[] tFs = target.getFields();
		if(sFs.length != tFs.length){
			return false;
		}
		return true;
	}
	
	private boolean compareMethods(ClassMeta source, ClassMeta target){
		if(null==source.getMethods() && null==target.getMethods()){
			return true;
		}else if(null==source.getMethods() || null==target.getMethods()){
			return false;
		}
		Method[] sFs = source.getMethods();
		Method[] tFs = target.getMethods();
		if(sFs.length != tFs.length){
			return false;
		}
		return true;
	}
	
	private boolean compareClassModifier(ClassMeta source, ClassMeta target){
		return source.getClassModifier()==target.getClassModifier();
	}
	
	@SuppressWarnings("rawtypes")
	private boolean compareConstructors(ClassMeta source, ClassMeta target){
		if(null==source.getConstructors() && null==target.getConstructors()){
			return true;
		}else if(null==source.getConstructors() || null==target.getConstructors()){
			return false;
		}
		Constructor[] sFs = source.getConstructors();
		Constructor[] tFs = target.getConstructors();
		if(sFs.length != tFs.length){
			return false;
		}
		return true;
	}
	
	public boolean compare(){
		initMeta(source);
		initMeta(target);
		if(null==source && null==target){
			return true;
		}else if(null==source || null==target){
			return false;
		}
		if(compareClassModifier(source,target) && compareConstructors(source,target) 
				&& compareFields(source,target) && compareMethods(source,target)){
			return true;
		}
		return false;
	}
	
//	public static boolean compare(ClassMeta source, ClassMeta target){
//		
//	}
}
