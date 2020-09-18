package com.seek.stocks.service;

import com.seek.stocks.model.Member;

import java.util.List;

public interface IMemberService {

    public List<Member> queryAllMember();

    public Member selectById(Integer id);

    public Integer addMember(Member member);

    public Integer updateMember(Member member);

    public Integer deleteMember(Integer id);

    Member selectByName(String username);
}
