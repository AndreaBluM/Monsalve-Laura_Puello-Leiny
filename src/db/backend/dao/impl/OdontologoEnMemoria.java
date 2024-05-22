package db.backend.dao.impl;

import db.backend.dao.IDao;
import db.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoEnMemoria implements IDao<Odontologo> {
    private Logger LOGGER = Logger.getLogger(OdontologoEnMemoria.class);

    List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Integer numeroMatricula = odontologos.size()+1;
        odontologo.setNumeroMatricula(numeroMatricula);

        odontologos.add(odontologo);
        LOGGER.info("Odontologo guardado..." + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        return odontologos;
    }

}


