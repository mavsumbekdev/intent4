package uz.datatalim.intent4

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import java.io.File

class MainActivity : AppCompatActivity() {
    val CHOOSE_IMAGE_CODE=1534
    lateinit var imgPicture:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initviews()
    }

    private fun initviews() {
        var btnShareText = findViewById<Button>(R.id.btn_share_text)
        var btnOpenGalery = findViewById<Button>(R.id.btn_open_galery)
        var btnShareImage = findViewById<Button>(R.id.btn_share_imagr)
        imgPicture=findViewById<ImageView>(R.id.img_picture)

        btnShareText.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Mavsumbek")
            startActivity(Intent.createChooser(intent,"Send via"))
        }
        btnOpenGalery.setOnClickListener {
            val intent=Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent,"choose one"),CHOOSE_IMAGE_CODE)
        }
        btnShareImage.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND)
            intent.type="image/*"
            var file = BitmapFactory.decodeResource(resources,R.drawable.gul)
            intent.action=Intent.ACTION_SEND
            var path = MediaStore.Images.Media.insertImage(contentResolver,file,"title","share")
            var uri = Uri.parse(path)
            intent.putExtra(Intent.EXTRA_STREAM,uri)
            intent.type="image/*"
            startActivity(Intent.createChooser(intent,"mavsumbek"))

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==CHOOSE_IMAGE_CODE && resultCode== RESULT_OK){
            val uri=data?.data
            imgPicture.setImageURI(uri)
        }

    }
}