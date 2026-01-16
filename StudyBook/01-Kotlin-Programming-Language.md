プログラミング言語 Kotlin
今回から、自分でコードを書いてアプリの振る舞いを作る方法を学びます。

Android アプリのネイティブ開発では Kotlin 言語を使います。まずはこの Kotlin 言語の基本について、Web アプリケーション開発コースで習った JavaScript と比較しながら見ていきましょう。

目次
【講義】Kotlin 言語とは
【実習】Kotlin で書かれたひな形を見てみよう
【講義】Kotlin 文法の基本
【実習】ひな形にロジックを追加してみよう
【講義】Kotlin 言語とは
Android アプリのネイティブ開発に用いられるプログラミング言語 Kotlin は、2011 年に JetBrains が公開しました。Kotlin は Java をベースにしつつ、より簡潔で安全に書けるように作られています。

詳しくはこれから説明してきますが、Kotlin は静的型付けのオブジェクト指向プログラミング言語となっています。この特徴により、比較的高速で堅牢なプログラムを作成することに向いており、Android アプリだけでなくウェブアプリケーションのサーバサイド言語として採用されることもあります。

【実習】Kotlin で書かれたひな形を見てみよう
実際に Android Studio で Kotlin のソースコードを見てみましょう。Android Studio を起動し、前回までで作成した My Application プロジェクトを開きましょう。

プロジェクトを開いたら、Project ツールウィンドウを使って app/java/com.example.myapplication/MainActivity.kt を探し、ダブルクリックで開きましょう。

MainActivity.kt が表示された

エディタウィンドウに、上記のようなソースコードが表示されます。

Android Studio のエディタには、ソースコードを解析して意味的なまとまりで折りたたみを行う機能（code folding）があり、その機能でコードが折りたたまれていることがあります。その場合、コードの左側にある小さな「+」をクリックすることで、折りたたまれた中身を展開できます。

「+」で折りたたまれている

ソースコードの全体は、次のようになっています。

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

Please Type!
いきなりすべてを理解するのは難しいので、上から順に見ていきます。Web アプリケーション開発コースの JavaScript で似たような機能は習っているものが多いので、それを思い出してください。

package com.example.myapplication

Kotlin には「パッケージ」と呼ばれる、ソースコードの場所を示す仕組みがあります。これにより、他のコードにアクセスしたり、逆にアクセスされたりできます。

この package で始まる書き方は「パッケージ宣言」といい、このファイルは com.example.myapplication という名前のパッケージに所属する、と決めています。My Application のソースコードは、とりあえず全てこのパッケージに含めておけば大丈夫です。

import android.os.Bundle
import androidx.activity.ComponentActivity
...

これらの import で始まる行は「インポート宣言」といいます。import を記述することで、指定したパッケージのコードを使用できるようになります。

Node.js を使った JavaScript でも require 関数を使って、別のモジュールを読み込み、変数や定数に入れるということを行いました。「別のファイルに書かれている処理を利用するための方法」である点が似ていますね。

ただし Kotlin では、変数や定数を経由するのではなく「宣言」をすることで、宣言したファイル内全体から別パッケージの部品が使用できるようになるという違いがあります1。

class MainActivity : ComponentActivity() {
    ...
}

この部分では「クラス」を作っています。ここでは MainActivity という名前のクラスを定義しています。クラスを定義することで、import すれば他のファイルからも利用できるようになります。

import で使えるコードには、クラス以外にもいくつかありますが、詳しくは次回解説します。

後半の : ComponentActivity() の部分では継承という機能を使っています。一から自分で様々な機能を作るのは大変なので、あらかじめ用意されているクラスを継承して、そのクラスが持っている機能を使えるようにしています。ComponentActivity は、インポート宣言により使えるようになった別のパッケージにあるクラスですね。

ここで、Ctrl キー（Mac では ⌘ キー）を押しながら、ソースコード中の ComponentActivity という文字の部分をクリックしてみましょう。すると、エディタウィンドウに新しいタブで、ComponentActivity.java が開かれます。

ComponentActivity.java が表示された

このファイルは Android SDK により用意されたファイルです。大きなファイルですが、パッケージ宣言やインポート宣言があり、ComponentActivity クラスが定義されていることが読み取れます。

