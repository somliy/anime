package com.somliy.anime.common.http;


import com.somliy.anime.common.commonentity.SpiderData;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaodong
 * @Date: 2019/7/12 15:10
 * @Description:
 */
public class RestTemplateTest {
    public SpiderData reverseData(int pn) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("pn", pn);
        return restTemplate.getForObject("https://api.bilibili.com/medialist/gateway/base/spaceDetail?media_id=326002049&pn={pn}&ps=20&keyword=&order=mtime&type=0&tid=0&jsonp=jsonp", SpiderData.class, params);
    }
}
