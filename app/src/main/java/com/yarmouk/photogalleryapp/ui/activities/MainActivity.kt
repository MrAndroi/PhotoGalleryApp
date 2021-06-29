package com.yarmouk.photogalleryapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yarmouk.photogalleryapp.R
import com.yarmouk.photogalleryapp.databinding.ActivityMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}