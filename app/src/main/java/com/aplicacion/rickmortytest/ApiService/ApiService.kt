package com.aplicacion.rickmortytest.Service


import com.aplicacion.rickmortytest.Models.Characters
import com.aplicacion.rickmortytest.Models.CharactersResponse

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character/")
    suspend fun getCharacters():Response<CharactersResponse>


}