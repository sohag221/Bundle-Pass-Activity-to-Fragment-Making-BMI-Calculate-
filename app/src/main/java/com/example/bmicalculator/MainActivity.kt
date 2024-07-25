package com.example.bmicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener {

            if (binding.weight.text.isNotEmpty() && binding.height.text.isNotEmpty()) {
                val weight = binding.weight.text.toString().toInt()
                val height = binding.height.text.toString().toInt()
                showBmiResultFragment(weight, height)

            } else {
                Toast.makeText(this, "Enter weight and height", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showBmiResultFragment(weight: Int, height: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Check if the fragment is already added
        val existingFragment = fragmentManager.findFragmentByTag("BMI_FRAGMENT")
        if (existingFragment != null) {
            fragmentTransaction.remove(existingFragment)
        }

        // Create a new fragment instance and set arguments
        val bmiFragment = BMI_fragment()
        val bundle = Bundle()
        bundle.putInt("weight", weight)
        bundle.putInt("height", height)
        bmiFragment.arguments = bundle

        // Replace any existing fragment with the new fragment
        fragmentTransaction.replace(R.id.fram_layout, bmiFragment, "BMI_FRAGMENT")
        fragmentTransaction.commit()
    }
}
