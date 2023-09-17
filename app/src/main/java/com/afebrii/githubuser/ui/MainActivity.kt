package com.afebrii.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.afebrii.githubuser.R
import com.afebrii.githubuser.data.response.GithubResponse
import com.afebrii.githubuser.data.response.ItemsItem
import com.afebrii.githubuser.data.retrofit.ApiConfig
import com.afebrii.githubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
        private const val USER_ID = "Afebrii"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvListUserGithub.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListUserGithub.addItemDecoration(itemDecoration)

        findUserGithub()
    }


    private fun findUserGithub() {
        showLoading(true)
        val client = ApiConfig.getApiService().getUsers(USER_ID)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setListData(responseBody.items)
                    }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    showLoading(false)
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        }

            private fun setListData(userList: List<ItemsItem>) {
                val adapter = ListGithubUserAdapter()
                adapter.submitList(userList)
                binding.rvListUserGithub.adapter = adapter
            }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE

            }
        }
}