import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        //buildAndPrint();
        //buildAndEvaluate();
        //defineCircle();
        //diffPoly();
        diffCircle();

    }

    static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
    }

    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.format(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    static void defineCircle(){
        class Pair{
            final double x;
            final double y;
            Pair(double x, double y){
                this.x = x;
                this.y = y;
            }
            @Override
            public String toString() {
                return "\nx: " + String.format("%.4f", x) + " y: " + String.format("%.4f", y);
            }
        }

        List<Pair> pairs = new ArrayList<>(100);
        int i = 0;
        int j = 0;
        Variable x = new Variable("x");
        Variable y = new Variable("y");


        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);


        while(i<100){
            double xv = 4 * (Math.random() - 1);
            double yv = 4 * (Math.random() - 1);
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
//            System.out.println(fv);
            if(fv<0){
                pairs.add(new Pair(xv, yv));
                i++;
            }
            j++;
        }
        System.out.println("Znaleziono 100 punktow w " + j + " obiegach: " + pairs);
        System.out.format("Prawdopodobienstwo = %.4f", 100*(double)i/j);
    }


    static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

    }

    static void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());

    }

}