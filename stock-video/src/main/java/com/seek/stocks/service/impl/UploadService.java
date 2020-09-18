package com.seek.stocks.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.constant.Constants;
import com.seek.stocks.service.IUploadService;
import com.seek.stocks.utils.VideoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Service
public class UploadService implements IUploadService {

    @Value("${upload.file.path}")
    public String uploadPath;

    @Value("${delete.file.path}")
    public String deletePath;

    @Value("${upload.file.basePath}")
    public String basePath;

    private Logger log = LoggerFactory.getLogger(UploadService.class);

    @Override
    public JSONObject uploadVideo(HttpServletRequest request,MultipartFile file)  {
        JSONObject resultJson=new JSONObject();
        try{
            String uuid = UUID.randomUUID().toString();
            //文件原始名称
            String fileName = file.getOriginalFilename();
            //从最后一个.开始截取。截取fileName的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //文件新名称
            String newFileName = uuid+suffixName;
            String filePath = uploadPath+"video"+File.separator+uuid+File.separator+newFileName;
            File newFile = new File(filePath);
            //判断目标文件所在目录是否存在
            if(!newFile.getParentFile().exists()){
                //如果目标文件所在的目录不存在，则创建父目录
                newFile.getParentFile().mkdirs();
            }

            //将内存中的数据写入磁盘
            file.transferTo(newFile);
            //视频上传保存url
            String videoUrl = basePath + newFileName;
            //视频封面图处理
            String newImgName = uuid+".jpg";
            //视频上传保存url
            String imageUrl = basePath + newImgName;
            //图片最终位置目录
            String imgUrlSave = uploadPath+"video"+File.separator+uuid;
            //视频截取封面图
            String imgUrl= VideoUtil.videoImage(newFile,6, imgUrlSave ,uuid);
            log.info("获取视频："+newFile.getName()+"封面成功，存储路径："+imgUrl);
            //计算视频大小 M
            String videosize = VideoUtil.ReadVideoSize(newFile);
            //计算视频时长
            String videotimesize = VideoUtil.formatDuration(VideoUtil.videoDuration(newFile));
            resultJson.put("videoSize", videosize);
            resultJson.put("videoTimeSize", videotimesize);
            resultJson.put("videoUrl", videoUrl);
            resultJson.put("imgUrl", imageUrl);
            resultJson.put(Constants.RES_CODE, Constants.RES_CODE_SUCCESS);

        }catch (Exception e){
            log.error("上传图片失败："+e.getMessage(),e);
            resultJson.put(Constants.RES_CODE, Constants.RES_CODE_FAIL);
            resultJson.put(Constants.RES_SHOW, Constants.RES_SHOW_FAIL);
        }
        return resultJson;
    }

    @Override
    public JSONObject uploadImage(HttpServletRequest request,MultipartFile file)  {
        JSONObject resultJson=new JSONObject();
        try{
            String basePath = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort()+"/mimi/upload/images/";

            String uuid = UUID.randomUUID().toString();

            String fileName = file.getOriginalFilename();//文件原始名称
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//从最后一个.开始截取。截取fileName的后缀名
            String newFileName = uuid+suffixName; //文件新名称

            String filePath = uploadPath+newFileName;
            File newFile = new File(filePath);
            //判断目标文件所在目录是否存在
            if(!newFile.getParentFile().exists()){
                //如果目标文件所在的目录不存在，则创建父目录
                newFile.getParentFile().mkdirs();
            }

            //将内存中的数据写入磁盘
            file.transferTo(newFile);
            //图片上传保存url
            String imgUrl = basePath + newFileName;

            resultJson.put("imgUrl", imgUrl);
            resultJson.put(Constants.RES_CODE, Constants.RES_CODE_SUCCESS);

        }catch (Exception e){
            log.error("上传图片失败："+e.getMessage(),e);
            resultJson.put(Constants.RES_CODE, Constants.RES_CODE_FAIL);
            resultJson.put(Constants.RES_SHOW, Constants.RES_SHOW_FAIL);
        }
        return resultJson;
    }
}
