package com.afebrii.githubuser.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afebrii.githubuser.R
import com.afebrii.githubuser.data.response.ItemsItem
import com.afebrii.githubuser.databinding.ItemUserGithubBinding
import com.bumptech.glide.Glide

class ListGithubUserAdapter : ListAdapter<ItemsItem, ListGithubUserAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = getItem(position)
        holder.bind(list)
    }

    class MyViewHolder(val binding: ItemUserGithubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ItemsItem){
            Glide.with(binding.root.context)
                .load("${list.avatarUrl}").into(binding.imgProfile)
            binding.tvUsernameGithub.text = "${list.login}"
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}