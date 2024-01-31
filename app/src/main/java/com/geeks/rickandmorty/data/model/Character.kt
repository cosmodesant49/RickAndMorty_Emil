package com.geeks.rickandmorty.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*data class BaseResponse<T>(
    var results: List<T>
) : Serializable*/
data class CharacterResponse(
    var results: List<Character>
) : Serializable

data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var image: String,
    var species: String,
    var origin: Origin,
    var location: Location,
) : Serializable

data class Origin(
    var name: String
) : Serializable

data class Location(
    var name: String
) : Serializable
