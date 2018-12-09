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
        restartBTN.setOnClickListener { v -> restart(v) }
    }

    fun photo(v: View){
        editText = findViewById<View>(R.id.editText_1) as EditText
        val temp = editText.text.toString().trim()
        textOfYourName.text = temp
        if(temp != ""){
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager) != null){
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        }else{
            Snackbar.make(findViewById(android.R.id.content), "Fill all fields", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun restart(v: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    yourPhoto.setImageBitmap(data.extras.get("data") as Bitmap)
                    textOfYourName.setVisibility(View.VISIBLE);
                    editText_1.setVisibility(View.GONE);
                    takeaPhoto.setVisibility(View.GONE);
                    restartBTN.setVisibility(View.VISIBLE);
                }
            }
            else -> {
                Snackbar.make(findViewById(android.R.id.content), "No camera here", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}