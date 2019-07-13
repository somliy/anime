package com.somliy.anime.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏内容 收藏内容(BilibiliCollectionItem)表实体类
 *
 * @author somliy
 * @since 2019-07-13 15:13:38
 */
@SuppressWarnings("serial")
@Data
public class BilibiliCollectionItem extends Model<BilibiliCollectionItem> {
    //id
    private String id;
    //封面
    private String cover;
    //时间
    private Date createdTime;
    //标题
    private String title;
    //av号
    private String avId;
    //信息
    private String info;

    private String selfStatic;

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