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

import com.testproject.sales.Year;


/**
 * ServiceImpl object for domain model class Year.
 *
 * @see Year
 */
@Service("sales.YearService")
@Validated
public class YearServiceImpl implements YearService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YearServiceImpl.class);


    @Autowired
    @Qualifier("sales.YearDao")
    private WMGenericDao<Year, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Year, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Year create(Year year) {
        LOGGER.debug("Creating a new Year with information: {}", year);

        Year yearCreated = this.wmGenericDao.create(year);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(yearCreated);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Year getById(Integer yearId) {
        LOGGER.debug("Finding Year by id: {}", yearId);
        return this.wmGenericDao.findById(yearId);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Year findById(Integer yearId) {
        LOGGER.debug("Finding Year by id: {}", yearId);
        try {
            return this.wmGenericDao.findById(yearId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Year found with id: {}", yearId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public List<Year> findByMultipleIds(List<Integer> yearIds, boolean orderedReturn) {
        LOGGER.debug("Finding Years by ids: {}", yearIds);

        return this.wmGenericDao.findByMultipleIds(yearIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
    @Override
    public Year update(Year year) {
        LOGGER.debug("Updating Year with information: {}", year);

        this.wmGenericDao.update(year);
        this.wmGenericDao.refresh(year);

        return year;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Year partialUpdate(Integer yearId, Map<String, Object>yearPatch) {
        LOGGER.debug("Partially Updating the Year with id: {}", yearId);

        Year year = getById(yearId);

        try {
            ObjectReader yearReader = this.objectMapper.reader().forType(Year.class).withValueToUpdate(year);
            year = yearReader.readValue(this.objectMapper.writeValueAsString(yearPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", yearPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        year = update(year);

        return year;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Year delete(Integer yearId) {
        LOGGER.debug("Deleting Year with id: {}", yearId);
        Year deleted = this.wmGenericDao.findById(yearId);
        if (deleted == null) {
            LOGGER.debug("No Year found with id: {}", yearId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Year.class.getSimpleName(), yearId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public void delete(Year year) {
        LOGGER.debug("Deleting Year with {}", year);
        this.wmGenericDao.delete(year);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Year> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Years");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Year> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Years");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service sales for table Year to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service sales for table Year to {} format", options.getExportType());
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