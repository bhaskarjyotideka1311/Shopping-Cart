package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private Map<String, Item> cart;
    class Item{
        double price;
        int quantity;
        Item(double price, int quantity){
            this.price = price;
            this.quantity = quantity;
        }


    }
    public Main(){
        this.cart = new HashMap<>();
    }

    //Add Item to cart
    public void addItem(String itemName, double price, int quantity){

        if(cart.containsKey(itemName)){
            cart.get(itemName).quantity += quantity;
        }
        else{
            cart.put(itemName, new Item(price, quantity));
        }
        System.out.println(quantity + "X" + itemName + " added to the cart");
    }

    //Remove Item
    public void remove(String itemName){
        if(cart.containsKey(itemName)){
            cart.remove(itemName);
            System.out.println(itemName + " removed from the cart");
        }else{
            System.out.println("Not in the cart");
        }
    }

    //Modify item quantity in the cart
    public void modify(String itemName, int quantity){
        if(cart.containsKey(itemName)){
            if(quantity>0){
                cart.get(itemName).quantity = quantity;
                System.out.println("Quantity of " +itemName+ " updated to" +quantity+ ".");
            }else{
                cart.remove(itemName);
            }
        } else{
            System.out.println(itemName + " item not found in the cart");
        }
    }

    //Check if item exist in the cart
    public boolean check(String itemName){
        return cart.containsKey(itemName);
    }

    //Display cart contents
    public void viewCart(){
        if(cart.isEmpty()){
            System.out.println("Cart is empty");
        }
        else{
            System.out.println("Your cart contains: ");
            for(Map.Entry<String, Item> entry : cart.entrySet()){
                System.out.println(entry.getKey() +":"+ entry.getValue().quantity +"@$" + entry.getValue().price + " each");
            }
        }
    }

    //Bill
    public void calculateBill(double taxRate){
        double subTotal = 0;
        for(Item item : cart.values()){
            subTotal += item.price + item.quantity;
        }
        double tax = subTotal * taxRate;
        double total = subTotal + tax;

        System.out.printf("Subtotal : $%.2f\n", subTotal);
        System.out.printf("Tax (%.0f%%): $%.2f\n", taxRate * 100, tax);
        System.out.printf("Total: $%.2f\n", total);
    }

    //Clear the cart
    public void clear(){
        cart.clear();
        System.out.println("cart has been cleared");
    }

    public static void main(String[] args) {
        Main cart = new Main();
        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            //Display menu
            System.out.println("\n1. Add item to the cart");
            System.out.println("2. Remove item from the cart");
            System.out.println("3. Modify item");
            System.out.println("4. Bill calculation");
            System.out.println("5. clear cart");
            System.out.println("6. Exit program");

            choice = sc.nextInt();
            sc.nextLine(); // for new line

            switch (choice){
                case 1:
                    //Add item
                    System.out.println("Enter item name");
                    String itemName = sc.nextLine();
                    System.out.println("Enter the price");
                    double price = sc.nextDouble();
                    System.out.println("Enter the quantity");
                    int quantity = sc.nextInt();
                    cart.addItem(itemName, price, quantity);
                    break;
                case 2:
                    //Remove item
                    System.out.println("Remove item");
                    System.out.println("Enter the item name");
                    itemName = sc.nextLine();
                    cart.remove(itemName);
                    break;
                case 3:
                    //Modify
                    System.out.println("Enter the item name to modify");
                    itemName = sc.nextLine();
                    System.out.println("Enter the quantity");
                    quantity = sc.nextInt();
                    cart.modify(itemName, quantity);
                    break;
                case 4:
                    //Calculate bill
                    cart.viewCart();
                    cart.calculateBill(0.1); //taxRate = 10%
                    break;
                case 5:
                    //Clear cart
                    cart.clear();
                    break;
                case 6:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while(choice != 6);

        sc.close();

    }
}