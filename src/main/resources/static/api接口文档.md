# 人脸识别考勤系统接口文档-V1.0

## 0.用户权限系统

### 0. 登录

#### 0.1 基本信息

> 请求路径：/user/login
>role_permissions
> 请求方式：POST
>
> 接口描述：该接口用于用户登录

#### 0.2 请求参数

格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| username | string | 必须     | 用户名 |
| password | string | 必须     | 密码   |

请求参数样例：

{
  "username": "admin",
  "password": "123456"
}
```



#### 0.3 响应数据
参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | --------- |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |
| **success**      | `boolean`     | 必须     | 响应是否成功                     |
| **data**         | `object`      | 必须     | 返回的数据对象                   |
| \|- **Id**       | `Long`        |  必须    | id                              |
| \|- **avatar**   | `string`      | 非必须   | 用户头像地址                     |
| \|- **username** | `string`      | 必须     | 用户名                           |
| \|- **nickname** | `string`      | 非必须   | 用户昵称                         |
| \|- **roles**    | `Array<string>` | 必须   | 当前登录用户的角色列表           |
| \|- **permissions** | `Array<string>` | 必须 | 按钮级别权限列表                 |
| \|- **accessToken** | `string`   | 必须     | 用户的访问 `token`               |
| \|- **refreshToken** | `string`  | 必须     | 用于刷新 `accessToken` 的 `token` |
| \|- **expires**  | `Date`        | 必须     | `accessToken` 的过期时间          |

#### 示例返回值
```json
{  
  "code": 1,
  "success": true,
  "data": {
    "avatar": "https://example.com/avatar.png",
    "username": "johndoe",
    "nickname": "John",
    "roles": ["admin", "editor"],
    "permissions": ["read", "write", "delete"],
    "accessToken": "abc123xyz",
    "refreshToken": "refresh456def",
    "expires": "2024/12/01 23:59:59"
  }
}
```

### 0.2 注册

#### 0.2.1 基本信息

> 请求路径：/user/register
>
> 请求方式：POST
>
> 接口描述：该接口用于用户注册

#### 0.2.2 请求参数

格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| username | string | 必须     | 用户名 |
| password | string | 必须     | 密码   |
| repeatPassword | string | 必须     | 确认密码   |

请求参数样例：

```json
{
  "username": "admin",
  "password": "123456",
  "repeatPassword": "123456"
}
```
#### 0.2.3 响应数据
参数格式：application/json

### 0.3新增用户
#### 0.3.1 基本信息

> 请求路径：/admin/addUser
>
> 请求方式：POST
>
> 接口描述：该接口用于新增用户

#### 0.3.2 请求参数

格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| username | string | 必须     | 用户名 |
| email | string | 必须     | 密码   |
| password | string | 必须     | 确认密码   |
| role | Array<String> | 必须     | 角色权限   |

请求参数样例：

```json
{
  "username": "admin",
  "email": "123456",
  "password": "123456",
  "role": {
    "admin"
  }
}







## 1. 课程管理

### 1.1 课程列表查询

#### 1.1.1 基本信息

> 请求路径：/course/getAll
>
> 请求方式：GET
>
> 接口描述：该接口用于课程列表数据查询



#### 1.1.2 请求参数

无


#### 1.1.3 响应数据

参数格式：application/json

参数说明：

| 参数名           | 类型           | 是否必须 | 备注                           |
| ---------------- | ---------     | -------- | ------------------------------ |
| code             | number        | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg              | string        | 非必须   | 提示信息                       |
| data             | object[]      | 非必须   | 返回的数据                     |
| \|- id           | number         | 非必须   | id                             |
| \|- courseNum    | int           | 非必须   | 课程号                         |
| \|- teacherNum   | Long          | 非必须   | 老师号                         |
| \|- courseName   | string        | 非必须   | 课程名称                       |
| \|- credit       | number        | 非必须   | 课程学分                       |
| \|- courseHours  | number        | 非必须   | 课时                           |





响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "courseNum": 101,
      "teacherNum": 1010001,
      "courseName": "操作系统",
      "courseCredit":3,
      "courseHours":64
    },
    {
      "id": 2,
      "courseNum": 108,
      "teacherNum": 1010017,
      "courseName": "需求分析",
      "courseCredit":3,
      "courseHours":64
    }
  ]
}
```





### 1.2 删除课程

#### 1.2.1 基本信息

> 请求路径：/course/del/{ids}
>
> 请求方式：DELETE
>
> 接口描述：该接口用于根据ID批量删除课程数据



#### 1.2.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| id     | number | 必须     | 部门ID |

请求参数样例：

```
/course/del/1,2,3
```



#### 1.2.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```







