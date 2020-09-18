package com.seek.stocks.controller;


import com.alibaba.fastjson.JSONObject;
import com.seek.stocks.model.Member;
import com.seek.stocks.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @GetMapping("/queryAllMember")
    public String  queryAllMember(){
        List<Member> memberList = memberService.queryAllMember();
        String jsonStr = JSONObject.toJSONString(memberList);
        return jsonStr;
    }

    @GetMapping("/selectById")
    public String  selectById(@RequestParam(value = "id") Integer id){
        Member member = memberService.selectById(id);
        String jsonStr = JSONObject.toJSONString(member);
        return jsonStr;
    }

    @PostMapping("/addMember")
    public Integer addMember(Member member){
        return memberService.addMember(member);
    }

    @PostMapping("/updateMember")
    public Integer updateMember(Member member){
        return memberService.updateMember(member);
    }

    @GetMapping("/deleteMember")
    public Integer deleteMember(@RequestParam("id") Integer id){
        return memberService.deleteMember(id);
    }

}
