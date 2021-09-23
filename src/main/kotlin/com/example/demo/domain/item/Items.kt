package com.example.demo.domain.item

import com.example.demo.domain.Category
import com.example.demo.domain.Item
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    val artist: String,
    val etc: String
):Item()

@Entity
@DiscriminatorValue("M")
class Movie(
    val director: String,
    val actor: String
):Item()

@Entity
@DiscriminatorValue("B")
class Book(
    val director: String = "",
    val actor: String = ""
):Item()