package com.coded.spring.ordering.orders

import com.coded.spring.ordering.items.ItemEntity
import com.coded.spring.ordering.users.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository //This annotates that this file is gonna be the repository. I dont need to write it here since am using JpaRepository, I will need to write it if am creating my own custom repository class (not just an interface)
interface OrdersRepository : JpaRepository<OrderEntity, Long>{ //This line creates the repository, and Jpa lets the repository talk to the DB and gives me all the ready functions to use (.save , .findall)
  // <OrderEntity, Long> means: This repository is for the orders table, and the ID type (the @Id column in the table) is a Long number
//  fun findByUser_Id(user_id: Long): List<OrderEntity>
}

@Entity //this indicates that this class should become a table
@Table(name = "orders")
data class OrderEntity(
    @Id //This is the unique identifier "Primary key"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name= "user_id") //Creates a column in the orders table called user_id. No need to declare a user_id cause Spring handles it automatically when i define the relationship
    val user: UserEntity,
    //If the relationship is: Many Orders can belong to One User, then the User will be brought to the OrderEntity as an Object or as a whole UserEntity

    val restaurant: String,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL]) //cascade means: “If I save an order, also save their items. If I delete an order, delete their items too.”. without it I’d have to manually save the order then save its items one by one
    val items: List<ItemEntity> = listOf()
    //If the relationship is: One Order can have Many Items, then the Items will be brought to the OrderEntity as a List
    //This lets the order list its items, but the actual foreign key is in the items table, not the orders table (only the child "the Many" holds the foreign key)
){
    constructor() : this(null, UserEntity(), "", listOf()) //Spring needs this to be able to create a blank User object behind the scenes.
}

