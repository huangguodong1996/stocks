package com.seek.stocks.model;

public class UmsAdminPermissionRelation {
    private Long id;

    private Long admin_id;

    private Long permission_id;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}