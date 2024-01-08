package com.system.erp_system.convertor;

import com.system.erp_system.domain.Company;
import com.system.erp_system.domain.Debt;
import com.system.erp_system.dto.debt.DebtResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class DebtConverter {
    public List<DebtResponseDto> from(
            List<Debt> debtList,
            List<Company> companies
    ) {
        List<DebtResponseDto> debtResponseDtos = new ArrayList<>();

        debtList.parallelStream().forEach(debt -> companies.forEach(company -> {
                if(Objects.equals(debt.getCompanyId(), company.getId())) {
                    debtResponseDtos.add(
                         DebtResponseDto.builder()
                                 .id(debt.getId())
                                 .companyName(company.getName())
                                 .companyId(company.getId())
                                 .amount(debt.getAmount())
                                 .status(debt.getStatus())
                                 .createdAt(debt.getCreatedAt())
                                 .updatedAt(debt.getUpdatedAt())
                                 .build()
                    );
                }
            })
        );
        return debtResponseDtos;
    }
}
