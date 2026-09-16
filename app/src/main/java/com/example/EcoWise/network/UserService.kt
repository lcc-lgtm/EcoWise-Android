package com.example.EcoWise.network

import io.github.jan.supabase.postgrest.from

class UserService {
    private val tableName = "user_table"

    suspend fun fetchUserFromSupabase(email: String): UserRemoteDto? {
        return try {
            SupabaseClient.client
                .from(tableName)
                .select {
                    filter { eq("email", email) }
                }
                .decodeSingleOrNull<UserRemoteDto>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun upsertUserToSupabase(user: UserRemoteDto) {
        try {
            SupabaseClient.client
                .from(tableName)
                .upsert(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}