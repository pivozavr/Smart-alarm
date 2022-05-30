package com.example.smart_alarm;

import java.util.Random;

public class Equation_v2 {
    static final int min = -10;
    static final int max = 10;
    static final int[] signs ={-1,1};
    static final Random rand = new Random();

    public int[] generate() {
        int[] answers = new int[3];
        answers[0] = rand.nextInt(max - min) + min;
        answers[1] = rand.nextInt(max - min) + min;
        answers[2] = signs[rand.nextInt(2)]*(rand.nextInt(max - 1) + 1);
        return answers;
    }
    public String[] equation(){
        int[] answers = generate();
        int ce = answers[0] * answers[1] * answers[2];
        int be = -1*(answers[0] + answers[1])*answers[2];

        String b = be > 0 ? "+ " + String.valueOf(be) : "- " + String.valueOf(Math.abs(be));
        String c = ce > 0 ? "+ " + String.valueOf(ce) : "- " + String.valueOf(Math.abs(ce));

        String eq = answers[2]+"x² " + b + "x " + c + " = 0";
        String[] per = {String.valueOf(answers[0]), String.valueOf(answers[1]), eq};
        return per;
    }
}
