package lk.ijse.hardwareManagment.model;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {


    public static CustomerDto searchById(String contactnumber) throws SQLException, ClassNotFoundException {

            String sql = "SELECT * FROM customer WHERE  contactnumber=?";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            pstm.setObject(1,contactnumber);
            ResultSet resultSet = pstm.executeQuery();

            CustomerDto customerDto = null;




            if (resultSet.next()) {
                String customerId = resultSet.getString(2);
                String customerName = resultSet.getString(1);
                String contact = resultSet.getString(4);
                String customerAddress = resultSet.getString(3);
                String customerEmail = resultSet.getString(5);


                customerDto  = new CustomerDto( customerId, customerName, contact,customerAddress,customerEmail);
            }
            return customerDto;
        }

    public static boolean updatecustomer(CustomerDto customerDto) throws SQLException {
        //String sql = "update customer  name = ?,address = ?,email = ? , contactnumber = ? where id =?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("update customer  name = ?,address = ?,email = ? , contactnumber = ? where id =?");
        pstm.setObject(1,customerDto.getName());
        pstm.setObject(5,customerDto.getId());
        pstm.setObject(3,customerDto.getEmail());
        pstm.setObject(4,customerDto.getContact());
        pstm.setObject(2,customerDto.getAddress());

        return pstm.executeUpdate() > 0;
    }




    public int saveCustomer(String id, String name, String address, String tel, String email) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES(?, ?, ?, ?,?)");
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, address);
            pstm.setObject(5, tel);
            pstm.setObject(4, email);
            return pstm.executeUpdate();

        } catch (SQLException var10) {
throw new RuntimeException();
        }

    }







    public int deleteCustomer(String id) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM customer WHERE id = ?");
            pstm.setObject(1, id);
return pstm.executeUpdate();

        } catch (SQLException var5) {
            throw new RuntimeException();

        }
    }


    public ArrayList<CustomerDto> tble() {
        ArrayList<CustomerDto> Customer = new ArrayList<>();


        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from Customer");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {


                CustomerDto customerDto = new CustomerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)


                );
                Customer.add(customerDto);


            }
            return Customer;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }


    public ArrayList<String> getallEmail() {
        ArrayList<String> allEmail = new ArrayList<>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select email from customer");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                allEmail.add(String.valueOf(resultSet.getString(1)));
            }
            return allEmail;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public CustomerDto searchCustomer(String email ) throws SQLException {
        String sql = "SELECT * FROM customer WHERE  email=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, email);
        ResultSet resultSet = pstm.executeQuery();

        CustomerDto customerDto = null;


        if (resultSet.next()) {
            String customerId = resultSet.getString(2);
            String customerName = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String customerAddress = resultSet.getString(3);
            String customerEmail = resultSet.getString(5);


            customerDto = new CustomerDto(customerId, customerName, contact, customerAddress, customerEmail);
        }
        return customerDto;


    }
}




