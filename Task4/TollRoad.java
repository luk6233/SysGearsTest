package Task4;

import static java.util.Arrays.stream;

public class TollRoad {
    private static final int[] payPoints = new int[] {2, 5, 10, 8, 7, 8, 5, 9, 10, 1};
    private static final int tournamentCount = 4;
    private static final int populationCount = 500;
    private static final double mutationProbability = 1;
    private PaymentOrder[] population = new PaymentOrder[populationCount];

    private int count = 0;

    public static void main(String[] args) {
        new TollRoad().calculation();
    }

    public void calculation() {
        createInitialPopulation();
        addCreditToPaymentOrder();
        count++;

        System.out.println("Minimal credit: " + minCredit(payPoints));

        while (true) {
            for (PaymentOrder paymentOrder : population) {
                if (paymentOrder.getCredit() == minCredit(payPoints)) {
                    System.out.println("Optimal payment order:");
                    for (int i : paymentOrder.getOrder()) {
                        System.out.print(i + " ");
                    }
//                    System.out.println();
//                    System.out.println("Number of iteration: " + count);
                    return;
                }
            }
            createNewGeneration();
            addCreditToPaymentOrder();
            count++;
        }
    }

    public void createInitialPopulation () {
        for (int i = 0; i < populationCount; i++) {
            population[i] = new PaymentOrder();
            PaymentOrder.createRandomOrder(population[i]);
        }
    }

    public void addCreditToPaymentOrder() {
        for (int i = 0; i < populationCount; i++) {
            population[i].credit(payPoints);
        }
    }

    public int[][] selectionPairParents() {
        int[][] parents = new int[populationCount][2];

        for (int i = 0; i < populationCount; i++) {
            int father = tournament();
            int mother = tournament();

            while (father != mother) {
                mother = tournament();
            }

            parents[i][0] = father;
            parents[i][1] = mother;
        }

        return parents;
    }

    public int tournament() {

        int bestOrder = (int) (Math.random() * populationCount);
        int bestCredit = population[bestOrder].getCredit();

        for (int i = 0; i < tournamentCount - 1; i++) {
            int tempOrder = (int) (Math.random() * populationCount);
            if (population[tempOrder].getCredit() < bestCredit) {
                bestCredit = population[tempOrder].getCredit();
                bestOrder = tempOrder;
            }
        }

        return bestOrder;
    }

    // double-point crossovering
    public PaymentOrder crossOveringAndMutation(PaymentOrder fatherOrder, PaymentOrder motherOrder) {
        int[] father = fatherOrder.getOrder();
        int[] mother = motherOrder.getOrder();

        int crossingPoint = (int) (Math.random() * father.length);
        int length = (int) (Math.random() * (father.length - crossingPoint));

        int[] child = new int[father.length];

        System.arraycopy(father, crossingPoint, child , 0, length);

        int k = length;
        for (int i = 0; i < mother.length; i++) {
            boolean contain = false;

            for (int j = 0; j < k; j++) {
                if (mother[i] == child[j]) {
                    contain = true;
                }
            }

            if (!contain) {
                child[k] = mother[i];
                k++;
            }
        }

        if (Math.random() <= mutationProbability / 100) {
            child = mutation(child);
        }

        PaymentOrder childOrder = new PaymentOrder();
        childOrder.setOrder(child);

        return childOrder;

    }

    public int[] mutation(int[] paymentOrder) {
        int first = (int) Math.random() * 10;
        int second = (int) Math.random() * 10;
        int temp = paymentOrder[first];
        paymentOrder[first] = paymentOrder[second];
        paymentOrder[second] = temp;

        return paymentOrder;
    }

    public void createNewGeneration() {
        int[][] pair = selectionPairParents();
        PaymentOrder[] newGeneration = new PaymentOrder[populationCount];
        newGeneration[0] = orderWithMinCredit();

        for (int i = 1; i < populationCount; i++) {
            PaymentOrder father = population[pair[i][0]];
            PaymentOrder mother = population[pair[i][1]];

            PaymentOrder child = crossOveringAndMutation(father, mother);
            newGeneration[i] = child;
            newGeneration[i].credit(payPoints);
        }

        population = newGeneration;
    }

    public PaymentOrder orderWithMinCredit() {
        int min = population[0].getCredit();
        PaymentOrder result = population[0];
        for (int i = 1; i < populationCount; i++) {
            if (population[i].getCredit() < min) {
                min = population[i].getCredit();
                result = population[i];
            }
        }

        return  result;
    }

    public static int minCredit(int[] payPoints) { //minimal credit for current payment order
        if (payPoints.length != 10) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        int sum = 0;
        for (int i = 0; i < payPoints.length; i++) {
            sum += payPoints[i];
        }

        return sum - 55;
    }
}
