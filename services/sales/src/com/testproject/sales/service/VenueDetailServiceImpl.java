/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testproject.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.testproject.sales.VenueDetail;


/**
 * ServiceImpl object for domain model class VenueDetail.
 *
 * @see VenueDetail
 */
@Service("sales.VenueDetailService")
@Validated
public class VenueDetailServiceImpl implements VenueDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VenueDetailServiceImpl.class);


    @Autowired
    @Qualifier("sales.VenueDetailDao")
    private WMGenericDao<VenueDetail, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<VenueDetail, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public VenueDetail create(VenueDetail venueDetail) {
        LOGGER.debug("Creating a new VenueDetail with information: {}", venueDetail);

        VenueDetail venueDetailCreated = this.wmGenericDao.create(venueDetail);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(venueDetailCreated);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public VenueDetail getById(Integer venuedetailId) {
        LOGGER.debug("Finding VenueDetail by id: {}", venuedetailId);
        return this.wmGenericDao.findById(venuedetailId);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public VenueDetail findById(Integer venuedetailId) {
        LOGGER.debug("Finding VenueDetail by id: {}", venuedetailId);
        try {
            return this.wmGenericDao.findById(venuedetailId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No VenueDetail found with id: {}", venuedetailId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public List<VenueDetail> findByMultipleIds(List<Integer> venuedetailIds, boolean orderedReturn) {
        LOGGER.debug("Finding VenueDetails by ids: {}", venuedetailIds);

        return this.wmGenericDao.findByMultipleIds(venuedetailIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
    @Override
    public VenueDetail update(VenueDetail venueDetail) {
        LOGGER.debug("Updating VenueDetail with information: {}", venueDetail);

        this.wmGenericDao.update(venueDetail);
        this.wmGenericDao.refresh(venueDetail);

        return venueDetail;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public VenueDetail partialUpdate(Integer venuedetailId, Map<String, Object>venueDetailPatch) {
        LOGGER.debug("Partially Updating the VenueDetail with id: {}", venuedetailId);

        VenueDetail venueDetail = getById(venuedetailId);

        try {
            ObjectReader venueDetailReader = this.objectMapper.reader().forType(VenueDetail.class).withValueToUpdate(venueDetail);
            venueDetail = venueDetailReader.readValue(this.objectMapper.writeValueAsString(venueDetailPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", venueDetailPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        venueDetail = update(venueDetail);

        return venueDetail;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public VenueDetail delete(Integer venuedetailId) {
        LOGGER.debug("Deleting VenueDetail with id: {}", venuedetailId);
        VenueDetail deleted = this.wmGenericDao.findById(venuedetailId);
        if (deleted == null) {
            LOGGER.debug("No VenueDetail found with id: {}", venuedetailId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), VenueDetail.class.getSimpleName(), venuedetailId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public void delete(VenueDetail venueDetail) {
        LOGGER.debug("Deleting VenueDetail with {}", venueDetail);
        this.wmGenericDao.delete(venueDetail);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<VenueDetail> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all VenueDetails");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<VenueDetail> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all VenueDetails");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service sales for table VenueDetail to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service sales for table VenueDetail to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}