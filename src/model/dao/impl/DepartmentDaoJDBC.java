package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                                    "INSERT INTO department "
                                        + "(Name) "
                                        + "values (?);", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,department.getName());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Department id: "+ id);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("ERROR, no rows affected!!");
            }


        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                                        "UPDATE department SET Name = ? "
                                            + "WHERE Id = ?;");
            st.setString(1, department.getName());
            st.setInt(2, department.getId());
            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
       PreparedStatement st = null;
       ResultSet rs = null;

       try {
           st = conn.prepareStatement("SELECT * from department WHERE Id = ? ");

           st.setInt(1, id);

           rs = st.executeQuery();
           if(rs.next()){
               Department department = instantiateDepartment(rs);
               return department;
           }else{
               return null;
           }
       }catch (SQLException e){
           throw new DbException(e.getMessage());
       }finally {
           DB.closeStatement(st);
           DB.closeResultSet(rs);
       }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department ORDER BY Name");
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            while (rs.next()){
                list.add(instantiateDepartment(rs));
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department department = new Department();
        department.setId(rs.getInt("Id"));
        department.setName(rs.getString("Name"));
        return department;
    }


}
