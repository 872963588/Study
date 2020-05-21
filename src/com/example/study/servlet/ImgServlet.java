package com.example.study.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.example.study.dao.CourseDao;
import com.example.study.dao.UserDao;
import com.google.gson.Gson;

@WebServlet("/img")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImgServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		// 获得磁盘文件条月工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取文件需要上传到的路径
		String path = this.getServletContext().getRealPath("/images");
		File file = new File(path);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			// System.out.println(savePath + "目录不存在，需要创建");
			// 创建目录
			file.mkdir();
		}
		// 如果没以下两行设置的话上传太的文件会点用很多内存。
		// 设置置时在放的存储室，这个存储室。可以积最終存储文件的县录不同
//		/由由
//		.愿理它是先存到鷲財在储室。然后在真正馬到对应县录的硬盘上按理来说当上一一个文件时。其实
//		*格式的然后再将其真正写到对应目录的硬盘上
		factory.setRepository(new File(path));
		// 设置緩存的太小，当上传文件的容量超过该缓存財直接放到暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件.上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {

			// 可以上传多个文件
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
			for (FileItem item : list) {
				String name = item.getFieldName();
				if (item.isFormField()) {
					String value = item.getString();
					request.setAttribute(name, value);
				} else {
					String value = item.getName();
					int start = value.lastIndexOf("\\");
					String filename = value.substring(start + 1);
					// String filename = "123";
					request.setAttribute(name, filename);

					// 更新数据库
					int index1 = filename.lastIndexOf("_");
					int index2 = filename.lastIndexOf(".");
					String type = filename.substring(0, index1);

					int id = Integer.parseInt(filename.substring(index1 + 1, index2));
					// String imgname = value.substring(0, index);
					// int id = Integer.parseInt(imgname.substring(imgname.lastIndexOf("_")+1));

					if ("course".equals(type)) {
						CourseDao courseDao = new CourseDao();
						courseDao.updateCourseImg(id, filename);
					} else if ("user".equals(type)) {
						UserDao userDao = new UserDao();
						userDao.updateCourseImg(id, filename);
					} else {

					}

					OutputStream out = new FileOutputStream(new File(path, filename));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					// System.out.println("获取上传文件的总共的在量." + item.getSize());
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();

				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "true");
		print.print(g.toJson(map));
		print.close();
	}

}
