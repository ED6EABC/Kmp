package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase
import com.ee.kmp.data.model.Breed

class SaveFavoriteUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    suspend fun invoke(breed: Breed) {
        breedsDataBase.breedTableQueries.insetFaoriteBreed(
            id = breed.id,
            name = breed.name,
            cfa_url = breed.cfaUrl,
            vetstreet_url = breed.vetstreetUrl,
            vcahospitals_url = breed.vcahospitalsUrl,
            temperament = breed.temperament,
            origin = breed.origin,
            country_codes = breed.countryCodes,
            country_code = breed.countryCode,
            description = breed.description,
            life_span = breed.lifeSpan,
            indoor = breed.indoor.toLong(),
            lap = breed.lap.toLong(),
            alt_names = breed.altNames,
            adaptability = breed.adaptability.toLong(),
            affection_level = breed.affectionLevel.toLong(),
            child_friendly = breed.childFriendly.toLong(),
            dog_friendly = breed.dogFriendly.toLong(),
            energy_level = breed.energyLevel.toLong(),
            grooming = breed.grooming.toLong(),
            health_issues = breed.healthIssues.toLong(),
            intelligence = breed.intelligence.toLong(),
            shedding_level = breed.sheddingLevel.toLong(),
            social_needs = breed.socialNeeds.toLong(),
            stranger_friendly = breed.strangerFriendly.toLong(),
            vocalisation = breed.vocalisation.toLong(),
            experimental = breed.experimental.toLong(),
            hairless = breed.hairless.toLong(),
            natural = breed.natural.toLong(),
            rare = breed.rare.toLong(),
            rex = breed.rex.toLong(),
            suppressed_tail = breed.suppressedTail.toLong(),
            short_legs = breed.shortLegs.toLong(),
            wikipedia_url = breed.wikipediaUrl,
            hypoallergenic = breed.hypoallergenic.toLong(),
            reference_image_id = breed.referenceImageId,
            weight_imperial = breed.weight.imperial,
            weight_metric = breed.weight.metric
        )
    }
}