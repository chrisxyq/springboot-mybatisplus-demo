package com.chrisxyq.mybatisplusdemo.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author chrisxu
 * @create 2021-10-16 17:31
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 * // 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/spring-demos/mybatisplus-demo/src/main/java");
        gc.setAuthor("chrisxyq");
        gc.setOpen(false);
        gc.setIdType(IdType.INPUT);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/school?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("ModuleName"));
        pc.setModuleName("generate");
        pc.setParent("com.example.mybatisplusdemo");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig.setInclude("usertbl");
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setLogicDeleteFieldName("deleted");
//        // 策略配置
//        strategyConfig.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//        // 公共父类
//        strategyConfig.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//        // 写于父类中的公共字段
//        strategyConfig.setSuperEntityColumns("id");
        TableFill createTime = new TableFill("create_Time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_Time", FieldFill.INSERT_UPDATE);
        List<TableFill> list = new ArrayList<>();
        list.add(createTime);
        list.add(updateTime);
        strategyConfig.setTableFillList(list);
        strategyConfig.setVersionFieldName("version");
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategyConfig);
        mpg.execute();
    }

}
