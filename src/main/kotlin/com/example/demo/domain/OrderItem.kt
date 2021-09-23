package com.example.demo.domain

import javax.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem(
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    val id:Long =0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item, //주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order = Order(), // 주문

    var orderPrice: Int = 0,// 주문 가격
    var count: Int = 0//주문 수량

){
    companion object{
        fun createOrderItem(item:Item, orderPrice: Int, count: Int): OrderItem{ // 주문 상품 가격 수량정보를 사용해서 주문 상품 엔티티를 생성
            var orderItem : OrderItem = OrderItem(item = item)
            orderItem.item = item
            orderItem.orderPrice = orderPrice
            orderItem.count = count

            item.removeStock(count) // 주문한 수량만큼 상품의 재고를 줄인다
            return orderItem
        }
    }

    //비지니스 로직
    //주문 취소
    fun cancel(){
        item.addStock(count) // 취소한 주문 수량만큼 상품의 재고를 증가
    }
    //조회 로직
    fun getTotalPrice() :Int{
        return orderPrice * count
    }
}