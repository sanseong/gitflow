package com.example.demo.service

import com.example.demo.domain.*
import com.example.demo.repository.ItemRepository
import com.example.demo.repository.MemberRepository
import com.example.demo.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService (
    val memberRepository: MemberRepository,
    val orderRepository: OrderRepository,
    val itemRepository: ItemRepository
) {
    //주문 -> 주문하는 회원 식별자, 상품 식별자, 주문 수량 정보를 받아서 실제 주문 엔티티를 생성한 후 저장한다.
    @Transactional
    fun order(memberId:Long, itemId: Long, count:Int):Long{
        //엔티티 조회
        val member: Member = memberRepository.findOne(memberId)
        val item: Item = itemRepository.findOne(itemId)



        //주문상품 생성
        val orderItem : OrderItem = OrderItem.createOrderItem(item, item.price, count)

        //주문 생성
        val order :Order = Order.createOrder(member, orderItem)

        //배송정보 생성
        var delivery: Delivery = Delivery(order = order)
        delivery.address = member.address
        delivery.status = Delivery.DeliveryStatus.READY

        //주문 저장
        orderRepository.save(order)
        return order.id
    }
    //주문 취소
    @Transactional
    fun cancelOrder(orderId : Long){ // 주문 식별자를 받아서 주문 엔티티를 조회한 후 주문 엔티티에 주문취소를 요청한다.
        //주문 엔티티 조회
        val order: Order = orderRepository.findOne(orderId)
        //주문 취소
        order.cancel()
    }

    //주문 검색
    /*
    fun findOrders(orderSearch : OrderSearch) : MutableList<Order> { //검색 조건을 가진 객체로 주문 엔티티를 검사한다.
        return orderRepository.findAll(orderSearch)
    }

     */

}