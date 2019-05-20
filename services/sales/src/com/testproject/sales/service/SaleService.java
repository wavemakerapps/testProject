/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testproject.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.testproject.sales.Sale;

/**
 * Service object for domain model class {@link Sale}.
 */
public interface SaleService {

    /**
     * Creates a new Sale. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Sale if any.
     *
     * @param sale Details of the Sale to be created; value cannot be null.
     * @return The newly created Sale.
     */
    Sale create(@Valid Sale sale);


	/**
     * Returns Sale by given id if exists.
     *
     * @param saleId The id of the Sale to get; value cannot be null.
     * @return Sale associated with the given saleId.
	 * @throws EntityNotFoundException If no Sale is found.
     */
    Sale getById(Integer saleId);

    /**
     * Find and return the Sale by given id if exists, returns null otherwise.
     *
     * @param saleId The id of the Sale to get; value cannot be null.
     * @return Sale associated with the given saleId.
     */
    Sale findById(Integer saleId);

	/**
     * Find and return the list of Sales by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param saleIds The id's of the Sale to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Sales associated with the given saleIds.
     */
    List<Sale> findByMultipleIds(List<Integer> saleIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Sale. It replaces all fields of the existing Sale with the given sale.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Sale if any.
     *
     * @param sale The details of the Sale to be updated; value cannot be null.
     * @return The updated Sale.
     * @throws EntityNotFoundException if no Sale is found with given input.
     */
    Sale update(@Valid Sale sale);


    /**
     * Partially updates the details of an existing Sale. It updates only the
     * fields of the existing Sale which are passed in the salePatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Sale if any.
     *
     * @param saleId The id of the Sale to be deleted; value cannot be null.
     * @param salePatch The partial data of Sale which is supposed to be updated; value cannot be null.
     * @return The updated Sale.
     * @throws EntityNotFoundException if no Sale is found with given input.
     */
    Sale partialUpdate(Integer saleId, Map<String, Object> salePatch);

    /**
     * Deletes an existing Sale with the given id.
     *
     * @param saleId The id of the Sale to be deleted; value cannot be null.
     * @return The deleted Sale.
     * @throws EntityNotFoundException if no Sale found with the given id.
     */
    Sale delete(Integer saleId);

    /**
     * Deletes an existing Sale with the given object.
     *
     * @param sale The instance of the Sale to be deleted; value cannot be null.
     */
    void delete(Sale sale);

    /**
     * Find all Sales matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Sales.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Sale> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Sales matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Sales.
     *
     * @see Pageable
     * @see Page
     */
    Page<Sale> findAll(String query, Pageable pageable);

    /**
     * Exports all Sales matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all Sales matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the Sales in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Sale.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);


}