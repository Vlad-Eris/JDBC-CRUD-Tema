package ro.emanuel.oop.jdbc;

import java.util.Properties;

public class BooksMain {
    
    public static void main(String[] args) throws SQLException {
        
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");
        
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library",
                connectionProps);
        
        Statement stmt = conn.createStatement();
        
        // INSERT
        String bookTitle = "Luceafărul";
        int publicationYear = 1883;
        String genre = "Poezie";
        
        String sqlInsert = "INSERT INTO books (title, publication_year, genre) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ps.setString(1, bookTitle);
        ps.setInt(2, publicationYear);
        ps.setString(3, genre);
        ps.executeUpdate();
        System.out.println("Book inserted");
        
        // UPDATE
        String update = "UPDATE books SET genre = ? WHERE title = ?";
        PreparedStatement ps2 = conn.prepareStatement(update);
        ps2.setString(1, "Poem");
        ps2.setString(2, "Luceafărul");
        ps2.executeUpdate();
        System.out.println("Book updated");
        
        // DELETE
        String deleteValue = "DELETE FROM books WHERE id >= ?";
        PreparedStatement ps3 = conn.prepareStatement(deleteValue);
        ps3.setInt(1, 5);
        ps3.executeUpdate();
        System.out.println("Books deleted");
        
        // SELECT
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int year = rs.getInt("publication_year");
            String bookGenre = rs.getString("genre");
            
            System.out.println(id + " | " + title + " | " + year + " | " + bookGenre);
        }
        
        conn.close();        
    }
}