package com.example.juricass.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://sandbox-api.piste.gouv.fr"//"https://api.piste.gouv.fr"
private const  val API_KEY = "a453fd66-7f55-48b9-bd2f-fda4d6666392"//a453fd66-7f55-48b9-bd2f-fda4d6666392

private val client = OkHttpClient.Builder()
    .addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("KeyId", API_KEY)
                .build()
            return chain.proceed(request)
        }
    })
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .addCallAdapterFactory(ResultCallAdapterFactory())
    .build()

interface JudilibreApiService {
    @GET("cassation/judilibre/v1.0/healthcheck")
    suspend fun healthcheck(): Result<String>
}

object JudilibreApi {
    val retrofitService: JudilibreApiService by lazy {
        retrofit.create(JudilibreApiService::class.java)
    }
}

