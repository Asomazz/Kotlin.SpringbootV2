package com.coded.spring.ordering.orders

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdersRepository : JpaRepository<OrderEntity, Long>{
    fun findByUserId(userId: Long): List<OrderEntity>
}


