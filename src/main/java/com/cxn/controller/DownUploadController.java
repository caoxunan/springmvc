package com.cxn.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @program: springmvc
 * @description: 演示上传下载
 * @author: cxn
 * @create: 2018-04-18 16:27
 * @Version v1.0
 */
@Controller
public class DownUploadController {

    // 通过参数MultipartFile file来接收上传的文件，这个是SpringMVC中定义的类，会自动注入
    // localhost:8085/path1/show9/down-upload.do
    @RequestMapping(value = "/upload1")
    public ModelAndView upload1(@RequestParam("file") MultipartFile file) throws Exception {

        ModelAndView mv = new ModelAndView("hello");
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            System.out.println("originalFilename:" + originalFilename);
            System.out.println("contentType:" + contentType);

            // 将上传的文件保存到指定位置
            File destFile = new File("file/" + originalFilename);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            file.transferTo(destFile);
            mv.addObject("msg", "上传成功！");
        }
        return mv;
    }

    // 下载文件
    @RequestMapping("/download1")
    public void download1(HttpServletResponse response) throws Exception {
        String filePath = "file/SpringMVC.xmind";
        File file = new File(filePath);
        OutputStream out = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            //设置响应头和客户端保存文件名
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("下载完成！");
    }

    // 自定根据条件找到即将下载的文件
    @RequestMapping("download2")
    public ResponseEntity<byte[]> download2() throws Exception{

        String filePath = "file/SpringMVC.xmind";
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
