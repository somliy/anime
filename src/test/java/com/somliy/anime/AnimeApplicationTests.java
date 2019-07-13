package com.somliy.anime;

import cn.hutool.core.util.IdUtil;
import com.somliy.anime.common.commonentity.CollectionInfo;
import com.somliy.anime.common.commonentity.CollectionMedias;
import com.somliy.anime.common.commonentity.SpiderData;
import com.somliy.anime.common.http.RestTemplateTest;
import com.somliy.anime.entity.BilibiliCollection;
import com.somliy.anime.entity.BilibiliCollectionItem;
import com.somliy.anime.service.BilibiliCollectionItemService;
import com.somliy.anime.service.BilibiliCollectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimeApplicationTests {

    @Autowired
    private BilibiliCollectionService bilibiliCollectionService;

    @Autowired
    private BilibiliCollectionItemService bilibiliCollectionItemService;


    @Test
    public void test() {
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


    }

    private void SaveMedias(List<CollectionMedias> medias) {
        List<BilibiliCollectionItem> list = new ArrayList<>();
        for(CollectionMedias media : medias) {
            BilibiliCollectionItem bilibiliCollectionItem = new BilibiliCollectionItem();
            bilibiliCollectionItem.setId(IdUtil.simpleUUID());
            bilibiliCollectionItem.setAvId(media.getId());
            bilibiliCollectionItem.setTitle(media.getTitle());
            bilibiliCollectionItem.setCover(media.getCover());
            bilibiliCollectionItem.setCreatedTime(new Date());
            bilibiliCollectionItem.setInfo(media.getIntro());
            list.add(bilibiliCollectionItem);
        }
        bilibiliCollectionItemService.saveBatch(list);
    }

    private void SaveInfo(CollectionInfo info) {
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
