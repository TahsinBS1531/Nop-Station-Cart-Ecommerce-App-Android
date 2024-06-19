package com.example.nopstationcart.Services.Model.Product_Search

data class CatalogProductsModel(
    val AllowCustomersToSelectPageSize: Boolean,
    val AllowProductSorting: Boolean,
    val AllowProductViewModeChanging: Boolean,
    val AvailableSortOptions: List<AvailableSortOption>,
    val AvailableViewModes: List<AvailableViewMode>,
    val CustomProperties: CustomPropertiesXXXXXXXXXXX,
    val FirstItem: Int,
    val HasNextPage: Boolean,
    val HasPreviousPage: Boolean,
    val LastItem: Int,
    val ManufacturerFilter: ManufacturerFilter,
    val NoResultMessage: Any,
    val OrderBy: Any,
    val PageIndex: Int,
    val PageNumber: Int,
    val PageSize: Int,
    val PageSizeOptions: List<PageSizeOption>,
    val PriceRangeFilter: PriceRangeFilter,
    val Products: List<Product>,
    val SpecificationFilter: SpecificationFilter,
    val TotalItems: Int,
    val TotalPages: Int,
    val UseAjaxLoading: Boolean,
    val ViewMode: String,
    val WarningMessage: Any
)