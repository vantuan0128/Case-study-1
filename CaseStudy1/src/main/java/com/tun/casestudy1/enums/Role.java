package com.tun.casestudy1.enums;

public enum Role {
    ADMIN,
    USER;

    public String getAuthority()  {
        return "ROLE_" + this.name();
    }
}