### 1.3 添加课程

#### 1.3.1 基本信息

> 请求路径：/course/add
>
> 请求方式：POST
>
> 接口描述：该接口用于添加课程数据




#### 1.3.2 请求参数

格式：application/json

参数说明：

| 参数名       | 类型   | 是否必须 | 备注     |
| ------------ | ------ | -------- | -------- |
| courseNum    | number | 必须     | 课程号   |
| courseName   | string | 必须     | 课程名   |
| teacherNum   | number | 非必须   | 老师号   |
| courseCredit | number | 非必须   | 课程学分 |
| courseHours  | number | 非必须   | 课时     |

请求参数样例：

```json
{
      "courseNum": 110,
      "teacherNum": 1010018,
      "courseName": "数据结构",
      "courseCredit":3,
      "courseHours":64
}
```



#### 1.3.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```





### 1.4 根据ID查询

#### 1.4.1 基本信息

> 请求路径：/course/getById/{id}
>
> 请求方式：GET
>
> 接口描述：该接口用于根据ID查询课程数据




#### 1.4.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| id     | number | 必须     | 部门ID |

请求参数样例：

```
/course/getById/1
```





#### 1.4.3 响应数据

参数格式：application/json

参数说明：

| 参数名           | 类型   | 是否必须 | 备注                           |
| ---------------- | ------ | -------- | ------------------------------ |
| code             | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg              | string | 非必须   | 提示信息                       |
| data             | object | 非必须   | 返回的数据                     |
| \|- id           | number | 非必须   | id                             |
| \|- courseNum    | number | 非必须   | 课程号                         |
| \|- teacherNum   | number | 非必须   | 老师号                         |
| \|- courseName   | string | 非必须   | 课程名称                       |
| \|- courseCredit | number | 非必须   | 课程学分                       |
| \|- courseHours  | number | 非必须   | 课时                           |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data":    {
      "id": 1,
      "courseNum": 101,
      "teacherNum": 1010001,
      "courseName": "操作系统",
      "courseCredit":3,
      "courseHours":64
    }
}
```





### 1.5 修改课程
#### 1.5.1 基本信息

> 请求路径：/course/update
>
> 请求方式：PUT
>
> 接口描述：该接口用于修改部门数据



#### 1.5.2 请求参数

格式：application/json

参数说明：

| 参数名       | 类型   | 是否必须 | 备注     |
| ------------ | ------ | -------- | -------- |
| courseNum    | number | 必须     | 课程号   |
| courseName   | string | 必须     | 课程名   |
| teacherNum   | number | 非必须   | 老师号   |
| courseCredit | number | 非必须   | 课程学分 |

请求参数样例：

```json
{
      "courseNum": 110,
      "teacherNum": 1010018,
      "courseName": "数据结构",
      "courseCredit":3,
      "courseHours":64
}
```



#### 1.5.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```



### 1.6 课程条件查询

#### 1.6.1 基本信息

请求路径:/course

请求方式:Get

接口描述：该接口用于课程列表数据的条件分页查询

#### 1.6.2 请求参数

参数格式：queryString

参数说明：

| 参数名称    | 是否必须 | 示例 | 备注                                       |
| ----------- | -------- | ---- | ------------------------------------------ |
| courseName  | 否       | 数据 | 课程名                                     |
| teacherName | 否       | 张   | 老师名                                     |
| page        | 是       | 1    | 分页查询的页码，如果未指定，默认为1        |
| pageSize    | 是       | 10   | 分页查询的每页记录数，如果未指定，默认为10 |

请求数据样例：

```shell
/course?courseName=数据&teacherName=张&page=1&pageSize=10
```









## 2. 学生管理

### 2.1 学生列表查询

#### 2.1.1 基本信息

> 请求路径：/student/getAll
>
> 请求方式：GET
>
> 接口描述：该接口用于课程列表数据查询



#### 2.1.2 请求参数

无



#### 2.1.3 响应数据

参数格式：application/json

参数说明：

| 参数名          | 类型      | 是否必须 | 备注                           |
| --------------- | --------- | -------- | ------------------------------ |
| code            | number    | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg             | string    | 非必须   | 提示信息                       |
| data            | object[ ] | 非必须   | 返回的数据                     |
| \|- id          | number    | 非必须   | id                             |
| \|- studentNum  | number    | 非必须   | 学生号                         |
| \|- studentName | string    | 非必须   | 学生名                         |





