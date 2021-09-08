package com.frj.flyeducation.service.impl;

import com.frj.flyeducation.domain.entity.market.Market;
import com.frj.flyeducation.domain.entity.market.MarketIsReleasedChangeBean;
import com.frj.flyeducation.domain.mapper.MarketMapper;
import com.frj.flyeducation.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private MarketMapper marketMapper;
    @Value("${posterUrl}")
    private String posterUrl;
    /**
     * 创建营销课程
     *
     * @param market
     */
    @Override
    public void createMarket(Market market) {
        marketMapper.insertMarket(market);
    }

    /**
     * 上传海报服务
     * @param file
     */
    @Override
    public void uploadPoster(MultipartFile file) {
        String path = posterUrl;
        String filename = file.getOriginalFilename();
        File filePath = new File(path, filename);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过营销课主键修改发布
     * @param marketIsReleasedChangeBean
     */
    @Override
    public void changeIsReleasedByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean) {
        Integer isReleased = marketMapper.selectIsReleasedByMarketId(marketIsReleasedChangeBean);
        if(isReleased==0){
            marketMapper.updateIsReleasedByMarketId1(marketIsReleasedChangeBean);
        }else{
            marketMapper.updateIsReleasedByMarketId0(marketIsReleasedChangeBean);
        }
    }

    /**
     * 通过营销课主键删除营销课
     * @param marketIsReleasedChangeBean
     */
    @Override
    public void removeMarketByMarketId(MarketIsReleasedChangeBean marketIsReleasedChangeBean) {
        marketMapper.deleteMarketByMarketId(marketIsReleasedChangeBean);
    }


}
