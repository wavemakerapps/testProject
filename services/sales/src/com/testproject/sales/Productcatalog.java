/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testproject.sales;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Productcatalog generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`productcatalog`")
public class Productcatalog implements Serializable {

    private Integer productId;
    private String planCode;
    private String unitedHealthCarePlan;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`product_id`", nullable = false, scale = 0, precision = 10)
    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "`planCode`", nullable = false, length = 30)
    public String getPlanCode() {
        return this.planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    @Column(name = "`unitedHealthCarePlan`", nullable = false, length = 30)
    public String getUnitedHealthCarePlan() {
        return this.unitedHealthCarePlan;
    }

    public void setUnitedHealthCarePlan(String unitedHealthCarePlan) {
        this.unitedHealthCarePlan = unitedHealthCarePlan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Productcatalog)) return false;
        final Productcatalog productcatalog = (Productcatalog) o;
        return Objects.equals(getProductId(), productcatalog.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }
}