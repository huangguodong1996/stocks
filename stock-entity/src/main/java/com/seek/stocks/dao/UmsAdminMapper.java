package com.seek.stocks.dao;

import com.seek.stocks.model.UmsAdmin;
import com.seek.stocks.model.UmsPermission;

import java.util.List;

public interface UmsAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin record);

    int insertSelective(UmsAdmin record);

    UmsAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdmin record);

    int updateByPrimaryKey(UmsAdmin record);

    UmsAdmin getAdminByUsername(String username);

    List<UmsPermission> getPermissionList(Long id);
}
