package com.example.demo.domain

import javax.persistence.*

@Entity
open class Member (
    @Id @GeneratedValue
    @Column(name = "member_id")
    val id:Long = 0,
    var name: String = "",
    @Embedded
    var address: Address = Address(),

    @OneToMany(mappedBy = "member")
    var orders : MutableList<Order> = mutableListOf()
)