package cn.sina.weibo.xycoding.checkj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JFilesUtil {
	public final static String JAR = "jar";
	public final static String CLASS = "class";
	private List<String> jarList = new ArrayList<String>();
	private List<String> classList = new ArrayList<String>();
	private Map<String, List<String>> map = new HashMap<String, List<String>>();

	public JFilesUtil(String dirName){
		findJ(dirName);
	}
	
	public JFilesUtil(String[] dirNames){
		for(String dirName : dirNames){
			findJ(dirName);	
		}
	}
	
	public void findJ(String dirName) {
		File dir = new File(dirName);
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			String fName = fs[i].getAbsolutePath();
			if (fName.endsWith(".jar")) {
				jarList.add(fName);
			} else if (fName.endsWith(".class")) {
				classList.add(fName);
			}
			if (fs[i].isDirectory()) {
				try {
					findJ(fs[i].getAbsolutePath());
				} catch (Exception e) {
					e.printStackTrace();
				}
//				return null;
//			} else {
//				return map;
			}

		}
	}

	public Map<String, List<String>> getMap() {
		map.put(JAR, jarList);
		map.put(CLASS, classList);
		return map;
	}
	
	public List<String> getJarList() {
		return jarList;
	}

	public List<String> getClassList() {
		return classList;
	}
}
