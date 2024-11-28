package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.Department;
import com.schoolmanagerserver.pojos.DepartmentQueryVo;
import com.schoolmanagerserver.pojos.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    /**
     * 根据查询条件查找部门列表
     *
     * @param departmentQueryVo 查询条件
     * @return 部门列表
     */
    Result<List<Department>> findDepartmentList(DepartmentQueryVo departmentQueryVo);

    /**
     * 查找父部门列表
     *
     * @return 父部门列表
     */
    Result<List<Department>> findParentDepartment();

    /**
     * 判断部门是否有子部门
     *
     * @param departmentId 部门 ID
     * @return 是否有子部门
     */
    Result<Boolean> hasChildrenOfDepartment(Long departmentId);

    /**
     * 判断部门是否有用户
     *
     * @param departmentId 部门 ID
     * @return 是否有用户
     */
    Result<Boolean> hasUserOfDepartment(Long departmentId);
}