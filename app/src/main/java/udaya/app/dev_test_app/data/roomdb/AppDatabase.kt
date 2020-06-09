package udaya.app.dev_test_app.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Data::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}