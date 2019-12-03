package com.example.calendaraplication

class ContentDTO(var explain: String? = null,
                 var imageUrl : String? = null,
                 var uid : String? = null,
                 var userId : String? = null,
                 var timestamp : Long? = null,
                 var favoriteCount : Int = 0) {
    data class comment(
        var uid: String? = null,
        var userId: String? = null,
        var comment: String? = null,
        var timestamp: Long? = null
    )
}