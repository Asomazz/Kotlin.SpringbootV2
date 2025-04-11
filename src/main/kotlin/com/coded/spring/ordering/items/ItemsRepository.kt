package com.coded.spring.ordering.items

import com.coded.spring.ordering.orders.OrderEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

interface ItemsRepository: JpaRepository<ItemEntity, Long>

@Entity
@Table(name="items")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val quantity: Int,

    @ManyToOne
    @JoinColumn(name = "order_id")
    val order: OrderEntity

){
    constructor() : this(null, "", 0,OrderEntity() )
}
