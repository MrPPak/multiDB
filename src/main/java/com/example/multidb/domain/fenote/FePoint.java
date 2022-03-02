package com.example.multidb.domain.fenote;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(FePoint.FePointId.class)
@Table(name = "fe_point")
@Entity
public class FePoint {
    @Id
    private String userId;

    @Id
    private Integer pointNo;

    private String orderid;
    private Integer points;
    private String useWay;
    private String pointType;
    private LocalDateTime regtm;

    @Builder
    private FePoint(String userId, Integer pointNo, String orderid, Integer points,
                    String useWay, String pointType) {
        this.userId = userId;
        this.pointNo = pointNo;
        this.orderid = orderid;
        this.points = points;
        this.useWay = useWay;
        this.pointType = pointType;
        this.regtm = LocalDateTime.now();
    }

    public void updateOrderId(String orderId) {
        this.orderid = orderId;
    }

    @NoArgsConstructor
    @Data
    public static class FePointId implements Serializable {
        private String userId;
        private Integer pointNo;

        public FePointId(String userId, Integer pointNo) {
            this.userId = userId;
            this.pointNo = pointNo;
        }
    }
}
