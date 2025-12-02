package com.example.quicknotesplus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val smallTextSize = 14.sp
val mediumTextSize = 18.sp
val largeTextSize = 24.sp

val padding_6 = 6.dp
val padding_8 = 8.dp
val padding_10 = 10.dp
val padding_16 = 16.dp
val padding_20 = 20.dp
val padding_24 = 24.dp