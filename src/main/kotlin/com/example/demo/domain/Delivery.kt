package com.example.demo.domain

import net.minidev.json.annotate.JsonIgnore
import javax.persistence.*

@Entity
class Delivery (
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    val id: Long = 0,


    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order,

    @Embedded
    var address: Address = Address(),

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.COMP
){
    enum class DeliveryStatus{READY, COMP}
}

