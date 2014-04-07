package cn.sina.weibo.xycoding.checkj;

public class CheckJ {
	public static void main(String[] args) throws Exception {
		System.out.println("trying to find conflict jars and classes, please wait.......");
		System.out.println("suggest -XX:MaxPermSize more than 512M .......");
		JFilesUtil fu = new JFilesUtil(args);
		NameConflictCheck ccc = new NameConflictCheck(fu.getJarList());
		ccc.printConfict();
	}

}
