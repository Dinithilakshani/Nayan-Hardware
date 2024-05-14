package lk.ijse.hardwareManagment.model;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.TransportDeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportModel {
    public static ArrayList<TransportDeto> tble() {
        ArrayList<TransportDeto> Transport = new ArrayList<>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from transportDetails");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                TransportDeto transportDeto = new TransportDeto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4)

                );
                Transport.add(transportDeto);

            }
            return Transport;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static TransportDeto searchById(String vehicalid) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM transportdetails WHERE  id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, vehicalid);
        ResultSet resultSet = pstm.executeQuery();

        TransportDeto transportDeto = null;


        if (resultSet.next()) {
            String Id = resultSet.getString(3);
            String time = resultSet.getString(4);
            String date = resultSet.getString(2);
            String VId = resultSet.getString(5);
            String area = resultSet.getString(1);


            transportDeto = new TransportDeto(Id, time, date, VId, area);
        }
        return transportDeto;
    }




    public int Savetransport(String id, String area, String time, String vehical, String date) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO transportdetails VALUES(?, ?, ?, ?,?)");
            pstm.setObject(3, id);
            pstm.setObject(1, area);
            pstm.setObject(2, time);
            pstm.setObject(4, vehical);
            pstm.setObject(5, date);
            return pstm.executeUpdate();

        } catch (SQLException var10) {
            throw new RuntimeException();
        }

    }

    public int Updatetransport(String id, String area, String time, String vehical, String date) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE TransportDetails SET t_time = ?, T_area = ? ,id =?,T_Date WHERE T_id = ?");
            pstm.setObject(3, id);

            pstm.setObject(1, area);
            pstm.setObject(2, time);
            pstm.setObject(4, vehical);
            pstm.setObject(5, date);
            return pstm.executeUpdate();


        } catch (SQLException var8) {
            throw new RuntimeException();
        }

    }

    public int Deletetransport(String id) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM tansportDetails WHERE T_id = ?");
            pstm.setObject(1, id);

            return pstm.executeUpdate();

        } catch (SQLException var5) {
            throw new RuntimeException();
        }
    }
}
