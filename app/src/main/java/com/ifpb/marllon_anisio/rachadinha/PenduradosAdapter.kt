package com.ifpb.marllon_anisio.rachadinha

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpb.marllon_anisio.rachadinha.databinding.ItemPenduradoBinding
import java.text.NumberFormat
import java.util.Locale

class PenduradosAdapter(private val items: List<Pendurado>) :
    RecyclerView.Adapter<PenduradosAdapter.ViewHolder>() {

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    class ViewHolder(val binding: ItemPenduradoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPenduradoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvItemName.text = item.nome
        holder.binding.tvItemDebt.text = currencyFormat.format(item.valor)
    }

    override fun getItemCount() = items.size
}
