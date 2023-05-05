package com.chrisxyq.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chrisxyq.mybatisplusdemo.entity.Usertbl;
import org.springframework.stereotype.Repository;

/**
 * @author chrisxu
 * @create 2021-10-16 17:48
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
@Repository
public interface UserMapper extends BaseMapper<Usertbl> {

}
