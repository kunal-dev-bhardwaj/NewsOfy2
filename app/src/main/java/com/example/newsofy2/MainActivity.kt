package com.example.newsofy2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.newsofy.Article
import com.example.newsofy.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var actionBarToggle: ActionBarDrawerToggle
    var articles = mutableListOf<Article>()
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SetupDrawer()


    }



    private fun SetupDrawer() {
        setSupportActionBar(tool_bar)
        actionBarToggle =
            ActionBarDrawerToggle(this, drawer_layout, R.string.app_name, R.string.app_name)
        actionBarToggle.syncState() //for syncking navigation and app bar together
        setUpfragment(TopHeadingsIndiaFragment())
        navigation_view.setCheckedItem(R.id.headlines_text)

        navigation_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.headlines_text -> {
                    Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
                    setUpfragment(TopHeadingsIndiaFragment())
                }
                R.id.internation_text -> {
                    Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
                    setUpfragment(InterNationalNewsFragment())

                }

            }


            drawer_layout.closeDrawers()
            true

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean { //for open navigation after click on appbar icon
        if (actionBarToggle.onOptionsItemSelected(item)) {
        }

        return true
        return super.onOptionsItemSelected(item)
    }


    private fun setUpfragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
//        setTitle(title)
        drawer_layout.closeDrawers()

    }


}