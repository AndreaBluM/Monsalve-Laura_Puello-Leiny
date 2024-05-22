package db.backend.dao.impl;

import db.backend.dao.IDao;
import db.backend.db.H2Connection;
import db.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoIDaoH2  implements IDao<Odontologo> {
    public static Logger LOGGER = Logger.getLogger(OdontologoIDaoH2.class);
    public static String SQL_INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT, ?, ?,?)";
    public static String SQL_SELECT = "SELECT * FROM ODONTOLOGOS WHERE NOMBRE = ?";
    public static String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;
        try {
            connection = H2Connection.getConnection();
            connection = connection;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                odontologoARetornar = odontologo;
            }
            LOGGER.info("odontologoARetornar" + odontologoARetornar);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoARetornar;

    }

    @Override
    public List<Odontologo> listar() {
            List<Odontologo> odontologos = new ArrayList<>();
            Connection connection = null;

            try {
                connection = H2Connection.getConnection();
                Statement statement= connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
                while (resultSet.next()){
                    Integer numeroMatricula = resultSet.getInt(1);
                    String nombre = resultSet.getString(2);
                    String apellido = resultSet.getString(3);

                    Odontologo odontologo = new Odontologo(numeroMatricula,nombre,apellido);
                    LOGGER.info("Odontologo listado: " + odontologo);
                    odontologos.add(odontologo);
                }
            }catch (Exception e){
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.info(e.getMessage());
                    e.printStackTrace();
                }
            }

            return odontologos;
        }

}
