package com.example.crudoperationfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.core.view.View as View1

class fetchingData : AppCompatActivity() {

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var empList: ArrayList<EmployeeModal>
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching_data)

        empRecyclerView = findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        empList = arrayListOf<EmployeeModal>()

        getEmployeesData()
    }

    private fun getEmployeesData() {
            empRecyclerView.visibility = GONE
            tvLoadingData.visibility = View.VISIBLE

            database = FirebaseDatabase.getInstance().getReference("Employees")

            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                  if (snapshot.exists()) {
                      for (empSnap in snapshot.children) {
                          val empData = empSnap.getValue(EmployeeModal::class.java)
                          empList.add(empData!!)
                      }


                      val mAdapter = EmpAdapter(empList)
                      empRecyclerView.adapter = mAdapter

                      mAdapter.setOnItemClickListener(object : EmpAdapter.onItemClickListener{
                          override fun onItemClick(position: Int) {

                              val intent = Intent(this@fetchingData, EmployeeDetails::class.java)

                              //put extras
                              intent.putExtra("empId", empList[position].empId)
                              intent.putExtra("empName", empList[position].empName)
                              intent.putExtra("empAge", empList[position].empAge)
                              intent.putExtra("empSalary", empList[position].empSalary)
                              startActivity(intent)
                          }

                      })

                      empRecyclerView.visibility = View.VISIBLE
                      tvLoadingData.visibility = GONE
                  }

                  }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")

                }
            })






        }
    }

