## 返回的状态码

|  类型  | stateCode | info |
| :--: | :-------: | :--: |
|  成功  |    200    | NULL |
|  错误  |    500    | 错误信息 |

## 用户登录/注册

|     路由      |  方法  |                    说明                    |
| :---------: | :--: | :--------------------------------------: |
| /api/login  | POST | 提交用户登录表单 userName, password, 允许邮箱/手机/用户名登录 |
| /api/signup | POST | 提交用户注册表单 userName, password, email, phone |
| /api/logout | PUT  |                    登出                    |
|  /api/user  | GET  |                 获取当前用户信息                 |
|  /api/user  | PUT  | 修改当前用户信息，填写需要修改的项，username，email，phone，oldPassword，newPassword |

## 管理员账号

|         路由          |   方法   |                    说明                    |
| :-----------------: | :----: | :--------------------------------------: |
|  /api/admin/login   |  POST  |          adminname, password 登录          |
|  /api/admin/logout  |  PUT   |                    登出                    |
| /api/admin/newMovie |  POST  | 需要填写的域chineseName,englishName,pictureUrl,type,length,releaseDate,introduction |
|   /api/admin/{id}   | DELETE |                当初对应id的电影                 |
|   /api/admin/{id}   |  PUT   |      更新电影信息，只需要填写需要更新的域，和创建电影的域名字相同      |


## 获取电影信息
|              路由              |  方法  |                  说明                   |
| :--------------------------: | :--: | :-----------------------------------: |
|   /api/movie/name/{查询电影名}    | GET  |     返回电影名对应信息，允许查询中英文电影名，返回一条记录或空     |
| /api/movie/type/{type}?id=ID | GET  |   返回电影类型列表, 数目为从id开始往后20条，默认id = 0    |
| /api/movie/date/day/20170501 | GET  | 返回2017-05-01上映的电影列表，如果输入非法日期，返回当天上映列表 |
| /api/movie/date/month/201705 | GET  |  返回2017-05上映的电影列表，如果输入非法日期，返回当月上映列表   |
|  /api/movie/date/year/2017   | GET  |    返回2017上映的电影列表，如果输入非法日期，返回当年上映列表    |
|       /api/movie/{id}        | GET  |             返回ID=id的电影信息              |
| /api/movie/showing/{number}  | GET  |        返回最近一个月上映的电影列表,number条         |

