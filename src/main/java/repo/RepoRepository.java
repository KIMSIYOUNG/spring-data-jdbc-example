package repo;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RepoRepository extends CrudRepository<Repo, String> {
    @Query("select * from REPO repo where repo.id = :id FOR UPDATE")
    @Override
    Optional<Repo> findById(@Param("id") String id);
}
