package com.coded.spring.ordering.orders

import com.coded.spring.ordering.items.ItemEntity
import com.coded.spring.ordering.items.ItemsRepository
import com.coded.spring.ordering.users.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController //this tells Spring that this class is in charge of listening to the internet and sending back answers
class Controller( //this is saying whenever Spring creates this controller, please give it these objects to use:
    val ordersRepository: OrdersRepository,
    val userRepository: UserRepository,
    val itemsRepository: ItemsRepository
) {

//    @GetMapping("/order")
//    fun homePage() =  "Welcome to Ooreedoo, please place your order!"
//
//    @GetMapping("/orders/v1/list")
//    fun orders() = ordersService.listOrders()
//
//    @GetMapping("/orders")
//    fun listOrders(): List<Order> = ordersRepository.findAll()

    @PostMapping("/order")
    fun createOrder(@RequestBody request: OrderRequest) { //this tells Spring: Expect the data to come in the body of the request, and convert it into this Kotlin object
        val user = userRepository.findById(request.user_id).orElseThrow(); //this means: Go to the users table and try to find the user with the given user_id
        var newOrder = OrderEntity(
            user = user,
            restaurant = request.restaurant
        ).apply {
            items = request.items.map {
                ItemEntity(name = it.name, quantity = it.quantity, order = this)
            }
        }
            //or use this direct assignment:
            //items = listOf()
            //)
            //val items = request.items.map {
            //            ItemEntity(name = it.name, quantity = it.quantity, order = newOrder)
            //        }
            //
            //        newOrder.items = items
        ordersRepository.save(newOrder) //this tells spring to save this order in the DB, and because of cascade feature, also save all the items with it

    }

}

data class OrderRequest(
    val user_id: Long,
    val restaurant: String,
    val items: List<ItemRequest>
)

data class ItemRequest(
    val name: String,
    val quantity: Int
)