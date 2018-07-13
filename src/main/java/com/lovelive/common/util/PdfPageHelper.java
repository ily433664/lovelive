package com.lovelive.common.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

/**
 * @ClassName PdfPageHelper
 * @Description pdf添加页码
 * document的open之前,
 * writer.setPageEvent(new PdfPageHelper());//添加页码
 */
public class PdfPageHelper extends PdfPageEventHelper {

    private PdfTemplate tpl = null;
    private BaseFont bf = null;

    public void onOpenDocument(PdfWriter writer, Document document) {
        try {
            tpl = writer.getDirectContent().createTemplate(100, 100);
            tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();// 得到层
        cb.saveState();
        // 开始
        cb.beginText();

        cb.setFontAndSize(bf, 10);

        /*
         * // Header float x = document.top(-20);// 位置 // 左
         * cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "H-Left",
         * document.left(), x, 0); // 中
         * cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "第" +
         * writer.getPageNumber() + "页", (document.right() + document.left()) /
         * 2, x, 0); // 右 cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,
         * "H-Right", document.right(), x, 0);
         */
        // Footer
        float y = document.bottom(-20);
        /*
         * // 左 cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "F-Left",
         * document.left(), y, 0);
         */
        // 中
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "第" + writer.getPageNumber() + "页   ",
                (document.right() + document.left()) / 2, y, 0);
        /*
         * // 右 cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "F-Right",
         * document.right(), y, 0);
         */
        cb.endText();

        cb.addTemplate(tpl, (document.right() + document.left()) / 2 + 10, y);
        // cb.saveState();
        // cb.stroke();

        cb.restoreState();

    }

    @SuppressWarnings("unused")
    public void onCloseDocument(PdfWriter writer, Document document) {
        // 关闭document的时候获取总页数，并把总页数按模版写道之前预留的位置
        tpl.beginText();
        tpl.setFontAndSize(bf, 10);
        tpl.setTextMatrix(0, 0);
        float y = document.bottom(-25);
        // tpl.showTextAligned(PdfContentByte.ALIGN_CENTER, "第" +
        // writer.getPageNumber() + "页", (document.right() + document.left()) /
        // 2, y, 0);

        tpl.showText("/ 共" + String.valueOf(writer.getPageNumber() - 1) + "页");
        tpl.endText();

        // tpl.closePath();//sanityCheck();

    }
}
