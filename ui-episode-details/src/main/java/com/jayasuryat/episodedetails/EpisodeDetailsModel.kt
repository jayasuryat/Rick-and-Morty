package com.jayasuryat.episodedetails


internal sealed interface EpisodeDetailsModel

internal interface EpisodeDetailsDtoModel : EpisodeDetailsModel
internal interface EpisodeDetailsEntityModel : EpisodeDetailsModel
internal interface EpisodeDetailsDomainModel : EpisodeDetailsModel
