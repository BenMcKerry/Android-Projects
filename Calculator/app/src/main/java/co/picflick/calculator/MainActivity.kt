
package co.picflick.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import co.picflick.calculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

private lateinit var mainBinding: ActivityMainBinding
private lateinit var inputText: TextView
class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = true
    var lastDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        inputText = mainBinding.inputText
        setContentView(mainBinding.root)
    }

    fun onDigit(view: View) {
        inputText.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        inputText.text = ""
        lastNumeric = true
        lastDecimal = false
    }

    fun onDecimal(view: View) {
        if (inputText.text == ""){
            inputText.append("0.")
            lastNumeric = false
            lastDecimal = true
        } else if (lastNumeric && !lastDecimal) {
            inputText.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isAdded(inputText.text.toString())) {
            inputText.append((view as Button).text)
        }
    }

    fun onEquals(view: View) {
        if (lastNumeric) {
            var textValue = inputText.text.toString()
            var prefix = ""
            try {
                if (textValue.startsWith("-")) {
                    prefix = "-"
                    textValue = textValue.substring(1)
                }
                if (textValue.contains("-")) {
                    val splitValue = textValue.split("-")
                    var first = splitValue[0]
                    var second = splitValue[1]

                    if (!prefix.isEmpty()) {
                        first = prefix + first
                    }
                    inputText.text = stripTrailingZeros((first.toDouble() - second.toDouble()).toString())
                } else if (textValue.contains("+")) {
                    val splitValue = textValue.split("+")
                    var first = splitValue[0]
                    var second = splitValue[1]

                    if (!prefix.isEmpty()) {
                        first = prefix + first
                    }
                    inputText.text = (first.toDouble() + second.toDouble()).toString()
                } else if (textValue.contains("*")) {
                    val splitValue = textValue.split("*")
                    var first = splitValue[0]
                    var second = splitValue[1]

                    if (!prefix.isEmpty()) {
                        first = prefix + first
                    }
                    inputText.text = stripTrailingZeros((first.toDouble() * second.toDouble()).toString())
                } else if (textValue.contains("/")) {
                    val splitValue = textValue.split("/")
                    var first = splitValue[0]
                    var second = splitValue[1]

                    if (!prefix.isEmpty()) {
                        first = prefix + first
                    }
                    inputText.text = stripTrailingZeros((first.toDouble() / second.toDouble()).toString())
                }

                lastNumeric = true
                lastDecimal = false
            } catch (e: ArithmeticException) {
                inputText.text = "!ERR"
            }
        }
    }

    private fun isAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    private fun stripTrailingZeros(input: String): String {
        var result = input
        while (result.last() == '0' || result.last() == '.') {
            result = result.subSequence(0, result.length-1) as String
        }
        return result
    }

    private fun testToast() {
        Toast.makeText(this, "workinonit", Toast.LENGTH_SHORT).show()
    }

}