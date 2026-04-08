
import java.time.LocalTime;
import java.util.*;

public class TransactionFeeSorting {

    // Transaction class
    static class Transaction {
        String id;
        double fee;
        LocalTime timestamp;

        public Transaction(String id, double fee, String ts) {
            this.id = id;
            this.fee = fee;
            this.timestamp = LocalTime.parse(ts);
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    // 🔵 Bubble Sort (≤100)
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        boolean swapped;
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early stop
        }

        System.out.println("Bubble Sort → Passes: " + passes + ", Swaps: " + swaps);
    }

    // 🟢 Insertion Sort (100–1000)
    public static void insertionSortByFeeAndTime(List<Transaction> list) {
        int shifts = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
                shifts++;
            }

            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort → Shifts: " + shifts);
    }

    // Comparator (fee → timestamp)
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // 🚨 Outlier detection (>50)
    public static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                result.add(t);
            }
        }
        return result;
    }

    // ⚙️ Smart sorter (auto-select algorithm)
    public static void sortTransactions(List<Transaction> list) {
        int size = list.size();

        if (size <= 100) {
            System.out.println("Using Bubble Sort...");
            bubbleSortByFee(list);
        } else if (size <= 1000) {
            System.out.println("Using Insertion Sort...");
            insertionSortByFeeAndTime(list);
        } else {
            System.out.println("Large dataset → Use advanced sort (not implemented)");
        }
    }

    // ▶️ Main method (demo)
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Sort based on size
        sortTransactions(transactions);

        System.out.println("Sorted Transactions: " + transactions);

        // Outliers
        List<Transaction> outliers = findOutliers(transactions);
        System.out.println("High-fee outliers: " + outliers);
    }
}
