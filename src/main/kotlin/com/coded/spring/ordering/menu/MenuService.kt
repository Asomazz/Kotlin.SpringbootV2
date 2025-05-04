package com.coded.spring.com.coded.spring.ordering.menu

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val menuProvider: MenuProvider
) {
    fun createMenu(menu: MenuEntity): ResponseEntity<String> {
        menuRepository.save(menu)
        return ResponseEntity.ok("Menu item created")
    }

    fun getAllMenus(): ResponseEntity<List<MenuEntity>> {
        return ResponseEntity.ok(menuProvider.getMenus())
    }
}