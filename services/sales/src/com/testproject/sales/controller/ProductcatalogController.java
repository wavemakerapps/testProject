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

import com.testproject.sales.Productcatalog;
import com.testproject.sales.service.ProductcatalogService;


/**
 * Controller object for domain model class Productcatalog.
 * @see Productcatalog
 */
@RestController("sales.ProductcatalogController")
@Api(value = "ProductcatalogController", description = "Exposes APIs to work with Productcatalog resource.")
@RequestMapping("/sales/Productcatalog")
public class ProductcatalogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductcatalogController.class);

    @Autowired
	@Qualifier("sales.ProductcatalogService")
	private ProductcatalogService productcatalogService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Productcatalog instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Productcatalog createProductcatalog(@RequestBody Productcatalog productcatalog) {
		LOGGER.debug("Create Productcatalog with information: {}" , productcatalog);

		productcatalog = productcatalogService.create(productcatalog);
		LOGGER.debug("Created Productcatalog with information: {}" , productcatalog);

	    return productcatalog;
	}

    @ApiOperation(value = "Returns the Productcatalog instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Productcatalog getProductcatalog(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting Productcatalog with id: {}" , id);

        Productcatalog foundProductcatalog = productcatalogService.getById(id);
        LOGGER.debug("Productcatalog details with id: {}" , foundProductcatalog);

        return foundProductcatalog;
    }

    @ApiOperation(value = "Updates the Productcatalog instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Productcatalog editProductcatalog(@PathVariable("id") Integer id, @RequestBody Productcatalog productcatalog) {
        LOGGER.debug("Editing Productcatalog with id: {}" , productcatalog.getProductId());

        productcatalog.setProductId(id);
        productcatalog = productcatalogService.update(productcatalog);
        LOGGER.debug("Productcatalog details with id: {}" , productcatalog);

        return productcatalog;
    }
    
    @ApiOperation(value = "Partially updates the Productcatalog instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Productcatalog patchProductcatalog(@PathVariable("id") Integer id, @RequestBody @MapTo(Productcatalog.class) Map<String, Object> productcatalogPatch) {
        LOGGER.debug("Partially updating Productcatalog with id: {}" , id);

        Productcatalog productcatalog = productcatalogService.partialUpdate(id, productcatalogPatch);
        LOGGER.debug("Productcatalog details after partial update: {}" , productcatalog);

        return productcatalog;
    }

    @ApiOperation(value = "Deletes the Productcatalog instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteProductcatalog(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting Productcatalog with id: {}" , id);

        Productcatalog deletedProductcatalog = productcatalogService.delete(id);

        return deletedProductcatalog != null;
    }

    /**
     * @deprecated Use {@link #findProductcatalogs(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Productcatalog instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<Productcatalog> searchProductcatalogsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Productcatalogs list by query filter:{}", (Object) queryFilters);
        return productcatalogService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Productcatalog instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Productcatalog> findProductcatalogs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Productcatalogs list by filter:", query);
        return productcatalogService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Productcatalog instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<Productcatalog> filterProductcatalogs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Productcatalogs list by filter", query);
        return productcatalogService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportProductcatalogs(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return productcatalogService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportProductcatalogsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Productcatalog.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> productcatalogService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Productcatalog instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countProductcatalogs( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Productcatalogs");
		return productcatalogService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getProductcatalogAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return productcatalogService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProductcatalogService instance
	 */
	protected void setProductcatalogService(ProductcatalogService service) {
		this.productcatalogService = service;
	}

}