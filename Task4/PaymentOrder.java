package Task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentOrder { //chromosome
    private int[] order; //payment order
    private int credit;  //credit for current payment order

    public static void createRandomOrder(PaymentOrder paymentOrder) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        paymentOrder.setOrder(array);
    }


    //Fitness function
    public void credit(int[] payPoints) {
        if (payPoints.length != getOrder().length) {
            throw new IllegalArgumentException("Wrong parameter");
        }

        int credit = 0;
        for (int i = 0; i < payPoints.length; i++) {
            int delta = payPoints[i] - getOrder()[i];
            if (delta > 0) {
                credit += delta;
            }
        }

        setCredit(credit);
   }

    public int[] getOrder() {
        return order;
    }

    public void setOrder(int[] order) {
        this.order = order;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
