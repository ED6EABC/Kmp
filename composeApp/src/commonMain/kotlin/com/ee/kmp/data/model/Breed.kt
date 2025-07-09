package com.ee.kmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Breed (

    @SerialName("weight"             ) var weight           : Weight = Weight(),
    @SerialName("id"                 ) var id               : String,
    @SerialName("name"               ) var name             : String = "",
    @SerialName("cfa_url"            ) var cfaUrl           : String = "",
    @SerialName("vetstreet_url"      ) var vetstreetUrl     : String = "",
    @SerialName("vcahospitals_url"   ) var vcahospitalsUrl  : String = "",
    @SerialName("temperament"        ) var temperament      : String = "",
    @SerialName("origin"             ) var origin           : String = "",
    @SerialName("country_codes"      ) var countryCodes     : String = "",
    @SerialName("country_code"       ) var countryCode      : String = "",
    @SerialName("description"        ) var description      : String = "",
    @SerialName("life_span"          ) var lifeSpan         : String = "",
    @SerialName("indoor"             ) var indoor           : Int = 0,
    @SerialName("lap"                ) var lap              : Int = 0,
    @SerialName("alt_names"          ) var altNames         : String = "",
    @SerialName("adaptability"       ) var adaptability     : Int = 0,
    @SerialName("affection_level"    ) var affectionLevel   : Int = 0,
    @SerialName("child_friendly"     ) var childFriendly    : Int = 0,
    @SerialName("dog_friendly"       ) var dogFriendly      : Int = 0,
    @SerialName("energy_level"       ) var energyLevel      : Int = 0,
    @SerialName("grooming"           ) var grooming         : Int = 0,
    @SerialName("health_issues"      ) var healthIssues     : Int = 0,
    @SerialName("intelligence"       ) var intelligence     : Int = 0,
    @SerialName("shedding_level"     ) var sheddingLevel    : Int = 0,
    @SerialName("social_needs"       ) var socialNeeds      : Int = 0,
    @SerialName("stranger_friendly"  ) var strangerFriendly : Int = 0,
    @SerialName("vocalisation"       ) var vocalisation     : Int = 0,
    @SerialName("experimental"       ) var experimental     : Int = 0,
    @SerialName("hairless"           ) var hairless         : Int = 0,
    @SerialName("natural"            ) var natural          : Int = 0,
    @SerialName("rare"               ) var rare             : Int = 0,
    @SerialName("rex"                ) var rex              : Int = 0,
    @SerialName("suppressed_tail"    ) var suppressedTail   : Int = 0,
    @SerialName("short_legs"         ) var shortLegs        : Int = 0,
    @SerialName("wikipedia_url"      ) var wikipediaUrl     : String = "",
    @SerialName("hypoallergenic"     ) var hypoallergenic   : Int = 0,
    @SerialName("reference_image_id" ) var referenceImageId : String = ""

)

@Serializable
data class Weight (

    @SerialName("imperial" ) var imperial : String = "",
    @SerialName("metric"   ) var metric   : String = ""

)