import java.util.*;

public class Ecommerce {

    // productId -> stock
    static HashMap<String, Integer> stockMap = new HashMap<>();

    // waiting list for each product
    static HashMap<String, LinkedList<Integer>> waitingList = new HashMap<>();

    // Check stock availability
    public static void checkStock(String productId) {
        int stock = stockMap.getOrDefault(productId, 0);
        System.out.println(productId + " → " + stock + " units available");
    }

    // Purchase item
    public static void purchaseItem(String productId, int userId) {

        int stock = stockMap.getOrDefault(productId, 0);

        if (stock > 0) {
            stockMap.put(productId, stock - 1);
            System.out.println("Success! " + (stock - 1) + " units remaining");
        }
        else {
            waitingList.putIfAbsent(productId, new LinkedList<>());
            waitingList.get(productId).add(userId);

            int position = waitingList.get(productId).size();
            System.out.println("Added to waiting list, position #" + position);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Initial stock
        stockMap.put("IPHONE15_256GB", 100);

        System.out.print("Enter Product ID: ");
        String productId = sc.nextLine();

        checkStock(productId);

        System.out.print("Enter User ID to purchase: ");
        int userId = sc.nextInt();

        purchaseItem(productId, userId);

        sc.close();
    }
}
