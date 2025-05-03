package com.coded.spring.ordering

import com.coded.spring.com.coded.spring.ordering.items.ItemEntity
import com.coded.spring.com.coded.spring.ordering.orders.OrderRequest
import com.coded.spring.ordering.items.ItemsRepository
import com.coded.spring.ordering.orders.OrderEntity
import com.coded.spring.ordering.orders.OrdersRepository
import com.coded.spring.ordering.users.UserRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val ordersRepository: OrdersRepository,
    private val userRepository: UserRepository,
    private val itemsRepository: ItemsRepository
) {

    fun createOrder(request: OrderRequest) {

        val newOrder = OrderEntity(
            userId = request.userId,
            restaurant = request.restaurant
        ).apply {
            items = request.items.map {
                ItemEntity(name = it.name, quantity = it.quantity, order = this)
            }
        }

        ordersRepository.save(newOrder)
    }
}
