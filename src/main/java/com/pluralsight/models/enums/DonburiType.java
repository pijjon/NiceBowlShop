package com.pluralsight.models.enums;

public enum DonburiType {
    GYU_DON("Savory-sweet beef & onions over rice."),
    BUTA_DON("Tender pork in a sweet soy glaze on rice."),
    OYAKO_DON("Chicken & egg simmered on rice."),
    UNA_DON("Grilled eel with sweet sauce on rice."),
    SAKE_DON("Fresh salmon sashimi over rice."),
    YASAI_DON("Seasonal veggies lightly sautéed on rice.");

    private String description;

    DonburiType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
