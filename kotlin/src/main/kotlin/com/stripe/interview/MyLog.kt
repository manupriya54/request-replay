package com.stripe.interview

data class MyResponse(
    val body: String,
    val code: Long

)

data class MyRequest(
    val url: String,
    val header: Map<String, String>,
    val body: String,
    val method: String
)

data class MyLog(
    val request: MyRequest,
    val response: MyResponse
)