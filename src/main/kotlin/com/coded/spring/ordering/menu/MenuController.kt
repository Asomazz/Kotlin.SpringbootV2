package com.coded.spring.com.coded.spring.ordering.menu


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/menu")
class MenuController(
    private val menuService: MenuService
) {
    @PostMapping
    fun createMenu(@RequestBody menu: MenuEntity): ResponseEntity<String> {
        return menuService.createMenu(menu)
    }

    @GetMapping
    fun getAllMenus(): ResponseEntity<List<MenuEntity>> {
        return menuService.getAllMenus()
    }

}