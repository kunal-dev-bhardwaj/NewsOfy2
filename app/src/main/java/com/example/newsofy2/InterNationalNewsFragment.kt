package com.example.newsofy2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsofy.*
import kotlinx.android.synthetic.main.activity_internation_news.*
import kotlinx.android.synthetic.main.fragment_inter_national_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InterNationalNewsFragment : Fragment() {
    var articles = mutableListOf<Article>()

    //    lateinit var adapter: NewsAdapter
    lateinit var adapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getNewsInternation()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inter_national_news, container, false)


    }


    private fun getNewsInternation() {
//        val news = NewsService.newsInstace.getHeadLines("us", 1)
        val news=NewsService.getInstance().create(NewsInterface::class.java).getHeadLines("us",1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val newss = response.body()
                if (newss != null) {
                    articles.addAll(newss.articles)
                    progressBar2_international.visibility = View.INVISIBLE
                    recyclerview_news_international.visibility = View.VISIBLE
                    setUpadapter()
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(requireContext(), "Error fetching data$t", Toast.LENGTH_SHORT)
            }


        })

    }

    private fun setUpadapter() {
        //setting adapter here
        adapter = NewsAdapter(requireContext(), articles)
        var recycler = view?.findViewById<RecyclerView>(R.id.recyclerview_news_international)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.adapter = adapter


    }


}