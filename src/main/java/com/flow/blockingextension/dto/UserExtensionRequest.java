package com.flow.blockingextension.dto;

import com.flow.blockingextension.model.Extension;

import java.util.List;

public record UserExtensionRequest(String token, List<Extension> extensions) {
}
