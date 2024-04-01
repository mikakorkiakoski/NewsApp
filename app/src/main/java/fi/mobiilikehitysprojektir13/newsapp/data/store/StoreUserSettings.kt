package fi.mobiilikehitysprojektir13.newsapp.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import fi.mobiilikehitysprojektir13.newsapp.screens.settings.FontSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserSettings(private val context: Context) {
    companion object {
        private val Context.dataStorage: DataStore<Preferences> by preferencesDataStore("userSettings")
        val COUNTRY_KEY = stringPreferencesKey("country")
        val DARK_MODE_KEY = booleanPreferencesKey("darkMode")
        val FONT_SIZE_KEY = stringPreferencesKey("fontSize")
    }

    val getCountry: Flow<String> = context.dataStorage.data.map { preferences ->
        preferences[COUNTRY_KEY] ?: ""
    }

    val isDarkMode: Flow<Boolean> = context.dataStorage.data.map { preferences ->
        preferences[DARK_MODE_KEY] ?: false
    }

    val getFontSize: Flow<FontSize> = context.dataStorage.data.map { preferences ->
        FontSize.valueOf(preferences[FONT_SIZE_KEY] ?: FontSize.Medium.name)
    }

    suspend fun saveCountry(country: String) {
        context.dataStorage.edit { preferences ->
            preferences[COUNTRY_KEY] = country
        }
    }

    suspend fun enableDarkMode(darkMode: Boolean) {
        context.dataStorage.edit { preferences ->
            preferences[DARK_MODE_KEY] = darkMode
        }
    }

    suspend fun saveFontSize(fontSize: FontSize) {
        context.dataStorage.edit { preferences ->
            preferences[FONT_SIZE_KEY] = fontSize.name
        }
    }
}