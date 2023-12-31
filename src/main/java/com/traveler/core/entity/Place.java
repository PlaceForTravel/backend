package com.traveler.core.entity;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="place")
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID")
    private int placeId;
    @Column(name = "PLACE_NAME")
    private String placeName;
    @Column(name = "LATITUDE")
    private float latitude;
    @Column(name = "LONGITUDE")
    private float longitude;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CATEGORY")
    private String category;

    @OneToMany(mappedBy = "place")
    private List<BoardPlace> boardPlaceList = new ArrayList<>();

    public Place() {

    }

    public int getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public List<BoardPlace> getBoardPlaceList() {
        return boardPlaceList;
    }

    @Builder
    public Place(String name, float longitude, float latitude, String address){
        this.placeName = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }



}
