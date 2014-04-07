package cn.sina.weibo.xycoding.checkj;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class NameConflictCheck {
	
	private List<String> jarFiles =new ArrayList<String>();
	private Set<String> conflictJarFiles =new HashSet<String>();
	private Map<String,Set<String>> conflicts = new HashMap<String, Set<String>>();
	
	public void printConfict() throws Exception{
		findConfict();
		System.out.println("have found "+conflicts.size()+" conflict class...");
		for(Map.Entry<String,Set<String>>  entry : conflicts.entrySet()){
			String libs = "";
			for(String location : entry.getValue()){
				libs=libs.concat(location.substring(location.lastIndexOf("\\")+1)).concat("; ");
			}
			conflictJarFiles.add(libs);
		}
		System.out.println("have found "+conflictJarFiles.size()+" conflict jars...");
		System.out.println("\nDetail as Follow:");
		int i = 0;
		for(String libs : conflictJarFiles){
			System.out.print("jar conflict NO."+(++i)+": ");
			System.out.println(libs);
		}
		int j = 0;
		for(Map.Entry<String,Set<String>>  entry : conflicts.entrySet()){
			String clazz = entry.getKey();
			System.out.print("name conflict NO."+(++j)+": ");
			System.out.println(clazz);
			for(String location : entry.getValue()){
				System.out.println("\t"+location);
			}
		}
	}
	
	public Set<String> getConflictJarFiles() {
		return conflictJarFiles;
	}

	public void setConflictJarFiles(Set<String> conflictJarFiles) {
		this.conflictJarFiles = conflictJarFiles;
	}

	public NameConflictCheck(List<String> jarFiles){
		this.jarFiles = jarFiles;
	}
	
	@SuppressWarnings("unused")
	private NameConflictCheck(){
		
	}
	
	public Map<String, Set<String>> getConflicts() {
		return conflicts;
	}



	public void findConfict() throws Exception {
		List<ClassMeta> cnames = new ArrayList<ClassMeta>();
		for(String jar : jarFiles){
			cnames.addAll(getClassFromJar(jar));
		}
		for(int x = 0;x<cnames.size();x++){
			ClassMeta cm =cnames.get(x);
			String className = cm.getClassName();
			for(int y = x; y<cnames.size();y++){
				ClassMeta ccm =cnames.get(y);
				if(!cm.getLocation().equals(ccm.getLocation()) && className.equals(ccm.getClassName())){
					ClassCompare cc = new ClassCompare(cm, ccm);
					if(cc.compare()){
						continue;
					}
					
					Set<String> set = conflicts.get(className);
					if(set!=null && set.size()>0){
						set.add(ccm.getLocation());
					}else{
						HashSet<String> lset = new HashSet<String>();
						lset.add(cm.getLocation());
						lset.add(ccm.getLocation());
						conflicts.put(className, lset);
					}
				}
			}

		}
	}
	
	private List<ClassMeta> getClassFromJar(String jarFile) throws Exception {
		List<ClassMeta> cnames = new ArrayList<ClassMeta>();
		JarFile jar = new JarFile(jarFile);
		Enumeration<JarEntry> entries = jar.entries();
		for (; entries.hasMoreElements();) {
			JarEntry entry = (JarEntry) entries.nextElement();
			if (entry.getName().indexOf("META-INF") < 0) {
				String entryName = entry.getName();
				if (entryName.contains(".class")) {
					String className = entryName.replace("/", ".").replace(
							".class", "");
//					System.out.println(className + "...in" + jarFile);
					ClassMeta cm = new ClassMeta(className);
					cm.setLocation(jarFile);
					cnames.add(cm);
				}
			}
		}
		return cnames;
	}

	@SuppressWarnings("unused")
	private boolean canbeLoad(String className, String jarFile) {
		try {
			URL url = new URL("file:" + jarFile);
			URLClassLoader cl = new URLClassLoader(new URL[] { url }, Thread
					.currentThread().getContextClassLoader().getParent());
			cl.loadClass(className);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
