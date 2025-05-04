package com.coded.spring.com.coded.spring.ordering.menu


import com.hazelcast.core.HazelcastInstance
import org.springframework.stereotype.Component

@Component
class MenuProvider(
    private val menuRepository: MenuRepository,
    private val hazelcastInstance: HazelcastInstance
) {
    private val menuCache = hazelcastInstance.getMap<String, List<MenuEntity>>("menus")

    fun getMenus(): List<MenuEntity> {
        val cachedMenus = menuCache["menus"]
        if (cachedMenus.isNullOrEmpty()) {
            println("No menus in cache. Fetching from DB...")
            val menus = menuRepository.findAll()
            menuCache["menus"] = menus
            return menus
        }
        println("Returning ${cachedMenus.size} menus from cache")
        return cachedMenus
    }
}