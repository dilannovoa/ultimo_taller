package co.edu.sena.proyect_novoa.model.repository;

import co.edu.sena.proyect_novoa.connection_test.ConnectionPool;
import co.edu.sena.proyect_novoa.model.beans.Category;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoryRepositoryImpl implements Repository<Category> {
    private String sql= null;
    @Override
    public List<Category> listAllObj() throws SQLException {
        sql ="select c.category_id, c.category_firstname" +
                "from category_tbl c";
        List<Category> Category = new ArrayList<>();

        try(Connection conn = ConnectionPool.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Category c= createObj(rs);
                Category.add(c);
            }//while
        }//try
        return Category;
    }

    @Override
    public Category byIdObj(Integer id) throws SQLException {
        sql ="select c.category_firstname," +
                "from category_tbl c where c.category_id =?";
        Category Category = null;

        try(Connection conn= ConnectionPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Category = createObj(rs);
                }
            }
        }//Try
        return Category;
    }

    @Override
    public Integer saveObj(Category category) throws SQLException {
        int rowsAffected = 0;

        if(category.getCategory_id() != null && category.getCategory_id() > 0){
            sql ="update category_tbl, category_firstname = ?"+
                    "where category_id=?";
        } else {
            sql = "insert into category_tbl(category_firstname)"+
                    "values(upper(?))";

        }
        try(Connection conn = ConnectionPool.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setString(1, category.getCategory_name());
            if (category.getCategory_id() != null){
                ps.setInt(2, category.getCategory_id());
            }
            rowsAffected = ps.executeUpdate();
        }
        return rowsAffected;
    }

    @Override
    public void deleteObj(Integer id) throws SQLException {
        sql="delete from category_tbl where user_id=?";
        try(Connection conn= ConnectionPool.getConnection();
            PreparedStatement ps =conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }//Try
    }// deleteObj


    @Override
    public Category createObj(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setCategory_id(rs.getInt("category_id"));
        category.setCategory_firstname(rs.getString("category_firstname"));
        return category;
    }
}// createObj
