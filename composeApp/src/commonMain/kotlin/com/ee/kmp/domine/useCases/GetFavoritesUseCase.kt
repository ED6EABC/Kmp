package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase
import com.ee.kmp.data.model.Breed
import com.ee.kmp.data.model.Weight

class GetFavoritesUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    fun invoke(): List<Breed> {
        return breedsDataBase.breedsDataBaseQueries.reedAllFavoritesBreeds()
            .executeAsList()
            .map {
                (   id,
                    name,
                    cfaUrl,
                    vetStreetUrl,
                    vcaHospitalsUrl,
                    temperament,
                    origin,
                    countryCodes,
                    countryCode,
                    description,
                    lifeSpan,
                    indoor,
                    lap,
                    altNames,
                    adaptability,
                    affectionLevel,
                    childFriendly,
                    dogFriendly,
                    energyLevel,
                    grooming,
                    healthIssues,
                    intelligence,
                    sheddingLevel,
                    socialNeeds,
                    strangerFriendly,
                    vocalisation,
                    experimental,
                    hairless,
                    natural,
                    rare,
                    rex,
                    suppressedTail,
                    shortLegs,
                    wikipediaUrl,
                    hypoallergenic,
                    referenceImageId,
                    weightImperial,
                    weightMetric
                ) ->
                    Breed(
                        id = id,
                        name = name,
                        cfaUrl = cfaUrl,
                        vetstreetUrl = vetStreetUrl,
                        vcahospitalsUrl = vcaHospitalsUrl,
                        temperament = temperament,
                        origin = origin,
                        countryCodes = countryCodes,
                        countryCode = countryCode,
                        description = description,
                        lifeSpan = lifeSpan,
                        indoor = indoor.toInt(),
                        lap = lap.toInt(),
                        altNames = altNames,
                        adaptability = adaptability.toInt(),
                        affectionLevel = affectionLevel.toInt(),
                        childFriendly = childFriendly.toInt(),
                        dogFriendly = dogFriendly.toInt(),
                        energyLevel = energyLevel.toInt(),
                        grooming = grooming.toInt(),
                        healthIssues = healthIssues.toInt(),
                        intelligence = intelligence.toInt(),
                        sheddingLevel = sheddingLevel.toInt(),
                        socialNeeds = socialNeeds.toInt(),
                        strangerFriendly = strangerFriendly.toInt(),
                        vocalisation = vocalisation.toInt(),
                        experimental = experimental.toInt(),
                        hairless = hairless.toInt(),
                        natural = natural.toInt(),
                        rare = rare.toInt(),
                        rex = rex.toInt(),
                        suppressedTail = suppressedTail.toInt(),
                        shortLegs = shortLegs.toInt(),
                        wikipediaUrl = wikipediaUrl,
                        hypoallergenic = hypoallergenic.toInt(),
                        referenceImageId = referenceImageId,
                        weight = Weight(
                            imperial = weightImperial,
                            metric = weightMetric
                        )
                    )
            }
    }
}