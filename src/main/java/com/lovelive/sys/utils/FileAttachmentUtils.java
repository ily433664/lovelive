package com.lovelive.sys.utils;

import com.lovelive.common.uitls.FileUtils;
import com.lovelive.common.uitls.IdGenerator;
import com.lovelive.sys.entity.FileAttachment;
import com.lovelive.sys.service.IFileAttachmentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 附件 utils
 *
 * @author dHe
 */
@Component
public class FileAttachmentUtils {

    private static IFileAttachmentService fileAttachmentService;

    /**
     * @param fileAttachment 附件对象
     * @param multipartFile  上传的文件
     * @return
     * @throws IOException
     */
    public static FileAttachment createFileAttachment(FileAttachment fileAttachment, MultipartFile multipartFile) throws IOException {

        // 获取文件后缀
        String suffix = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        // 计算文件的md5
        String md5Hex = DigestUtils.md5Hex(multipartFile.getInputStream());
        FileAttachment temp = fileAttachmentService.getFileAttachmentByMd5Hex(md5Hex);
        String filePath;
        if (temp != null) {
            filePath = temp.getFilePath();
        } else {
            Calendar calendar = Calendar.getInstance();
            filePath = File.separator + calendar.get(Calendar.YEAR)
                    + File.separator + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH)
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()) + "" + IdGenerator.uuid() + "." + suffix;

            // 保存新的附件到服务器
            File file = new File(filePath);
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        }

        // 如果之前没有上传过附件，则新建
        if (fileAttachment == null) {
            fileAttachment = new FileAttachment();
        }

        fileAttachment.setFilePath(filePath);
        fileAttachment.setSuffix(suffix);
        fileAttachment.setFileSize(multipartFile.getSize());
        fileAttachment.setMd5Hex(md5Hex);

        return fileAttachment;
    }

    @Autowired
    public void setFileAttachmentService(IFileAttachmentService fileAttachmentService) {
        FileAttachmentUtils.fileAttachmentService = fileAttachmentService;
    }
}
