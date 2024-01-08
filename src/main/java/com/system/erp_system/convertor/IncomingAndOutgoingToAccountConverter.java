package com.system.erp_system.convertor;

import com.system.erp_system.domain.Company;
import com.system.erp_system.domain.IncomingAndOutgoingToAccount;
import com.system.erp_system.dto.incoming_and_outgoing_to_account.IncomingAndOutgoingToAccountResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class IncomingAndOutgoingToAccountConverter {
    public List<IncomingAndOutgoingToAccountResponseDto> from(
            List<IncomingAndOutgoingToAccount> incomingAndOutgoingToAccounts,
            List<Company> companies
    ) {
        List<IncomingAndOutgoingToAccountResponseDto> incomingProductToInventoryResponseDtos = new ArrayList<>();

        incomingAndOutgoingToAccounts.forEach(incomingAndOutgoingToAccount -> companies.forEach(company -> {
                if(incomingAndOutgoingToAccount.getCompanyId().equals(company.getId())) {
                    incomingProductToInventoryResponseDtos.add(
                            IncomingAndOutgoingToAccountResponseDto.builder()
                                    .id(incomingAndOutgoingToAccount.getId())
                                    .price(incomingAndOutgoingToAccount.getPrice())
                                    .companyId(incomingAndOutgoingToAccount.getCompanyId())
                                    .companyName(company.getName())
                                    .status(incomingAndOutgoingToAccount.getStatus())
                                    .createdAt(incomingAndOutgoingToAccount.getCreatedAt())
                                    .updatedAt(incomingAndOutgoingToAccount.getUpdatedAt())
                                    .build()
                    );
                }
            }));

        return incomingProductToInventoryResponseDtos;
    }
}
