/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testproject.sales.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.testproject.sales.VenueDetail;
import com.testproject.sales.service.VenueDetailService;


/**
 * Controller object for domain model class VenueDetail.
 * @see VenueDetail
 */
@RestController("sales.VenueDetailController")
@Api(value = "VenueDetailController", description = "Exposes APIs to work with VenueDetail resource.")
@RequestMapping("/sales/VenueDetail")
public class VenueDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VenueDetailController.class);

    @Autowired
	@Qualifier("sales.VenueDetailService")
	private VenueDetailService venueDetailService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new VenueDetail instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VenueDetail createVenueDetail(@RequestBody VenueDetail venueDetail) {
		LOGGER.debug("Create VenueDetail with information: {}" , venueDetail);

		venueDetail = venueDetailService.create(venueDetail);
		LOGGER.debug("Created VenueDetail with information: {}" , venueDetail);

	    return venueDetail;
	}

    @ApiOperation(value = "Returns the VenueDetail instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VenueDetail getVenueDetail(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting VenueDetail with id: {}" , id);

        VenueDetail foundVenueDetail = venueDetailService.getById(id);
        LOGGER.debug("VenueDetail details with id: {}" , foundVenueDetail);

        return foundVenueDetail;
    }

    @ApiOperation(value = "Updates the VenueDetail instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VenueDetail editVenueDetail(@PathVariable("id") Integer id, @RequestBody VenueDetail venueDetail) {
        LOGGER.debug("Editing VenueDetail with id: {}" , venueDetail.getId());

        venueDetail.setId(id);
        venueDetail = venueDetailService.update(venueDetail);
        LOGGER.debug("VenueDetail details with id: {}" , venueDetail);

        return venueDetail;
    }
    
    @ApiOperation(value = "Partially updates the VenueDetail instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VenueDetail patchVenueDetail(@PathVariable("id") Integer id, @RequestBody @MapTo(VenueDetail.class) Map<String, Object> venueDetailPatch) {
        LOGGER.debug("Partially updating VenueDetail with id: {}" , id);

        VenueDetail venueDetail = venueDetailService.partialUpdate(id, venueDetailPatch);
        LOGGER.debug("VenueDetail details after partial update: {}" , venueDetail);

        return venueDetail;
    }

    @ApiOperation(value = "Deletes the VenueDetail instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVenueDetail(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting VenueDetail with id: {}" , id);

        VenueDetail deletedVenueDetail = venueDetailService.delete(id);

        return deletedVenueDetail != null;
    }

    /**
     * @deprecated Use {@link #findVenueDetails(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of VenueDetail instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<VenueDetail> searchVenueDetailsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering VenueDetails list by query filter:{}", (Object) queryFilters);
        return venueDetailService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of VenueDetail instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VenueDetail> findVenueDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering VenueDetails list by filter:", query);
        return venueDetailService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of VenueDetail instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<VenueDetail> filterVenueDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering VenueDetails list by filter", query);
        return venueDetailService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportVenueDetails(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return venueDetailService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportVenueDetailsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = VenueDetail.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> venueDetailService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of VenueDetail instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countVenueDetails( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting VenueDetails");
		return venueDetailService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getVenueDetailAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return venueDetailService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VenueDetailService instance
	 */
	protected void setVenueDetailService(VenueDetailService service) {
		this.venueDetailService = service;
	}

}