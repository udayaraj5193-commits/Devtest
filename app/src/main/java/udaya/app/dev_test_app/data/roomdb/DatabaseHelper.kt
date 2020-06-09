package udaya.app.dev_test_app.data.roomdb


interface DatabaseHelper {

    suspend fun getUsers(): List<Data>

    suspend fun insertAll(users: List<Data>)

}