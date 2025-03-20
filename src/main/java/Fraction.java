public final class Fraction extends Number {
    private int numerator;
    private int denominator;

    public Fraction(){
        this.numerator=0;
        this.denominator=1;
    }

    @Override
    public int intValue() {
        return numerator/denominator;
    }

    @Override
    public long longValue() {
        return numerator/denominator;
    }

    @Override
    public float floatValue() {
        return ((float)(numerator)/(float)denominator);
    }

    @Override
    public double doubleValue() {
        return ((float)(numerator)/(float)denominator);
    }

    public Fraction(int x){
        this.numerator=x;
        this.denominator=1;
    }

    public Fraction(int numerator, int denominator) {
        if(denominator<=0) throw new IllegalArgumentException("Задано нулевое или отрицательное значение");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(Fraction fraction){
        this.numerator= fraction.getNumerator();
        this.denominator= fraction.getDenominator();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return "%d/%d".formatted(numerator,denominator);
    }

    public Fraction add(int x) {
        Fraction fraction1 = new Fraction(x);
        Fraction fraction2= new Fraction(this.numerator,this.denominator);
        return fraction2.add(fraction1);
    }
    public Fraction substract(int x) {
        Fraction fraction1 = new Fraction(x);
        Fraction fraction2= new Fraction(this.numerator,this.denominator);
        return fraction2.substract(fraction1);
    }


    public Fraction add(Fraction fraction2) {
        int numerator1=0;
        int denominator1=1;
        if (denominator == fraction2.getDenominator()) {
            numerator1 = numerator + fraction2.getNumerator();
        }
        else {
            int result;
            result=numerator * fraction2.getDenominator() + fraction2.getNumerator() * denominator;
            numerator1=result;
            denominator1 = denominator * fraction2.getDenominator();
        }
        return new Fraction(numerator1,denominator1);

    }
    public Fraction substract(Fraction fraction2) {
        int numerator1=0;
        int denominator1=1;
        if (denominator == fraction2.getDenominator()) {
            numerator = numerator - fraction2.getNumerator();
        }
        else {
            int result;
            result=numerator * fraction2.getDenominator() - fraction2.getNumerator() * denominator;
            numerator1=result;
            denominator1 = denominator * fraction2.getDenominator();
        }
        return new Fraction(numerator1,denominator1);
    }
}
