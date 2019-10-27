package Task2;

public class Test {
    public static void main(String[] args) {
        Crane crane = new Crane(4);

        int numberOfSlabs = crane.getNumberOfSlabs();
        char car1 = 'a';
        char car2 = 'c';
        char tempStorage = 'b';

        crane.slabsMoving(numberOfSlabs, car1, tempStorage, car2);

    }
}
