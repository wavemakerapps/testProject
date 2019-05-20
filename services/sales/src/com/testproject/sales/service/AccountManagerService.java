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

import com.testproject.sales.AccountManager;

/**
 * Service object for domain model class {@link AccountManager}.
 */
public interface AccountManagerService {

    /**
     * Creates a new AccountManager. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AccountManager if any.
     *
     * @param accountManager Details of the AccountManager to be created; value cannot be null.
     * @return The newly created AccountManager.
     */
    AccountManager create(@Valid AccountManager accountManager);


	/**
     * Returns AccountManager by given id if exists.
     *
     * @param accountmanagerId The id of the AccountManager to get; value cannot be null.
     * @return AccountManager associated with the given accountmanagerId.
	 * @throws EntityNotFoundException If no AccountManager is found.
     */
    AccountManager getById(Integer accountmanagerId);

    /**
     * Find and return the AccountManager by given id if exists, returns null otherwise.
     *
     * @param accountmanagerId The id of the AccountManager to get; value cannot be null.
     * @return AccountManager associated with the given accountmanagerId.
     */
    AccountManager findById(Integer accountmanagerId);

	/**
     * Find and return the list of AccountManagers by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param accountmanagerIds The id's of the AccountManager to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return AccountManagers associated with the given accountmanagerIds.
     */
    List<AccountManager> findByMultipleIds(List<Integer> accountmanagerIds, boolean orderedReturn);


    /**
     * Updates the details of an existing AccountManager. It replaces all fields of the existing AccountManager with the given accountManager.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AccountManager if any.
     *
     * @param accountManager The details of the AccountManager to be updated; value cannot be null.
     * @return The updated AccountManager.
     * @throws EntityNotFoundException if no AccountManager is found with given input.
     */
    AccountManager update(@Valid AccountManager accountManager);


    /**
     * Partially updates the details of an existing AccountManager. It updates only the
     * fields of the existing AccountManager which are passed in the accountManagerPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AccountManager if any.
     *
     * @param accountmanagerId The id of the AccountManager to be deleted; value cannot be null.
     * @param accountManagerPatch The partial data of AccountManager which is supposed to be updated; value cannot be null.
     * @return The updated AccountManager.
     * @throws EntityNotFoundException if no AccountManager is found with given input.
     */
    AccountManager partialUpdate(Integer accountmanagerId, Map<String, Object> accountManagerPatch);

    /**
     * Deletes an existing AccountManager with the given id.
     *
     * @param accountmanagerId The id of the AccountManager to be deleted; value cannot be null.
     * @return The deleted AccountManager.
     * @throws EntityNotFoundException if no AccountManager found with the given id.
     */
    AccountManager delete(Integer accountmanagerId);

    /**
     * Deletes an existing AccountManager with the given object.
     *
     * @param accountManager The instance of the AccountManager to be deleted; value cannot be null.
     */
    void delete(AccountManager accountManager);

    /**
     * Find all AccountManagers matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AccountManagers.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<AccountManager> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all AccountManagers matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AccountManagers.
     *
     * @see Pageable
     * @see Page
     */
    Page<AccountManager> findAll(String query, Pageable pageable);

    /**
     * Exports all AccountManagers matching the given input query to the given exportType format.
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
     * Exports all AccountManagers matching the given input query to the given exportType format.
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
     * Retrieve the count of the AccountManagers in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the AccountManager.
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