package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.ConstructionSite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionSiteMapper {
    int deleteByPrimaryKey(Integer constructionSiteId);

    int insert(ConstructionSite record);

    ConstructionSite selectByPrimaryKey(Integer constructionSiteId);

    List<ConstructionSite> selectByUserId(Integer userId);

    List<ConstructionSite> selectAll();

    int updateByPrimaryKey(ConstructionSite record);
}