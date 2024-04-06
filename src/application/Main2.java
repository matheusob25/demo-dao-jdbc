package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department department = new Department("music");
        departmentDao.insert(department);

        List<Department> departments = new ArrayList<>();
        departments = departmentDao.findAll();
        for (Department department1 : departments) {
            System.out.println(department1);
        }
    }
}
