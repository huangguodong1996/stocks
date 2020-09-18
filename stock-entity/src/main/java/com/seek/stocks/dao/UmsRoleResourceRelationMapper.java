package com.seek.stocks.dao;

import com.seek.stocks.model.UmsRoleResourceRelation;

public interface UmsRoleResourceRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsRoleResourceRelation record);

    int insertSelective(UmsRoleResourceRelation record);

    UmsRoleResourceRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsRoleResourceRelation record);

    int updateByPrimaryKey(UmsRoleResourceRelation record);
}