package com.flow.blockingextension.controller;

import com.flow.blockingextension.dto.UserExtensionRequest;
import com.flow.blockingextension.model.Extension;
import com.flow.blockingextension.model.ExtensionType;
import com.flow.blockingextension.service.BlockingExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class BlockingExtensionController {

    private final BlockingExtensionService extensionService;

    @GetMapping("/blocking-extensions/{id}")
    public ResponseEntity<Extension> getExtensionById(@PathVariable Long id) {
        return ResponseEntity.ok(extensionService.getExtensionById(id));
    }

    @GetMapping("/blocking-extensions")
    public ResponseEntity<List<Extension>> getExtensionsByType(@RequestParam ExtensionType type) {
        List<Extension> list = extensionService.getExtensionsByType(type);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/blocking-extensions/user")
    public ResponseEntity<List<Extension>> getUserExtensions(@RequestParam String token) {
        List<Extension> list = extensionService.getUserExtension(token);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/blocking-extensions/user")
    public ResponseEntity<List<Extension>> updateUserExtensions(@RequestBody UserExtensionRequest request) {
        List<Extension> list = extensionService.updateUserExtension(request.token(), request.extensions());
        return ResponseEntity.ok(list);
    }

}
