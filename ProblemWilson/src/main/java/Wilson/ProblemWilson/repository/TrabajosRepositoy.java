package Wilson.ProblemWilson.repository;

import Wilson.ProblemWilson.entity.Trabajos;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajosRepositoy extends MongoRepository<Trabajos, Long>{
    
    public Optional<Trabajos> findByFechaRegistro(Date fecha);
    
}
