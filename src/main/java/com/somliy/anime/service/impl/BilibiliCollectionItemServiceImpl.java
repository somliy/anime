package com.somliy.anime.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.somliy.anime.common.commonentity.CollectionInfo;
import com.somliy.anime.common.commonentity.CollectionMedias;
import com.somliy.anime.common.commonentity.SpiderData;
import com.somliy.anime.common.http.RestTemplateTest;
import com.somliy.anime.dao.BilibiliCollectionItemDao;
import com.somliy.anime.entity.BilibiliCollection;
import com.somliy.anime.entity.BilibiliCollectionItem;
import com.somliy.anime.service.BilibiliCollectionItemService;
import com.somliy.anime.service.BilibiliCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收藏内容 收藏内容(BilibiliCollectionItem)表服务实现类
 *
 * @author somliy
 * @since 2019-07-13 15:13:38
 */
@Service("bilibiliCollectionItemService")
public class BilibiliCollectionItemServiceImpl extends ServiceImpl<BilibiliCollectionItemDao, BilibiliCollectionItem> implements BilibiliCollectionItemService {

    @Autowired
    private BilibiliCollectionService bilibiliCollectionService;

    @Autowired
    private BilibiliCollectionItemService bilibiliCollectionItemService;

    @Override
    public boolean updateList() {
        RestTemplateTest restTemplate = new RestTemplateTest();
        SpiderData data = restTemplate.reverseData(1);
        int page = (data.getData().getInfo().getMedia_count() + 19) / 20;
        SaveInfo(data.getData().getInfo());
        for (int i = 1; i <= page; i++) {
            data = restTemplate.reverseData(i);
            int count = 1;
            List<CollectionMedias> medias = data.getData().getMedias();
            SaveMedias(medias);
            for (CollectionMedias media : data.getData().getMedias()) {
                System.out.println("第 " + i + " 页 第 " + ((i - 1) * 20 + count) + " 条 : " + media.toString());
                count++;
            }
        }
        return false;
    }

    private void SaveInfo(CollectionInfo info) {
        BilibiliCollection collection = bilibiliCollectionService.getOne(
                new LambdaQueryWrapper<BilibiliCollection>()
                        .eq(BilibiliCollection::getMothId, info.getId())
        );
        if (collection != null) {
            collection.setNumber(info.getMedia_count());
            bilibiliCollectionService.updateById(collection);
        }else {
            BilibiliCollection bilibiliCollection = new BilibiliCollection();
            bilibiliCollection.setId(IdUtil.simpleUUID());
            bilibiliCollection.setCover(info.getCover());
            bilibiliCollection.setCreatedTime(new Date());
            bilibiliCollection.setMothId(info.getId());
            bilibiliCollection.setNumber(info.getMedia_count());
            bilibiliCollection.setTitle(info.getTitle());
            bilibiliCollectionService.save(bilibiliCollection);
        }
    }

    private void SaveMedias(List<CollectionMedias> medias) {
        List<String> collect = bilibiliCollectionItemService.list()
                .stream().map(BilibiliCollectionItem::getAvId).collect(Collectors.toList());
        List<BilibiliCollectionItem> list = new ArrayList<>();
        for(CollectionMedias media : medias) {
            if(collect.contains(media.getId())) {
                continue;
            }
            BilibiliCollectionItem bilibiliCollectionItem = new BilibiliCollectionItem();
            bilibiliCollectionItem.setId(IdUtil.simpleUUID());
            bilibiliCollectionItem.setAvId(media.getId());
            bilibiliCollectionItem.setTitle(media.getTitle());
            bilibiliCollectionItem.setCover(media.getCover());
            bilibiliCollectionItem.setCreatedTime(new Date());
            bilibiliCollectionItem.setInfo(media.getIntro());
            bilibiliCollectionItem.setSelfStatic("0");
            list.add(bilibiliCollectionItem);
        }
        bilibiliCollectionItemService.saveBatch(list);
    }


}