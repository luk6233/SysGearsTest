package Task3;

public class Archery {
    public static void main(String[] args) {
        double arch1Prob = Math.random() * 0.2 + 0.1;
        double arch2Prob = Math.random() * 0.2 + 0.1;
        int firstStep = Math.random() < 0.5 ? 1 : 2;

        System.out.println(stepForFirstArcher(arch1Prob, arch2Prob, firstStep));
    }

    public static int stepForFirstArcher(double arch1Prob, double arch2Prob, int firstStep) {
        if ((arch1Prob < 0.1 || arch1Prob > 0.3) || (arch2Prob < 0.1 || arch2Prob > 0.3)
                || (firstStep != 1 && firstStep != 2)) {
            throw new IllegalArgumentException("Wrong parameters");
        }

        double step1Prob = (1 - arch1Prob) / 10;
        double step2Prob = (1 - arch2Prob) / 10;

        for (int i = 1; i <= 10; i++) {
            if (firstStep == 1) {
                if (arch1Prob >= (1 - arch2Prob)) {
                    return i;
                }

                arch1Prob += step1Prob;
                arch2Prob += step2Prob;

            }
            else {
                arch2Prob += step2Prob;

                if (arch1Prob >= (1 - arch2Prob)) {
                    return i;
                }

                arch1Prob += step1Prob;
            }
        }

        return 0;
    }
}
