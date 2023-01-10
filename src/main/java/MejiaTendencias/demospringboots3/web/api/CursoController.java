package MejiaTendencias.demospringboots3.web.api;

import MejiaTendencias.demospringboots3.repository.CursoRepository;
import MejiaTendencias.demospringboots3.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import MejiaTendencias.demospringboots3.model.Curso;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    List<Curso> getAll(){
        return cursoRepository.findAll()
                .stream()
                .peek(curso -> curso.setImagenUrl(s3Service.getObjectUrl(curso.getImagenPath())))
                .collect(Collectors.toList());
    }

    @PostMapping
    Curso create(@RequestBody Curso curso){
        cursoRepository.save(curso);
        curso.setImagenUrl(s3Service.getObjectUrl(curso.getImagenPath()));
        return curso;
    }
}
