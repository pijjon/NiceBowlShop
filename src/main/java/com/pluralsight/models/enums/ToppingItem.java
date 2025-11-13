package com.pluralsight.models.enums;

public enum ToppingItem {
    // PREMIUM
    TAMAGOYAKI("Tamagoyaki", ToppingType.PREMIUM),
    MARINATED_EGG("Marinated Egg", ToppingType.PREMIUM),
    SPICY_MINCED_PORK("Spicy Minced Pork", ToppingType.PREMIUM),
    GROUND_BEEF("Ground Beef", ToppingType.PREMIUM),
    IKURA("Ikura", ToppingType.PREMIUM),
    FRIED_EGG("Fried Egg", ToppingType.PREMIUM),

    // AROMA OIL
    CHILI_CRISP_OIL("Chili Crisp Oil", ToppingType.AROMA_OIL),
    GINGER_SCALLION_OIL("Ginger Scallion Oil", ToppingType.AROMA_OIL),
    TRUFFLE_OIL("Truffle Oil", ToppingType.AROMA_OIL),
    BLACK_GARLIC_OIL("Black Garlic Oil", ToppingType.AROMA_OIL),
    SESAME_OIL("Sesame Oil", ToppingType.AROMA_OIL),

    // REGULAR
    SCALLION("Scallion", ToppingType.REGULAR),
    CORN("Corn", ToppingType.REGULAR),
    BONITO_FLAKE("Bonito Flake", ToppingType.REGULAR),
    GARLIC_CRISP("Garlic Crisp", ToppingType.REGULAR),
    ONION_CRISP("Onion Crisp", ToppingType.REGULAR),
    FURIKAKE("Furikake", ToppingType.REGULAR),

    // SAUCE
    SOY_SAUCE("Soy Sauce", ToppingType.SAUCE),
    CITRUS_PONZU("Citrus Ponzu", ToppingType.SAUCE),
    SPICY_MAYO("Spicy Mayo", ToppingType.SAUCE),
    WASABI_AIOLI("Wasabi Aioli", ToppingType.SAUCE),
    TERIYAKI_MAYO("Teriyaki Mayo", ToppingType.SAUCE),

    // SIDE
    KARAAGE("Karaage", ToppingType.SIDE),
    VEGGIE_GYOZA("Veggie Gyoza", ToppingType.SIDE);

    private final String displayName;
    private final ToppingType type;

    ToppingItem(String displayName, ToppingType type) {
        this.displayName = displayName;
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ToppingType getType() {
        return type;
    }
}
