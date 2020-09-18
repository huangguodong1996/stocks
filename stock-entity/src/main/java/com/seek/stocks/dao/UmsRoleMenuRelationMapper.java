package com.seek.stocks.dao;

import com.seek.stocks.model.UmsRoleMenuRelation;

public interface UmsRoleMenuRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsRoleMenuRelation record);

    int insertSelective(UmsRoleMenuRelation record);

    UmsRoleMenuRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsRoleMenuRelation record);

    int updateByPrimaryKey(UmsRoleMenuRelation record);
}