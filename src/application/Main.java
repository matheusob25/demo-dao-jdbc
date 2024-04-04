package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);

        System.out.println("\n\n=============== FindByDepartment ================");
        List<Seller> list = sellerDao.findByDepartment(new Department(1, null));
        for (Seller s : list) {
            System.out.println(s);
        }
    }
}