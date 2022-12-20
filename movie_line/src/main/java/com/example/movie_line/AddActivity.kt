package com.example.movie_line

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.util.IntentUtils
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.content_camera_only.*
import kotlinx.android.synthetic.main.content_gallery_only.*
import java.util.*

class AddActivity : AppCompatActivity() {
    companion object {
        private const val GALLERY_IMAGE_REQ_CODE = 102
    }

    private var mGalleryUri: Uri? = null

    lateinit var etMovieName: EditText
    lateinit var etLine: EditText
    lateinit var etMovieYear: EditText

    // for hints
    lateinit var movie_name_string: String
    lateinit var line_string: String
    lateinit var movie_year_string: String

    lateinit var imageName: String

    // for collection
    lateinit var movieName: String
    lateinit var movieLine: String
    lateinit var selectedMovieType: String

    val MIN_PASSWORD_LENGTH = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewInitializations()


    }

    fun viewInitializations() {
        etMovieName = findViewById(R.id.et_movie_name)
        etLine = findViewById(R.id.et_line)
        etMovieYear = findViewById(R.id.et_movie_year)

        //for text hints
        movie_name_string = getString(R.string.movie_name)
        line_string = getString(R.string.input_movie_line)
        movie_year_string = getString(R.string.input_movie_year)
        hideHint()

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideHint() {
        if (etMovieName.text!!.isNotEmpty()){ etMovieName.hint="" } else etMovieName.hint = movie_name_string
        if (etLine.text!!.isNotEmpty()){etLine.hint=""} else etLine.hint = line_string
        if (etMovieYear.text!!.isNotEmpty()){etMovieYear.hint=""} else etMovieYear.hint = movie_year_string
    }

    @Suppress("UNUSED_PARAMETER")
    fun pickGalleryImage(view: View) {
        ImagePicker.with(this)
            // Crop Image(User can choose Aspect Ratio)
            .crop()
            // User can only select image from Gallery
            .galleryOnly()

            .galleryMimeTypes( // no gif images at all
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            // Image resolution will be less than 1080 x 1920
            .maxResultSize(1080, 1920)
            // .saveDir(getExternalFilesDir(null)!!)
            .start(AddPhotoSampleActivity.GALLERY_IMAGE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // Uri object will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            when (requestCode) {

                AddPhotoSampleActivity.GALLERY_IMAGE_REQ_CODE -> {
                    mGalleryUri = uri
                    imgGallery.setLocalImage(uri)
                }

            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dialog(view: View, text: String) {
        AlertDialog.Builder(view.context).apply {
            setTitle("")
            setMessage(text)
            setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            })
            show()
        }
    }

    fun showImageCode(view: View) {
        val resource = when (view) {
            imgCameraCode -> R.drawable.img_camera_code
            else -> 0
        }
        ImageViewerDialog.newInstance(resource).show(supportFragmentManager, "")
    }

    fun showImage(view: View) {
        val uri = when (view) {
            imgGallery -> mGalleryUri
            else -> null
        }

        uri?.let {
            startActivity(IntentUtils.getUriViewIntent(this, uri))
        }
    }

    private fun uploadImageToFirebase(fileUri: Uri) {
        val fileName = UUID.randomUUID().toString() +".jpg"

        imageName = fileName

        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

        refStorage.putFile(fileUri)
            .addOnSuccessListener(
                OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        val imageUrl = it.toString()
                    }
                })

            ?.addOnFailureListener(OnFailureListener { e ->
                print(e.message)
            })
    }

    fun saveCollectionToFirebaseDB (view: View, movieName: String, movieLine: String, movieYear: String) {
        val db = Firebase.firestore
        val movieYearNum = movieYear.toInt()
        if (movieName.isNotEmpty()) {
            val user = hashMapOf(
                "movie_name" to movieName,
                "year" to movieYearNum,
                "movie_line" to movieLine,
                "imageName" to imageName
            )

            db.collection("movieLinesCollection")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("firestore", "Error adding document", e)
                }

            Toast.makeText(this, "Line was successfully saved to DB.",Toast.LENGTH_SHORT).show()

            goToMain()

        } else {
            dialog(view, "Error. Line was not saved")
        }
    }

    fun goToMain() {
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun performSaveLine (view: View) {
        if(etMovieName.text.isEmpty()) {
            dialog(view, getString(R.string.no_movie_name))
        } else {
            if (etLine.text.isEmpty()) {
                dialog(view, getString(R.string.no_movie_line_text))
            } else {
                if (etMovieYear.text.isEmpty()) {
                    dialog(view, getString(R.string.no_movie_year))
                } else {
                    if (mGalleryUri == null) {
                        dialog(view, getString(R.string.no_movie_image))
                    } else {
                        uploadImageToFirebase(mGalleryUri!!)
                        saveCollectionToFirebaseDB(view, etMovieName.text.toString(), etLine.text.toString(), etMovieYear.text.toString())
                    }
                }
            }
        }
    }
}