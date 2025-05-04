package com.coded.spring


import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
	helloWorldConfig.getMapConfig("pets").setTimeToLiveSeconds(5)
}

val menuConfig = StatusLogger.Config("menu-cache")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(menuConfig)