package com.ruoyi.common.utils;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class OssUtils {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.secretAccessKey}")
    private String secretAccessKey;

    @Value("${aliyun.oss.endPoint}")
    private String endPoint;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public String uploadOneFile(MultipartFile file) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);
        //设置文件名
        String fileName = new DateTime().toString("yyyy/MM/dd")
                + UUID.randomUUID().toString().replace("-", "")
                + file.getOriginalFilename();

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, fileName, file.getInputStream());

            String url = "https://" + bucketName + "." + endPoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public List<String> uploadArrayFile(MultipartFile[] files) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);
        List<String> list = new ArrayList<>();

        try {
            //设置文件名
            for (MultipartFile file : files) {
                String fileName = new DateTime().toString("yyyy/MM/dd")
                        + UUID.randomUUID().toString().replace("-", "")
                        + file.getOriginalFilename();
                // 创建PutObject请求。
                ossClient.putObject(bucketName, fileName, file.getInputStream());

                String url = "http://" + bucketName + "." + endPoint + "/" + fileName;
//                System.out.println(url);
                list.add(url);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return list;

    }

    public boolean deleteFile(String fileUrl) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);
        /** oss删除文件是根据文件完成路径删除的，但文件完整路径中不能包含Bucket名称。
         * 比如文件路径为：http://edu-czf.oss-cn-guangzhou.aliyuncs.com/2022/08/abc.jpg",
         * 则完整路径就是：2022/08/abc.jpg
         */
        int begin = ("http://" + bucketName + "." + endPoint + "/").length(); //找到文件路径的开始下标
        String deleteUrl = fileUrl.substring(begin);

        try {
            // 删除文件请求
            ossClient.deleteObject(bucketName, deleteUrl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}

