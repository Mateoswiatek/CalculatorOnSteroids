public class Power extends Node {
    double p;
    Node arg;
    Power(Node arg, double p){
        this.arg = arg;
        this.p = p;
    }
    @Override
    double evaluate() {
        return Math.pow(arg.evaluate(), p);
    }
    int getArgumentsCount(){return 1;}

    Node diff(Variable var) {
        Prod r =  new Prod(sign ? -p : p ,new Power(arg,p-1));
        r.mul(arg.diff(var));
        return r;
    }

    @Override
    boolean isZero() {
        return p == 0;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        boolean argSin = arg.getSign();
        int cnt = arg.getArgumentCount();
        boolean useBracket = argSin || cnt > 1;

        if(sign) stringBuilder.append("-");
        if(useBracket) stringBuilder.append("(");
        stringBuilder.append(arg.toString());
        if(useBracket) stringBuilder.append(")");
        stringBuilder.append("^");
        stringBuilder.append(p);

        return stringBuilder.toString();
    }
}
