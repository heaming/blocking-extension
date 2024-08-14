package com.flow.blockingextension.respository;

import com.flow.blockingextension.model.Extension;
import com.flow.blockingextension.model.ExtensionType;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    Optional<Extension> findExtensionById(Long id);
    Optional<Extension> findExtensionByIdOrTitle(@Nullable Long id, String title);
    Optional<List<Extension>> findByExtensionTypeOrderById(ExtensionType type);

}
