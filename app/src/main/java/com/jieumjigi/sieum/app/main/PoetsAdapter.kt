package com.jieumjigi.sieum.app.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jieumjigi.sieum.R
import com.jieumjigi.sieum.api.model.User
import com.jieumjigi.sieum.util.RoundImageView

class PoetsAdapter(var poetList: List<User>) : RecyclerView.Adapter<PoetsAdapter.ViewHolder>() {
    private var listener: OnClickListener? = null

    fun setListener(clickListener: OnClickListener) {
        this.listener = clickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo: User = poetList[position]
        holder.bindItems(todo)

        holder.itemView.setOnClickListener(View.OnClickListener {
            if (listener != null) {
                listener!!.onItemClick(todo, position)
            }
        })

    }

    override fun getItemCount(): Int {
        return poetList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_poet, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(poet: User) {
            val rivPoet = itemView.findViewById<RoundImageView>(R.id.rivPoet)

            rivPoet.loadImage(poet.profileImageUrl)


        }
    }

    interface OnClickListener {
        fun onItemClick(poet: User, position: Int)
        fun onItemDelete(poet: User)
    }
}