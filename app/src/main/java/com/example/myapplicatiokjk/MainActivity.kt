package com.example.myapplicatiokjk


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var countnumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write a message to the database
        val database = Firebase.database
        val myReference = database.getReference()

        save.setOnClickListener {
            var nameperson = PersonName.text.toString()
            var idperson = PersonID.text.toString()
            var ageperson = PersonAge.text.toString()

            val person = hashMapOf(
                "name" to "$nameperson",
                "id" to "$idperson",
                "age" to "$ageperson"
            )

            myReference.child("person").child("$countnumber").setValue(person)
            countnumber++
            Toast.makeText(applicationContext, "SuccessData", Toast.LENGTH_SHORT).show()
        }

        get.setOnClickListener {
            // Read from the database
            myReference.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue()
                    textData.text = value.toString()
                    Toast.makeText(applicationContext, "SuccessData", Toast.LENGTH_SHORT).show()
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "ErrorData", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }
}