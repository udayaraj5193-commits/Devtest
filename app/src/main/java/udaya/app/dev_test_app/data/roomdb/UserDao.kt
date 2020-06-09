package udaya.app.dev_test_app.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Query("SELECT * FROM Data")
    suspend fun getAll(): List<Data>

    @Insert
    suspend fun insertAll(users: List<Data>)

}