package com.somliy.anime.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.somliy.anime.dao.BilibiliCollectionDao;
import com.somliy.anime.entity.BilibiliCollection;
import com.somliy.anime.service.BilibiliCollectionService;
import org.springframework.stereotype.Service;

/**
 *  动漫收藏(BilibiliCollection)表服务实现类
 *
 * @author somliy
 * @since 2019-07-13 15:13:34
 */
@Service("bilibiliCollectionService")
public class BilibiliCollectionServiceImpl extends ServiceImpl<BilibiliCollectionDao, BilibiliCollection> implements BilibiliCollectionService {

}