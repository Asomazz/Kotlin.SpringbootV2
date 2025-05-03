package com.coded.spring.ordering.orders

import com.coded.spring.com.coded.spring.ordering.items.ItemEntity
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val userId: Long,
    //If the relationship is: Many Orders can belong to One User, then the User will be brought to the OrderEntity as an Object or as a whole UserEntity

    val restaurant: String,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var items: List<ItemEntity> = listOf()
    //If the relationship is: One Order can have Many Items, then the Items will be brought to the OrderEntity as a List
    //This lets the order list its items, but the actual foreign key is in the items table, not the orders table (only the child "the Many" holds the foreign key)
    // this is saying: If I get an order from the database, and I want to see its items, look in the items table and find all the rows where order_id = this order’s id
){
    constructor() : this(null, 1, "", listOf())
}
