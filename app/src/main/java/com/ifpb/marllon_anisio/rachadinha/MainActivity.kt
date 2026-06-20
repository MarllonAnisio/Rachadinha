package com.ifpb.marllon_anisio.rachadinha

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.ifpb.marllon_anisio.rachadinha.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calculateValues()
            }
        }

        binding.etBillValue.addTextChangedListener(textWatcher)
        binding.etNumPeople.addTextChangedListener(textWatcher)

        binding.rgTipPercentage.setOnCheckedChangeListener { _, _ ->
            calculateValues()
        }

        binding.btnViewDebts.setOnClickListener {
            val intent = Intent(this, PenduradosActivity::class.java)
            startActivity(intent)
        }

        binding.btnShare.setOnClickListener {
            shareBill()
        }
    }

    private fun calculateValues() {
        val billValue = binding.etBillValue.text.toString().toDoubleOrNull() ?: 0.0
        val numPeople = binding.etNumPeople.text.toString().toIntOrNull() ?: 1

        val tipPercentage = when (binding.rgTipPercentage.checkedRadioButtonId) {
            R.id.rbTip0 -> 0.0
            R.id.rbTip10 -> 0.10
            R.id.rbTip15 -> 0.15
            R.id.rbTip20 -> 0.20
            else -> 0.10
        }

        val tipAmount = billValue * tipPercentage
        val totalAmount = billValue + tipAmount
        
        // Validação rigorosa: evitar divisão por zero e exibir aviso
        if (numPeople <= 0) {
            binding.tilNumPeople.error = getString(R.string.error_zero_people)
            updateUI(tipAmount, totalAmount, 0.0)
        } else {
            binding.tilNumPeople.error = null
            val perPersonValue = totalAmount / numPeople
            updateUI(tipAmount, totalAmount, perPersonValue)
        }
    }

    private fun updateUI(tip: Double, total: Double, perPerson: Double) {
        binding.tvTipAmountValue.text = currencyFormat.format(tip)
        binding.tvTotalAmountValue.text = currencyFormat.format(total)
        binding.tvPerPersonValue.text = currencyFormat.format(perPerson)
        
        // Habilitar compartilhar apenas se houver valor
        binding.btnShare.isEnabled = perPerson > 0
    }

    private fun shareBill() {
        val perPerson = binding.tvPerPersonValue.text.toString()
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, perPerson))
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, null))
    }
}
