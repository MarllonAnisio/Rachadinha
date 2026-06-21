package com.ifpb.marllon_anisio.rachadinha

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifpb.marllon_anisio.rachadinha.databinding.ActivityPenduradosBinding

/**
 * Activity responsável por gerenciar a caderneta de calotes (Pendurados).
 * Utiliza uma RecyclerView para exibir a lista dinâmica de débitos.
 */
class PenduradosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPenduradosBinding
    private val penduradosList = mutableListOf<Pendurado>()
    private lateinit var adapter: PenduradosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPenduradosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupListeners()
    }

    /**
     * Inicializa a RecyclerView com o Adapter customizado e o LayoutManager.
     * Define o estado inicial da tela (vazia ou com lista).
     */
    private fun setupRecyclerView() {
        adapter = PenduradosAdapter(penduradosList)
        binding.rvPendurados.layoutManager = LinearLayoutManager(this)
        binding.rvPendurados.adapter = adapter
        updateEmptyState()
    }

    /**
     * Configura a ação do botão de adicionar novos débitos.
     * Realiza a validação básica de campos antes de inserir na lista.
     */
    private fun setupListeners() {
        binding.btnAddDebt.setOnClickListener {
            val name = binding.etName.text.toString()
            val debt = binding.etDebt.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotBlank() && debt > 0) {
                // Inserção no topo da lista para melhor feedback visual
                penduradosList.add(0, Pendurado(name, debt))
                adapter.notifyItemInserted(0)
                binding.rvPendurados.scrollToPosition(0)
                
                // Limpeza e reset do foco para nova entrada
                binding.etName.text?.clear()
                binding.etDebt.text?.clear()
                binding.etName.clearFocus()
                
                updateEmptyState()
            }
        }
    }

    /**
     * Alterna a visibilidade entre a mensagem de "Lista Vazia" e a RecyclerView
     * baseado no conteúdo da lista de pendurados.
     */
    private fun updateEmptyState() {
        if (penduradosList.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.rvPendurados.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.rvPendurados.visibility = View.VISIBLE
        }
    }
}
