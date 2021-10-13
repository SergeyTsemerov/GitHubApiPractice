package ru.geekbrains.librariescoursepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.geekbrains.librariescoursepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCounter1.setOnClickListener {
            presenter.firstCounterClick()
        }
        binding.btnCounter2.setOnClickListener {
            presenter.secondCounterClick()
        }
        binding.btnCounter3.setOnClickListener {
            presenter.thirdCounterClick()
        }
    }

    override fun setFirstButtonText(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setSecondButtonText(text: String) {
        binding.btnCounter2.text = text
    }

    override fun setThirdButtonText(text: String) {
        binding.btnCounter3.text = text
    }
}