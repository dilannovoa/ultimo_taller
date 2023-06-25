package co.edu.sena.proyect_novoa.servlets;


import co.edu.sena.proyect_novoa.model.beans.Category;
import co.edu.sena.proyect_novoa.model.repository.CategoryRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registerCategory")
public class CategoryRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //collect all form data
        String category_firstname = request.getParameter("category_firstname");

        // fill it up in a User bean
        Category category = new  Category(category_firstname);

        // call Repository layer and save the user object to DB
        co.edu.sena.proyect_novoa.model.repository.Repository<Category> repository = new CategoryRepositoryImpl();
        int rows = 0;
        try {
            rows = repository.saveObj(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String infoMessage = null;
        if (rows == 0){
            //infoMessage = "¡TIENE UN PROBLEMA!"
            System.out.println("¡TIENE UN PROBLEMA!");
        } else {
            //infoMessage = "¡EXITOSO!"
            System.out.println("¡EXITOSO!");
        }

        //
    }
}
