package fr.lacl.tamago.aca.term;



public enum OnEnum {

    USER,
    ROLE,
    ORGANISATION;

    public static OnEnum fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }

}
