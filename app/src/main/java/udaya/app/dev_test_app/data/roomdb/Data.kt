package udaya.app.dev_test_app.data.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "first_name") val first_name: String?,
    @ColumnInfo(name = "last_name") val last_name: String?,
    @ColumnInfo(name = "avatar") val avatar: String?

)