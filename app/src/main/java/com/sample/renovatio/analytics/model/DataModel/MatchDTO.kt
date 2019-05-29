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
) {
    companion object {
        fun createMockDTO(): MatchReferenceDTO {
            return MatchReferenceDTO(7, 3648829435, "MID", "KR", 420, "SOLO", 13, 1557060333555)
        }
    }
}

data class MatchListDTO(
    val endIndex: Int,
    val matches: ArrayList<MatchReferenceDTO>,
    val startIndex: Int,
    val totalGames: Int
) {
    companion object {
        fun createMockDTO(): MatchListDTO {
            return MatchListDTO(
                100,
                arrayListOf(
                    MatchReferenceDTO.createMockDTO(),
                    MatchReferenceDTO.createMockDTO(),
                    MatchReferenceDTO.createMockDTO(),
                    MatchReferenceDTO.createMockDTO()
                ),
                0,
                116
            )
        }
    }
}
