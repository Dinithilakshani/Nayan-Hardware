package lk.ijse.hardwareManagment.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
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
            PreparedStatement pstm = connection.prepareStatement("select * from transportdetails");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                TransportDeto transportDeto = new TransportDeto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(5)

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




    public int Savetransport(String id, String area,String date ,String time, String vehical ) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO transportdetails VALUES(?, ?, ?, ?,?)");
            pstm.setObject(3, vehical);
            pstm.setObject(1, area);
            pstm.setObject(5, date);
            pstm.setObject(2, time);
            pstm.setObject(4, id);
            
            return pstm.executeUpdate();

        } catch (SQLException var10) {
            throw new RuntimeException();
        }
    }


    public int Deletetransport(String id) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM tansportDetails WHERE id = ?");
            pstm.setObject(1, id);

            return pstm.executeUpdate();

        } catch (SQLException var5) {
            throw new RuntimeException();
        }
    }
    public static ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        DbConnection Dbconnection;
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select description , qty from order_detail";

        ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        while (resultSet.next()) {
            String description = resultSet.getString("description");
            Integer qty = resultSet.getInt("stockLevel");
            series.getData().add(new XYChart.Data<>(description, qty));
        }
        datalist.add(series);
        return datalist;
    }

    public int updateTransport(TransportDeto dto) {
        try {
            System.out.println(dto.getTid());
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE transportdetails  SET  T_area = ?, t_time = ?, T_id = ? , T_Date = ?WHERE id = ?");

            pstm.setObject(4, dto.getDate());
            pstm.setObject(5, dto.getTid());
            pstm.setObject(2, dto.getTtime());
            pstm.setObject(3, dto.getVehicalId());
            pstm.setObject(1, dto.getTarea());

            return pstm.executeUpdate();

        } catch (SQLException var8) {
            throw new RuntimeException();
        }
    }
    }


