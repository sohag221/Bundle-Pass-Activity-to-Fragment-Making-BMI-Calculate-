package com.example.bmicalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bmicalculator.databinding.FragmentBMIFragmentBinding

class BMI_fragment : Fragment() {

    lateinit var cancelImage: ImageView
    lateinit var result: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_b_m_i_fragment, container, false)

        result = view.findViewById(R.id.result)
        cancelImage = view.findViewById(R.id.cancel_image)

        val weight = arguments?.getInt("weight")?.toInt() ?: 0
        val height = arguments?.getInt("height")?.toInt() ?: 0

        val bmi: Double = ((weight * 1000) / (height * height)).toDouble()
        result.text = "Your BMI is: $bmi"

        cancelImage.setOnClickListener {
            // Remove the fragment when the cancel image is clicked
            removeFragment()
        }

        return view
    }

    private fun removeFragment() {
        parentFragmentManager.beginTransaction().remove(this).commit()
    }
}
