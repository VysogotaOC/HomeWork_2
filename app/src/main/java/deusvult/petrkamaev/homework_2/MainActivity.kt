package deusvult.petrkamaev.homework_2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takeaPhoto.setOnClickListener { v -> photo(v) }
    }

    fun photo(v: View){
        editText = findViewById<View>(R.id.editText_1) as EditText
        var temp = editText.text.toString().trim()
        if(temp != ""){
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager) != null){
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        }else{
            Snackbar.make(findViewById(android.R.id.content), "Fill all fields", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    editText = findViewById<View>(R.id.editText_1) as EditText
                    var temp = editText.text.toString().trim()
                    val intent = Intent(this, Main2Activity::class.java)
                    intent.putExtra("image", data.extras.get("data") as Bitmap)
                    intent.putExtra("name", temp)
                    startActivity(intent)
                }
            }
            else -> {
                Snackbar.make(findViewById(android.R.id.content), "No camera here", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}