package Application.Repositories;

import java.util.Optional;

import Application.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
	Optional<Video> findByFilename(String filename);
}
