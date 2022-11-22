package com.lionsbot.evaluation.dibyajyoti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHIPMENT_TBL")

public class Shipment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private UUID id;

    @NonNull
    @Column(name = "order_id", columnDefinition = "VARCHAR(36)")
    private UUID orderId;

    @NonNull
    private Date shipmentDate;

    @NonNull
    @Column(name = "method_id", columnDefinition = "VARCHAR(36)")
    private UUID methodId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "method_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ShipmentMethod shipmentMethod;

}
