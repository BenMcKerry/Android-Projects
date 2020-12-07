package co.picflick.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import co.picflick.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


private lateinit var mainBinding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.ageButton.setOnClickListener {view ->
            clickAgeButton(view)
        }
    }

    private fun clickAgeButton(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)

        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDate ->
            val dateString =  "$selectedDate/${selectedMonth+1}/$selectedYear"
            mainBinding.dateText.text = dateString

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA)
            val formattedDate = sdf.parse(dateString)

            var dateInMinutes: Long = 0
            if (formattedDate != null) {
                dateInMinutes = formattedDate.time / 60000
            }

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            var currentDateInMinutes: Long = 0
            if (currentDate != null) {
                currentDateInMinutes = currentDate.time / 60000
            }

            val differenceInMinutes = currentDateInMinutes - dateInMinutes
            mainBinding.minutesText.text = differenceInMinutes.toString()
        }, year, month, date)
        
        datePicker.datePicker.maxDate = Date().time - 86400000
        datePicker.show()
    }

    fun testToast() {
        Toast.makeText(this,"workinonit",Toast.LENGTH_SHORT).show()
    }
}