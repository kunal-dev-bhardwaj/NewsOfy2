package com.example.newsofy

data class News(
    val totalResults: Int,
    val articles: List<Article>) {

}
data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
)
