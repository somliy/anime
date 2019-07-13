package com.somliy.anime.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.somliy.anime.entity.BilibiliCollectionItem;

/**
 * 收藏内容 收藏内容(BilibiliCollectionItem)表服务接口
 *
 * @author somliy
 * @since 2019-07-13 15:13:38
 */
public interface BilibiliCollectionItemService extends IService<BilibiliCollectionItem> {

    boolean updateList();

}