package com.seek.stocks.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelleoController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value="/hellotest", method= RequestMethod.GET)
    @ResponseBody
    public String index() {

        String sql = "SELECT username FROM member WHERE memberId = ?";

        // 通过jdbcTemplate查询数据库
        String mobile = (String) jdbcTemplate.queryForObject(sql, new Object[]{1}, String.class);

        return "Hello " + mobile;
    }
}
