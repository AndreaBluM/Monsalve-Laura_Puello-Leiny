package db.backend.service;

import db.backend.model.Odontologo;
import db.backend.dao.IDao;
import db.backend.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {this.odontologoIDao = odontologoIDao;};

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoIDao.registrar(odontologo);
    }

    public List<Odontologo> ListarOdontologo() {
        return odontologoIDao.listar();
    }
}
