package com.han.screw.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据库枚举
 *
 * @author han
 * @date 2022/08/02
 */
@AllArgsConstructor
public enum DatabaseEnum {

    TMD_BLOCK_CHAIN("tmd_block_chain"),
    TMD_CRM("tmd_crm"),
    TMD_FINANCE("tmd_finance"),
    TMD_MESSAGE("tmd_message"),
    TMD_MT4("tmd_mt4"),
    TMD_OPERATION_CONFIG("tmd_operation_config");

    @Getter
    private final String name;
}
