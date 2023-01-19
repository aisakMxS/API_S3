package MejiaTendencias.demospringboots3.dao;

import MejiaTendencias.demospringboots3.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IproductoDao extends CrudRepository<Producto,Integer> {
}
