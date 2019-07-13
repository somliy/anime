package com.somliy.anime.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 *  动漫收藏(BilibiliCollection)表实体类
 *
 * @author somliy
 * @since 2019-07-13 15:13:33
 */
@SuppressWarnings("serial")
@Data
public class BilibiliCollection extends Model<BilibiliCollection> {
    //id
    private String id;
    //收藏夹Id
    private String mothId;
    //创建时间
    private Date createdTime;
    //数量
    private Integer number;
    //标题
    private String title;
    //封面
    private String cover;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }