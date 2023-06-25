package co.edu.sena.proyect_novoa.model.beans;



public class Category {
    private Integer category_id;
    private String category_firstname;

    public Category() {
    }

    public Category(Integer category_id, String category_firstname) {
        this.category_id = category_id;
        this.category_firstname = category_firstname;
    }

    public Category(String category_firstname){
        this.category_firstname=category_firstname;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_firstname;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public void setCategory_firstname(String category_firstname) {
        this.category_firstname = category_firstname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_firstname='" + category_firstname + '\'' +
                '}';
    }
}
