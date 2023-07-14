package com.example.forum.Model

class Channel(var name: String, val description: String, val id: String) {
    override fun toString(): String {
        return "#$name"
    }
}