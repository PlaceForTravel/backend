package com.traveler.core.controller;


import com.traveler.core.dto.*;

import com.traveler.core.entity.Board;
import com.traveler.core.entity.BoardPlace;
import com.traveler.core.repository.BoardPlaceRepository;
import com.traveler.core.repository.BoardRepository;
import com.traveler.core.service.BoardService;
import com.traveler.core.service.FCMNotificationService;
import com.traveler.core.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController( BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/list")
    public Page<BoardListResponseDTO> boardList(@RequestParam String userId, @PageableDefault(page = 1) Pageable pageable){
//        List<Board> optionalBoardList= Optional.ofNullable(boardRepository.findAll()).orElse(Collections.emptyList());
//        List<BoardListResponseDTO> boardListResponseDTOList = optionalBoardList.stream().map(emp-> new BoardListResponseDTO(emp)).collect(Collectors.toList());
          Page<BoardListResponseDTO> boardListResponseDTOs = boardService.listPaging(pageable, userId);
        return boardListResponseDTOs;
    }
//    @GetMapping(value = "/search/city/{searchWord}")
//    public Page<BoardListResponseDTO> searchCityList(@PageableDefault(page = 1) Pageable pageable, @PathVariable String searchWord,@RequestBody UserDTO userDTO){
//        Page<BoardListResponseDTO> boardListResponseDTOs = boardService.placePaging(pageable,searchWord, userDTO.getUserId());
//        return boardListResponseDTOs;
//    }
//    @GetMapping(value = "/search/place/{placeName}")
//    public Page<BoardPlaceListResponseDTO> searchPlaceList(@PageableDefault(page = 1) Pageable pageable, @PathVariable String placeName){
//        Page<BoardPlaceListResponseDTO> placeListResponseDTOs = boardService.boardPlacePaging(pageable,placeName);
//        return placeListResponseDTOs;
//    }

    @GetMapping(value = "/detail/{boardId}")
    public BoardDetailResponseDTO boardDetail(@RequestParam String userId, @PathVariable int boardId){
        BoardDetailResponseDTO boardDetailResponseDTO = boardService.showBoardDetail(boardId,userId);
        return boardDetailResponseDTO;
    }
    @PostMapping(value = "/save")
    public void saveBoard(@RequestPart(value="content") BoardSaveDTO boardSaveDTO, @RequestPart(value="files") List<MultipartFile> multipartFiles){
        boardService.save(boardSaveDTO, multipartFiles);
    }
    @DeleteMapping(value = "/delete/{boardId}")
    public void deleteBoard(@PathVariable int boardId){
        boardService.delete(boardId);
    }
    @PostMapping(value = "/like/{boardId}")
    public void like(@PathVariable int boardId, @RequestParam String userId){
        if(boardService.like(boardId, userId)){
        boardService.likeNoti(boardId, userId);
        }
    }
    @PostMapping(value = "/saveBoardPlace/{boardPlaceId}")
    public void likeBoardPlace(@PathVariable int boardPlaceId, @RequestParam String userId){
        boardService.likeBoardPlace(boardPlaceId, userId);
    }
    @PutMapping(value = "/edit/{boardId}")
    public void editBoard(@PathVariable int boardId, @RequestBody BoardEditDTO boardEditDTO){
        boardService.edit(boardId, boardEditDTO);
    }


}
