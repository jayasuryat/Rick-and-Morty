package com.jayasuryat.data.models

public sealed interface Model

internal interface DtoModel : Model
internal interface EntityModel : Model
public interface DomainModel : Model