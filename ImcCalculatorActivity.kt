package com.manu.myfirstapplication.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.manu.myfirstapplication.R
import com.manu.myfirstapplication.firstapp.imccalculator.ResultIMCActivity
import java.text.DecimalFormat

class imcCalculatorActivity : ComponentActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 60
    private var currentAge: Int = 18
    private var currentHeight: Int = 120


    private lateinit var cardViewMen: CardView
    private lateinit var cardVieWoman: CardView
    private lateinit var textHeight: TextView
    private lateinit var sliderHeight: RangeSlider
    private lateinit var buttonPlus: FloatingActionButton
    private lateinit var buttonSustract: FloatingActionButton
    private lateinit var buttonPlus2: FloatingActionButton
    private lateinit var buttonSustract2: FloatingActionButton
    private lateinit var textWeight: TextView
    private lateinit var textAge: TextView
    private lateinit var buttonCalculate: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imccalculator)
        initcomponents()
        initlisteners()
        initUI()

    }


    private fun initcomponents() {
        cardViewMen = findViewById(R.id.cardViewMen)
        cardVieWoman = findViewById(R.id.cardViewWoman)
        textHeight = findViewById(R.id.textHeight)
        sliderHeight = findViewById(R.id.sliderHeight)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonSustract = findViewById(R.id.buttonSustract)
        textWeight = findViewById(R.id.textWeight)
        textAge = findViewById(R.id.textAge)
        buttonPlus2 = findViewById(R.id.buttonPlus2)
        buttonSustract2 = findViewById(R.id.buttonSustract2)
        buttonCalculate = findViewById(R.id.buttonCalculate)

    }

    private fun initlisteners() {
        cardViewMen.setOnClickListener {
            changeGender()
            setGendercolor(isMaleSelected)
        }
        cardVieWoman.setOnClickListener {
            changeGender()
            setGendercolor(isFemaleSelected)
        }

        sliderHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()

            textHeight.text = "$currentHeight CM"
        }
        buttonPlus.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        buttonSustract.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        buttonPlus2.setOnClickListener {
            currentAge += 1
            setAge()
        }
        buttonSustract2.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        buttonCalculate.setOnClickListener{
            val result = calculateIMC()
            navigateToResult(result)

        }


    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()

    }

    private fun setWeight() {
        textWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        textAge.text = currentAge.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

    }

    private fun setGendercolor(isViewSelected: Boolean) {
        cardViewMen.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        cardVieWoman.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }


    private fun getBackgroundColor(isSelectComponent: Boolean): Int {
        val colorReference = if (isSelectComponent) {
            R.color.verde_oscuro
        } else {
            R.color.verde_claro_seleccion

        }
        return ContextCompat.getColor(this, colorReference)


    }

    private fun initUI() {
        setGendercolor(isMaleSelected)
        setWeight()
        setAge()

    }


}

