package com.example.nopstationcart.Services.Model.Update_Cart

data class Cart(
    val ButtonPaymentMethodViewComponents: List<Any>,
    val CheckoutAttributes: List<CheckoutAttribute>,
    val CustomProperties: CustomPropertiesXX,
    val DiscountBox: DiscountBox,
    val DisplayTaxShippingInfo: Boolean,
    val GiftCardBox: GiftCardBox,
    val HideCheckoutButton: Boolean,
    val IsEditable: Boolean,
    val Items: List<Item>,
    val MinOrderSubtotalWarning: Any,
    val OnePageCheckoutEnabled: Boolean,
    val OrderReviewData: OrderReviewData,
    val ShowProductImages: Boolean,
    val ShowSku: Boolean,
    val ShowVendorName: Boolean,
    val TermsOfServiceOnOrderConfirmPage: Boolean,
    val TermsOfServiceOnShoppingCartPage: Boolean,
    val TermsOfServicePopup: Boolean,
    val Warnings: List<Any>
)