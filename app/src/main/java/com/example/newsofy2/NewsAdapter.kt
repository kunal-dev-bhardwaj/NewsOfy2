package com.example.newsofy

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsofy2.DetailActivity
import com.example.newsofy2.R

//created the adapter and pass values in constructor
class NewsAdapter(val context:Context,val articles:List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
//three functions for recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
    return ArticleViewHolder(view) //hme return kra diya apna view article view holder k through
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article= articles[position]
        holder.newsTitle.text=article.title //setting text
        holder.newsDescription.text=article.description //setting text on description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener {
            Toast.makeText(context,article.title,Toast.LENGTH_SHORT).show()
            val intent=Intent(context, DetailActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }
    }
//for holding the views
class ArticleViewHolder(itemView: View): ViewHolder(itemView) {
    val newsImage=itemView.findViewById<ImageView>(R.id.news_image)
    val newsTitle=itemView.findViewById<TextView>(R.id.news_title)
    val newsDescription=itemView.findViewById<TextView>(R.id.news_description)

}


}