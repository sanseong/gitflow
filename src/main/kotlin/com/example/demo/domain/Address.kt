package com.example.demo.domain

import javax.persistence.Embeddable

@Embeddable
class Address(val city: String = "",
              val street: String = "",
              val zipcode: String = "")


