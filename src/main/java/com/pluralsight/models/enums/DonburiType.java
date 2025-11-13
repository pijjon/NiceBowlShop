package com.pluralsight.models.enums;

public enum DonburiType {
    GYUDON("Gyudon", "Savory-sweet beef & onions over rice."),
    BUTADON("Butadon", "Tender pork in a sweet soy glaze on rice."),
    OYAKODON("Oyakodon", "Chicken & egg simmered on rice."),
    UNAGI_DON("Unagi Don", "Grilled eel with sweet sauce on rice."),
    SAKE_DON("Sake Don", "Fresh salmon sashimi over rice."),
    YASAI_DON("Yasai Don", "Seasonal veggies lightly sautéed on rice.");

    private final String description;
    private final String displayName;

    DonburiType(String displayName, String description) {
        this.description = description;
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return displayName;
    }

}
