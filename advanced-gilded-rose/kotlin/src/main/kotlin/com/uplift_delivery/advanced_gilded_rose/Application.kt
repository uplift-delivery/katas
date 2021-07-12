package com.uplift_delivery.advanced_gilded_rose


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args);

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val config = HikariConfig()
    config.driverClassName = "org.postgresql.Driver"
    config.jdbcUrl = ""
    config.username = ""
    config.password = ""
    config.maximumPoolSize = 3

    val db = Database.connect(HikariDataSource(config))

    routing {
        get("/") {
            val items = arrayListOf<Item>()
            transaction(db) {
                exec("select * from items") { rs ->
                    {
                        while(rs.next()) {
                            items.add(Item(rs.getInt(0), rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)))
                        }
                    }
                }
            }
            call.respond(items)
        }

        post("/") {
            transaction(db) {
                val items = arrayListOf<Item>()
                exec("select * from items") { rs ->
                    {
                        while(rs.next()) {
                            items.add(Item(rs.getInt(0), rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)))
                        }
                    }
                }

                for (i in 0..items.size) {
                    if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) {
                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                items[i].quality = items[i].quality - 1;
                                exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                            }
                        }
                    } else {
                        if (items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1;
                            if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                                if (items[i].sell_in < 11) {
                                    if (items[i].quality < 50) {
                                        items[i].quality = items[i].quality + 1;
                                        exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                                    }
                                }
                                if (items[i].sell_in < 6) {
                                    if (items[i].quality < 50) {
                                        items[i].quality = items[i].quality + 1;
                                        exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                                    }
                                }
                            }
                        }
                    }
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        items[i].sell_in = items[i].sell_in - 1;
                        exec("update items set sellIn = ${items[i].sell_in} where id = ${items[i].id}")
                    }
                    if (items[i].sell_in < 0) {
                        if (items[i].name != "Aged Brie") {
                            if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                                if (items[i].quality > 0) {
                                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                        items[i].quality = items[i].quality - 1;
                                        exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                                    }
                                }
                            } else {
                                items[i].quality = items[i].quality - items[i].quality;
                                exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                            }
                        } else {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                                exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                            }
                        }
                    }
                }
            }
        }
    }
}