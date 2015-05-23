package com.xwkj.sevenstars.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.Document;
import com.xwkj.sevenstars.bean.User;
import com.xwkj.sevenstars.dao.DocumentDao;
import com.xwkj.sevenstars.util.DateTool;
import com.xwkj.sevenstars.util.FileTool;

@WebServlet("/DocumentServlet")
public class DocumentServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public DocumentServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "getContent":
			getContent(request,response);
			break;
		case "download":
			download(request,response);
			break;
		case "downloadTest":
			downloadTest(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "upload":
			upload(request,response);
			break;
		case "insert":
			insert(request,response);
			break;
		default:
			break;
		}
	}

	private void getContent(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		int did=Integer.parseInt(request.getParameter("did"));
		Document document=DocumentDao.get(did);
		response.setCharacterEncoding("UTF-8");
		if(document!=null)
			response.getWriter().print(document.getContent());
		else
			response.getWriter().print(false);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		User user=SessionServlet.get(request);
		if(user==null)
			response.getWriter().print(-1);
		else
		{
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String uid=user.getUid();
			DateTime time=new DateTime(DateTool.getSystemTime());
			Document document=new Document(uid, title, content, time);
			int did=DocumentDao.insert(document);
			response.getWriter().print(did);
		}
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException 
	{
		request.setCharacterEncoding("UTF-8");
		int did=Integer.parseInt(request.getParameter("did"));
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String filepath=rootPath+"document\\"+did;
		File dir=new File(filepath);
		String fileName="";
		if(dir.exists())
		{
			FileTool.delAllFile(filepath);
			fileName=upload(request, filepath);
		}
		else
			if(dir.mkdir())
				fileName=upload(request, filepath);
		DocumentDao.writeName(fileName, did);
	}
	
	private String upload(HttpServletRequest request,String filepath)
	{
		String fileName=null;
		//为文件对象产生工厂对象。
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*4); //设置缓冲区的大小，此处为4kb
		factory.setRepository(new File(filepath)); //设置上传文件的目的地
		//产生servlet上传对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(4*1024*1024);  //设置上传文件的大小，此处为4M
		try 
		{
			List<FileItem> list=upload.parseRequest(request); //取得所有的上传文件信息
			Iterator<FileItem> it=list.iterator();
			while(it.hasNext())
			{
			    FileItem item=it.next();
			    if(item.isFormField()==false)
			    { 
				    fileName=item.getName();   //文件名
				    //取文件名  
				    fileName=fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length());               
				    if(!fileName.equals("")&&!(fileName==null))
				    {
				    	//如果fileName为null，即没有上传文件  
				    	File uploadedFile=new File(filepath,fileName);  
				        try 
				        {
				        	item.write(uploadedFile);
				        } 
				        catch (Exception e)
				        {
				        	e.printStackTrace();
				        }  
				    }            
			    }
			}
		} 
		catch (FileUploadException e) 
		{
			e.printStackTrace();
		}
		return fileName;
	}

	private void downloadTest(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		int did=Integer.parseInt(request.getParameter("did"));
		Document document=DocumentDao.get(did);
		PrintWriter out=response.getWriter();
		if(document==null)
			out.print("itemNotFound");
		else
		{
			if(document.getFilename()==null||document.getFilename().equals(""))
				out.print("fileNameNotFound");
			else
			{
				String rootPath=getServletConfig().getServletContext().getRealPath("/");
				String filePath=rootPath+"document\\"+did;
				File file=new File(filePath+"\\"+document.getFilename());
				if(!file.exists())
					out.print("fileNotFound");
				else
					out.print("fileExist");
			}
		}
	}

	private void download(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		int did=Integer.parseInt(request.getParameter("did"));
		Document document=DocumentDao.get(did);
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String filePath=rootPath+"document\\"+did;
		String fileName=document.getFilename();
		download(filePath, fileName, response);
	}
	
	private void download(String filePath,String fileName,HttpServletResponse response) throws UnsupportedEncodingException 
	{
		FileInputStream in=null;
		ServletOutputStream out=null;
		response.setContentType("application/x-msdownload; charset=UTF-8");
		response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("UTF-8"),"iso8859-1"));
		try
		{
			in=new FileInputStream(filePath+"\\"+fileName);
			out=response.getOutputStream();
			out.flush();
			int aRead=0;
			while((aRead=in.read())!=-1&in!=null)
				out.write(aRead);
			out.flush();
			in.close();
			out.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		int did=Integer.parseInt(request.getParameter("did"));
		Document document=DocumentDao.get(did);
		boolean success=false;
		if(document!=null)
		{
			String rootPath=getServletConfig().getServletContext().getRealPath("/");
			String filePath=rootPath+"document\\"+did;
			File dir=new File(filePath);
			if(dir.exists())
			{
				FileTool.delAllFile(filePath);
				if(dir.delete())
					if(DocumentDao.delete(did))
						success=true;				
			}
			else
				if(DocumentDao.delete(did))
					success=true;	
		}
		response.getWriter().print(success);
	}
}
