package lk.ijse.hardwareManagment.model;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.ItemDto;
import lk.ijse.hardwareManagment.tm.ItemTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {


    public static ItemDto searchById(String description) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM item WHERE  description=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, description);
        ResultSet resultSet = pstm.executeQuery();

        ItemDto itemDto = null;
        if (resultSet.next()) {
            String code = resultSet.getString(1);
            String descriptions = resultSet.getString(2);
            String qty = resultSet.getString(4);
            String price = resultSet.getString(3);

            itemDto = new ItemDto(code, descriptions, qty, price);
        }
        return itemDto;
    }


    public int DeleteItem(String id) {

        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM item WHERE code = ?");
            pstm.setObject(1, id);

            return pstm.executeUpdate();

        } catch (SQLException var5) {
            throw new RuntimeException();
        }
    }

    public int SaveItem(String code, String description, String qty, String price) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO item VALUES(?, ?, ?, ?)");
            pstm.setObject(1, code);
            pstm.setObject(2, description);
            pstm.setObject(4, qty);
            pstm.setObject(3, price);
            return pstm.executeUpdate();

        } catch (SQLException var10) {
            throw new RuntimeException();
        }
    }

    public int UpdateItem(String code, String description, String qty, String price) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE item SET description = ?, qtyOnHand= ?, unitPrice = ? WHERE code = ?");
            pstm.setObject(1, code);
            pstm.setObject(2, description);
            pstm.setObject(4, qty);
            pstm.setObject(3, price);
            return pstm.executeUpdate();

        } catch (SQLException var8) {
            throw new RuntimeException();
        }


    }


    public ArrayList<ItemDto> tble() {
        ArrayList<ItemDto> item = new ArrayList<>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from item");
            ResultSet resultSet = pstm.executeQuery();
            ItemDto itemDto;
            while (resultSet.next()) {

                itemDto = new ItemDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getString(3)
                );
            }

            return item;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}




