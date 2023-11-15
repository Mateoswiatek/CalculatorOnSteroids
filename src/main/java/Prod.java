import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prod extends Node{
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node... nodes){
        args.addAll(Arrays.asList(nodes));
    }

    Prod(double... c){
        for(double d : c) {
            args.add(new Constant(d));
        }
    }

    Prod(double c, Node node){
        args.add(new Constant(c));
        args.add(node);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }



    @Override
    double evaluate() {
        double result = 1;
        for(Node n : args){
            result*=n.evaluate();
        }
        return result;
    }
    int getArgumentsCount(){ return args.size(); }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(sign) stringBuilder.append("-");
        for(Node n : args){
            stringBuilder.append(n.toString());
        }
        return stringBuilder.toString();
    }
}


