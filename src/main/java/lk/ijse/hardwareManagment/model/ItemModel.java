package lk.ijse.hardwareManagment.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.EmployeeDto;
import lk.ijse.hardwareManagment.dto.ItemDto;
import lk.ijse.hardwareManagment.tm.ItemTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ItemModel {


    private Object code;
    private Object qty;
    private Object price;
    private Object descriptions;

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
            int qty = resultSet.getInt(4);
            double price = resultSet.getDouble(3);

            itemDto = new ItemDto(code, descriptions, qty, price);
        }
        return itemDto;
    }

    public static ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "select description , qty from order_detail";

            ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();

            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            XYChart.Series<String, Integer> series = new XYChart.Series<>();

            while (resultSet.next()) {
                String description = resultSet.getString("description");
                Integer qty = resultSet.getInt("qty");
                series.getData().add(new XYChart.Data<>(description, qty));
            }
            datalist.add(series);
            return datalist;
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

    public int SaveItem(String code, String description, int qtyOnHeand, double price) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO item VALUES(?, ?, ?, ?)");
            pstm.setObject(1, code);
            pstm.setObject(2, description);
            pstm.setObject(4, qtyOnHeand);
            pstm.setObject(3, price);
            return pstm.executeUpdate();

        } catch (SQLException var10) {
            throw new RuntimeException();
        }
    }

    public int UpdateItem(ItemDto dto) {
        try {
            System.out.println(dto.getCode());
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE item SET description = ?, unitPrice= ?, qtyOnHand = ? WHERE code = ?");

            pstm.setObject(4, dto.getCode());
            pstm.setObject(1, dto.getDesctription());
            pstm.setObject(3, dto.getQtyOnHeand());
            pstm.setObject(2, dto.getPrice());
            return pstm.executeUpdate();

        } catch (SQLException var8) {
            throw new RuntimeException();
        }







    } public ArrayList<ItemDto> tble() {
        ArrayList<ItemDto> item = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from item");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                ItemDto itemDto = new ItemDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(4),
                        resultSet.getDouble(3)

                );
                item.add(itemDto);

            }
            return item;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }




    public ItemDto searchItem(String code) throws SQLException {
        String sql = "SELECT * FROM item WHERE  code=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,code);
        ResultSet resultSet = pstm.executeQuery();

        ItemDto itemDto = null;





        if (resultSet.next()) {
            String codes = resultSet.getString(2);
            String descrption = resultSet.getString(1);
            int qtyOnHenad = resultSet.getInt(4);
            double price = resultSet.getDouble(3);


            itemDto  = new ItemDto( codes, descrption, qtyOnHenad,price);
        }
        return itemDto;
    }




    public ArrayList<ItemDto> getAll() {
        ArrayList<ItemDto> allData = new ArrayList<ItemDto>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                        new ItemDto(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getDouble(4)


                );

            }
            return allData;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    // public boolean UpdateItem(ItemDto itemDto) {




}






