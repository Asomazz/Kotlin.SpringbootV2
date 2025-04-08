package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    val ordersRepository: OrdersRepository
){

    @GetMapping("/order")
    fun homePage() =  "Welcome to Ooreedoo, please place your order!"

    @GetMapping("/orders")
    fun listOrders(): List<Order> = ordersRepository.findAll()

    @PostMapping("/order")
    fun submitOrder( @RequestBody request: EnterOrderRequest) = ordersRepository.save(Order(username = request.user, restaurant = request.restaurant, items = request.items))

}

data class EnterOrderRequest(
    val user: String,
    val restaurant: String,
    val items: MutableList<String>
)