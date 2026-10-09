package com.academia.cursosplataforma.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.academia.cursosplataforma.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByTituloContainingIgnoreCase(String texto);

}
