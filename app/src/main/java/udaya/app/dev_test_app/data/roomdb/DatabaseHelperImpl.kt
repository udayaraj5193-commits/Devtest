package udaya.app.dev_test_app.data.roomdb


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<Data> = appDatabase.userDao().getAll()

    override suspend fun insertAll(users: List<Data>) = appDatabase.userDao().insertAll(users)

}