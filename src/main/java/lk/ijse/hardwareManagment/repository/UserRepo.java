package lk.ijse.hardwareManagment.repository;

import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepo {
    public boolean saveUser(User user) throws SQLException {
        String sql = "INSERT INTO admin VALUES(?,?,?) ";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getEmail());
        preparedStatement.setString(3,user.getPassword());

        return preparedStatement.executeUpdate()>0;
    }
}
