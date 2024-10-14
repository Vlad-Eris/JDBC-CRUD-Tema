package ro.emanuel.oop.jdbc;

import java.util.Properties;

public class AuthorsMain {
    
    public static void main(String[] args) throws SQLException {
        
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");
        
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library",
                connectionProps);
        
        Statement stmt = conn.createStatement();
        
        // INSERT
        String authorName = "Mihai Eminescu";
        String authorBirthDate = "1850-01-15";
        String authorNationality = "Român";
        
        String sqlInsert = "INSERT INTO authors (name, birth_date, nationality) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ps.setString(1, authorName);
        ps.setString(2, authorBirthDate);
        ps.setString(3, authorNationality);
        ps.executeUpdate();
        System.out.println("Author inserted");
        
        // UPDATE
        String update = "UPDATE authors SET nationality = ? WHERE name = ?";
        PreparedStatement ps2 = conn.prepareStatement(update);
        ps2.setString(1, "Român (Moldovean)");
        ps2.setString(2, "Mihai Eminescu");
        ps2.executeUpdate();
        System.out.println("Author updated");
        
        // DELETE
        String deleteValue = "DELETE FROM authors WHERE id >= ?";
        PreparedStatement ps3 = conn.prepareStatement(deleteValue);
        ps3.setInt(1, 5);
        ps3.executeUpdate();
        System.out.println("Authors deleted");
        
        // SELECT
        ResultSet rs = stmt.executeQuery("SELECT * FROM authors");
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String birthDate = rs.getString("birth_date");
            String nationality = rs.getString("nationality");
            
            System.out.println(id + " | " + name + " | " + birthDate + " | " + nationality);
        }
        
        conn.close();        
    }
}