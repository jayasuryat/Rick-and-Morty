package com.jayasuryat.locationlist


internal sealed interface LocationListModel

internal interface LocationListDto : LocationListModel
internal interface LocationListEntity : LocationListModel
internal interface LocationListDomainModel : LocationListModel
