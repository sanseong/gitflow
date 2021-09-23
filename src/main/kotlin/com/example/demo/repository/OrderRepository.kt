package com.example.demo.repository

import com.example.demo.domain.Order
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
class OrderRepository(
    @PersistenceContext
    val em: EntityManager
) {
    fun save(order: Order){
        em.persist(order)
    }
    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }
    //fun findAll(orderSearch: OrderSearch) : MutableList<Order>{}
}