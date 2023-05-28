enum RomanNumeral {
    I(1), V(5), X(10);
    private int arabicNumeral;

    RomanNumeral(int arabicNumeral) {
        this.arabicNumeral = arabicNumeral;
    }

    public int getArabicNumeral() {
        return arabicNumeral;
    }
}
