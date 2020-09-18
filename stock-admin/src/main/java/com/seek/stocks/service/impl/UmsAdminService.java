package com.seek.stocks.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.constant.Constants;
import com.seek.stocks.dao.UmsAdminMapper;
import com.seek.stocks.model.UmsAdmin;
import com.seek.stocks.model.UmsPermission;
import com.seek.stocks.service.IUmsAdminService;
import com.seek.stocks.utils.EncrypUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsAdminService implements IUmsAdminService {

    private Logger log = LoggerFactory.getLogger(UmsAdminService.class);

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return umsAdminMapper.getAdminByUsername(username);
    }

    @Override
    public List<UmsPermission> getPermissionList(Long id) {
        return umsAdminMapper.getPermissionList(id);
    }

    @Override
    public String register(UmsAdmin umsAdmin) {
        JSONObject resultObj = new JSONObject();
        try {
            /** 数据处理 **/
            //明文md5加密
            String password = umsAdmin.getPassword();
            umsAdmin.setPassword(EncrypUtil.md5Encryp(password));
            //设置创建时间
            umsAdmin.setCreate_time(new Date());
            Integer registerFlag = umsAdminMapper.insertSelective(umsAdmin);
            if(registerFlag > 0){
                resultObj.put(Constants.RES_CODE,Constants.RES_CODE_SUCCESS);
                resultObj.put(Constants.RES_SHOW,Constants.RES_SHOW_SUCCESS);
            }
        } catch (Exception e) {
            log.error("注册失败：{}",e.getMessage(),e);
            resultObj.put(Constants.RES_CODE,Constants.RES_CODE_FAIL);
            resultObj.put(Constants.RES_SHOW,Constants.RES_SHOW_FAIL);
        }
        return resultObj.toJSONString();
    }
}
