package com.example.multidb.domain.femarket;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@IdClass(PdtStatic.PdtStaticId.class)
@Table(name = "fe_pdt_static")
@Entity
public class PdtStatic {
    @Id
    private String shopid;
    @Id
    private String pdtCode;

    @Column(name = "order_3days_cnt")
    private Integer order3daysCnt; // 3일 주문수

    @Column(name = "order_10days_cnt")
    private Integer order10daysCnt; // 10일 주문수

    @Column(name = "click_3days_cnt")
    private Integer click3daysCnt; // 3일 클릭수

    @Column(name = "click_10days_cnt")
    private Integer click10daysCnt; // 10일 클릭수

    @Column(name = "review_3days_cnt")
    private Integer review3daysCnt; // 3일 리뷰수

    @Column(name = "review_10days_cnt")
    private Integer review10daysCnt; // 10일 리뷰수

    @Column(name = "review_30days_cnt")
    private Integer review30daysCnt; // 30일 리뷰수

    private Integer rStock; // 현재 재고수

    private LocalDateTime updtm;
    private LocalDateTime regtm;

    @Builder
    private PdtStatic(String shopid, String pdtCode, int order3daysCnt, int order10daysCnt,
                      int click3daysCnt, int click10daysCnt, int review3daysCnt, int review10daysCnt,
                      int review30daysCnt, int rStock) {
        this.shopid = shopid;
        this.pdtCode = pdtCode;
        this.order3daysCnt = order3daysCnt;
        this.order10daysCnt = order10daysCnt;
        this.click3daysCnt = click3daysCnt;
        this.click10daysCnt = click10daysCnt;
        this.review3daysCnt = review3daysCnt;
        this.review10daysCnt = review10daysCnt;
        this.review30daysCnt = review30daysCnt;
        this.rStock = rStock;
        this.updtm = LocalDateTime.now();
        this.regtm = LocalDateTime.now();
    }

    public void updateOrder3daysCnt(int count) {
        this.order3daysCnt = count;
    }

    @NoArgsConstructor
    @Data
    public static class PdtStaticId implements Serializable {
        private String shopid;
        private String pdtCode;

        public PdtStaticId(String shopid, String pdtCode) {
            this.shopid = shopid;
            this.pdtCode = pdtCode;
        }
    }
}
