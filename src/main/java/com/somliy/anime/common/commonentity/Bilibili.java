package com.somliy.anime.common.commonentity;

import lombok.Data;

import java.util.List;

/**
 * @Author: zhaodong
 * @Date: 2019/7/12 14:31
 * @Description:
 */
@Data
public class Bilibili {

    private CollectionInfo info;

    private List<CollectionMedias> medias;

}
