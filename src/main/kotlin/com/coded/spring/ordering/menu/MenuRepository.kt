package com.coded.spring.com.coded.spring.ordering.menu

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<MenuEntity, Long>{

}
