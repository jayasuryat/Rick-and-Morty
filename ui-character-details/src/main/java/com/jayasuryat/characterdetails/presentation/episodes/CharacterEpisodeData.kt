package com.jayasuryat.characterdetails.presentation.episodes

sealed class CharacterEpisodeData(
    val id: Long,
) {

    data class EpisodeData(
        val episodeId: Long,
        val episodeName: String,
        val season: Int,
        val episode: Int,
    ) : CharacterEpisodeData(episodeId)

    class SeasonDivider(
        breakAfterSeason: Long,
    ) : CharacterEpisodeData(breakAfterSeason * -1)
}
