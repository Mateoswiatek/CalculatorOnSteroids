public class Variable extends Node {
    String name;
    Double value;

    Variable(String name, double value){
        this.name = name;
        this.value = value;
    }
    Variable(String name){
        this.name = name;
        this.value = 1.0;
    }

    Variable(double d){
        this.name = "";
        this.value = d;
    }

    void setValue(double value){
        this.value = value;
    }

    void setName(String name){
        this.name = name;
    }

    Node diff(Variable var) {
        if(var.name.equals(name))return new Constant(sign ? -1 : 1);
        else return new Constant(0);
    }
    @Override
    boolean isZero() {
        return false;
    }

    @Override
    double evaluate(){
        return sign ? -value : value;
    }

    @Override
    public String toString(){
        return (sign ? "(-" : "") + name + (sign ? ")" : "");
    }
}