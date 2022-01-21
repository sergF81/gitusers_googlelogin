package com.github


import android.content.Context

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View


// data is passed into the constructor

    class RecyclerViewAdapter(context: Context?,private val userArray: MutableList<String>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        private val inflater: LayoutInflater

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.recyclerview_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

           // val state = states[position]
            holder.nameView.text = userArray[position]
            println(userArray[position])
            println("Суккааааааа")
           // holder.capitalView.text = state.capital
        }

        override fun getItemCount(): Int {
            return userArray.size
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val nameView: TextView

            init {
                nameView = view.findViewById(R.id.textViewUserRow)
                println("Суккааааааа")
            }
        }

        init {
            inflater = LayoutInflater.from(context)
        }
    }