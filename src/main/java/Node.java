abstract public class Node {
    boolean sign;
    Node plus(){
        sign = false;
        return this;
    }
    Node minus(){
        sign = true;
        return this;
    }
    boolean getSign(){ return sign;}

    abstract double evaluate();

    @Override
    public String toString(){
        return "";
    }

    int getArgumentCount(){return 0;}
}
