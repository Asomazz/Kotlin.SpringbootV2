package com.coded.spring.ordering.orders

import com.coded.spring.com.coded.spring.ordering.orders.OrderRequest
import com.coded.spring.ordering.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping("/order")
    fun homePage() = "Welcome to Ooreedoo, please place your order!"

    @PostMapping("/order")
    fun createOrder(@RequestBody request: OrderRequest) {
        orderService.createOrder(request)
    }
}