package com.emrehmrc.pref_datastore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.emrehmrc.pref_datastore.MainActivityViewModel
import com.emrehmrc.pref_datastore.MainActivityViewModelFactory
import com.emrehmrc.pref_datastore.databinding.ActivityMainBinding

/**
 *  Rev           1.0
 *  Author        EmreHamurcu
 *  Date          4/24/2021
 *  FileName     MainActivity
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainActivityViewModelFactory(application)).get(
            MainActivityViewModel::class.java
        )

        //Read String
        mainViewModel.readStringDataStore.observe(this, {
            binding.txtName.text = it
        })

        //Read boolean
        mainViewModel.readBooleanDataStore.observe(this, {
            binding.cb.isChecked = it
        })

        //Update data store
        binding.btnUpdate.setOnClickListener {
            mainViewModel.saveStringToDataStore(binding.edtName.text.toString())
            mainViewModel.saveBooleanToDataStore(binding.cb.isChecked)
        }
    }
}