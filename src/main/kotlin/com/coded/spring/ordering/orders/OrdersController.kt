package com.coded.spring.ordering.orders

import com.coded.spring.ordering.items.ItemEntity
import com.coded.spring.ordering.users.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    val ordersRepository: OrdersRepository,
    val userRepository: UserRepository,
) {

//    @GetMapping("/order")
//    fun homePage() =  "Welcome to Ooreedoo, please place your order!"
//
//    @GetMapping("/orders/v1/list")
//    fun orders() = ordersService.listOrders()
//
//    @GetMapping("/orders")
//    fun listOrders(): List<Order> = ordersRepository.findAll()

//    @PostMapping("/order")
//    fun createOrder( @RequestBody request: EnterOrderRequest) = ordersRepository.save(
//        com.coded.spring.ordering.orders.OrderEntity(
//            user = request.user_id,
//            restaurant = request.restaurant, items = null)
//        )
//    )

    @PostMapping("/order")
    fun createOrder(@RequestBody request: EnterOrderRequest) {
        val user = userRepository.findById(request.user_id).orElseThrow();
        var newOrder = OrderEntity(
            user = user,
            restaurant = request.restaurant,
            items = request.items.map { ItemEntity(name = it.name, quantity = it.quantity)
            }
        )
        ordersRepository.save(newOrder)

    }

}

data class EnterOrderRequest(
    val user_id: Long,
    val restaurant: String,
    val items: List<ItemRequest>
)

data class ItemRequest(
    val name: String,
    val quantity: Int
)
