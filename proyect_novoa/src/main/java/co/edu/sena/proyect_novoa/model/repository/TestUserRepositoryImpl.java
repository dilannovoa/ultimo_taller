package co.edu.sena.proyect_novoa.model.repository;



import co.edu.sena.proyect_novoa.model.beans.User;

import java.sql.SQLException;

public class TestUserRepositoryImpl {
    public  static void main(String [] args ) throws SQLException{
        Repository<User> repository = new UserRepositoryImpl();

        System.out.println("========== saveObj Insert ==========");//insert

        User userInsert = new User();
        userInsert.setUser_firstname("Daniel");
        userInsert.setUser_lastname("Martinez");
        userInsert.setUser_email("DanielMart@gmail.com");
        userInsert.setUser_password("dAniel.12345");
        repository.saveObj(userInsert);

        userInsert.setUser_firstname("MARia");
        userInsert.setUser_lastname("paEz");
        userInsert.setUser_email("paEzMARia@gmail.com");
        userInsert.setUser_password("6780paEz");
        repository.saveObj(userInsert);

        System.out.println("========= listAllObj =========");
        repository.listAllObj().forEach(System.out::println);
        System.out.println();

        System.out.println("========= byIdObj ===========");
        System.out.println(repository.byIdObj(1));
        System.out .println();

        System.out .println("========= saveObj =========="); //update
        User userUpdate;
        userUpdate = new User();
        userUpdate.setUser_id(2);
        userUpdate. setUser_firstname("DAYANA");
        userUpdate.setUser_lastname("CAMACHO");
        userUpdate. setUser_email ("Dayana@email.com");
        userUpdate. setUser_password("10988876554");
        repository.saveObj(userInsert);


        repository. listAllObj().forEach(System.out::println);

        System.out.println();

        System.out.println("========== delete0bj ==========");
        repository.deleteObj(2);
        repository. listAllObj().forEach(System.out::println);

    }
}// TestUserRepositoryImpl
