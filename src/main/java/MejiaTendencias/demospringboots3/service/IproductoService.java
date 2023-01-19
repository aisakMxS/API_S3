package MejiaTendencias.demospringboots3.service;

import MejiaTendencias.demospringboots3.model.Producto;

import java.util.List;

public interface IproductoService {
    public List<Producto> findAll();

    public Producto save(Producto c);

    public Producto findById(Integer id);

    public void delete(Integer id);
}
