import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchUpdateDemo {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","123456qW");
            String query = "INSERT INTO employee (name, age) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,"John");
            preparedStatement.setInt(2,25);
            preparedStatement.addBatch();
            preparedStatement.setString(1,"Jane");
            preparedStatement.setInt(2,30);
            preparedStatement.addBatch();
            preparedStatement.setString(1,"Tom");
            preparedStatement.setInt(2,35);
            preparedStatement.addBatch();

            int [] updateCount = preparedStatement.executeBatch();
            System.out.println(" Update Count");
            for (int update:updateCount
                 ) {
                System.out.println(update);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
