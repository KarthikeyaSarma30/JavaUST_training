import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Transaction {

    private int transactionId;
    private String accountNumber;
    private String transactionType;
    private double amount;
    private LocalDate transactionDate;

    // Constructor
    public Transaction(int transactionId,
                       String accountNumber,
                       String transactionType,
                       double amount,
                       LocalDate transactionDate) {

        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    // toString
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountNumber='" + accountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }

    // Main Method
    public static void main(String[] args) {

        List<Transaction> transactions = Arrays.asList(

                new Transaction(101, "ACC1001",
                        "Debit", 25000,
                        LocalDate.of(2026, 5, 10)),

                new Transaction(102, "ACC1002",
                        "Credit", 75000,
                        LocalDate.of(2026, 5, 15)),

                new Transaction(103, "ACC1003",
                        "Debit", 60000,
                        LocalDate.of(2026, 5, 20)),

                new Transaction(104, "ACC1004",
                        "Credit", 40000,
                        LocalDate.of(2026, 5, 22)),

                new Transaction(105, "ACC1005",
                        "Credit", 90000,
                        LocalDate.of(2026, 5, 25))
        );

        // 1. Find all debit transactions
        System.out.println("Debit Transactions:");

        List<Transaction> debitTransactions =
                transactions.stream()
                .filter(t -> t.getTransactionType()
                        .equalsIgnoreCase("Debit"))
                .toList();

        debitTransactions.forEach(
                t -> System.out.println(t));

        // 2. Transactions above 50000
        System.out.println("\nTransactions Above 50000:");

        List<Transaction> highTransactions =
                transactions.stream()
                .filter(t -> t.getAmount() > 50000)
                .toList();

        highTransactions.forEach(
                t -> System.out.println(t));

        // 3. Total credited amount
        double totalCreditAmount =
                transactions.stream()
                .filter(t -> t.getTransactionType()
                        .equalsIgnoreCase("Credit"))
                .mapToDouble(t -> t.getAmount())
                .sum();

        System.out.println(
                "\nTotal Credited Amount: "
                        + totalCreditAmount);

        // 4. Group by transaction type
        System.out.println("\nGrouped Transactions:");

        Map<String, List<Transaction>>
                groupedTransactions =
                transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTransactionType()
                ));

        System.out.println(groupedTransactions);

        // 5. Find latest transaction
        System.out.println("\nLatest Transaction:");

        Transaction latestTransaction =
                transactions.stream()
                .max(Comparator.comparing(
                        t -> t.getTransactionDate()))
                .get();

        System.out.println(latestTransaction);

        // 6. Sort by amount descending
        System.out.println(
                "\nSorted Transactions Descending:");

        List<Transaction> sortedTransactions =
                transactions.stream()
                .sorted(Comparator.comparing(
                        (Transaction t) ->
                                t.getAmount())
                        .reversed())
                .toList();

        sortedTransactions.forEach(
                t -> System.out.println(t));

        // 7. Parallel Stream
        System.out.println(
                "\nParallel Stream Processing:");

        transactions.parallelStream()
                .forEach(t -> System.out.println(t));
    }
}