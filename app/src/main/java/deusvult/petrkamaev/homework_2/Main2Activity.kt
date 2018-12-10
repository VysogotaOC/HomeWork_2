package deusvult.petrkamaev.homework_2

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val image = intent.getParcelableExtra<Bitmap>("image")
        yourPhoto.setImageBitmap((image) as Bitmap)

        val name = intent.getStringExtra("name")
        textOfYourName.text = name
    }
}