响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "studentNum": 21011265,
      "studentName": "曹勇虎",
    },
    {
      "id": 2,
      "studentNum": 21011266,
      "studentName": "吴彦祖",
    },
  ]
}
```







### 2.2 删除学生

#### 2.2.1 基本信息

> 请求路径：/student/del/{ids}
>
> 请求方式：DELETE
>
> 接口描述：该接口用于批量删除学生的数据信息



#### 2.2.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型       | 示例  | 是否必须 | 备注         |
| ------ | ---------- | ----- | -------- | ------------ |
| ids    | 数组 array | 1,2,3 | 必须     | 员工的id数组 |

请求参数样例：

```
/student/del/1,2,3
```



#### 2.2.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```







### 2.3 添加学生

#### 2.3.1 基本信息

> 请求路径：/student/add
>
> 请求方式：POST
>
> 接口描述：该接口用于添加员工的信息



#### 2.3.2 请求参数

参数格式：application/json

参数说明：

| 名称        | 类型   | 是否必须 | 备注   |
| ----------- | ------ | -------- | ------ |
| studentNum  | number | 必须     | 学生号 |
| studentName | string | 必须     | 学生名 |

请求数据样例：

```json
{
  "studentNum":21011265
  "studentName": "曹勇虎",
}
```





#### 2.3.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```





### 2.4 根据ID查询

#### 2.4.1 基本信息

> 请求路径：/student/getById/{id}
>
> 请求方式：GET
>
> 接口描述：该接口用于根据主键ID查询学生的信息



#### 2.4.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| id     | number | 必须     | 学生ID |

请求参数样例：

```
/student/getById/1
```



#### 2.4.3 响应数据

参数格式：application/json

参数说明：

| 名称            | 类型   | 是否必须 | 默认值 |
| --------------- | ------ | -------- | ------ |
| code            | number | 必须     |        |
| msg             | string | 非必须   |        |
| data            | object | 必须     |        |
| \|- id          | number | 非必须   |        |
| \|- studentNum  | number | 非必须   |        |
| \|- studentName | string | 非必须   |        |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 2,
    "studentNum":21011265
    "studentname": "zhangwuji",
  }
}
```







### 2.5 修改学生
#### 2.5.1 基本信息

> 请求路径：/student/update
>
> 请求方式：PUT
>
> 接口描述：该接口用于修改学生的数据信息



#### 2.5.2 请求参数

参数格式：application/json

参数说明：

| 名称        | 类型   | 是否必须 | 备注   |
| ----------- | ------ | -------- | ------ |
| id          | number | 必须     | id     |
| studentNum  | number | 必须     | 学生号 |
| studentName | string | 必须     | 学生名 |

请求数据样例：

```json
{
  "id": 1,
  "studentNum": 21011265,
  "studentName": "林平之",
}
```



#### 2.5.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```



## 3. 为课程分配学生管理

### 3.1 课程对应学生列表查询

#### 3.1.1 基本信息

> 请求路径：/course/courseStudent
>
> 请求方式：GET
>
> 接口描述：该接口用于课程对应学生列表数据查询,用于在窗口中显示该课程已经分配的学生



#### 3.1.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                         |
| ------ | ------ | -------- | ---------------------------- |
| id     | number | 必须     | id(课程表对应的id)不是课程号 |

请求参数样例：

```
/course/courseStudent/1
```





#### 3.1.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型      | 是否必须 | 备注                           |
| ------ | --------- | -------- | ------------------------------ |
| code   | number    | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string    | 非必须   | 提示信息                       |
| data   | object[ ] | 非必须   | 返回的数据                     |
| \|- id | number    | 非必须   | id(学生表的id)                 |





响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": [1,2,3,5]
}
```

```
后端JAVA
/*设置角色菜单对应关系*/
@GetMapping("/roleMenu/{roleId}")
public Result getRoleMenu(@PathVariable Integer roleId){
    List<Integer> menu_ids =roleMenuService.getRoleMenu(roleId);
    return Result.success(menu_ids);
}
```

















### 3.2 设置课程对应的学生

#### 3.2.1 基本信息

> 请求路径：/course/courseStudent
>
> 请求方式：POST
>
> 接口描述：该接口用于为课程分配信息



#### 3.2.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 示例  | 是否必须 | 备注   |
| ------ | ------ | ----- | -------- | ------ |
| id     | number | 1,2,3 | 必须     | 课程id |



+参数路径:application/json

参数说明：

| 名称 | 类型 | 是否必须 | 备注    |
| ---- | ---- | -------- | ------- |
| id   | 数组 | 必须     | 学生ids |



请求参数样例：

```
/course/courseStudent/1
```

请求数据样例：

```json
[1,2,3,4]
```



后端JAVA

```
/*设置角色菜单对应关系*/
@PostMapping("/roleMenu/{roleId}")
public Result setRoleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds){
    roleMenuService.setRoleMenu(roleId,menuIds);
    return Result.success();
}
```



#### 3.2.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```













