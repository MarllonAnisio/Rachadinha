package com.ifpb.marllon_anisio.rachadinha

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpb.marllon_anisio.rachadinha.databinding.ItemPenduradoBinding
import java.text.NumberFormat
import java.util.Locale

/**
 * Adapter customizado para a lista de Pendurados.
 * Faz a ponte entre a lista de objetos [Pendurado] e o layout XML item_pendurado.
 */
class PenduradosAdapter(private val items: List<Pendurado>) :
    RecyclerView.Adapter<PenduradosAdapter.ViewHolder>() {

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    /**
     * ViewHolder que mantém a referência para os componentes de UI de cada item.
     */
    class ViewHolder(val binding: ItemPenduradoBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Infla o layout do item e cria uma nova instância do ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPenduradoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    /**
     * Vincula os dados do objeto [Pendurado] à posição correspondente na lista da UI.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvItemName.text = item.nome
        holder.binding.tvItemDebt.text = currencyFormat.format(item.valor)
    }

    /**
     * Retorna o tamanho total da lista.
     */
    override fun getItemCount() = items.size
}
