import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.lang.Math.abs;

public class Constant extends Node{
    double value;
    Constant(double value){
        this.value = abs(value);
        this.sign = value<0;
    }
    @Override
    double evaluate() {
        return sign ? -value : value;
    }

    @Override
    public String toString(){
        DecimalFormat format = new DecimalFormat("0.#####",new DecimalFormatSymbols(Locale.US));
        return (sign ? "(-" : "") + format.format(value) + (sign ? ")" : "") ;
    }

}
