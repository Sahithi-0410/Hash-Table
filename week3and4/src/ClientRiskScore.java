import java.util.*;

public class ClientRiskScore {

    // Client class
    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore + "($" + accountBalance + ")";
        }
    }

    // 🔵 Bubble Sort (Ascending riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // visualization
                    System.out.println("Swap: " + arr[j].name + " ↔ " + arr[j + 1].name);
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("Total swaps: " + swaps);
    }

    // 🟢 Insertion Sort (Descending riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j]; // shift
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // Comparator: riskScore DESC, then accountBalance DESC
    private static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    // 🔝 Top N highest risk clients
    public static List<Client> getTopClients(Client[] arr, int n) {
        List<Client> result = new ArrayList<>();

        for (int i = 0; i < Math.min(n, arr.length); i++) {
            result.add(arr[i]);
        }

        return result;
    }

    // ▶️ Main method
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Bubble Sort (Ascending)
        System.out.println("Bubble Sort (Ascending):");
        bubbleSort(clients);
        System.out.println("Result: " + Arrays.toString(clients));

        // Insertion Sort (Descending)
        System.out.println("\nInsertion Sort (Descending):");
        insertionSort(clients);
        System.out.println("Result: " + Arrays.toString(clients));

        // Top 3 (use 10 in real scenario)
        System.out.println("\nTop Risk Clients:");
        List<Client> topClients = getTopClients(clients, 3);

        for (Client c : topClients) {
            System.out.println(c.name + " (" + c.riskScore + ")");
        }
    }
}