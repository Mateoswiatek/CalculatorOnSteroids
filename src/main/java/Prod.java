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

    Node diffVanilla(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            if(m.isZero()){
                r.add(m);
            }
        }
        return r;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(sign) stringBuilder.append("-");
        for(Node n : args){
            stringBuilder.append(n.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    Node diff(Variable var) {
        return diffVanilla(var);
    }

    @Override
    boolean isZero() {
        return this.evaluate() == 0;
    }
}


