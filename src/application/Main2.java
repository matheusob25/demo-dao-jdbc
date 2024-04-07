package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;


//testes das funções de department
public class Main2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department department = new Department("movies");
        System.out.println("============= insert department "+ department.getName() +" ===================");
        departmentDao.insert(department);
        System.out.println("===========================================================");

        System.out.println("\n\n======================= Find all departments ====================================");
        //FindAll department
        List<Department> departments = new ArrayList<>();
        departments = departmentDao.findAll();
        for (Department department1 : departments) {
            System.out.println(department1);
        }
        System.out.println("===========================================================");
        System.out.println("\n\n===================== Find by id ======================================");
        System.out.println(departmentDao.findById(department.getId()));
        System.out.println("===========================================================");

        System.out.println("\n\n===================== delete by id ======================================");
        Department depTest = new Department("feijões");
        departmentDao.insert(depTest);
        departmentDao.deleteById(depTest.getId());
        System.out.println("===========================================================");



    }
}
