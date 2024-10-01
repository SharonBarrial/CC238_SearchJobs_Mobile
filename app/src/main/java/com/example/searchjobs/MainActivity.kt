package com.example.searchjobs

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var jobs: List<Job>
    lateinit var jobAdapter: JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btSearch = findViewById<Button>(R.id.btSearch)
        btSearch.setOnClickListener {
            searchJob()
        }
    }

    private fun searchJob() {
        // Get the description from the input field
        val inputLayout = findViewById<TextInputLayout>(R.id.etDescription)
        val etDescription = inputLayout.editText as TextInputEditText

        val description = etDescription.text.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://test-api-maryjenn05s-projects.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create a service for the JobService interface
        val service = retrofit.create(JobService::class.java)

        val request = service.searchJob(description)

        request.enqueue(object : Callback<List<Job>> {
            override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                if (response.isSuccessful && response.body() != null) {
                    jobs = response.body()!!

                    // Asegúrate de que el RecyclerView y el Adapter estén configurados antes de usar el layoutManager
                    val rvJob = findViewById<RecyclerView>(R.id.rvJob)

                    // Inicializar y asignar el adapter
                    jobAdapter = JobAdapter(jobs)
                    rvJob.adapter = jobAdapter

                    // Asignar el layoutManager
                    rvJob.layoutManager = LinearLayoutManager(this@MainActivity)
                } else {
                    Log.d("MainActivity", "Respuesta no exitosa o cuerpo nulo.")
                }
            }



            override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                Log.d("MainActivity", t.toString())
                Toast.makeText(this@MainActivity, "Error fetching jobs", Toast.LENGTH_SHORT).show()
            }
        })

    }
}