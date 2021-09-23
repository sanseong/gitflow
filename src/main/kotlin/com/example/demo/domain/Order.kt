package com.example.demo.domain

import org.aspectj.weaver.ast.Or
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue
    @Column(name = "order_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = Member(),

    @OneToMany(mappedBy = "order", cascade = [(CascadeType.ALL)])
    val orderItems : MutableList<OrderItem> = mutableListOf(),

    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    var delivery : Delivery? = null,

    val orderDate: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER
){

    fun setMem(member:Member){
        this.member = member
        member.orders.add(this)
    }
    fun addOrderItem(orderItem: OrderItem){
        orderItems.add(orderItem)
        orderItem.order = this
    }
    fun setDeliver(delivery: Delivery){
        this.delivery = delivery
        delivery.order = this
    }

    //생성 메서드
    companion object{
        fun createOrder(member: Member, vararg orderItems: OrderItem):Order{ // 주문 엔티티 생성할 때 사용(실제 주문 엔티티 생성)
            val order: Order = Order()
            order.member = member
            for(orderItem in orderItems){
                order.addOrderItem(orderItem)
            }
            return order
        }
    }
    //비지니스 로직
    fun cancel() { // 주문 취소시 사용, 주문 상태를 취소로 변경
        if(delivery?.status == Delivery.DeliveryStatus.COMP){
            throw IllegalStateException("이미 배송 완료된 상품 취소 불가")
        }
        this.status = OrderStatus.CANCEL
        for(orderItem in orderItems){
            orderItem.cancel()
        }
    }

    //조회 로직
    //전체 주문 가격 조회
    fun getTotalPrice():Int{
        var totalPrice: Int = 0
        for(orderItem in orderItems){
            totalPrice += orderItem.orderPrice
        }
        return totalPrice
    }




}
    enum class OrderStatus {ORDER, CANCEL}
