package com.jayasuryat.episodelist

internal sealed class EpisodeListData(val id: String) {

    abstract val seasonName: String

    data class Season(
        override val seasonName: String,
        val isExpanded: Boolean,
    ) : EpisodeListData(seasonName)

    data class Episode(
        val episodeId: Long,
        override val seasonName: String,
        val episodeName: String,
        val episodeNumber: Int,
        val url: String,
    ) : EpisodeListData(url)
}