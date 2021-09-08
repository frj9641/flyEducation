package com.frj.flyeducation.service;

import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.market.MarketIsReleasedChangeBean;
import org.springframework.web.multipart.MultipartFile;

public interface MarketService {
    /**
     * 创建营销课程
     * @param market
     */
    void createMarket(Market market);

    /**
     * 上传海报服务
     * @param file
     */
    void uploadPoster(MultipartFile file);

    /**
     * 通过营销课主键修改发布
     * @param marketIsReleasedChangeBean
     */
    void changeIsReleasedByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean);

    /**
     * 通过营销课主键删除营销课
     * @param marketIsReleasedChangeBean
     */
    void removeMarketByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean);
}
