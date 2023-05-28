enum ArabNumeral {
    NUM1("I"), NUM2("II"), NUM3("III"), NUM4("IV"),
    NUM5("V"), NUM6("VI"), NUM7("VII"), NUM8("IIX"),
    NUM9("IX"), NUM10("X"),
//    NUM11("XI"), NUM12("XII"),
//    NUM13("XIII"), NUM14("XIV"), NUM15("XV"), NUM16("XVI"),
//    NUM17("XVII"), NUM18("XIIX"), NUM19("XIX"), NUM20("XX"),
//    NUM21("XXI"), NUM22("XXII"), NUM23("XXIII"), NUM24("XXIV"),
//    NUM25("XXV"),
    NUM50("L"), NUM100("C");
    private String romanNumeral;

    ArabNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
    }

    public String getRomanNumeral() {
        return romanNumeral;
    }
}