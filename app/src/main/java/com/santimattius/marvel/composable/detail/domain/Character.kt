package com.santimattius.marvel.composable.detail.domain

interface Character {
    val id: Long
    val name: String
    val thumbnail: String
    val comics: List<Item>
    val series: List<Item>
    val stories: List<Item>
}


interface Item {
    val name: String
}


