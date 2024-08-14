package com.flow.blockingextension.service;

import com.flow.blockingextension.exception.ApplicationException;
import com.flow.blockingextension.exception.ErrorCode;
import com.flow.blockingextension.model.Extension;
import com.flow.blockingextension.model.ExtensionType;
import com.flow.blockingextension.model.Subscribe;
import com.flow.blockingextension.respository.ExtensionRepository;
import com.flow.blockingextension.respository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.flow.blockingextension.exception.ErrorCode.INVALID_EXTENSION;

@RequiredArgsConstructor
@Service
@Slf4j
public class BlockingExtensionService {

    private final ExtensionRepository extensionRepository;
    private final SubscribeRepository subscribeRepository;

    public Extension getExtensionById(Long id) {
        return extensionRepository.findExtensionById(id).orElseThrow(INVALID_EXTENSION::build);
    }

    public List<Extension> getExtensionsByType(ExtensionType type) {
        return extensionRepository.findByExtensionTypeOrderById(type)
                .orElse(new ArrayList<>());
    }

    public List<Extension> getUserExtension(String token) {
        Subscribe subscribes = subscribeRepository.findSubscribeByToken(token).orElse(
                Subscribe.builder()
                        .token(token)
                        .extensions(new ArrayList<>())
                        .build()
        );

        List<Extension> extensionList = new ArrayList<>();
        for(Long extensionId : subscribes.getExtensions())  {
            extensionList.add(extensionRepository.findById(extensionId)
                    .orElseThrow(INVALID_EXTENSION::build));
        }

        if(!extensionList.isEmpty()) {
            extensionList.sort(Comparator.comparingLong(Extension::getId));
        }

        return extensionList;
    }

    public List<Extension> updateUserExtension(String token, List<Extension> extensions) {
        Subscribe subscribe = subscribeRepository.findSubscribeByToken(token).orElse(null);
        if(subscribe == null) {
            subscribe = Subscribe.builder()
                    .token(token)
                    .extensions(new ArrayList<>())
                    .build();
        }

        List<Long> extensionList = new ArrayList<>();
        for(Extension e : extensions) {

            Extension extension = extensionRepository.findExtensionByIdOrTitle(e.getId(), e.getTitle()).orElse(null);

            if(extension == null) {
                 extension = extensionRepository.save(
                            Extension.builder()
                                    .title(e.getTitle())
                                    .extensionType(e.getExtensionType())
                                    .createdBy(token)
                                    .build());
            }

            extensionList.add(extension.getId());
        }

        subscribeRepository.save(
                Subscribe.builder()
                        .id(subscribe.getId())
                        .token(subscribe.getToken())
                        .extensions(extensionList)
                        .build()
        );

        return this.getUserExtension(token);
    }
}
