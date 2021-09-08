package com.frj.flyeducation.domain.entity.market;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Market {
    private Integer marketId;
    private String marketName;
    private String subject;
    private String introduction;
    private String posterUrl;
    private String teacherName;
    private String registrarUnionid;
    private String registrarName;
    private Integer isPeriod;
    private Integer period;
    private Integer isReleased;
    private String price;
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
