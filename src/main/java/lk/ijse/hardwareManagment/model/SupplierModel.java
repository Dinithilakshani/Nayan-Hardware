package lk.ijse.hardwareManagment.model;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.hardwareManagment.db.DbConnection;
import lk.ijse.hardwareManagment.dto.EmployeeDto;
import lk.ijse.hardwareManagment.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Supplier;

public class SupplierModel {
    public static ArrayList<SupplierDto> tble() {
        ArrayList<SupplierDto> Supplier = new ArrayList<>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from supplier");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {

                SupplierDto supplierDto = new SupplierDto(
                        resultSet.getString(1),
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(2)


                );
                Supplier.add(supplierDto);

            }
            return Supplier;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static SupplierDto searchByemail(String emailAddress) throws SQLException, ClassNotFoundException {


        String sql = "SELECT * FROM supplier WHERE  imeladdress=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, emailAddress);
        ResultSet resultSet = pstm.executeQuery();

        SupplierDto supplierDto = null;


        if (resultSet.next()) {
            String Supplierid = resultSet.getString(3);
            String Suppliername = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String Address = resultSet.getString(2);


            supplierDto = new SupplierDto(Supplierid, Suppliername, contact, Address);
        }
        return supplierDto;
    }

    public static int UpdateSuppliers(SupplierDto dto) {

        try {
            System.out.println(dto.getSupplierCompany());
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE supplier SET  SName = ?, contactnumber = ?,imeladdress  = ? WHERE  SID= ?");

            pstm.setObject(2, dto.getSupplierCompany());
            pstm.setObject(4, dto.getDescription());
            pstm.setObject(1, dto.getNumber());
            pstm.setObject(3, dto.getEmailAddress());
            return pstm.executeUpdate();

        } catch (SQLException var8) {
            throw new RuntimeException();
        }
    }


    public int SaveSupplier(String id, String name, String address, String tel) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO supplier VALUES(?, ?, ?, ?)");
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(4, address);
            pstm.setObject(3, tel);
            return pstm.executeUpdate();

        } catch (SQLException var10) {
            throw new RuntimeException();


        }
    }

    public int DeleteSupplier(String id) {
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM supplier WHERE SId = ?");
            pstm.setObject(1, id);

            return pstm.executeUpdate();


        } catch (SQLException var5) {
            throw new RuntimeException();
        }
    }


}