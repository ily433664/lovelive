package com.lovelive.common.uitls;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * 二维码生成和读的工具类
 */
public class QRCodeUtils {

    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param os          文件输出流路径
     * @param content     二维码携带信息
     * @param qrCodeSize  二维码图片大小
     * @param imageFormat 二维码的格式
     * @throws WriterException
     * @throws IOException
     */
    public static boolean createQRCode(OutputStream os, String content, int qrCodeSize, String imageFormat)
            throws WriterException, IOException {

        int width = qrCodeSize + 200;
        int height = qrCodeSize + 200;
        // 设置二维码纠错级别map
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 矫错级别
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // 创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
        // 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        int matrixHeight = byteMatrix.getHeight();
        BufferedImage image = new BufferedImage(matrixWidth - 200, matrixHeight - 200, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixHeight);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixHeight; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i - 100, j - 100, 1, 1);
                }
            }
        }
        return ImageIO.write(image, imageFormat, os);
    }

    /**
     * 读二维码并输出携带的信息
     */
    public static String readQRCode(InputStream is) throws IOException {
        // 从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(is);
        // 将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null;
        try {
            result = reader.decode(bitmap);
        } catch (ReaderException e) {
            e.printStackTrace();
        }
        return result != null ? result.getText() : null;
    }

    /**
     * 测试代码
     *
     * @throws WriterException
     */
    /*public static void main(String[] args) throws IOException, WriterException {
        createQRCode(new FileOutputStream(new File("d:\\qrcode.jpg")), "WE1231238239128sASDASDSADSDWEWWREWRERWSDFDFSDSDF123123123123213123", 900, "JPEG");
        readQRCode(new FileInputStream(new File("d:\\qrcode.jpg")));
    }*/

}