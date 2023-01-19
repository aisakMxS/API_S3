package MejiaTendencias.demospringboots3.service;

import MejiaTendencias.demospringboots3.dao.IproductoDao;
import MejiaTendencias.demospringboots3.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductoService implements IproductoService{
    @Autowired
    private IproductoDao pDao;
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) pDao.findAll();
    }

    @Override
    @Transactional
    public Producto save(Producto c) {
        return pDao.save(c);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Integer id) {
        return pDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        pDao.deleteById(id);
    }
}
