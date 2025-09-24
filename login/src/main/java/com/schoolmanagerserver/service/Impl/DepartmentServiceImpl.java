package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.pojos.Department;
import com.schoolmanagerserver.pojos.DepartmentQueryVo;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public Result<List<Department>> findDepartmentList(DepartmentQueryVo departmentQueryVo) {
        return null;
    }

    @Override
    public Result<List<Department>> findParentDepartment() {
        return null;
    }

    @Override
    public Result<Boolean> hasChildrenOfDepartment(Long departmentId) {
        return null;
    }

    @Override
    public Result<Boolean> hasUserOfDepartment(Long departmentId) {
        return null;
    }
}
