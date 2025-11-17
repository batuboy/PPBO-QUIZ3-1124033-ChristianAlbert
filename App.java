
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

class CLIUtils {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static Number getNumber() throws InvalidInputException {
        return scanner.nextDouble();
    }
}

class StaticList<T> {
    private List<T> numList;

    public StaticList() {
        numList = new ArrayList<>();
    }

    public List<T> getList(){
        return numList;
    }

    public void add(T value){
        numList.add(value);
    }

    public double getAverage() throws EmptyListException {
        if (numList.isEmpty())
            throw new EmptyListException("List Kosong");

        double avg = 0;
        for (int i = 0; i < numList.size(); i++) {
            avg += (double) numList.get(i);
        }

        return avg;
    }

    public T getMax() throws EmptyListException {
        if (numList.isEmpty())
            throw new EmptyListException("List Kosong");

        return numList.get(numList.size() - 1);
    }

    public T getMin() throws EmptyListException {
        if (numList.isEmpty())
            throw new EmptyListException("List Kosong");

        return numList.get(0);
    }

    public double getMedian() throws EmptyListException {
        if (numList.isEmpty())
            throw new EmptyListException("List Kosong");

        if (numList.size() % 2 == 0) {
            return (double) numList.get(numList.size() / 2) + (double) numList.get((numList.size() / 2) - 1);
        } else {
            return (double) numList.get(numList.size() / 2);
        }
    }
}

class EmptyListException extends Exception {

    public EmptyListException(String message) {
        super("Message From EmpytListException: " + message);
    }

}

class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super("Message From InvalidInputException: " + message);
    }

}

class Menu {
    private Scanner mScanner = new Scanner(System.in);
    private StaticList<Integer> stat = new StaticList();

    public void printMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Tambah Angka");
        System.out.println("2. Tampilkan Rata-Rata");
        System.out.println("3. Tampilkan Nilai Maksimum");
        System.out.println("4. Tampilkan Nilai Minimum");
        System.out.println("5. Tampilkan Median");
        System.out.println("6. Tampilkan Daftar");
        System.out.println("0. Keluar");
    }

    public void render() {
        while (true) {
            this.printMenu();
            int menu = mScanner.nextInt();
            try {
                switch (menu) {
                    case 1:
                        System.out.println("Masukan Angka: ");
                        int value  = (int) CLIUtils.getNumber();
                        stat.add(value);
                        break;
                    case 2:
                        System.out.println("Rata-Rata: " + stat.getAverage());
                        break;
                    case 3:
                        Collections.sort(stat.getList());
                        System.out.println("Maksimum: " + stat.getMax());
                        break;
                    case 4:
                        Collections.sort(stat.getList());
                        System.out.println("Minimum" + stat.getMin());
                        break;
                    case 5:
                        Collections.sort(stat.getList());
                        System.out.println("Median: " + stat.getMedian());
                        break;
                    case 6:
                        Collections.sort(stat.getList());
                        String print = "";
                        for (int i = 0; i < stat.getList().size(); i++) {
                            print += stat.getList().get(i) + " ";
                        }

                        System.out.println("Sorted: " + print);
                        break;
                    case 0:
                        return;
                }
                this.printMenu();
                menu = mScanner.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.render();
    }

}
