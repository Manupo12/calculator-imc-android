package com.manu.myfirstapplication.firstapp.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.manu.myfirstapplication.R
import com.manu.myfirstapplication.firstapp.imcCalculatorActivity.Companion.IMC_KEY


class ResultIMCActivity : ComponentActivity() {

    private lateinit var textTypeWeight: TextView
    private lateinit var textNumberImc: TextView
    private lateinit var textConsejo: TextView
    private lateinit var buttonRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultimc)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initListeners()
        initUI(result)


    }

    private fun initListeners() {
        buttonRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        textNumberImc.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //bajo peso
                textTypeWeight.text = getString(R.string.UnderWeight)
                textTypeWeight.setTextColor(ContextCompat.getColor(this, R.color.UnderWeight))
                textConsejo.text = getString(R.string.UnderWeighTip)


            }

            in 18.51..24.99 -> { //peso normal
                textTypeWeight.text = getString(R.string.NormalWeight)
                textTypeWeight.setTextColor(ContextCompat.getColor(this, R.color.NiceWeight))
                textConsejo.text = getString(R.string.NormalWeightTip)


            }

            in 25.00..29.99 -> { //sobrepeso
                textTypeWeight.text = getString(R.string.Overweight)
                textTypeWeight.setTextColor(ContextCompat.getColor(this, R.color.OverWeight))
                textConsejo.text = getString(R.string.OverWeightTip)


            }

            in 30.00..99.00 -> { //obesidad
                textTypeWeight.text = getString(R.string.Obesity)
                textTypeWeight.setTextColor(ContextCompat.getColor(this, R.color.Obesity))
                textConsejo.text = getString(R.string.ObesityTip)


            }

            else -> { //error
                textTypeWeight.text = getString(R.string.Error)
                textConsejo.text = getString(R.string.Error)
                textNumberImc.text = getString(R.string.Error)
            }

        }
    }

    private fun initComponents() {
        textNumberImc = findViewById(R.id.textNumberImc)
        textTypeWeight = findViewById(R.id.textTypeWeight)
        textConsejo = findViewById(R.id.textConsejo)
        buttonRecalculate = findViewById(R.id.buttonRecalculate)

    }
}
