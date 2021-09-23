package com.example.demo.domain

import javax.persistence.*

@Entity
class Category(
    @Id @GeneratedValue
    @Column(name = "category_id")
    val id: Long,// 기본값을 정해주는 것은 값이 안들어 왔을 경우 디폴트 값으로 해준다는 의미, 들어오면 해당 값으로 해준다.

    var name : String,

    @ManyToMany //실무에서는 사용하는 것이 안좋음
    @JoinTable(name ="category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")])
    var items : MutableList<Item>,




){
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category = this

    @OneToMany(mappedBy = "parent" )
    var child: MutableList<Category> = mutableListOf()

    fun addChildCategory(child: Category){
        this.child.add(child)
        child.parent = this
    }

}