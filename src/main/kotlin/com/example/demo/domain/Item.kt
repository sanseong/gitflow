package com.example.demo.domain


import com.example.demo.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item  // Getter methods of lazy classes cannot be final: com.example.demo.domain.Item#getId
{
    @Id @GeneratedValue
    @Column(name = "item_id")
    val id:Long =0

    var name : String = ""
    var price: Int = 0
    var stockQuantity: Int = 0

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = mutableListOf()

    //비지니스 로직
    fun addStock(quantity:Int){
        this.stockQuantity +=quantity // add되는 양마다 재고량 증가
    }
    fun removeStock(quantity: Int){
        var restStock = this.stockQuantity - quantity
        if(restStock < 0 ){
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock
    }
}