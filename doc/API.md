## 返回的状态码


|  类型  | stateCode | info |
| :--: | :-------: | :--: |
|  成功  |    200    | NULL |
|  错误  |    500    | 错误信息 |

## 用户登录/注册

|          路由           |  方法  |                    说明                    |            |  测试  |
| :-------------------: | :--: | :--------------------------------------: | :--------: | :--: |
|      /api/login       | POST | 提交用户登录表单 username, password, 允许邮箱/手机/用户名登录 |            |  OK  |
|      /api/signup      | POST | 提交用户注册表单 username, password, email, phone |            |  OK  |
|      /api/logout      | PUT  |                    登出                    |            |  OK  |
|       /api/user       | GET  |                 获取当前用户信息                 |            |  OK  |
|       /api/user       | PUT  | 修改当前用户信息，填写需要修改的项，username，email，phone，oldPassword，newPassword |            |  OK  |
|    /api/user/order    | GET  |                查看该用户的所有订单                | OrderModel |  OK  |
| /api/user/screen/{id} | PUT  |           锁定/购买座位，需要上传 需要用户登录后           |  Seat 见后文  |      |

```java
public class OrderModel {
	private List<FilmOrder> filmOrderModelList;
}
public class FilmOrder {
    private Integer id;
    private User user;
    private Integer screenId;
    private String seat;
}
```

## 管理员账号

|            路由            |   方法   |                    说明                    |    提交格式     |
| :----------------------: | :----: | :--------------------------------------: | :---------: |
|     /api/admin/login     |  POST  |          adminname, password 登录          |             |
|    /api/admin/logout     |  PUT   |                    登出                    |             |
|   /api/admin/newMovie    |  POST  | 需要填写的域chineseName,englishName,pictureUrl,type,length,releaseDate,introduction |             |
|     /api/admin/{id}      | DELETE |                当初对应id的电影                 |             |
|     /api/admin/{id}      |  PUT   |      更新电影信息，只需要填写需要更新的域，和创建电影的域名字相同      |             |
|  /api/admin/cinema/{id}  | DELETE |                参数对应id的影院                 |             |
| /api/admin/cinema/create |  POST  |                  创建一个影院                  | CinemaModel |
|  /api/admin/cinema/{id}  |  PUT   |                 修改一个影院信息                 | CinemaModel |

```java
CinemaModel {
    private String name;
    private String address;
    private String phone;
    private List<Screen> screens;
}
```


## 获取电影信息

|                    路由                    |  方法  |                    说明                    |       返回值       |  测试  |
| :--------------------------------------: | :--: | :--------------------------------------: | :-------------: | :--: |
|         /api/movie/name/{查询电影名}          | GET  |      返回电影名对应信息，允许查询中英文电影名，返回一条记录或空       |    SimpMovie    |  OK  |
|       /api/movie/type/{type}?id=ID       | GET  |     返回电影类型列表, 数目为从id开始往后20条，默认id = 0     | List<SimpMovie> |  OK  |
|       /api/movie/date/day/20170501       | GET  |  返回2017-05-01上映的电影列表，如果输入非法日期，返回当天上映列表   | List<SimpMovie> |  OK  |
|       /api/movie/date/month/201705       | GET  |    返回2017-05上映的电影列表，如果输入非法日期，返回当月上映列表    | List<SimpMovie> |  OK  |
|        /api/movie/date/year/2017         | GET  |     返回2017上映的电影列表，如果输入非法日期，返回当年上映列表      | List<SimpMovie> |  OK  |
|             /api/movie/{id}              | GET  |              返回ID=id的电影详细信息              |      Movie      |  OK  |
|       /api/movie/showing/{number}        | GET  |          返回最近一个月上映的电影列表,number条          | List<SimpMovie> |  OK  |
| /api/movie/query/count?type={}&area={}&year={} | GET  |   year=2007, 允许type,area,year字段为"all"    |     Integer     |  OK  |
| /api/movie/query?type={}&area={}&year={}&page={}&step={} | GET  | 返回 [page\*stap, page\*step+step]的数据,允许type,area,year字段为"all" | List<SimpMovie> |  OK  |

```java
SimpMovie {
    private String name;
    private Integer id;
    private String url;
}
```

## 获取演员/导演信息

|           路由           |  方法  |       说明       |     返回值      |  测试  |
| :--------------------: | :--: | :------------: | :----------: | :--: |
|    /api/person/{id}    | GET  |  通过演员/导演的ID获取  |    Person    |  OK  |
| /api/person/movie/{id} | GET  | 获取电影ID的演员/导演名单 | List<Person> |  OK  |

```java
Person {
    private Integer id;
    // 名字
    private String name;
    // 照片的URL
    private String url;
    // 表示是导演还是演员
    private String type; // "actor", "director"
}
```

## 获取影院信息

没有说明默认返回影院简要信息：影院id，影院名字name
|                路由                |  方法  |           说明           | 接受内容 |       返回值        |  测试  |
| :------------------------------: | :--: | :--------------------: | ---- | :--------------: | :--: |
| /api/cinema?number={}&address={} | GET  | number选填默认10，address必填 |      | List<SimpCinema> |      |
|         /api/cinema/{id}         | GET  |        返回影院详细信息        |      |      Cinema      |  OK  |
|   /api/cinema/showing?id={id}    | GET  |   返回正在该影院上映的电影简要信息列表   |      | List<SimpMovie>  |  OK  |

```java
SimpCinema {
    private Integer id;
    private String name;
}

Cinema {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private List<Screen> screens;
}
```

## 获取排片信息

|                    路由                    |  方法  |        说明         | 接受内容 |     返回值      |  测试  |
| :--------------------------------------: | :--: | :---------------: | :--: | :----------: | :--: |
| /api/screen?cinemaid={}&movieid={}&date={}&time={} | GET  | 获取对应影院对应电影的排片情况列表 |      | List<Screen> |  OK  |
|             /api/screen/{id}             | GET  |    获取对应id的排片情况    |      |    Screen    |  OK  |


```java
Seat {
    private List<Integer> vacancy;
    private List<Integer> soldOut;
    private List<Integer> locking;
}

Screen {
    private Integer id;
    private Date time;
    private String language;
    private String room;
    private Double price;
    private Cinema cinema;
    private String movieName;
    private String seats; // '0'->空位,'1'->被锁定,'2'->已售出 8x11 列优先, 比如2行1列下标为8
}
```

## 搜索功能

|          路由          |  方法  |        说明        |       返回值       |  测试  |
| :------------------: | :--: | :--------------: | :-------------: | :--: |
| /api/search?query={} | GET  | 目前只能搜索影片(可以模糊搜索) | List<SimpMovie> |  OK  |

