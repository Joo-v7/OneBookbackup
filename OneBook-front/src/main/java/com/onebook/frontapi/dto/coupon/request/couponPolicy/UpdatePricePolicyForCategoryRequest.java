package com.onebook.frontapi.dto.coupon.request.couponPolicy;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UpdatePricePolicyForCategoryRequest {

    private Long id;
    @NotNull
    private Integer minimumOrderAmount;
    @NotNull
    private Integer discountPrice;
    @NotNull
    private LocalDateTime expirationPeriodStart;
    @NotNull
    private LocalDateTime expirationPeriodEnd;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer policyStatusId;
}
