package com.example.androidpokemon.screens.detail.pokemon

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.androidpokemon.R

class CatchDialogFramgent(val color: Int) : DialogFragment() {

    var onSubmit: ((String) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.modal_layout, null)
            val button = view.findViewById<Button>(R.id.buttonSubmit)
            button.setBackgroundColor(color)
            button.setOnClickListener {
                val input = view.findViewById<EditText>(R.id.etNickName).text.toString()
                onSubmit?.invoke(input)
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}