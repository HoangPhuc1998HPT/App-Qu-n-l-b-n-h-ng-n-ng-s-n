package com.example.qlnongsan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qlnongsan.mode.NongSan
import io.realm.RealmResults

class NongSanAdapter(private val context: Context, private val nongSans: RealmResults<NongSan>)
    : RecyclerView.Adapter<NongSanAdapter.NongSanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NongSanViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nongsan, parent, false)
        return NongSanViewHolder(view)
    }

    override fun onBindViewHolder(holder: NongSanViewHolder, position: Int) {
        val nongSan = nongSans[position]
        holder.textViewIdNongSan.text = nongSan?.idNongSan
        holder.textViewTenNongSan.text = nongSan?.tenNongSan
        holder.textViewDVT.text = nongSan?.dvt
        holder.textViewGia.text = nongSan?.gia.toString()
        holder.textViewSLTonKho.text = nongSan?.slTonKho.toString()
    }

    override fun getItemCount(): Int {
        return nongSans.size
    }

    inner class NongSanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewIdNongSan: TextView = itemView.findViewById(R.id.textViewIdNongSan)
        val textViewTenNongSan: TextView = itemView.findViewById(R.id.textViewTenNongSan)
        val textViewDVT: TextView = itemView.findViewById(R.id.textViewDVT)
        val textViewGia: TextView = itemView.findViewById(R.id.textViewGia)
        val textViewSLTonKho: TextView = itemView.findViewById(R.id.textViewSLTonKho)
    }
}
