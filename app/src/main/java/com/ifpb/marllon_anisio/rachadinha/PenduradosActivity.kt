package com.ifpb.marllon_anisio.rachadinha

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifpb.marllon_anisio.rachadinha.databinding.ActivityPenduradosBinding

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

    private fun setupRecyclerView() {
        adapter = PenduradosAdapter(penduradosList)
        binding.rvPendurados.layoutManager = LinearLayoutManager(this)
        binding.rvPendurados.adapter = adapter
        updateEmptyState()
    }

    private fun setupListeners() {
        binding.btnAddDebt.setOnClickListener {
            val name = binding.etName.text.toString()
            val debt = binding.etDebt.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotBlank() && debt > 0) {
                penduradosList.add(0, Pendurado(name, debt))
                adapter.notifyItemInserted(0)
                binding.rvPendurados.scrollToPosition(0)
                
                // Limpar campos
                binding.etName.text?.clear()
                binding.etDebt.text?.clear()
                binding.etName.clearFocus()
                
                updateEmptyState()
            }
        }
    }

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
