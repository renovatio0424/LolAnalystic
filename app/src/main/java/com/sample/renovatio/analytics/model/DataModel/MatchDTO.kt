package com.sample.renovatio.analytics.model.DataModel

data class MatchReferenceDTO(
    val champion: Int,
    val gameId: Long,
    val lane: String,
    val platformId: String,
    val queue: Int,
    val role: String,
    val season: Int,
    val timestamp: Long
)

data class MatchListDTO(
    val endIndex: Int,
    val matches: List<MatchReferenceDTO>,
    val startIndex: Int,
    val totalGames: Int
)
