package com.chrisxyq.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chrisxu
 * @create 2021-10-16 17:46
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usertbl {
    //    @TableId(type = IdType.NONE)//默认
    //自增适合使用该注解，在数据库上一个最大的id基础上加1
    @TableId(type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //自动会进行驼峰转换，字段映射
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    //无需在数据库级别设置更新时候更新该字段的触发器
    private Date updateTime;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;
}
