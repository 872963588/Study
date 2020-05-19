package com.example.study.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

@WebServlet("/file")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获得磁盘文件条月工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取文件需要上传到的路径
		String path = this.getServletContext().getRealPath("/files");
		File file = new File(path);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			// System.out.println(savePath + "目录不存在，需要创建");
			// 创建目录
			file.mkdir();
		}
		// 如果没以下两行设置的话上传太的文件会点用很多内存。
		// 设置置时在放的存储室，这个存储室。可以积最終存储文件的县录不同
//				/由由
//				.愿理它是先存到鷲財在储室。然后在真正馬到对应县录的硬盘上按理来说当上一一个文件时。其实
//				*格式的然后再将其真正写到对应目录的硬盘上
		factory.setRepository(new File(path));
		// 设置緩存的太小，当上传文件的容量超过该缓存財直接放到暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件.上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {

			// 可以上传多个文件
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
			for (FileItem item : list) {
				// 获取表单的属性名主
				String name = item.getFieldName();
				// 如果获取的赤单信息是普通的文东值息
				if (item.isFormField()) {
					// 获取用户县体输入的字符患.名字起得挺好。因为表单提交过来的是字符串类型的
					String value = item.getString();
					request.setAttribute(name, value);
				} else {// 对传入的非简单的主符患进行处理山比如说二进制的图片..虫影这些
					// 以下三步，主要获取上传文件的名主
					// 获取路径名
					// String value = item.getName();
					// 索引到最后-个反斜杠
					// int start = value.lastIndexOf("\\");
					// 戴取上传文件的字符串名字，加1是去掉反斜杠...
					// String filename = value.substring(start + 1);
					String filename = "123";
					request.setAttribute(name, filename);
					// 真正写到磁盘上
					// 它抛出的异常用axcaptien攄捉
					// item.write( new File (path, filename) );//第三方提供的
					// 手动互的
					OutputStream out = new FileOutputStream(new File(path, filename));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("获取上传文件的总共的在量." + item.getSize());
					// in.read(buf) 每次读到的数据存放在bud数组虫
					while ((length = in.read(buf)) != -1) {
						// 在buf数组中取出数据互到(输出流)磁盘上
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// e.printStackTrace() ;

		}
	}

}
