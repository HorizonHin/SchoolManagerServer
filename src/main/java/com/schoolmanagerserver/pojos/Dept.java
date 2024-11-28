package com.schoolmanagerserver.pojos;

import lombok.Data;

@Data
public class Dept {
    private int deptId;
    private String deptName;
    public Dept(int deptId, String deptName) {}
    public  Dept(int deptId) {
        this.deptId = deptId;
    }
}