## 4. 老师管理

### 4.1 老师列表查询

#### 4.1.1 基本信息

> 请求路径：/teacher/getAll
>
> 请求方式：GET
>
> 接口描述：该接口用于老师列表数据查询



#### 4.1.2 请求参数

无



#### 4.1.3 响应数据

参数格式：application/json

参数说明：

| 参数名          | 类型      | 是否必须 | 备注                           |
| --------------- | --------- | -------- | ------------------------------ |
| code            | number    | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg             | string    | 非必须   | 提示信息                       |
| data            | object[ ] | 非必须   | 返回的数据                     |
| \|- id          | number    | 非必须   | id                             |
| \|- teacherNum  | number    | 非必须   | 老师号                         |
| \|- teacherName | string    | 非必须   | 老师名                         |





响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "teacherNum": 10100017,
      "teacherName": "张小勤",
    },
    {
      "id": 2,
      "teacherNum": 10100018,
      "teacherName": "孙立杰",
    },
  ]
}
```







### 4.2 删除老师

#### 4.2.1 基本信息

> 请求路径：/teacher/del/{ids}
>
> 请求方式：DELETE
>
> 接口描述：该接口用于批量删除老师的数据信息



#### 4.2.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型       | 示例  | 是否必须 | 备注         |
| ------ | ---------- | ----- | -------- | ------------ |
| ids    | 数组 array | 1,2,3 | 必须     | 老师的id数组 |

请求参数样例：

```
/teacher/del/1,2,3
```



#### 4.2.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```







### 4.3 添加老师

#### 4.3.1 基本信息

> 请求路径：/teacher/add
>
> 请求方式：POST
>
> 接口描述：该接口用于添加老师的信息



#### 4.3.2 请求参数

参数格式：application/json

参数说明：

| 名称        | 类型   | 是否必须 | 备注   |
| ----------- | ------ | -------- | ------ |
| teacherNum  | number | 必须     | 老师号 |
| teacherName | string | 必须     | 老师名 |

请求数据样例：

```json
{
  "teacherNum":21011265
  "teacherName": "曹勇虎",
}
```





#### 4.3.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```





### 4.4 根据ID查询

#### 4.4.1 基本信息

> 请求路径：/teacher/getById/{id}
>
> 请求方式：GET
>
> 接口描述：该接口用于根据主键ID查询老师的信息



#### 4.4.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| id     | number | 必须     | 老师ID |

请求参数样例：

```
/teacher/getById/1
```



#### 4.4.3 响应数据

参数格式：application/json

参数说明：

| 名称            | 类型   | 是否必须 | 默认值 |
| --------------- | ------ | -------- | ------ |
| code            | number | 必须     |        |
| msg             | string | 非必须   |        |
| data            | object | 必须     |        |
| \|- id          | number | 非必须   |        |
| \|- teacherNum  | number | 非必须   |        |
| \|- teacherName | string | 非必须   |        |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 2,
    "teacherNum":21011265
    "teachername": "zhangwuji",
  }
}
```







### 4.5 修改老师

#### 4.5.1 基本信息

> 请求路径：/teacher/update
>
> 请求方式：PUT
>
> 接口描述：该接口用于修改老师的数据信息



#### 4.5.2 请求参数

参数格式：application/json

参数说明：

| 名称        | 类型   | 是否必须 | 备注   |
| ----------- | ------ | -------- | ------ |
| id          | number | 必须     | id     |
| teacherNum  | number | 必须     | 老师号 |
| teacherName | string | 必须     | 老师名 |

请求数据样例：

```json
{
  "id": 1,
  "teacherNum": 21011265,
  "teacherName": "林平之",
}
```



#### 4.5.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```

















## 5. 签到管理

### 5.1 签到列表查询

#### 5.1.1 基本信息

> 请求路径：/check/getAll
>
> 请求方式：GET
>
> 接口描述：该接口用于签到总表数据查询



