package com.somliy.anime.common.commonentity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: zhaodong
 * @Date: 2019/7/12 14:32
 * @Description:
 */
@Data
public class CollectionInfo {

    private String id;

    private String title;

    private Integer media_count;

    private String cover;
}
