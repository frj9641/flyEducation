package com.frj.flyeducation.controller;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.market.MarketIsReleasedChangeBean;
import com.frj.flyeducation.query.MarketQuery;
import com.frj.flyeducation.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/market")
public class MarketController {
    @Autowired
    private MarketService marketService;
    @Autowired
    private MarketQuery marketQuery;

    /**
     * 创建营销课程
     * @param market
     */
    @PostMapping
    public void createMarket(Market market){
        marketService.createMarket(market);
    }

    /**
     * 上传海报接口
     * @param file
     */
    @PostMapping(value = "/poster")
    public void uploadPoster(@RequestParam("poster") MultipartFile file){
        marketService.uploadPoster(file);
    }

    /**
     * 查询返回所有启用的营销课
     * @return
     */
    @GetMapping
    public CommonResult queryMarketsByIsReleased(){
        return marketQuery.queryMarketsByIsReleased();
    }

    /**
     * 查询返回所有营销课
     * @return
     */
    @GetMapping("/publish")
    public CommonResult queryMarkets(){
        return marketQuery.queryMarkets();
    }

    /**
     * 通过营销课主键修改发布
     * @param marketIsReleasedChangeBean
     */
    @PutMapping
    public void changeIsReleasedByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean){
        marketService.changeIsReleasedByMarketId(marketIsReleasedChangeBean);
    }

    /**
     * 通过营销课主键删除营销课
     * @param marketIsReleasedChangeBean
     */
    @DeleteMapping
    public void removeMarketByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean){
        marketService.removeMarketByMarketId(marketIsReleasedChangeBean);
    }
}
