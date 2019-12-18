package com.lovelive.common.uitls;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * @param file     文件的路径[/aaa/bbb/ccc/123.txt]
     * @param fileName 下载后的文件名称[test], 自动根据文件添加后缀
     * @param suffix   手动设置文件后缀
     * @param request
     * @param response
     * @Description 下载文件
     * @author dhe
     */
    public static void downloadFile(String file, String fileName, String suffix, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (OutputStream ops = response.getOutputStream();
             FileInputStream fis = new FileInputStream(file)) {

            if (suffix == null || suffix.isEmpty()) {
                if (file.lastIndexOf(".") > 0) {
                    suffix = file.substring(file.lastIndexOf("."));// 获取文件的后缀
                }
            }

            //下载文件的文件名
            String tempFileName;
            if (suffix == null || fileName.endsWith(suffix)) {
                tempFileName = fileName;
            } else {
                tempFileName = fileName + suffix;
            }

            // 进行转码，使其支持中文文件名
            String agent = String.valueOf(request.getHeader("USER-AGENT"));
            if (agent != null && agent.toLowerCase().contains("firefox")) {
                tempFileName = new String(tempFileName.getBytes("UTF-8"), "iso-8859-1");
            } else {
                tempFileName = java.net.URLEncoder.encode(tempFileName, "UTF-8");
            }

            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + tempFileName);
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer, 0, buffer.length)) != -1) {
                ops.write(buffer, 0, bytesRead);
            }
            ops.flush();
        }
    }

    /**
     * @param fileName 下载的文件名称
     * @param filePath 文件的路径[/aaa/bbb/ccc/]
     * @Description 下载文件
     * @author dhe
     */
    public static ResponseEntity<byte[]> downloadFile(String fileName, String filePath) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(org.apache.commons.io.FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
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
    public static boolean deleteDirectorys(File file) throws FileNotFoundException {
        if (file == null) {
            throw new FileNotFoundException();
        }
        if (file.isFile()) {// 表示该文件不是文件夹
            return file.delete();
        } else {
            // 首先得到当前的路径
            String[] childFilePaths = file.list();
            if (childFilePaths != null) {
                for (String childFilePath : childFilePaths) {
                    File childFile = new File(file.getAbsolutePath() + File.separator + childFilePath);
                    deleteDirectorys(childFile);
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

    /**
     * 一行一行的读取出文本
     */
    public static List<String> getTxt(File file) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.add(s.trim());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 递归获取某路径下的所有文件，文件夹，并输出
     */
    public static void getFiles(String path, List<String> filePathList) {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    //System.out.println("目录：" + files[i].getPath());
                    getFiles(files[i].getPath(), filePathList);
                } else {
                    //System.out.println("文件：" + files[i].getPath());
                    filePathList.add(files[i].getPath());
                }

            }
        } else {
            //System.out.println("文件：" + file.getPath());
            filePathList.add(file.getPath());
        }
    }

}
