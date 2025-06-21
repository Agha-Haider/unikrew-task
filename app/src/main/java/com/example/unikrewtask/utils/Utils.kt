package com.example.unikrewtask.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {


    fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }


    }
