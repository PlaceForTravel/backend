package com.traveler.core.controller;

import com.traveler.core.dto.BoardListResponseDTO;
import com.traveler.core.dto.BoardPlaceListResponseDTO;
import com.traveler.core.entity.Place;
import com.traveler.core.entity.User;
import com.traveler.core.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/savedBoard/list")
    public Page<BoardListResponseDTO> savedBoardList(@RequestParam String userId, @PageableDefault Pageable pageable){
        Page<BoardListResponseDTO> boardListResponseDTOs=  userService.savedBoardPaging(userId, pageable);
        return boardListResponseDTOs;
    }
    @RequestMapping(value = "/savedBoardPlace/list")
    public Page<BoardPlaceListResponseDTO> savedBoardPlaceList(@RequestParam String userId, @RequestParam String cityName, @PageableDefault Pageable pageable){
        Page<BoardPlaceListResponseDTO> boardPlaceListResponseDTOs=userService.savedBoardPlacePaging(userId,cityName, pageable);
        return boardPlaceListResponseDTOs;
    }
    @RequestMapping(value = "/savedBoardPlace/city")
    public List<String> savedBoardPlaceCityList(@RequestParam String userId){
        List<String> cities = userService.savedBoardPlaceCity(userId);
        return cities;
    }
    @RequestMapping(value = "/savedPlace")
    public List<Place> savedPlaceList(@RequestParam String userId){
        List<Place> places = userService.savedPlace(userId);
        return places;
    }
    @RequestMapping(value = "/savedBoardPlace")
    public List<BoardPlaceListResponseDTO> savedBoardPlaceList(@RequestParam String userId,@RequestParam int placeId){
        List<BoardPlaceListResponseDTO> boardPlaces = userService.savedBoardPlaceList(userId,placeId);
        return boardPlaces;
    }

    @RequestMapping(value = "/login")
    public void join(@RequestBody User user){
        userService.login(user);
    }


    @RequestMapping(value = "/nickname")
    public boolean checkNickname(@RequestParam String nickname){
        boolean notUnique = userService.isNicknameUnique(nickname);
        return notUnique;
    }

    @RequestMapping(value = "/id")
    public String checkId(@RequestParam String userId){
        String userNickname = userService.isIdUnique(userId);
        return userNickname;
    }

    @RequestMapping(value = "/delete")
    public void deleteId(@RequestParam String userId){
        userService.deleteUser(userId);
    }
}
