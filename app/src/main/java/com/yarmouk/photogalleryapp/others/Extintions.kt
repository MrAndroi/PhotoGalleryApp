package com.yarmouk.photogalleryapp.others

import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputEditText

//this extension function will return to us string in edit text when ever user click on the search action
fun TextInputEditText.onSubmit(func: (text:String) -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->

        //check if the action is search action
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            func(this.text.toString())
        }

        true

    }
}