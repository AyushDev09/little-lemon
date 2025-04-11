package com.example.androidcapstone

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.androidcapstone.ui.theme.AndroidCapstoneTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    // Singleton instance of the database
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "little_lemon_db"
        ).build()
    }

    // HTTP client for fetching menu data
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Insert menu data in background
        lifecycleScope.launch {
            insertMenuItemsFromNetwork()
        }

        setContent {
            AndroidCapstoneTheme {
                val navController = rememberNavController()
                MyNavigation(navController = navController, database = db)
            }
        }
    }

    // Fetch menu from network and insert into local DB
    private suspend fun insertMenuItemsFromNetwork() {
        try {
            val menuItemsFromNetwork = fetchMenu()
            val menuEntities = menuItemsFromNetwork.map { it.toEntity() }
            db.menuDao().insertMenuItems(menuEntities)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error fetching/inserting menu items: ${e.message}")
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()
            .menu
    }

    private fun MenuItemNetwork.toEntity(): MenuItemEntity {
        return MenuItemEntity(
            id = id,
            title = title,
            description = description,
            price = price,
            image = image,
            category = category
        )
    }
}

