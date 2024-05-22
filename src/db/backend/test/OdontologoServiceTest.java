package db.backend.test;

import db.backend.dao.impl.OdontologoIDaoH2;
import db.backend.model.Odontologo;
import db.backend.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    public static Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    public static OdontologoService odontologoService = new OdontologoService(new OdontologoIDaoH2());
        @BeforeAll
        static void crearTablas() {
            Connection connection = null;
            try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:~/claseExamen-1-Back-End;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

    @Test
    @DisplayName("Testear odontologo guardado")
    void testOdontologoGuardado(){
        Odontologo odontologo = new Odontologo(1245,"Leiny","Puello");
        Odontologo odontologoDesdeLaDB = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoDesdeLaDB);
    }

    @Test
    @DisplayName("Testear listar odontologos")
    void testListarOdontologos(){
            Odontologo odontologo = new Odontologo(1245,"Leiny","Puello");
            odontologoService.ListarOdontologo(odontologo);

        List<Odontologo> odontologos = odontologoService.ListarOdontologo();
        assertEquals(2, odontologos.size());
        assertTrue(odontologos.contains(odontologo));
    }

}