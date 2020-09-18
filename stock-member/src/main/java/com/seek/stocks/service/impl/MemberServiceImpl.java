package com.seek.stocks.service.impl;

import com.seek.stocks.dao.MemberMapper;
import com.seek.stocks.model.Member;
import com.seek.stocks.service.IMemberService;
import com.seek.stocks.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements IMemberService {

    Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> queryAllMember() {
        return memberMapper.queryAllMember();
    }

    @Override
    public Member selectById(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer addMember(Member member) {
        try {
            member.setPassword(SignUtil.encryMD5(member.getPassword().getBytes()));
        } catch (Exception e) {
            log.error("新增用户失败，密码加密错误：",e);
        }
        return memberMapper.insertSelective(member);
    }

    @Override
    public Integer updateMember(Member member) {
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public Integer deleteMember(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Member selectByName(String username) {
        return memberMapper.selectByName(username);
    }
}
