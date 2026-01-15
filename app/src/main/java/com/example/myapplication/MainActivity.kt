package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Catクラスをインスタンス化する
    val tama = Cat(name = "タマ", age = 3, gender = "オス", breed = "三毛猫")
    val kuro = Cat(name = "クロ", age = 1, gender = "メス", breed = "黒猫")

    tama.say("${tama.name}は${kuro.gender}だにゃ")
    kuro.say("${kuro.name}は${kuro.gender}だにゃ")

    tama.sleep()
    kuro.say("寝てるし...")
    kuro.sleep()

    fun calculateFactorial(n: Int): Long {
        var product: Long = 1
        for (i in 1..n) {
            product *= i
        }
        return product
    }
    val factorial = calculateFactorial(20)
    //  val message = "タイトル"
    //  var product: Long = 1 // productの数が大きくなるため、Long型を使う
    //  for (i in 1..20) {
    //      product *= i
    //  }
    Text(
        text = factorial.toString(),
        //  text = message,
        //  text = product.toString(), // textはstring型のため、toStringでLong型からString型に変換している
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}