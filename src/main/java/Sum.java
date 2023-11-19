import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Sum extends Node{
    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node... nodes){
        args.addAll(Arrays.asList(nodes));
    }
    Sum add(Node... nodes){
        args.addAll(Arrays.asList(nodes));
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n){
        Node mul = new Prod(c, n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result = 0;
        for(Node n: args) result += n.evaluate();
        return sign ? -result : result;
    }

    int getArgumentsCount(){ return args.size();}

    Node diffVanilla(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            if(!n.isZero()){
                r.add(n.diff(var));
            }
        }
        return r;
    }

    public String toString(){
        if(args.isEmpty()) return "";

        // pewnie mozna lepiej zrobic
        StringBuilder stringBuilder = new StringBuilder();
        if(sign) stringBuilder.append("-(");

        StringJoiner stringJoiner = new StringJoiner(" + "); // pomiedzy elementami daje to co tu jest.
        for (Node n : args) {
            stringJoiner.add(n.toString());
        }
        stringBuilder.append(stringJoiner);

        if(sign) stringBuilder.append(")");
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
