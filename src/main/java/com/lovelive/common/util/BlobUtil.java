/**   
 * @Title Blob.java 
 * @Package blob 
 * @Description TODO(用一句话描述该文件做什么) 
 * @author dhe  
 * @date 2018年6月20日
 * @version V1.0   
 */
package com.lovelive.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @ClassName Blob
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author dhe
 * @date 2018年6月20日
 * @version V1.0
 */
public class BlobUtil {

	public static void blobTest(String file, HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 在这里可以进行权限验证等操作
		 */

		// 创建文件对象
		File f = new File("D:\\test\\test.mp4");
		// 获取文件名称
		String fileName = f.getName();
		// 导出文件
		String agent = request.getHeader("User-Agent").toUpperCase();
		InputStream fis = null;
		OutputStream os = null;
		try {
			fis = new BufferedInputStream(new FileInputStream(f.getPath()));
			byte[] buffer;
			buffer = new byte[fis.available()];
			fis.read(buffer);
			response.reset();
			// 由于火狐和其他浏览器显示名称的方式不相同，需要进行不同的编码处理
			if (agent.contains("FIREFOX")) {// 火狐浏览器
				response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
			} else {// 其他浏览器
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			}
			// 设置response编码
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Content-Length", "" + f.length());
			// 设置输出文件类型
			response.setContentType("video/mpeg4");
			// 获取response输出流
			os = response.getOutputStream();
			// 输出文件
			os.write(buffer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// 关闭流
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (os != null) {
						os.flush();
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						if (os != null) {
							os.close();
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}

}