#### 5.1.2 请求参数

无



#### 5.1.3 响应数据

参数格式：application/json

参数说明：

| 参数名               | 类型      | 是否必须 | 备注                           |
| -------------------- | --------- | -------- | ------------------------------ |
| code                 | number    | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg                  | string    | 非必须   | 提示信息                       |
| data                 | object[ ] | 非必须   | 返回的数据                     |
| \|- id               | number    | 非必须   | id                             |
| \|- courseNum        | number    | 非必须   | 课程号                         |
| \|- checkInDetailsID | number    | 非必须   | 签到表号                       |
| \|- courseName       | string    | 非必须   | 课程名称                       |
| \|-checkInTime       | datetime  | 非必须   | 签到日期                       |





响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "courseNum": 101,
      "checkInDetailsID": 2,
      "courseName":"操作系统",
      "checkInTime":"2023-6-1"
    },
    {
      "id": 2,
      "courseNum": 102,
      "checkInDetailsID": 3,
      "courseName":"数据结构",
      "checkInTime":"2023-6-1"
    },
  ]
}
```







### 5.2 删除签到

#### 5.2.1 基本信息

> 请求路径：/check/del/{ids}
>
> 请求方式：DELETE
>
> 接口描述：该接口用于批量删除签到的数据信息



#### 5.2.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型       | 示例  | 是否必须 | 备注         |
| ------ | ---------- | ----- | -------- | ------------ |
| ids    | 数组 array | 1,2,3 | 必须     | 老师的id数组 |

请求参数样例：

```
/check/del/1,2,3
```



#### 5.2.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```







### 5.3 发布签到

#### 5.3.1 基本信息

> 请求路径：/check/add
>
> 请求方式：POST
>
> 接口描述：该接口用于添加签到的信息



#### 5.3.2 请求参数

参数格式：application/json

参数说明：

| 名称       | 类型   | 是否必须 | 备注   |
| ---------- | ------ | -------- | ------ |
| courseName | string | 必须     | 课程名 |

请求数据样例：

```json
{
  "courseName": "数据结构",
}
```





#### 5.3.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```



### 5.4 根据签到详情表已签到学生查询

#### 5.4.1 基本信息

> 请求路径：/check/In/{id}
>
> 请求方式：GET
>
> 接口描述：该接口用于根据主键ID查询签到详情表的信息



#### 5.4.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| id     | number | 必须     | 老师ID |

请求参数样例：

```
/check/In/1
```



#### 4.4.3 响应数据

参数格式：application/json

参数说明：

| 名称    | 类型   | 是否必须 | 默认值           |
| ------- | ------ | -------- | ---------------- |
| code    | number | 必须     |                  |
| msg     | string | 非必须   |                  |
| data    | object | 必须     |                  |
| \|- ids | 数组   | 非必须   | 已签的学生表中id |

响应数据样例：

```json
[1,3,4,5]
```



### 5.4 根据签到详情表未签学生查询

#### 5.4.1 基本信息

> 请求路径：/check/out/{id}
>
> 请求方式：GET
>
> 接口描述：该接口用于根据主键ID查询签到详情表未签学生的信息



#### 5.4.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| id     | number | 必须     | 老师ID |

请求参数样例：

```
/check/out/1
```



#### 4.4.3 响应数据

参数格式：application/json

参数说明：

| 名称    | 类型   | 是否必须 | 默认值             |
| ------- | ------ | -------- | ------------------ |
| code    | number | 必须     |                    |
| msg     | string | 非必须   |                    |
| data    | object | 必须     |                    |
| \|- ids | 数组   | 非必须   | 未签到的学生表中id |

响应数据样例：

```json
[1,3,4,5]
```





# 状态码

- 200 OK - [GET]：服务器成功返回用户请求的数据
- 201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
- 202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
- 204 NO CONTENT - [DELETE]：用户删除数据成功。
- 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作
- 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
- 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
- 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
- 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
- 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
- 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
- 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。



# 请求方法

- GET /zoos：列出所有动物园
- POST /zoos：新建一个动物园（上传文件）
- GET /zoos/ID：获取某个指定动物园的信息
- PUT /zoos/ID：更新某个指定动物园的信息（提供该动物园的全部信息）
- DELETE /zoos/ID：删除某个动物园
- GET /zoos/ID/animals：列出某个指定动物园的所有动物
- DELETE /zoos/ID/animals/ID：删除某个指定动物园的指定动物