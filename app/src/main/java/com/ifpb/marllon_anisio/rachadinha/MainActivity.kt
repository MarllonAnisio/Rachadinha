package com.ifpb.marllon_anisio.rachadinha

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.ifpb.marllon_anisio.rachadinha.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

/**
 * Activity principal responsável pela interface de cálculo da conta.
 * Gerencia as entradas de valor, número de pessoas e escolha da gorjeta.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    /**
     * Configura todos os ouvintes de eventos (listeners) da tela.
     * Implementa monitoramento de texto em tempo real para os campos de entrada.
     */
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

        // Recalcula sempre que a porcentagem de gorjeta mudar
        binding.rgTipPercentage.setOnCheckedChangeListener { _, _ ->
            calculateValues()
        }

        // Navegação para a caderneta de pendurados
        binding.btnViewDebts.setOnClickListener {
            val intent = Intent(this, PenduradosActivity::class.java)
            startActivity(intent)
        }

        // Ação de compartilhamento do valor por pessoa
        binding.btnShare.setOnClickListener {
            shareBill()
        }
    }

    /**
     * Executa a lógica de negócio principal: cálculo da gorjeta e divisão da conta.
     * Possui validação rigorosa para evitar divisão por zero e manipulação de nulos.
     */
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
        
        // Validação rigorosa: evitar divisão por zero e exibir aviso visual via TextInputLayout
        if (numPeople <= 0) {
            binding.tilNumPeople.error = getString(R.string.error_zero_people)
            updateUI(tipAmount, totalAmount, 0.0)
        } else {
            binding.tilNumPeople.error = null
            val perPersonValue = totalAmount / numPeople
            updateUI(tipAmount, totalAmount, perPersonValue)
        }
    }

    /**
     * Atualiza os componentes de texto da UI com os valores calculados,
     * formatando-os adequadamente para a moeda local.
     */
    private fun updateUI(tip: Double, total: Double, perPerson: Double) {
        binding.tvTipAmountValue.text = currencyFormat.format(tip)
        binding.tvTotalAmountValue.text = currencyFormat.format(total)
        binding.tvPerPersonValue.text = currencyFormat.format(perPerson)
        
        // Proteção de UX: Habilitar compartilhar apenas se houver um valor válido a ser pago
        binding.btnShare.isEnabled = perPerson > 0
    }

    /**
     * Gera uma Intent de compartilhamento (Action SEND) para enviar o valor por pessoa
     * via aplicativos externos (como WhatsApp, Telegram, etc).
     */
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
