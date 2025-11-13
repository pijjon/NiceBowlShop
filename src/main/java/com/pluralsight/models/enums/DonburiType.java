package com.pluralsight.models.enums;

public enum DonburiType {
    GYUDON("Savory-sweet beef & onions over rice."),
    BUTADON("Tender pork in a sweet soy glaze on rice."),
    OYAKODON("Chicken & egg simmered on rice."),
    UNAGIDON("Grilled eel with sweet sauce on rice."),
    SAKEDON("Fresh salmon sashimi over rice."),
    YASAIDON("Seasonal veggies lightly sautéed on rice.");

    private String description;

    DonburiType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
