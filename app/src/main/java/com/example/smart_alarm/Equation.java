//доделать квадратные уравнения
package com.example.smart_alarm;

import java.util.Random;

public class Equation {
    static final int min = -100;
    static final int max = 100;
    static final Random rand = new Random();
    public int counter_rec = 0;

    private static final int Discriminant(int a, int b, int c) {
        return b*b - 4*a*c;
    }

    private static final int[] generate() {
        int[] coeff = new int[3];
        coeff[0] = rand.nextInt(max - min) + min;
        coeff[1] = rand.nextInt(max - min) + min;
        coeff[2] = rand.nextInt(max - min) + min;
        return coeff;
    }

    private int[] isEqual() {
        int[] coeff = generate();
        int a = coeff[0], b = coeff[1], c = coeff[2];
        if (a==0 || b==0 || c==0) {
            counter_rec++;
            return isEqual();
        }
        else if (Discriminant(a, b, c) <= 0) {
            counter_rec++;
            return isEqual();
        }
        else if (Math.sqrt(Discriminant(a, b, c)) % 1 != 0) {
            counter_rec++;
            return isEqual();
        }
        else if(( (double)((-1 * b + Math.sqrt(Discriminant(a,b,c))) / (2 * a))%1!=0) | (double)((-1 * b - Math.sqrt(Discriminant(a,b,c))) / (2 * a))%1!=0){
            counter_rec++;
            return isEqual();
        }
        else{
            int[] per = { a, b, c };
            return per;
        }
    }

    public String[] equation() {
        int[] coeff = isEqual();

        String a = String.valueOf(coeff[0]);
        String b = coeff[1] > 0 ? "+ " + String.valueOf(coeff[1]) : "- " + String.valueOf(Math.abs(coeff[1]));
        String c = coeff[2] > 0 ? "+ " + String.valueOf(coeff[2]) : "- " + String.valueOf(Math.abs(coeff[2]));

        String eq = a + "x² " + b + "x " + c + " = 0";
        String[] per = {eq, a, String.valueOf(coeff[1]), String.valueOf(coeff[2])};
        return (per);
    }

    public String[] answers() {
        String[] coeff = equation();

        int a = Integer.parseInt(coeff[1]);
        int b = Integer.parseInt(coeff[2]);
        int c = Integer.parseInt(coeff[3]);

        int answer1 = (int) ((-1 * b + Math.sqrt(Discriminant(a,b,c))) / (2 * a));
        int answer2 = (int) ((-1 * b - Math.sqrt(Discriminant(a,b,c))) / (2 * a));

        String[] answers = {String.valueOf(answer1), String.valueOf(answer2), coeff[0]};
        return (answers);
    }
}
