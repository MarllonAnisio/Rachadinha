package com.ifpb.marllon_anisio.rachadinha

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.ifpb.marllon_anisio.rachadinha.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))

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
    }

    private fun calculateValues() {
        val billValue = binding.etBillValue.text.toString().toDoubleOrNull() ?: 0.0
        val numPeople = binding.etNumPeople.text.toString().toIntOrNull() ?: 1

        val tipPercentage = when (binding.rgTipPercentage.checkedRadioButtonId) {
            R.id.rbTip10 -> 0.10
            R.id.rbTip15 -> 0.15
            R.id.rbTip20 -> 0.20
            else -> 0.10
        }

        val tipAmount = billValue * tipPercentage
        val totalAmount = billValue + tipAmount

        val perPersonValue = if (numPeople > 0) totalAmount / numPeople else 0.0

        updateUI(tipAmount, totalAmount, perPersonValue)
    }

    private fun updateUI(tip: Double, total: Double, perPerson: Double) {
        binding.tvTipAmountValue.text = currencyFormat.format(tip)
        binding.tvTotalAmountValue.text = currencyFormat.format(total)
        binding.tvPerPersonValue.text = currencyFormat.format(perPerson)
    }
}
