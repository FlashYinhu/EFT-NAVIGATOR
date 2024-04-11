package com.yinhu.web.controller;

import com.yinhu.web.pojo.Result;
import com.yinhu.web.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadFileController {

    @Autowired // 自动注入 阿里云OSS工具类对象
    private AliOSSUtils aliOSSUtils;

    // 本地存储文件的弊端 无法直接访问 磁盘满了 磁盘损坏
    // 现在已经不常用
    @PostMapping("/upload/a")
    public Result uploadFile(String username, Integer age, MultipartFile image) throws IOException {

        // 获取原始文件名 这种方法有弊端就是会覆盖同名文件
        String originalFilename = image.getOriginalFilename();

        // 构造唯一的文件名称
        int index = originalFilename.lastIndexOf(".");
        String extendName = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + extendName;

        // 将接收到的文件存储在服务器本地的磁盘目录上 C:\Users\Yinhu\Desktop\WebDev\data
        image.transferTo(new File("C:\\Users\\Yinhu\\Desktop\\WebDev\\data\\" + newFilename ));
        log.info("文件上传{} {} {}", username , age, image);
        return Result.success();
    }

    @PostMapping("/upload")
    public Result uploadOSS(MultipartFile image) throws IOException {
        log.info("文件上传 {}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件访问的路径为{}", url);
        return Result.success(url);
    }
}
