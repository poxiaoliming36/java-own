//package com.lq;
//
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.pdf.AcroFields;
//import com.lowagie.text.pdf.PdfReader;
//import com.lowagie.text.pdf.PdfStamper;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Description
// * @Date 2021/3/30 15:39
// * @Author lq
// */
//public class PdfA {
//    public static void main(String[] args) {
//        try {
//            createPDfA();
//        } catch (IOException e) {
//            System.out.println("文件找不到");
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            System.out.println("出错了");
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void createPDfA() throws IOException, DocumentException {
//        //填充创建pdf
//        PdfReader reader = null;
//        PdfStamper stamp = null;
//        try {
//            reader = new PdfReader("G:/module.pdf");
//            SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
//            String times = simp.format(new Date()).trim();
//            //创建生成报告名称
////            String root = ServletActionContext.getRequest().getRealPath("/upload") + File.separator;
//            String root = "G:\\pdf";
//            if (!new File(root).exists()) {
//                new File(root).mkdirs();
//            }
//            File deskFile = new File(root, times + ".pdf");
//            stamp = new PdfStamper(reader, new FileOutputStream(deskFile));
//            //取出报表模板中的所有字段
//            AcroFields form = stamp.getAcroFields();
//            // 填充数据
//            form.setField("name", "zhangsan");
//            form.setField("sex", "男");
//            form.setField("age", "15");
//
//            //报告生成日期
//            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//            String generationdate = dateformat.format(new Date());
//            form.setField("generationdate", generationdate);
//            stamp.setFormFlattening(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (stamp != null) {
//                stamp.close();
//            }
//            if (reader != null) {
//                reader.close();
//            }
//        }
//    }
//}
