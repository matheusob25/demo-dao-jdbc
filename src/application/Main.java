package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(1);
        System.out.println("================ FindByID ================");
        System.out.println(seller);
        System.out.println("=========================================");

        System.out.println("\n\n=============== FindByDepartment ================");
        List<Seller> list = sellerDao.findByDepartment(new Department(1, null));
        for (Seller s : list) {
            System.out.println(s);
        }

//        System.out.println("\n\n=============== insert seller ================");
//        System.out.println("Enter the new seller's details: ");
//        System.out.print("Name: ");
//        String name = sc.nextLine();
//        System.out.print("Email: ");
//        String email = sc.nextLine();
//        System.out.print("Birth date: ");
//        LocalDate birthdate = LocalDate.parse(sc.nextLine(),formatter);
//        System.out.print("Base salary: ");
//        Double baseSalary = sc.nextDouble();
//        System.out.print("Department id: ");
//        Integer depId = sc.nextInt();
//        Seller insertSeller = new Seller(name,email,birthdate,baseSalary,new Department(depId, null));
//        sellerDao.insert(insertSeller);

        System.out.println("=========================================");
        System.out.println("\n\n ================== Seller update =======================");
        seller = sellerDao.findById(8);
        seller.setName("Rogerin do maracujá");
        sellerDao.update(seller);
        System.out.println("Update completed: new seller: " + seller);

        System.out.println("=========================================");
        System.out.println("\n\n=============== FindAll ================");
        List<Seller> sellers = sellerDao.findAll();
        for (Seller s : sellers){
            System.out.println(s);
        }
        System.out.println("=========================================");
        System.out.println("\n\n================ Delete sellers =========================");
        sellerDao.deleteById(13);



        sc.close();
    }
}