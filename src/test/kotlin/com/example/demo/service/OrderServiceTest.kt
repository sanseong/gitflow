package com.example.demo.service

import com.example.demo.domain.*
import com.example.demo.domain.item.Book
import com.example.demo.repository.OrderRepository
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.PersistenceUnit

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional
class OrderServiceTest (

) {
    @PersistenceContext
    lateinit var em: EntityManager
    @Autowired
    lateinit var orderService: OrderService
    @Autowired
    lateinit var orderRepository: OrderRepository
    @Test
    fun 상품주문(){
        //Given -> 상품을 만들고
        val member:Member = createMember()
        val item: Item = createBook("시골 JPA", 10000, 10)
        val orderCount : Int = 2

        //When ->실제 상품을 주문
        val orderId: Long = orderService.order(member.id, item.id, orderCount)

        //then -> 주문 가격, 주문 후 재고 수량 검증
        val getOrder: Order = orderRepository.findOne(orderId)

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.status)
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.orderItems.size)
        assertEquals("주문 가격은 가격 * 수량이다.", 10000*2, getOrder.getTotalPrice())
        assertEquals("주문한 수량만큼 재고가 줄어야 한다.", 8, item.stockQuantity)

    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book: Book = Book()
        book.name = name
        book.stockQuantity = stockQuantity
        book.price = price
        em.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member:Member = Member()
        member.name = "회원 1"
        member.address = Address("서울", "강가", "123-123")
        em.persist(member)
        return member
    }
}