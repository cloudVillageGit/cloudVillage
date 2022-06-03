package com.cloudVillage.entity.crossResult;

import com.cloudVillage.entity.OrderTrack;
import com.cloudVillage.entity.OrderTrackMedium;
import com.cloudVillage.entity.OrderTrackSmall;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Link
 * @Date: 2022/06/02/1:08
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderTrackMS {
    private OrderTrackMedium orderTrackMedium;
    private List<OrderTrackSmall> OrderTrackSmallList;
}
