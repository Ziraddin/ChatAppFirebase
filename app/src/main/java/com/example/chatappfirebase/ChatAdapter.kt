package com.example.chatappfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: MutableList<MessageDTO>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTv: TextView = itemView.findViewById(R.id.SenderTV)
        val messageTv: TextView = itemView.findViewById(R.id.MessageTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.senderTv.text = messages[position].sender
        holder.messageTv.text = messages[position].message
    }

    fun addMessage(message: MessageDTO) {
        messages.add(message)
        notifyItemInserted(messages.size)
    }
}
