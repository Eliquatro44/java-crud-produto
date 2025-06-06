package src;

import java.text.SimpleDateFormat;
import java.util.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US); // garante que ponto seja usado como separador decimal
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int n = 0;
        try {
            System.out.print("Quantos produtos serão cadastrados? ");
            n = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite um número inteiro válido.");
            sc.close();
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("Produto #" + i + ":");
            System.out.print("Comum, usado ou importado (c/u/i)? ");
            char type = sc.next().charAt(0);
            sc.nextLine(); // limpar buffer antes de ler nome

            System.out.print("Nome: ");
            String name = sc.nextLine();

            System.out.print("Preço: ");
            double price = 0.0;
            try {
                price = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Erro: preço inválido. Use ponto como separador decimal.");
                sc.close();
                return;
            }

            if (type == 'c') {
                products.add(new Product(name, price));
            } else if (type == 'u') {
                System.out.print("Data de fabricação (dd/MM/yyyy): ");
                Date date;
                try {
                    date = sdf.parse(sc.next());
                } catch (Exception e) {
                    System.out.println("Erro: data inválida.");
                    sc.close();
                    return;
                }
                products.add(new UsedProduct(name, price, date));
            } else if (type == 'i') {
                System.out.print("Taxa de importação: ");
                double fee = 0.0;
                try {
                    fee = sc.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Erro: taxa inválida.");
                    sc.close();
                    return;
                }
                products.add(new ImportedProduct(name, price, fee));
            } else {
                System.out.println("Tipo de produto inválido.");
                i--; // repetir iteração para o mesmo número de produto
            }
        }

        System.out.println("\nETIQUETAS DE PREÇO:");
        for (Product p : products) {
            System.out.println(p.priceTag());
        }

        sc.close();
    }
}
