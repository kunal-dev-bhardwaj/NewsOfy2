package com.example.newsofy2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsofy.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_top_headings_india.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class TopHeadingsIndiaFragment : Fragment() {
    lateinit var actionBarToggle: ActionBarDrawerToggle
    var articles = mutableListOf<Article>()
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getNewsIndia()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_headings_india, container, false)

    }

    private fun setUpadapter() {
        //setting adapter here
//        val adapter:NewsAdapter
        adapter = NewsAdapter(requireContext(), articles)
        val recycler = view?.findViewById<RecyclerView>(R.id.recyclerview_news_india)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.adapter = adapter


    }


    private fun getNewsIndia() {
//        val news = NewsService.newsInstace.getHeadLines("in", 1)
        val news=NewsService.getInstance().create(NewsInterface::class.java).getHeadLines("in",1)
//        al quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val newss = response.body()
                if (newss != null) {
                    Log.d("abc", news.toString())
                    articles.addAll(newss.articles)
                    progressBar2_india.visibility = View.GONE
                    recyclerview_news_india.visibility = View.VISIBLE
                    setUpadapter()
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(requireContext(), "Error fetching data$t", Toast.LENGTH_SHORT)
            }
        })
    }


}