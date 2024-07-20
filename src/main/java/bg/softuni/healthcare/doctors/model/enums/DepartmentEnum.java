package bg.softuni.healthcare.doctors.model.enums;

public enum DepartmentEnum {
    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology"),
    NEUROLOGY("Neurology"),
    ORTHOPEDICS("Orthopedics"),
    PEDIATRICS("Pediatrics"),
    GENERAL_SURGERY("General Surgery"),
    GYNECOLOGY("Gynecology"),
    ONCOLOGY("Oncology"),
    PSYCHIATRY("Psychiatry"),
    RADIOLOGY("Radiology"),
    ENDOCRINOLOGY("Endocrinology");

    private final String displayName;

    DepartmentEnum(String name) {
        this.displayName = name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
