package com.coded.spring.ordering.orders

import com.coded.spring.ordering.items.ItemEntity
import com.coded.spring.ordering.users.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdersRepository : JpaRepository<OrderEntity, Long>{
  //  fun findByUser_Id(user_id: Long): List<OrderEntity>
}

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    val user: UserEntity,

    val restaurant: String,

    @OneToMany(mappedBy = "id", cascade = [CascadeType.ALL])
    val items: List<ItemEntity>? = null
){
    constructor() : this(null, UserEntity(), "", listOf())
}

