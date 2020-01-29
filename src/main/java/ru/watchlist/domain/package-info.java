@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence"
)

@org.hibernate.annotations.GenericGenerator(
        name = "ID_PERIOD_GENERATOR",
        strategy = "ru.watchlist.idgenerator.PeriodIdentifierGenerator"
)

package ru.watchlist.domain;