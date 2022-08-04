package com.example.scratchdemo2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.room.Room
import androidx.viewbinding.ViewBinding
import com.example.scratchdemo2.databinding.ActivityTask2Binding
import kotlinx.android.synthetic.main.activity_task2.*
import java.text.SimpleDateFormat
import java.util.*

const val DB_NAME = "todo.db"
class TaskActivity : AppCompatActivity(), View.OnClickListener {

    private val binding = ActivityTask2Binding.inflate(layoutInflater)

    lateinit var myCalendar: Calendar
    lateinit var dateSetListner: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    var finalDate = 0L
    var finalTime = 0L

    val db by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.dateEdt.setOnClickListener(this)
        binding.timeEdt.setOnClickListener(this)
        binding.btnsave?.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.dateEdt -> {
                setListner()
            }

            R.id.timeEdt -> {
                setTimeListner()
            }
        }
    }

    private fun setListner() {
        myCalendar = Calendar.getInstance()
        dateSetListner =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()
            }

        val datePickerDialog = DatePickerDialog(
            this, dateSetListner,myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

         private fun updateTime() {
            val myformat ="h:mm a"
            val sdf = SimpleDateFormat(myformat)
            timeEdt.setText(sdf.format(myCalendar.time))
            timeEdt.visibility = View.VISIBLE
        }

        private fun setTimeListner() {
            timeSetListener =
                TimePickerDialog.OnTimeSetListener() { _: TimePicker, hourOfDay: Int, min: Int ->
                    myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    myCalendar.set(Calendar.MINUTE, min)
                    updateTime()
                }
            val timePickerDialog = TimePickerDialog(
                this, timeSetListener, myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE), false,
            )
            timePickerDialog.show()
        }

    private fun updateDate() {
        val myformat ="EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myformat)
        dateEdt.setText(sdf.format(myCalendar.time))
        timeLay.visibility = View.VISIBLE
    }
    }


