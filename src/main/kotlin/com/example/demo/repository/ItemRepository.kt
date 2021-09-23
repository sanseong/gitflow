package com.example.demo.repository

import com.example.demo.domain.Item
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ItemRepository (
    @PersistenceContext
    val em : EntityManager
){
    fun save(item: Item){
        if(item.id == null){
            em.persist(item)
        }else{
            em.merge(item)
        }
    }
    fun findOne(id:Long):Item{
        return em.find(Item::class.java, id)
    }
    fun findAll():MutableList<Item> {
        return em.createQuery("select i from item i", Item::class.java).resultList
    }
}