package com.frj.flyeducation.domain.mapper;

import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.market.MarketIsReleasedChangeBean;
import com.frj.flyeducation.domain.entity.market.MarketResultBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarketMapper {

    /**
     * 创建营销课程
     * @param market
     */
    void insertMarket(Market market);

    /**
     * 查询返回所有启用的营销课
     * @return
     */
    List<Market> selectMarketsByIsReleased();

    /**
     * 通过营销课主键查询营销课信息
     * @param marketId
     * @return
     */
    Market selectMarketByMarketId(Integer marketId);

    /**
     * 通过教务unionid查询返回教务的营销课程
     * @param registrarUnionid
     * @return
     */
    List<MarketResultBean> selectMarketsByRegistrarUnionid(String registrarUnionid);

    /**
     * 查询返回所有营销课
     * @return
     */
    List<Market> selectMarkets();

    /**
     * 通过营销课主键修改发布至1
     * @param marketIsReleasedChangeBean
     */
    void updateIsReleasedByMarketId1(MarketIsReleasedChangeBean marketIsReleasedChangeBean);

    /**
     * 通过营销课主键修改发布至0
     * @param marketIsReleasedChangeBean
     */
    void updateIsReleasedByMarketId0(MarketIsReleasedChangeBean marketIsReleasedChangeBean);

    /**
     * 通过营销课主键查询返回发布情况
     * @param marketIsReleasedChangeBean
     * @return
     */
    Integer selectIsReleasedByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean);

    /**
     * 通过营销课主键删除营销课
     * @param marketIsReleasedChangeBean
     */
    void deleteMarketByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean);
}