確認ができたら、エディタウィンドウ上部のタブの「×」ボタンをクリックして、ComponentActivity.java を閉じましょう。元の MainActivity.kt に戻ってきます。

このように、Android Studio の機能を使って、関係しているクラスの情報を手軽に調べることができます。

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

次に、MainActivity クラスの中身は、上記のようになっています。onCreate という名前の関数を実装しています。

JavaScript と違い、「型」の情報も書いているので少し長くなっていますが、

function onCreate(savedInstanceState) {
    ...
}

という JavaScript の関数定義の形と基本的な読み方は変わりません。丸括弧 () の中に引数を書き、波括弧 {} の中に、その関数が呼ばれた際の処理内容を書きます。

なお、この onCreate は、このアクティビティ（画面）が生成されたときにシステム側から呼ばれるものです。そのときの処理内容が次の行ですね。

    super.onCreate(savedInstanceState)
    setContent {

どちらも、関数呼び出しをしています。super というのは継承元を指しますので、super.onCreate(savedInstanceState) というのは、継承している ComponentActivity の onCreate() を呼び出しているということです。

また setContent {} では、実際に画面に表示する内容を設定しています。この中身の実装については、あとで画面のデザインをしていくときに改めて説明します。

まとめましょう。

各ファイルは、パッケージ宣言によりパッケージに所属させることができる
各ファイルでインポート宣言をすれば、別パッケージのクラスを使うことができる
JavaScript と同じように関数を定義し、そこに処理内容を記述することができる
1. 厳密に言えば、限定名と呼ばれる、パッケージ名とクラス名を完全に書く書き方をすれば、import 宣言を行わなくても利用は可能です。たとえば androidx.activity.ComponentActivity のような長い名前を、使いたい場所にその都度書くということです。import 宣言は、単純名と呼ばれる、クラス名だけによるシンプルな書き方をするためのものなのです。
↩
【講義】Kotlin 文法の基本
JavaScript と Kotlin はどちらも手続き型言語であり、使われるキーワードなど、表面的な文法には似ている部分もあります。そのため Web アプリケーション開発コースで JavaScript を習得していれば、変数・値・制御構文・数など、基本的な考え方は JavaScript で学んだことが応用できます。

ただし、静的型付けという型の情報を明記するという文法上の違いなどはありますので、ここで少し文法を確認しておきましょう。

変数、値、そして型
JavaScript において、値にはいくつか種類があったのを覚えているでしょうか。

数値
文字列
真偽値
オブジェクト
のようなものがありました。Kotlin にも同様に、色々な値があります。そして Kotlin には、これらの「値の種類」を明確に区別するための、「型」という概念があります。

まず、整数を扱う型には、以下のようなものがあります。

型の名前	サイズ	値の範囲
Byte	1 バイト	-128 ~ 127
Short	2 バイト	-32768 ~ 32767
Int	4 バイト	-2147483648 ~ 2147483647
Long	8 バイト	-9223372036854775808 ~ 9223372036854775807
JavaScrpt では小さな数値を入れる場合でも大きな数値を入れる場合でも、特に意識せず let と書いて変数を宣言していましたが、Kotlin では変数宣言をするときに型を意識する必要があります。

変数の宣言は、基本的に JavaScript と同じようにできます。

let x = 100;

と書いていたのを、Kotlin では、

var x = 100

と書くことができます。ただし、Kotlin のほうの x には、自動的に Int という型がついています。これは「型推論」という Kotlin の機能になります。

ここで x は Int という型になっているので、x に 4 バイトを超える値を入れることはできません。

var x = 100
x = 10000000000 // エラー

もし x に 4 バイトを超えるような大きな値を入れる可能性がある場合は、自分で Long という型を明記しましょう。

var x: Long = 100
x = 10000000000 // OK

このように、とても大きい値を使うときには自分で型をつける必要がありますが、基本は Kotlin の型推論に任せてしまって良いでしょう。Short や Byte も、実際にはあまり使うことはありません。

: var と val
Kotlin では変数を宣言するとき、先頭に var または val をつけます。両者の違いは、var で宣言した変数は再代入できますが、val で宣言した変数は再代入することができません。


使い分けについては、基本的に val を使うようにしましょう。var で定義された変数はどこかで変更されている可能性があるため、想定していない値になっているリスクがあります。var で定義した変数に値を変更するよりは、その都度 val で変数を定義したほうがリスクを低く抑えられます。

Kotlin で小数を扱う場合には、浮動小数点数を表す型を使う必要があります。

型の名前	サイズ
Float	4 バイト
Double	8 バイト
Float と Double どちらも同じように使えますが、Float よりも Double の方が、より巨大な数、より細かい数を表現できます。

JavaScript では扱う数値が整数なのか小数なのかは区別しないで良かったのですが、Kotlin ではこれも明確に区別し、型で宣言する必要があるわけです。

val x: Int = 0.3   // これはエラー
val y: Float = 0.3 // Float になるので OK
val z = 0.3 // 型推論で自動的に Double になる

また、真偽値を表現するためには Boolean 型を使います。値としては true と false がある、というのは JavaScript と同様です。

val flag = false // 型推論で自動的に Boolean 型になる

文字列は String 型になります。文字列をソースコード中に記述するときには、ダブルクオーテーション " " を使わなければなりません。

Web アプリケーション開発コースにおける JavaScript では主にシングルクオーテーション ' ' を使ってきましたが、Kotlin におけるシングルクオーテーションは文字列（String 型）ではなく、文字（Char 型）という別のものを表現することになっているためです。

val message = "Hello" // 型推論で自動的に String 型になる

以上のように、値や変数を扱うときには、「型は何か」ということを意識する必要があります。

はじめは少し面倒に感じるかもしれませんが、型があることで間違いが防げたり、ソースコードが読みやすくなったりする側面もありますので慣れていきましょう。

演算子
JavaScript と同様、算術演算子や論理演算子、比較演算子などが使えます。

算術演算子:

演算子	
+	足し算
-	引き算
*	掛け算
/	割り算
%	割り算の余り
論理演算子:

演算子	
&&	論理積（かつ）
||	論理和（または）
!	否定（ではない）
比較演算子:

演算子	
<	より小さい（未満）
>	より大きい（超過）
<=	以下
>=	以上
==	等しい
!=	等しくない
関数定義
fun sum(a: Int, b: Int): Int {
    return a + b
}

JavaScript で function と書いていた場所には fun と書きます。また、関数が返す型を {} の前に定義します。上記の関数では、引数 a と b を足し算した結果を返すので、返り値の型が Int と定義されています。関数が何も値を返さない場合は返り値の型を書かなくて大丈夫です。

型の情報は、引数のところにも出てきます。a: Int, b: Int という書き方で、a という引数は Int 型である必要がある、ということを宣言しています。他の型の値や変数を引数にしてこの関数 sum を呼び出すコードを書いていると、ビルドの段階でエラーになります。

このように、型の情報を使ってプログラミングを進めることで、エラーの検出を早期に行うことができます。

制御構文
制御構文は、ほぼ JavaScript と同じです。if 文で、条件分岐を行います。else をつなげられる点も変わりません。

val x = 10

if (x < 5) {
    // 条件は真
} else {
    // 条件は偽
}

for 文や while 文で、ループを行うことができます。

var x = 0

for (i in 0..10) {
    x += i
}

while (true) {
    // 無限ループ
}

なお、JavaScript と同様に continue 文で今回のループ処理を終えて次回ループへ移ったり、break 文でループを強制的に抜けたりできます。

以上で確認した基本文法については、今後特に説明をせずに進めますので、わからないところがあれば Web アプリケーション開発コースで復習をするようにしましょう。

ここで説明をしていない Kotlin 言語の文法については、その文法が出てきたときに、詳しい説明をしていきます。

【実習】ひな形にロジックを追加してみよう
実際に、My Application の MainActivity.kt にコードを追加してみましょう。まず、次のように Greeting() 関数の中を修正してください。

@Composable
fun Greeting(name: String) {
    val message = "タイトル"
    Text(
        text = message,
    )
}

message という String 型の変数を用意して、"タイトル" という文字列を代入しています。その後、Text(message) という関数の呼び出しをしています。

編集ができたら、デザインタブの実行ボタンを押しましょう。Android Studio のバージョンによっては、コードの変更時点で自動的にプレビューが再レンダリングされる場合があります。次のような結果になったでしょうか。

「タイトル」という文字列が表示された

Text(message) により、アプリ上部の内容を指定した文字列「タイトル」に変えることができました。今度は、少し計算をするような処理に変えてみましょう。

@Composable
fun Greeting(name: String) {
    var product: Long = 1
    for (i in 1..20) {
        product *= i
    }
    Text(
        text = product.toString(),
    )
}

上記の product *= i という表記は、product = product * i という表記の省略形です。*= は右辺を左辺に掛け算するという意味になります。

デザインタブの実行ボタンを押すと、以下のようなプレビューが表示されたでしょうか。タイトル部分に、20 の階乗（1 から 20 までを全てかける）の計算結果が表示されています。

20 の階乗が表示された

このコードのポイントは 2 点あります。

1 つめは変数 product の型として大きな数が扱える Long 型を使っていることです。product の値は非常に大きな数になり、Int 型の範囲には収まらないため、このようにする必要があります。

試しに product の型を宣言しないと、どういう結果になるか見てみましょう。以下のように表示されたでしょうか。

-2102132736 が表示された

先ほどとは違う、誤った結果が表示されてしまいました。Int で表現できる値の上限を超えたために、このような不具合が起こっています。このように、Kotlin におけるプログラミングで数値を扱う場合は、値の範囲を気にする必要があるのです。

もう 1 つのポイントも型が関係します。text の引数に文字列型を渡すようにしている点が重要です。

型の扱いがあいまいな JavaScript の感覚では、

text = product

と書いてしまえばいいように思いますが、Kotlin ではこれはエラーになります。なぜならば、この text という引数は「文字列として表示できる型でないといけない」と定義されているからです。product は Long 型、つまり整数ですので、文字列ではないのです。

Long を文字列型に変換するために .toString() という関数を使っています。この関数により 2432902008176640000 という数値が "2432902008176640000" という文字列になり、無事 text に代入できます。

仕上げに、関数を作る練習として、階乗を計算する処理を別の関数に分けてみましょう。次のように calculateFactorial 関数を新しく実装します。記述する場所は Greeting() の中にしましょう。

fun Greeting(name: String, modifier: Modifier = Modifier) {
   fun calculateFactorial(n: Int): Long {
       var product: Long = 1
       for (i in 1..n) {
           product *= i
       }
       return product
   }

calculateFactorial では Int 型の引数を 1 つ受け取り、その階乗を計算して Long 型の整数値で返します。この関数を呼び出す側のコードは、calculateFactorial の下に次のように変更します。

fun Greeting(name: String, modifier: Modifier = Modifier) {
    fun calculateFactorial(n: Int): Long {
     // 省略
    }
    val factorial = calculateFactorial(20)
    Text(
        text = factorial.toString(),

なお、 val factorial = calculateFactorial(20) と入力した際に、括弧の中に n: という表示が現れると思います。これは Android Studio が calculateFactorial 関数内の引数名 n を読み取って親切に表示しているものになります。

引数名 n が表示された

修正できたら、デザインタブでプレビューしてみましょう。同じように 2432902008176640000 という数値が表示されていたら成功です。

引数名 n が表示された

まとめ
Kotlin 言語は、静的型付けのオブジェクト指向言語
Kotlin 言語の基本文法は、JavaScript の応用で理解できる
Kotlin 言語で書いた内容をそのままプレビューで確認できる
挑戦
初級
プレビューに 10 の階乗の計算結果を表示されるようにしてください。

上級
消費税 10% の税込価格を計算する関数 calculateTaxIncludedPrice を作成して、498 円の税込価格をプレビューに表示してください。税込価格なので、小数点以下は切り上げて整数で表示してください。切り上げは ceil() という関数で行うことができます。

挑戦の解答例
答えを隠す

初級
今回の講義で作成した calculateFactorial() に 10 を代入してみましょう。プレビューに 3628800 が表示されていれば正解です。

10 の階乗の答え

上級
消費税 10% の税込価格を計算する関数 calculateTaxIncludedPrice() を作成しましょう。ここでは元々の金額を 1.1 倍することで税込価格を求めます。

fun calculateTaxIncludedPrice(price: Int): Int {
    val multipliedPrice = price * 1.1
    return ceil(multipliedPrice).toInt()
}

この関数のポイントは、ceil で小数点以下を切り上げてから、toInt() で整数に変換していることです。そして、この関数に 498 を代入して得られた結果をプレビューに表示すれば OK です。

498 円の切り上げ税込価格の答え

お疲れさまでした！
学習したことをSNSで報告しよう！

