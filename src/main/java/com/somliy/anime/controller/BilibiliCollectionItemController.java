package com.somliy.anime.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.somliy.anime.entity.BilibiliCollectionItem;
import com.somliy.anime.service.BilibiliCollectionItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收藏内容 收藏内容(BilibiliCollectionItem)表控制层
 *
 * @author somliy
 * @since 2019-07-13 15:13:38
 */
@Controller
@RequestMapping("bilibiliCollectionItem")
public class BilibiliCollectionItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private BilibiliCollectionItemService bilibiliCollectionItemService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param bilibiliCollectionItem 查询实体
     * @return 所有数据
     */
    @ResponseBody
    @GetMapping
    public R selectAll(Page<BilibiliCollectionItem> page, BilibiliCollectionItem bilibiliCollectionItem) {
        return success(this.bilibiliCollectionItemService.page(page, new QueryWrapper<>(bilibiliCollectionItem)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ResponseBody
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.bilibiliCollectionItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param bilibiliCollectionItem 实体对象
     * @return 新增结果
     */
    @ResponseBody
    @PostMapping
    public R insert(@RequestBody BilibiliCollectionItem bilibiliCollectionItem) {
        return success(this.bilibiliCollectionItemService.save(bilibiliCollectionItem));
    }

    /**
     * 修改数据
     *
     * @param bilibiliCollectionItem 实体对象
     * @return 修改结果
     */
    @ResponseBody
    @PutMapping
    public R update(@RequestBody BilibiliCollectionItem bilibiliCollectionItem) {
        return success(this.bilibiliCollectionItemService.updateById(bilibiliCollectionItem));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.bilibiliCollectionItemService.removeByIds(idList));
    }

    @GetMapping("/updateList")
    public R updateList() {
        this.bilibiliCollectionItemService.updateList();
        return success("成功");
    }

    @RequestMapping(value = "/greeting")
    public ModelAndView test() {
        String s = "index12312312";
        return new ModelAndView("greeting", "index1", s);
    }

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        List<BilibiliCollectionItem> list = bilibiliCollectionItemService.list();
        list = list.stream().map(item -> {
            item.setSelfStatic("0".equals(item.getSelfStatic())? "未下载":"已下载" );
            return item;
        }).collect(Collectors.toList());
        return new ModelAndView("list", "list", list);
    }



}