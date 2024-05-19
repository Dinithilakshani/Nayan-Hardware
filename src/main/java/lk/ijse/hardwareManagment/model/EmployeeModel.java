package lk.ijse.hardwareManagment.model;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.CustomerDto;
import lk.ijse.hardwareManagment.dto.EmployeeDto;
import lk.ijse.hardwareManagment.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {

    public static EmployeeDto searchBycontact(String contactnumber) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE  contactnumber=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, contactnumber);
        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto employeeDto = null;


        if (resultSet.next()) {
            String Supplierid = resultSet.getString(3);
            String Suppliername = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String Address = resultSet.getString(2);


            employeeDto = new EmployeeDto(Supplierid, Suppliername, contact, Address);
        }
        return employeeDto;
    }

    public static int UpdateEmployee(EmployeeDto dto) {
        try {
            System.out.println(dto.getId());
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE employee SET  contactnumber = ?, address = ?,name  = ? WHERE eid = ?");

            pstm.setObject(2, dto.getAddress());
            pstm.setObject(4, dto.getId());
            pstm.setObject(1, dto.getContactnumber());
            pstm.setObject(3, dto.getName());
            return pstm.executeUpdate();

        } catch (SQLException var8) {
            throw new RuntimeException();
        }
    }




    public int SaveEmployee(String id, String name, String address, String tel) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Employee VALUES(?, ?, ?,?)");

            pstm.setObject(1, id);
            pstm.setObject(4, name);
            pstm.setObject(3, address);
            pstm.setObject(2, tel);
            return pstm.executeUpdate();


        } catch (SQLException var10) {
            throw new RuntimeException();

        }
    }



    public int DeleteEmployee(String id) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM employee WHERE eid = ?");
            pstm.setObject(1, id);
            return pstm.executeUpdate();


        } catch (SQLException var5) {
            throw new RuntimeException();


        }
    }

    public ArrayList<EmployeeDto> tble() {
        ArrayList<EmployeeDto> Employee = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from Employee");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                EmployeeDto employeeDto = new EmployeeDto(
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(1),
                        resultSet.getString(2)

                );
                Employee.add(employeeDto);

            }
            return Employee;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}



