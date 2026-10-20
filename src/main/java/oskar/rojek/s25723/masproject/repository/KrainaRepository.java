package oskar.rojek.s25723.masproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import oskar.rojek.s25723.masproject.model.Kraina;

import java.util.List;

public interface KrainaRepository extends CrudRepository<Kraina, Long> {
    @Query("SELECT kr FROM Kraina kr ORDER BY kr.nazwa, kr.dominujacyZywiol, kr.wielkosc")
    List<Kraina> findAllSorted();
}
