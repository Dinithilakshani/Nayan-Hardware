package lk.ijse.hardwareManagment.model;

import com.mysql.cj.callback.UsernameCallback;
import lk.ijse.hardwareManagment.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean verifyCredentials(String username, String password) throws SQLException {
        try {
            DbConnection instance = DbConnection.getInstance();
            Connection connection = instance.getConnection();

            String sql = "select password from admin where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (password.equals((resultSet.getString(1)))) {
                    ;
                    return true;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return false;
    }

    public boolean userCheck(String userName, String password) {
        try {
            System.out.println("hii"+userName);
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from admin where username=? && password=?");
            pstm.setObject(1,userName);
            pstm.setObject(2, password);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
