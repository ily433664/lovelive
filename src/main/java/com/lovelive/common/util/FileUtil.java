package com.lovelive.common.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {

    /**
     * @param file     文件的路径[/aaa/bbb/ccc/123.txt]
     * @param fileName 下载后的文件名称[test], 自动根据文件添加后缀
     * @param suffix   手动设置文件后缀
     * @param request
     * @param response
     * @Description 下载文件
     * @author dhe
     */
    public static void download(String file, String fileName, String suffix, HttpServletRequest request, HttpServletResponse response) throws IOException {

        OutputStream ops = null;
        FileInputStream fis = null;

        try {
            if (suffix == null || suffix.isEmpty()) {
                if (file.lastIndexOf(".") > 0) {
                    suffix = file.substring(file.lastIndexOf("."));// 获取文件的后缀
                }
            }

            response.reset();
            response.setCharacterEncoding("UTF-8");

            String tempFileName;
            // 进行转码，使其支持中文文件名
            String agent = String.valueOf(request.getHeader("USER-AGENT"));
            if (agent != null && agent.toLowerCase().contains("firefox")) {
                tempFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            } else {
                tempFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            }

            response.setHeader("Content-Disposition", "attachment;filename=" + tempFileName + suffix);
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");

            byte[] buffer = new byte[1024];
            int bytesRead;

            ops = response.getOutputStream();
            fis = new FileInputStream(file);
            while ((bytesRead = fis.read(buffer, 0, buffer.length)) != -1) {
                ops.write(buffer, 0, bytesRead);
            }
            ops.flush();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ops != null) {
                    ops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param fromFile
     * @param toFile
     * @throws IOException
     * @Description 复制文件
     */
    public static void copyFile(File fromFile, File toFile) throws IOException {
        try (FileInputStream ins = new FileInputStream(fromFile);
             FileOutputStream out = new FileOutputStream(toFile)) {
            byte[] b = new byte[1024];
            int bytesRead;
            while ((bytesRead = ins.read(b)) != -1) {
                out.write(b, 0, bytesRead);
            }
        }
    }

    /**
     * @param file
     * @Description 删除文件
     */
    public static boolean deleteDirectory(File file) {
        if (file == null) {
            return false;
        }
        if (file.isFile()) {// 表示该文件不是文件夹
            return file.delete();
        } else {
            // 首先得到当前的路径
            String[] childFilePaths = file.list();
            if (childFilePaths != null) {
                for (String childFilePath : childFilePaths) {
                    File childFile = new File(file.getAbsolutePath() + File.separator + childFilePath);
                    deleteDirectory(childFile);
                }
                return file.delete();
            } else {
                return false;
            }
        }
    }

    /**
     * @param sourcePath 文件或文件夹路径
     * @param zipPath    生成的zip文件存在路径（包括文件名）
     * @Description 创建ZIP文件
     */
    public static void createZip(String sourcePath, String zipPath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipPath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            zos.setEncoding("gbk");// 此处修改字节码方式。
            writeZip(new File(sourcePath), "", zos);
        }
    }

    private static void writeZip(File file, String parentPath, ZipOutputStream zos) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {// 处理文件夹
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files != null) {
                    if (files.length != 0) {
                        for (File f : files) {
                            writeZip(f, parentPath, zos);
                        }
                    } else { // 空目录则创建当前目录
                        zos.putNextEntry(new ZipEntry(parentPath));
                    }
                }
            } else {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(content)) != -1) {
                        zos.write(content, 0, bytesRead);
                        zos.flush();
                    }
                }
            }
        }
    }
}
