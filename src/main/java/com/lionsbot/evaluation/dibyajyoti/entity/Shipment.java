package com.lionsbot.evaluation.dibyajyoti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHIPMENT_TBL")

public class Shipment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @NonNull
    @Column(name = "order_id", length = 36)
    private String orderId;

    @NonNull
    private Date shipmentDate;

    @NonNull
    @Column(name = "method_id", length = 36)
    private String methodId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "method_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ShipmentMethod shipmentMethod;

}
