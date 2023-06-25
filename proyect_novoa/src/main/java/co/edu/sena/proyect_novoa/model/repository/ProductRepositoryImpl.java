package co.edu.sena.proyect_novoa.model.repository;
import co.edu.sena.proyect_novoa.connection_test.ConnectionPool;
import co.edu.sena.proyect_novoa.model.beans.Category;
import co.edu.sena.proyect_novoa.model.beans.Product;
import co.edu.sena.proyect_novoa.model.beans.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Product> {
    private  String sql = null;

    @Override
    public List<Product> listAllObj() throws SQLException {
        sql = "select p.*, c.category_firstname from product_tbl p inner join category_tbl c on " +
                "(p.category_id = c.category_id) order by p.product_name ";


        List<Product> products= new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Product p = createObj(rs);
                products.add(p);
            }//While
        }// try
        return products;
    }

    @Override
    public Product byIdObj(Integer id) throws SQLException {
        sql = "select p.*, c.category_firstname from product_tbl p inner join category_tbl c on " +
                "(p.category_id = c.category_id) where p.category_id =?";

        Product product = null;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    product = createObj(rs);
                }
            }
        }//Try
        return product;
    }


    @Override
    public Integer saveObj(Product product) throws SQLException {
        int rowsAffected =0;
        if (product.getProduct_id()!=null && product.getProduct_id()>0){
            sql = "update product_tbl set product_name=?, product_value=?,category_id=? "
                    +" where product_id=?";

        } else {
            sql = "insert into product_tbl( product_name, product_value, category_id) "+
                    "values(upper(?), (?),(?))";
        }
        try(Connection conn =ConnectionPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProduct_name());
            ps.setFloat(2, product.getProduct_value());
            ps.setInt(3, product.getCategory_id());
            if (product.getProduct_id()!=null) {
                ps.setInt(4,product.getProduct_id());
            }
            rowsAffected =ps.executeUpdate();
        }
        return rowsAffected;
    }

    @Override
    public void deleteObj(Integer id) throws SQLException {
        sql= "delete from product_tbl where product_id=? ";
        try(Connection conn = ConnectionPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }//try
    }// deleteObj

    @Override
    public Product createObj(ResultSet rs) throws SQLException {
        Product product = new Product();
        Category category = new Category();
        category.setCategory_id(rs.getInt("category_id"));
        category.setCategory_firstname(rs.getString("category_firstname"));
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_value(rs.getFloat("product_value"));
        product.setCategory_id(rs.getInt("category_id"));
        return product;
    }
}
