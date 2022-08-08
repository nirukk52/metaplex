package com.ql.giantbomb.utils

//
//import androidx.test.platform.app.InstrumentationRegistry
//import com.ql.giantbomb.MyTestApplication
//import java.io.IOException
//import java.io.InputStreamReader
//
//object ResponseFileReader {
//    fun readStringFromFile(fileName: String): String {
//        try {
//            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
//                .applicationContext as MyTestApplication).assets.open(fileName)
//            val builder = StringBuilder()
//            val reader = InputStreamReader(inputStream, "UTF-8")
//            reader.readLines().forEach {
//                builder.append(it)
//            }
//            return builder.toString()
//        } catch (e: IOException) {
//            throw e
//        }
//    }
//}