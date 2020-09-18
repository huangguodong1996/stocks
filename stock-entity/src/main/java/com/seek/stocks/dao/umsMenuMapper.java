package com.seek.stocks.dao;

import com.seek.stocks.model.umsMenu;

public interface umsMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(umsMenu record);

    int insertSelective(umsMenu record);

    umsMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(umsMenu record);

    int updateByPrimaryKey(umsMenu record);
}