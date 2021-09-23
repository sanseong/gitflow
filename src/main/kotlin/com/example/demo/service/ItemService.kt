package com.example.demo.service

import com.example.demo.domain.Item
import com.example.demo.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(val itemRepository: ItemRepository) {
    @Transactional
    fun saveItem(item:Item){
        itemRepository.save(item)
    }
    fun findItem():MutableList<Item>{
        return itemRepository.findAll()
    }
    fun findOne(itemId:Long): Item{
        return itemRepository.findOne(itemId)
    }
}