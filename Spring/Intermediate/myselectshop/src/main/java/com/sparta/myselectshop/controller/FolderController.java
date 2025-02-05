package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.FolderRequestDto;
import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    /**
     * 폴더 생성
     * @param folderRequestDto
     * @param userDetails
     */
    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto folderRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<String> folderNames = folderRequestDto.getFolderNames();
        folderService.addFolders(folderNames, userDetails.getUser());
    }

    /**
     * 폴더 전체 조회
     * @param userDetails
     * @return
     */
    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return folderService.getFolders(userDetails.getUser());
    }
}
