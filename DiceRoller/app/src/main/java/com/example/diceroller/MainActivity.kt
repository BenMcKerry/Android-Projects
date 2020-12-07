package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.example.diceroller.databinding.ActivityMainBinding
import java.util.*

class MainActivity() : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val rollButton = mainBinding.rollButton


        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val randInt = Random().nextInt(6) + 1
        val diceImage = mainBinding.diceImage
        val newDice = when (randInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(newDice)
    }

}