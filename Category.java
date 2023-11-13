import java.io.*;

class Category {
    private String categoryId;
    private String categoryName;

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("categories.txt", true))) {
            writer.println(categoryId + "," + categoryName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}