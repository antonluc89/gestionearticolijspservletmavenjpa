package it.gestionearticolijspservletjpamaven.dao;

import java.util.List;

import it.gestionearticolijspservletjpamaven.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	List<Articolo> findByExample(Articolo input) throws Exception;

}
