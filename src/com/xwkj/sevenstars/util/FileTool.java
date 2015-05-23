package com.xwkj.sevenstars.util;

import java.io.File;
import java.util.ArrayList;

public class FileTool
{
	
	public static String getFormat(String fileName)
	{
		int index = fileName.lastIndexOf(".");
	    String format = fileName.substring(index+1);
		return format;
	}
	
	public static ArrayList<File> getFiles(String path,String format)
	{
		File dir=new File(path);
		File[] files=dir.listFiles();
		ArrayList<File> fileList=new ArrayList<File>();
		for(int i=0;i<files.length;i++)
			if(format.equals(getFormat(files[i].getName())))
				fileList.add(files[i]);
		return fileList;
	}
	
	public static boolean modifyFileName(String path,String fileName,String newFileName)
	{
		File oldFile = new File(path+fileName);
		String rootPath = oldFile.getParent();
		File newFile = new File(rootPath + File.separator + newFileName);
		if (oldFile.renameTo(newFile)) 
			return true;
		else 
			return false;
	}
	
	public static int modifyFiles(String path,String format,String fileName,int start)
	{
		ArrayList<File> files=getFiles(path,format);
		for(int i=0;i<files.size();i++)
			modifyFileName(path,files.get(i).getName(),fileName+(start+i)+"."+format);
		return files.size();
	}
	
	public static boolean delAllFile(String path)
	{
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	     }
	
	public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        java.io.File myFilePath = new java.io.File(filePath);
	        myFilePath.delete(); //删除空文件夹
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
}
