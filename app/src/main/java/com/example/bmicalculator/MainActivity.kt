package com.example.bmicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val fm :FragmentManager = supportFragmentManager
        val ft:FragmentTransaction = fm.beginTransaction()
        val bmiFragment =BMI_fragment()

        binding.calculateButton.setOnClickListener {
            if (!binding.weight.text.isEmpty() && !binding.height.text.isEmpty()){
                if (bmiFragment.isVisible){
                    Toast.makeText(this, "Removing Previous Result", Toast.LENGTH_SHORT).show()
                }else{
                    val weiht = binding.weight.text.toString().toInt()
                    val height = binding.height.text.toString().toInt()

                    val bundle= Bundle()
                    bundle.putInt("weight",weiht)
                    bundle.putInt("height",height)
                    bmiFragment.arguments=bundle
                    ft.add(R.id.fram_layout,bmiFragment)
                    ft.commit()
                }
            }else{
                Toast.makeText(this, "Enter weight and height", Toast.LENGTH_SHORT).show()
            }

        }

    }
}