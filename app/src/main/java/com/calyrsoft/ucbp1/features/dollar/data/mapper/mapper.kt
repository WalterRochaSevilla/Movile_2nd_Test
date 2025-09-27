package com.calyrsoft.ucbp1.features.dollar.data.mapper

import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel

fun DollarEntity.toModel(): DollarModel {
    return DollarModel(
        officialBuy = officialBuy,
        officialSell = officialSell,
        parallelBuy = parallelBuy,
        parallelSell = parallelSell,
        updateDate = updateDate
    )
}

fun DollarModel.toEntity(): DollarEntity {
    return DollarEntity(
        officialBuy = officialBuy,
        officialSell = officialSell,
        parallelBuy = parallelBuy,
        parallelSell = parallelSell,
        updateDate = updateDate
    )
}

