package com.ruoyi.web.controller.common;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.OssUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Api(tags = "OSS对象存储Controller")
@RequestMapping("/oss")
public class OssController {
    @Autowired
    OssUtils ossUtils;

    @PostMapping("upload")
    public AjaxResult uploadFile(MultipartFile file) {
        //返回上传oss的url
        String url = ossUtils.uploadOneFile(file);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("fileName", file.getOriginalFilename());
        ajax.put("url", url);
        return ajax;
    }

    @PostMapping("uploadArrayFile")
    public List<String> uploadArrayFile(MultipartFile[] files) {
        //返回上传oss的url
        return ossUtils.uploadArrayFile(files);
    }

    @PostMapping("deleteFile")
    public boolean deleteFile(@RequestBody String fileUrl) {
        //返回是否删除成功
        return ossUtils.deleteFile(fileUrl);
    }
}
