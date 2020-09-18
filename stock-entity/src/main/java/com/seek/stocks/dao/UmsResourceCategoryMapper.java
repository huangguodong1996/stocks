package com.seek.stocks.dao;

import com.seek.stocks.model.UmsResourceCategory;

public interface UmsResourceCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsResourceCategory record);

    int insertSelective(UmsResourceCategory record);

    UmsResourceCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsResourceCategory record);

    int updateByPrimaryKey(UmsResourceCategory record);
}