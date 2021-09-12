package com.example.vehicle_networking.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/3/21 10:43
 */
@Slf4j
public class FileUtil {
    public static boolean upload(MultipartFile file,String filePath,String fileName){
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        if (file.isEmpty()) {
            log.info("文件为空");
            return false;
        }

//        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return true;
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return false;
    }

    /**
     * 下载文件
     *
     * @param response
     * @param realPath
     * @return
     */
    public static boolean downloadFile(HttpServletResponse response, String realPath) {
        File file = new File(realPath);
        //如果文件不存在
        if (!file.exists()) {
            log.error("文件 " + realPath + " 不存在!");
            return true;
        }
        String simpleName = file.getName().substring(file.getName().lastIndexOf("/") + 1);
        String newFileName = new String(simpleName.getBytes(), StandardCharsets.UTF_8);
        //不设置这个的话，有可能会乱码
        response.setContentType("application/force-download");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(newFileName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        //文件输入流
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        //输出流
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer);
                i = bis.read(buffer);
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("文件下载中........");
        try {
            assert bis != null;
            bis.close();
            fis.close();
            assert os != null;
            os.close();
        } catch (IOException e) {
            log.error("error:{}", e.getMessage());
        }
        log.info("文件下载完成");
        return false;
    }

    /**
     * 生成随机的文件名称
     * @param file
     * @return
     */
    public static String generateFileName(MultipartFile file){
        //获得文件名
        String originalFilename = file.getOriginalFilename();
        //获得后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新生成文件名 不带后缀
        String f = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());

        //新生成文件名 带后缀
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(f).append(suffix);

        return stringBuilder.toString();
    }

    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
            log.info("删除成功");
            return true;
        }else {
            log.info("删除失败，文件不存在");
            return false;
        }
    }
}
