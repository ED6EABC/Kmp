package com.ee.kmp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Breed (

    @SerialName("weight"             ) var weight           : Weight? = Weight(),
    @SerialName("id"                 ) var id               : String? = null,
    @SerialName("name"               ) var name             : String? = null,
    @SerialName("cfa_url"            ) var cfaUrl           : String? = null,
    @SerialName("vetstreet_url"      ) var vetstreetUrl     : String? = null,
    @SerialName("vcahospitals_url"   ) var vcahospitalsUrl  : String? = null,
    @SerialName("temperament"        ) var temperament      : String? = null,
    @SerialName("origin"             ) var origin           : String? = null,
    @SerialName("country_codes"      ) var countryCodes     : String? = null,
    @SerialName("country_code"       ) var countryCode      : String? = null,
    @SerialName("description"        ) var description      : String? = null,
    @SerialName("life_span"          ) var lifeSpan         : String? = null,
    @SerialName("indoor"             ) var indoor           : Int?    = null,
    @SerialName("lap"                ) var lap              : Int?    = null,
    @SerialName("alt_names"          ) var altNames         : String? = null,
    @SerialName("adaptability"       ) var adaptability     : Int?    = null,
    @SerialName("affection_level"    ) var affectionLevel   : Int?    = null,
    @SerialName("child_friendly"     ) var childFriendly    : Int?    = null,
    @SerialName("dog_friendly"       ) var dogFriendly      : Int?    = null,
    @SerialName("energy_level"       ) var energyLevel      : Int?    = null,
    @SerialName("grooming"           ) var grooming         : Int?    = null,
    @SerialName("health_issues"      ) var healthIssues     : Int?    = null,
    @SerialName("intelligence"       ) var intelligence     : Int?    = null,
    @SerialName("shedding_level"     ) var sheddingLevel    : Int?    = null,
    @SerialName("social_needs"       ) var socialNeeds      : Int?    = null,
    @SerialName("stranger_friendly"  ) var strangerFriendly : Int?    = null,
    @SerialName("vocalisation"       ) var vocalisation     : Int?    = null,
    @SerialName("experimental"       ) var experimental     : Int?    = null,
    @SerialName("hairless"           ) var hairless         : Int?    = null,
    @SerialName("natural"            ) var natural          : Int?    = null,
    @SerialName("rare"               ) var rare             : Int?    = null,
    @SerialName("rex"                ) var rex              : Int?    = null,
    @SerialName("suppressed_tail"    ) var suppressedTail   : Int?    = null,
    @SerialName("short_legs"         ) var shortLegs        : Int?    = null,
    @SerialName("wikipedia_url"      ) var wikipediaUrl     : String? = null,
    @SerialName("hypoallergenic"     ) var hypoallergenic   : Int?    = null,
    @SerialName("reference_image_id" ) var referenceImageId : String? = null

)

@Serializable
data class Weight (

    @SerialName("imperial" ) var imperial : String? = null,
    @SerialName("metric"   ) var metric   : String? = null

)