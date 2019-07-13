package com.somliy.anime.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.somliy.anime.entity.BilibiliCollection;
import com.somliy.anime.service.BilibiliCollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 *  动漫收藏(BilibiliCollection)表控制层
 *
 * @author somliy
 * @since 2019-07-13 15:13:34
 */
@RestController
@RequestMapping("bilibiliCollection")
public class BilibiliCollectionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private BilibiliCollectionService bilibiliCollectionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param bilibiliCollection 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<BilibiliCollection> page, BilibiliCollection bilibiliCollection) {
        return success(this.bilibiliCollectionService.page(page, new QueryWrapper<>(bilibiliCollection)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.bilibiliCollectionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param bilibiliCollection 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody BilibiliCollection bilibiliCollection) {
        return success(this.bilibiliCollectionService.save(bilibiliCollection));
    }

    /**
     * 修改数据
     *
     * @param bilibiliCollection 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody BilibiliCollection bilibiliCollection) {
        return success(this.bilibiliCollectionService.updateById(bilibiliCollection));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.bilibiliCollectionService.removeByIds(idList));
    }
}