package com.seek.stocks.dao;

import com.seek.stocks.model.UmsAdminPermissionRelation;

public interface UmsAdminPermissionRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminPermissionRelation record);

    int insertSelective(UmsAdminPermissionRelation record);

    UmsAdminPermissionRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminPermissionRelation record);

    int updateByPrimaryKey(UmsAdminPermissionRelation record);
}