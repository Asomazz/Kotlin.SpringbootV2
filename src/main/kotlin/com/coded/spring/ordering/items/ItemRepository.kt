package com.coded.spring.ordering.items

import com.coded.spring.com.coded.spring.ordering.items.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemsRepository: JpaRepository<ItemEntity, Long>
