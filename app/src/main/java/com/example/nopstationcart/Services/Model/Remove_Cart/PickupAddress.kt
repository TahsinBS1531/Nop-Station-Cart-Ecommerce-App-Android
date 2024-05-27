package com.example.nopstationcart.Services.Model.Remove_Cart

data class PickupAddress(
    val Address1: Any,
    val Address2: Any,
    val AvailableCountries: List<Any>,
    val AvailableStates: List<Any>,
    val City: Any,
    val CityEnabled: Boolean,
    val CityRequired: Boolean,
    val Company: Any,
    val CompanyEnabled: Boolean,
    val CompanyRequired: Boolean,
    val CountryEnabled: Boolean,
    val CountryId: Any,
    val CountryName: Any,
    val County: Any,
    val CountyEnabled: Boolean,
    val CountyRequired: Boolean,
    val CustomAddressAttributes: List<Any>,
    val CustomProperties: CustomPropertiesXX,
    val Email: Any,
    val FaxEnabled: Boolean,
    val FaxNumber: Any,
    val FaxRequired: Boolean,
    val FirstName: Any,
    val FormattedCustomAddressAttributes: Any,
    val Id: Int,
    val LastName: Any,
    val PhoneEnabled: Boolean,
    val PhoneNumber: Any,
    val PhoneRequired: Boolean,
    val StateProvinceEnabled: Boolean,
    val StateProvinceId: Any,
    val StateProvinceName: Any,
    val StreetAddress2Enabled: Boolean,
    val StreetAddress2Required: Boolean,
    val StreetAddressEnabled: Boolean,
    val StreetAddressRequired: Boolean,
    val ZipPostalCode: Any,
    val ZipPostalCodeEnabled: Boolean,
    val ZipPostalCodeRequired: Boolean
)