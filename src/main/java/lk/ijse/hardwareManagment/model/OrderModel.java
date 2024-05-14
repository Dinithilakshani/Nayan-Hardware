package lk.ijse.hardwareManagment.model;

import javafx.collections.ObservableList;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.ItemDto;
import lk.ijse.hardwareManagment.dto.OrderDto;
import lk.ijse.hardwareManagment.dto.OrderdetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {

    public static ItemDto Searchbydescription(String description) throws SQLException {
        String sql = "SELECT * FROM item WHERE  description=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,description);
        ResultSet resultSet = pstm.executeQuery();

        ItemDto itemDto = null;




        if (resultSet.next()) {
            String code = resultSet.getString(1);
            String descriptions = resultSet.getString(2);
            int QtyOnHeand = resultSet.getInt(4);
            double unitPrice = resultSet.getDouble(3);


            itemDto  = new ItemDto( code,descriptions,QtyOnHeand,unitPrice);
        }
        return itemDto;
    }




    public boolean saveOrder(String orderId, String date, String customerId, String email, Double
            amount, ObservableList<OrderdetailsDto> observableList) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            System.out.println("lala");
            boolean saveOrder = save(new OrderDto(orderId, date,customerId, email));
            System.out.println(saveOrder);
            if (saveOrder == true) {
                System.out.println("hi");

                boolean saveOrderDetails = orderdetailsSave(orderId, observableList);
                if (saveOrderDetails == true) {
                    System.out.println("hi2");
                    boolean b = updateItemQty(observableList);

                    if (b == true) {
                        connection.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private boolean updateItemQty(ObservableList<OrderdetailsDto> observableList) throws SQLException {
        for (OrderdetailsDto dto : observableList) {
            ItemModel itemModel = new ItemModel();
            ItemDto itemDto = itemModel.searchItem(dto.getCode());
            int b = itemModel.UpdateItem(new ItemDto(dto.getCode(), dto.getDescription(),itemDto.getQtyOnHeand()- dto.getQty(),dto.getPrice()));
            System.out.println(b);
            if (0<b) {
                return false;
            }
        }
        return true;
    }


    private boolean orderdetailsSave(String orderId, ObservableList<OrderdetailsDto> observableList) throws SQLException {
        for (OrderdetailsDto dto : observableList) {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("insert into order_detail values(?,?,?,?,?)");
            pstm.setObject(1, orderId);
            pstm.setObject(2, dto.getDescription());
            pstm.setObject(4,dto.getPrice());

            pstm.setObject(3, dto.getQty());
            pstm.setObject(5,dto.getAmount());
            boolean b = pstm.executeUpdate() > 0;
            if (!b) {
                return false;
            }
        }
        return true;
    }

    private boolean save(OrderDto orderDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("insert into orders values(?,?,?,?)");
        pstm.setObject(1, orderDto.getOrderId());
        pstm.setObject(2, orderDto.getDate());
        pstm.setObject(3, orderDto.getCustomerID());
        pstm.setObject(4, orderDto.getEmail());
        return pstm.executeUpdate() > 0;
    }
    }











