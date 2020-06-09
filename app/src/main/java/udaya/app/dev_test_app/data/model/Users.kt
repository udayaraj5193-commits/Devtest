package udaya.app.dev_test_app.data.model

import udaya.app.dev_test_app.data.roomdb.Data

data class Users(
    var page:Int,
    var per_page : Int,
    var total : Int,
    var total_pages : Int,
    val `data`: List<UserDetails>
)