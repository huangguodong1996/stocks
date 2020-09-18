package com.seek.stocks.dao;

import com.seek.stocks.model.Member;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer memberId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<Member> queryAllMember();

    Member selectByName(String username);

}
