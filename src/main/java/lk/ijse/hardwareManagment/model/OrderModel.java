package lk.ijse.hardwareManagment.model;

import javafx.collections.ObservableList;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.ItemDto;
import lk.ijse.hardwareManagment.dto.OrderDto;
import lk.ijse.hardwareManagment.dto.OrderdetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public static int deleteCustomer(String orderid) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE id = ?");
            pstm.setObject(1, orderid);
            return pstm.executeUpdate();

        } catch (SQLException var5) {
            throw new RuntimeException();

        }
    }

    public static OrderDto searchById(String email) throws SQLException {
        String sql = "SELECT * FROM order_detail WHERE  email=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, email);
        ResultSet resultSet = pstm.executeQuery();

        OrderDto orderDto = null;


        if (resultSet.next()) {
            String OrderId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String qty = resultSet.getString(3);
            String amount = resultSet.getString(4);
            String emails = resultSet.getString(5);


            orderDto = new OrderDto(OrderId, description, qty, amount, emails);
        }
        return orderDto;
    }


    public int saveCustomer(String amount, String qty, String payment, String description, String email, String orderId) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO order_detail VALUES(?, ?, ?, ?,?)");
            pstm.setObject(1, orderId);
            pstm.setObject(2, description);
            pstm.setObject(3, qty);
            pstm.setObject(4, amount);
            pstm.setObject(5, email);


            return pstm.executeUpdate();

        } catch (SQLException var10) {
            throw new RuntimeException();
        }

    }

    public int updateorder(String orderId, String description, String qty, String amount, String email) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE order_detail SET email  = ?, description = ?,qtyOnHand = ? , amount = ? WHERE orderId = ?");
            pstm.setObject(1, orderId);
            pstm.setObject(2, description);
            pstm.setObject(3, qty);
            pstm.setObject(4, amount);
            pstm.setObject(5, email);
            return pstm.executeUpdate();

        } catch (SQLException var8) {
            throw new RuntimeException();
        }


    }

    public ArrayList<OrderDto> tble() {
        ArrayList<OrderDto> Order = new ArrayList<>();


        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from order_detail");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {


                CustomerDto customerDto = new CustomerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)


                );
                Order.add(new OrderDto());


            }
            return Order;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean saveOrder(String orderId, String date, String customerId, String customerEmail, Double
            total, ObservableList<OrderdetailsDto> observableList) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean saveOrder = save(new OrderDto(orderId, customerId, customerEmail, date));
            if (saveOrder == true) {

                boolean saveOrderDetails = orderdetailsSave(orderId, observableList);
                if (saveOrderDetails == true) {
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

    private boolean updateItemQty(ObservableList<OrderdetailsDto> observableList) {
        for (OrderdetailsDto dto : observableList) {
            ItemModel itemModel = new ItemModel();
            ItemDto itemDto = itemModel.searchItem(dto.getcode());
            boolean b = itemModel.UpdateItem(new ItemDto(dto.ge(), dto.getDescription(), dto.g(), itemDto.getQty() - dto.getQty()));
            if (!b) {
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
            pstm.setObject(3, dto.getQtyOnHand());
            pstm.setObject(4, dto.getQty());
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









