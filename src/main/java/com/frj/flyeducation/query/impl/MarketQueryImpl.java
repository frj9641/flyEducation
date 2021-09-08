package com.frj.flyeducation.query.impl;

import com.frj.flyeducation.domain.entity.common.CommonResult;
import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.query.MarketQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketQueryImpl implements MarketQuery {
    @Autowired
    private MarketMapper marketMapper;
    /**
     * 查询返回所有启用的营销课
     * @return
     */
    @Override
    public CommonResult queryMarketsByIsReleased() {
        List<Market> markets = marketMapper.selectMarketsByIsReleased();
        Map<String,Object> map=new HashMap<>();
        map.put("markets",markets);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }

    /**
     * 查询返回所有营销课
     * @return
     */
    @Override
    public CommonResult queryMarkets() {
        List<Market> markets = marketMapper.selectMarkets();
        Map<String,Object> map=new HashMap<>();
        map.put("markets",markets);
        CommonResult result = CommonResult.builder().code(200).msg("查询成功").ext(map).build();
        return result;
    }
}
