import java.util.*;

public class HistoricalTradeVolume {

    // Trade class
    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    // 🔵 Merge Sort (Ascending, Stable)
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable comparison
            if (arr[i].volume <= arr[j].volume) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // Copy back
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 🟢 Quick Sort (Descending)
    public static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    // Lomuto Partition
    private static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Descending order
            if (arr[j].volume > pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Trade[] arr, int i, int j) {
        Trade temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 🔀 Merge two sorted arrays (Ascending)
    public static Trade[] mergeSortedTrades(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // 📊 Total volume calculation
    public static int calculateTotalVolume(Trade[] arr) {
        int total = 0;
        for (Trade t : arr) {
            total += t.volume;
        }
        return total;
    }

    // ▶️ Main method
    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort (Ascending)
        mergeSort(trades, 0, trades.length - 1);
        System.out.println("MergeSort (Ascending): " + Arrays.toString(trades));

        // Quick Sort (Descending)
        quickSortDesc(trades, 0, trades.length - 1);
        System.out.println("QuickSort (Descending): " + Arrays.toString(trades));

        // Morning session (already sorted)
        Trade[] morning = {
                new Trade("t1", 100),
                new Trade("t2", 300)
        };

        // Afternoon session (already sorted)
        Trade[] afternoon = {
                new Trade("t3", 200),
                new Trade("t4", 400)
        };

        // Merge sessions
        Trade[] merged = mergeSortedTrades(morning, afternoon);
        System.out.println("Merged Trades: " + Arrays.toString(merged));

        // Total volume
        int total = calculateTotalVolume(merged);
        System.out.println("Total Volume: " + total);
    }
}
