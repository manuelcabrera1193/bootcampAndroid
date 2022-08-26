package com.bootcamp.nttdata.bootcampandroid.models


data class Course(val id: String, val content: String, val details: String) {
    override fun toString(): String = content
}