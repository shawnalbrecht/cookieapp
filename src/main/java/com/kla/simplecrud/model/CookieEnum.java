package com.kla.simplecrud.model;

/**
 * A list of the different types of cookies. Should match up with cookie_types database.
 */
public enum CookieEnum {

    ANIMAL_CRACKER("ANIC", "Animal Cracker"),
    BISCUIT("BRIT", "Biscuit"),
    CHOC_CHIP("CCHP", "Chocolate Chip"),
    CHRISTMAS("XMAS", "Christmas"),
    FORTUNE("$$$$", "Fortune"),
    GINGERBREAD("GING", "Gingerbread"),
    OATMEAL("OATS", "Oatmeal"),
    OREO("OREO", "Oreo"),
    PEANUT_BUTTER("PBNJ", "Peanut Butter"),
    SNICKERDOODLE("DOOD", "Snickerdoodle"),
    SUGAR("SUGA", "Sugar"),
    WAFER("THIN", "Wafer"),
    WINDMILL("WIND", "Windmill"),
    MISCELLANEOUS("MISC", "Miscellaneous");

    private String code;

    private String description;

    CookieEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
