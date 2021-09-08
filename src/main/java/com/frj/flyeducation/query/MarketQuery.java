package com.frj.flyeducation.query;


import com.frj.flyeducation.domain.entity.common.CommonResult;

public interface MarketQuery {
    /**
     * 查询返回所有启用的营销课
     * @return
     */
    CommonResult queryMarketsByIsReleased();

    /**
     * 查询返回所有营销课
     * @return
     */
    CommonResult queryMarkets();
}
