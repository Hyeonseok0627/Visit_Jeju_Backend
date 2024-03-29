package com.backend.jejutour_server.controller;

import com.backend.jejutour_server.dto.FesByGpsDto;
import com.backend.jejutour_server.entity.FesEntity;
import com.backend.jejutour_server.service.FesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/fes")
public class FesController {

    @Autowired
    private FesService fesService;

    @GetMapping("/fesDtl/{fesId}")
    public List<FesEntity> FesList(
            @PathVariable("fesId") Long fesId) {
        try {
            List<FesEntity> fesList = fesService.getFesDtl(fesId);
            System.out.println("fesDtl 통신 확인  : " + fesId);
            return fesList;
        } catch(EntityNotFoundException e){
            System.out.println("fesDtl 통신 실패 : " + fesId);
            return null;
        }
    }

    @GetMapping("/fesAllList")
    public List<FesEntity> FesList() {
        try {
        List<FesEntity> fesList = fesService.getAllFesList();
            System.out.println("fesAllList 호출 성공");
        return fesList;
        } catch (EntityNotFoundException e){
            System.out.println("fesAllList 통신 실패");
            return null;
        }
    }

    @GetMapping("/fesList/{itemsRegion2CdValue}")
    public List<FesEntity> AccomItemsRegion2CdValueList(
            @PathVariable("itemsRegion2CdValue") Long itemsRegion2CdValue) {
        try {
            List<FesEntity> fesList = fesService.getitemsRegion2CdValueFesList(itemsRegion2CdValue);
            System.out.println("Fes 지역코드별 조회 실패");
            return fesList;
        } catch (EntityNotFoundException e){
            System.out.println("Fes 지역코드별 조회 실패");
            return null;
        }
    }


    @GetMapping("/fesList/fesByGPS")
    @ResponseBody
    public List<FesEntity> getFesByGPS(
//            @RequestParam(value = "lat") Double lat,
//            @RequestParam(value = "lnt") Double lnt,
//            @RequestParam(value = "radius") Double radius,
            FesByGpsDto fesByGpsDto,
            @RequestParam(value = "page") int page) {
        try {
            Pageable pageable = PageRequest.of( page, 5);
            Page<FesEntity> Fes = fesService.findFesByGPS(fesByGpsDto, pageable);
            System.out.println("fesByGPS 통신 확인 lat : " + fesByGpsDto.getLat() + " lnt : " + fesByGpsDto.getLnt()
                + " radius : " + fesByGpsDto.getRadius() + " page : " + page);
            return Fes.getContent();
        } catch (EntityNotFoundException e) {
            System.out.println("fesByGPS 조회 실패");
            return null;
        }
    }
}

// 지역 코드
//            case 11 : test = "제주시내"; break;
//            case 12 : test = "애월"; break;
//            case 13 : test = "한림"; break;
//            case 14 : test = "한경"; break;
//            case 15 : test = "조천"; break;
//            case 16 : test = "구좌"; break;
//            case 17 : test = "성산"; break;
//            case 21 : test = "서귀포시내"; break;
//            case 22 : test = "대정"; break;
//            case 23 : test = "안덕"; break;
//            case 24 : test = "중문"; break;
//            case 25 : test = "남원"; break;
//            case 26 : test = "표선"; break;
//            case 31 : test = "우도"; break;
//            case 32 : test = "추자도"; break;
//            case 33 : test = "마라도"; break;
