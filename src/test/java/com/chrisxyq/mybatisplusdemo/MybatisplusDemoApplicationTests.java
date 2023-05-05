package com.chrisxyq.mybatisplusdemo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chrisxyq.mybatisplusdemo.entity.Usertbl;
import com.chrisxyq.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ComponentScan("com.chrisxyq.mybatisplusdemo.mapper")
class MybatisplusDemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<Usertbl> users = userMapper.selectList(null);
        //forEach参数是consumer类型的
        users.forEach(System.out::println);
    }

    /**
     * 默认主键生成策略为雪花算法
     * 即使表配置了自增
     */
    @Test
    void test() {
        Usertbl user = new Usertbl();
        user.setAge(1);
        user.setEmail("183@qq.com");
        user.setName("zhangsan");
        userMapper.insert(user);
    }
    /**
     * 测试 MyMetaObjectHandler
     * @Component注解一定要加到MyMetaObjectHandler上
     * 使得MyMetaObjectHandler填充器生效
     */
    @Test
    void test1() {
        Usertbl user = new Usertbl();
        user.setId(6L);
        user.setEmail("updated");
        userMapper.updateById(user);
    }

    /**
     * 测试乐观锁成功
     */
    @Test
    void test2() {
        Usertbl usertbl = userMapper.selectById(1L);
        usertbl.setEmail("updated");
        userMapper.updateById(usertbl);
    }

    /**
     * 测试乐观锁失败
     */
    @Test
    void test3() {
        Usertbl usertbl1 = userMapper.selectById(1L);
        usertbl1.setEmail("updated1");
        Usertbl usertbl2 = userMapper.selectById(1L);
        usertbl2.setEmail("updated2");
        userMapper.updateById(usertbl2);//模拟插队
        userMapper.updateById(usertbl1);
    }
    /**
     * 测试按条件查询
     * ==>  Preparing: UPDATE usertbl SET name=?, age=?, email=?, create_time=?, update_time=?, version=? WHERE id=? AND version=?
     * ==> Parameters: Jone(String), 18(Integer), updated2(String), 2021-10-16 18:37:27.0(Timestamp), 2021-10-17 11:58:33.967(Timestamp), 3(Integer), 1(Long), 2(Integer)
     * <==    Updates: 1
     * Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@20184ade]
     * Creating a new SqlSession
     * SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@27ec0d06] was not registered for synchronization because synchronization is not active
     * 2021-10-17 11:58:34.099  INFO 21804 --- [           main] c.e.m.handler.MyMetaObjectHandler        : start update fill
     * JDBC Connection [HikariProxyConnection@645323114 wrapping com.mysql.cj.jdbc.ConnectionImpl@727dcc64] will not be managed by Spring
     * ==>  Preparing: UPDATE usertbl SET name=?, age=?, email=?, create_time=?, update_time=?, version=? WHERE id=? AND version=?
     * ==> Parameters: Jone(String), 18(Integer), updated1(String), 2021-10-16 18:37:27.0(Timestamp), 2021-10-17 11:58:34.099(Timestamp), 3(Integer), 1(Long), 2(Integer)
     * <==    Updates: 0
     * Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@27ec0d06]
     */
    @Test
    void test4() {
        Map<String, Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","Jone");
        List<Usertbl> usertbls = userMapper.selectByMap(map);
        usertbls.forEach(System.out::println);
    }
    /**
     * 测试分页查询
     * JDBC Connection [HikariProxyConnection@892091760 wrapping com.mysql.cj.jdbc.ConnectionImpl@4c13ca07] will not be managed by Spring
     * ==>  Preparing: SELECT COUNT(*) FROM usertbl
     * ==> Parameters:
     * <==    Columns: COUNT(*)
     * <==        Row: 6
     * <==      Total: 1
     * ==>  Preparing: SELECT id,name,age,email,create_time,update_time,version FROM usertbl LIMIT ?
     * ==> Parameters: 5(Long)
     */
    @Test
    void test5() {
        Page<Usertbl> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    /**
     * ==>  Preparing: UPDATE usertbl SET deleted=1 WHERE id=? AND deleted=0
     * ==> Parameters: 3(Long)
     */
    @Test
    void test6() {
        userMapper.deleteById(3L);
    }

}
