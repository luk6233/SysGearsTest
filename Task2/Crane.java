package Task2;

public class Crane {
    private int nSlabs;

    public Crane(int nSlabs) {
        if ((nSlabs >= 3) && (nSlabs <= 8)) {
            this.nSlabs = nSlabs;
        }
        else {
           throw new IllegalArgumentException("Wrong number of plates");
        }
    }

    public void slabsMoving(int topSlabs, char car1, char tempStorage, char car2) {
        if (topSlabs  == 1) {
            System.out.println(String.format("#1 slot_%c -> slot_%c", car1, car2));
        }
        else {
            slabsMoving(topSlabs - 1, car1, car2, tempStorage);
            System.out.println(String.format("#%d slot_%c -> slot_%c", topSlabs, car1, car2));
            slabsMoving(topSlabs - 1, tempStorage, car1, car2);
        }
    }

    public int getNumberOfSlabs() {
        return nSlabs;
    }
}
