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




    public int saveCustomer(String id, String name, String address, String tel, String email) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?,?)");
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

    public int updateCustomer(String eid, String name, String address, String contactnumber, String email) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET name = ?, address = ?,email = ? , contactnumber = ? WHERE id = ?");
            pstm.setObject(2, name);
            pstm.setObject(3, address);
            pstm.setObject(4, contactnumber);
            pstm.setObject(1, eid);
            pstm.setObject(5,email);
             return pstm.executeUpdate();

        } catch (SQLException var8) {
    throw new RuntimeException();
            }



        }

    public int deleteCustomer(String id) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE id = ?");
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


                CustomerDto customerDto   = new CustomerDto(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4)


                );
                Customer.add(customerDto);



            }
                return Customer;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}



