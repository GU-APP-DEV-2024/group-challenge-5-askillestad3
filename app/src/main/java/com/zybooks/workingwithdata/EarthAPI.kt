package com.zybooks.workingwithdata

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

//const val TAG = "EARTH_API"

class EarthAPI : AppCompatActivity() {
    lateinit var startDateTextView: TextView
    lateinit var startDateEditText: EditText
    lateinit var endDateTextView: TextView
    lateinit var endDateEditText: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var imageDataSet: ArrayList<ImageData>
    lateinit var imageCustomAdapter: ImageCustomAdapter
    lateinit var countEditText: EditText

    data class ImageData(val url: String, val description: String = "", val date: String = "") {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_earth_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Super Cool and Interesting Earth Photos!"


        startDateTextView = findViewById(R.id.dateTextView)
        startDateEditText = findViewById(R.id.dateEditText)
        startDateEditText.doAfterTextChanged {
            countEditText.isEnabled = startDateEditText.text.isEmpty()
        }

        endDateTextView = findViewById(R.id.endDateTextView)
        endDateEditText = findViewById(R.id.endDateEditText)
        endDateEditText.doAfterTextChanged {
            countEditText.isEnabled = endDateEditText.text.isEmpty()
        }

        countEditText = findViewById(R.id.countEditText)
        countEditText.doAfterTextChanged {
            startDateEditText.isEnabled = countEditText.text.isEmpty()
            endDateEditText.isEnabled = countEditText.text.isEmpty()
        }

        val rangeCheckBox: CheckBox = findViewById(R.id.rangeCheckBox)
        rangeCheckBox.setOnClickListener {
            if (rangeCheckBox.isChecked ) {
                endDateTextView.visibility = View.VISIBLE
                endDateEditText.visibility = View.VISIBLE
                startDateTextView.text = getString(R.string.start)
            } else {
                endDateTextView.visibility = View.INVISIBLE
                endDateEditText.visibility = View.INVISIBLE
                startDateTextView.text = getString(R.string.date)

            }
        }

        imageDataSet = arrayListOf(
            com.zybooks.workingwithdata.EarthAPI.ImageData(
                "https://apod.nasa.gov/apod/image/1908/EtnaCloudsMoon_Giannobile_960.jpg",
                "What's happening above that volcano? Although Mount Etna is seen erupting, the clouds are not related to the eruption. They are lenticular clouds formed when moist air is forced upwards near a mountain or volcano.  The surreal scene was captured by chance late last month when the astrophotographer went to Mount Etna, a UNESCO World Heritage Site in Sicily, Italy, to photograph the conjunction between the Moon and the star Aldebaran. The Moon appears in a bright crescent phase, illuminating an edge of the lower lenticular cloud.  Red hot lava flows on the right.  Besides some breathtaking stills, a companion time-lapse video of the scene shows the lenticular clouds forming and wavering as stars trail far in the distance.    Follow APOD in English on: Instagram, Facebook,  Reddit, or Twitter"
            ),
            com.zybooks.workingwithdata.EarthAPI.ImageData(
                "https://img.youtube.com/vi/f8rs3bcEO-o/0.jpg",
                "What would it look like to orbit a black hole? Many black holes are surrounded by swirling pools of gas known as accretion disks. These disks can be extremely hot, and much of the orbiting gas will eventually fall through the black hole's event horizon -- where it will never be seen again. The featured animation is an artist's rendering of the curious disk spiraling around the supermassive black hole at the center of spiral galaxy NGC 3147.  Gas at the inner edge of this disk is so close to the black hole that it moves unusually fast -- at 10 percent of the speed of light. Gas this fast shows relativistic beaming, making the side of the disk heading toward us appear significantly brighter than the side moving away.  The animation is based on images of NGC 3147 made recently with the Hubble Space Telescope.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library"
            )
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = imageCustomAdapter
    }
}