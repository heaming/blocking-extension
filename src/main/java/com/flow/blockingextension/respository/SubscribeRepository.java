package com.flow.blockingextension.respository;

import com.flow.blockingextension.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Optional<Subscribe> findSubscribeById(Long id);
    Optional<Subscribe> findSubscribeByToken(String token);

}
