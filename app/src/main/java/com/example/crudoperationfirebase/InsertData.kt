package com.example.crudoperationfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertData : AppCompatActivity() {
    private lateinit var etEmpName: TextInputEditText
    private lateinit var etEmpAge: TextInputEditText
    private lateinit var etEmpSalary: TextInputEditText
    private lateinit var btnSaveData : Button

    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)

        etEmpName = findViewById(R.id.etEmpName)
        etEmpAge = findViewById(R.id.etEmpAge)
        etEmpSalary = findViewById(R.id.etEmpSalary)
         btnSaveData = findViewById(R.id.btnSave)


        database = FirebaseDatabase.getInstance().getReference("Employees")
        btnSaveData.setOnClickListener {
            savedEmployeeData()
        }
    }

    private fun savedEmployeeData() {
        //getings Values

        val empName = etEmpName.text.toString()
        val empAge = etEmpAge.text.toString()
        val empSalary = etEmpSalary.text.toString()
        if (empName.isEmpty()) {
            etEmpName.error = "Please enter name"
        }
        if (empAge.isEmpty()) {
            etEmpAge.error = "Please enter age"
        }
        if (empSalary.isEmpty()) {
            etEmpSalary.error = "Please enter salary"
        }

        val empId = database.push().key!!

        val employee = EmployeeModal(empId, empName, empAge, empSalary)

        database.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etEmpName.text?.clear()
                etEmpAge.text?.clear()
                etEmpSalary.text?.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}