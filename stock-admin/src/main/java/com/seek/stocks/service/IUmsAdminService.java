package com.seek.stocks.service;

import com.seek.stocks.model.UmsAdmin;
import com.seek.stocks.model.UmsPermission;

import java.util.List;

public interface IUmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    List<UmsPermission> getPermissionList(Long id);

    String register(UmsAdmin umsAdmin);
}
