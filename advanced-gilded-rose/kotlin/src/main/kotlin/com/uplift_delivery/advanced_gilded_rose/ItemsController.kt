package com.uplift_delivery.advanced_gilded_rose

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager

class ItemsController {
    fun getAll(): ArrayList<Item> {
        val items = arrayListOf<Item>()
        val transaction = getDb().transactionManager.newTransaction()
        transaction.exec("select * from items") { rs ->
            while (rs.next()) {
                items.add(Item(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)))
            }
        }
        return items
    }

    fun update() {
        val items = arrayListOf<Item>()
        val transaction = getDb().transactionManager.newTransaction()
        transaction.exec("select * from items") { rs ->
            while (rs.next()) {
                items.add(Item(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)))
            }
        }

        for (i in 0..items.size) {
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) {
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        items[i].quality = items[i].quality - 1;
                        transaction.exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sell_in < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                                transaction.exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                            }
                        }
                        if (items[i].sell_in < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                                transaction.exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                            }
                        }
                    }
                }
            }
            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sell_in = items[i].sell_in - 1;
                transaction.exec("update items set sellIn = ${items[i].sell_in} where id = ${items[i].id}")
            }
            if (items[i].sell_in < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) {
                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                items[i].quality = items[i].quality - 1;
                                transaction.exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                        transaction.exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                        transaction.exec("update items set quality = ${items[i].quality} where id = ${items[i].id}")
                    }
                }
            }
        }
        transaction.commit()
    }

    private fun getDb(): Database {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = "jdbc:postgresql://localhost:5432/"
        config.username = "docker"
        config.password = "docker"
        config.catalog = "docker"
        config.maximumPoolSize = 3

        return Database.connect(HikariDataSource(config))
    }
}