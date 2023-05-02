package com.example.handycraftlk

import SessionManager
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handycraftlk.databinding.ActivityAccountInformationUpdateBinding
import com.google.firebase.database.*

class AccountInformationUpdate : AppCompatActivity(){

    private lateinit var binding: ActivityAccountInformationUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        binding = ActivityAccountInformationUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sessionManager = SessionManager(this)

        if (!sessionManager.isLoggedIn()) {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }

        val userEmail = sessionManager.getEmail()
        database = FirebaseDatabase.getInstance().reference

        val userRef = database.child("Users")
        val query = userRef.orderByChild("email").equalTo(userEmail)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.value as HashMap<String, String>
                        val name = user["name"]
                        val email = user["email"]
                        val phoneNumber = user["phoneNumber"]
                        val id = user["id"]
                        val role = user["role"]
                        binding.tvNameTop.text = name
                        binding.tvEmailTop.text = email
                        binding.tvNameAcc.setText(name)
                        binding.tvEmailAc.text = email
                        binding.tvPhone.setText(phoneNumber)
                        binding.tvId.text = id
                        binding.tvRole.text = role

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(ContentValues.TAG, "Failed to read user details", error.toException())
            }
        })


        binding.btnDone.setOnClickListener {
            val name = binding.tvNameAcc.text.toString()
            val phoneNumber = binding.tvPhone.text.toString()

            val query = userRef.orderByChild("email").equalTo(userEmail)

            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.value as HashMap<String, String>
                            user["name"] = name
                            user["phoneNumber"] = phoneNumber
                            userRef.child(userSnapshot.key!!).setValue(user)
                        }
                        // Update the UI with the new values
                        binding.tvNameAcc.setText(name)
                        binding.tvPhone.setText(phoneNumber)


                        // Show a success message
                        Toast.makeText(
                            this@AccountInformationUpdate,
                            "User details updated",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(ContentValues.TAG, "Failed to update user details", error.toException())
                    // Show an error message
                    Toast.makeText(
                        this@AccountInformationUpdate,
                        "Failed to update user details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            val intent = Intent(this, AccInfomation::class.java)
            startActivity(intent)
        }

        binding.btnBackEditAccount.setOnClickListener{
            val intent = Intent(this, AccInfomation::class.java)
            startActivity(intent)
        }
    }

}