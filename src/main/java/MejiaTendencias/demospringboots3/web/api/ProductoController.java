package MejiaTendencias.demospringboots3.web.api;
import MejiaTendencias.demospringboots3.dao.IproductoDao;
import MejiaTendencias.demospringboots3.model.Producto;
import MejiaTendencias.demospringboots3.service.IproductoService;
import MejiaTendencias.demospringboots3.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private IproductoService service;
    @Autowired
    private IproductoDao iproductoDao;
    @Autowired
    private S3Service s3Service;

    @GetMapping("/listarproductos")
    public List<Producto> indext(){
        return service.findAll();
    }

    @PostMapping("/crearproducto")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto save(@RequestParam MultipartFile file,String nombre,String cantidad,String precio){
        Producto pro= new Producto();
        String key = s3Service.putObject((file));
        pro.setNombre(nombre);
        pro.setCantidad(cantidad);
        pro.setPrecio(precio);
        pro.setUrl((String) s3Service.getObjectUrl(key));
        return service.save(pro);
    }

    @GetMapping("/buscarproducto/{id}")
    public Producto findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @DeleteMapping("/borrarproducto")
    public Producto deleted(@PathVariable Integer id){
        Producto p=service.findById(id);
        String[] a=p.getUrl().split("/");
        s3Service.deleteObject(a[a.length-1]);
        service.delete(id);
        return p;
    }


}
