package com.coded.spring.com.coded.spring.ordering.config


import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HazelcastConfig {

    @Bean
    fun hazelcastInstance(): HazelcastInstance {
        val config = Config("menu-cache")
        config.getMapConfig("menus").timeToLiveSeconds = 5
        return Hazelcast.newHazelcastInstance(config)
    }
}