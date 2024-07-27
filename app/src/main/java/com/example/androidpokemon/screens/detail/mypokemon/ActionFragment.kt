package com.example.androidpokemon.screens.detail.mypokemon

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.androidpokemon.R

class ActionFragment(
    val color: Int,
    val btnText: String,
    val noEt: Boolean = false,
    val confirm: Boolean = false
) : DialogFragment() {

    var onSubmit: ((String) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.modal_layout, null)
            val button = view.findViewById<Button>(R.id.buttonSubmit)
            val inputView = view.findViewById<EditText>(R.id.etNickName)
            val confirmText = view.findViewById<TextView>(R.id.confirmText)
            if (confirm) {
                confirmText.visibility = View.VISIBLE
            } else {
                confirmText.visibility = View.GONE
            }
            if (noEt) {
                inputView.visibility = View.GONE
            }
            button.text = btnText
            button.setBackgroundColor(color)
            button.setOnClickListener {
                var input = inputView.text.toString()
                onSubmit?.invoke(input)
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}