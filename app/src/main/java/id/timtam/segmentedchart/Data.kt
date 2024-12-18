package id.timtam.segmentedchart

import id.timtam.segmentedchart.data.StockAllocationChartUIState
import id.timtam.segmentedchart.data.StockAllocationItemUIState

object Data {
    val stockData =
        listOf(
            StockAllocationChartUIState(25000, color = "#0BA16B"),
            StockAllocationChartUIState(15000, color = "#35CBB1"),
            StockAllocationChartUIState(10000, color = "#09AE91"), // 50
            StockAllocationChartUIState(5000, color = "#34518D"),
            StockAllocationChartUIState(5000, color = "#2073AC"), // 60
            StockAllocationChartUIState(5000, color = "#439ACB"),
            StockAllocationChartUIState(5000, color = "#796FEC"),
            StockAllocationChartUIState(3000, color = "#645CC1"),
            StockAllocationChartUIState(2000, color = "#8228BD"),
            StockAllocationChartUIState(3000, color = "#7E4B9F"),
            StockAllocationChartUIState(2000, color = "#861D75"),
            StockAllocationChartUIState(2000, color = "#E65BBF"),
            StockAllocationChartUIState(2000, color = "#D5625D"),
            StockAllocationChartUIState(2000, color = "#D74342"),
            StockAllocationChartUIState(2000, color = "#FF3540"),
            StockAllocationChartUIState(2000, color = "#E4830C"),
            StockAllocationChartUIState(1000, color = "#FCBC1D"),
            StockAllocationChartUIState(1000, color = "#966B40"),
            StockAllocationChartUIState(1000, color = "#89A23F"),
            StockAllocationChartUIState(1000, color = "#008D00"),
            StockAllocationChartUIState(6000, color = "#616161"),
        )

    val top20StocksData =
        listOf(
            StockAllocationItemUIState(
                symbol = "ADRO",
                value = "Rp25,000,000",
                percentageText = "25.00%",
                percentage = 0.25f,
                color = "#0BA16B"
            ),
            StockAllocationItemUIState(
                symbol = "BMRI",
                value = "Rp15,000,000",
                percentageText = "15.00%",
                percentage = 0.15f,
                color = "#35CBB1"
            ),
            StockAllocationItemUIState(
                symbol = "BBRI",
                value = "Rp10,000,000",
                percentageText = "10.00%",
                percentage = 0.1f,
                color = "#09AE91"
            ),
            StockAllocationItemUIState(
                symbol = "BBCA",
                value = "Rp5,000,000",
                percentageText = "5.00%",
                percentage = 0.05f,
                color = "#34518D"
            ),
            StockAllocationItemUIState(
                symbol = "AALI",
                value = "Rp5,000,000",
                percentageText = "5.00%",
                percentage = 0.05f,
                color = "#2073AC"
            ), //
            StockAllocationItemUIState(
                symbol = "ADHI",
                value = "Rp5,000,000",
                percentageText = "5.00%",
                percentage = 0.05f,
                color = "#439ACB"
            ),
            StockAllocationItemUIState(
                symbol = "ANTM",
                value = "Rp5,000,000",
                percentageText = "5.00%",
                percentage = 0.05f,
                color = "#796FEC"
            ),
            StockAllocationItemUIState(
                symbol = "ASII",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#645CC1"
            ),
            StockAllocationItemUIState(
                symbol = "BRIS",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#8228BD"
            ),
            StockAllocationItemUIState(
                symbol = "BSDE",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#7E4B9F"
            ), //
            StockAllocationItemUIState(
                symbol = "CTIM",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#861D75"
            ),
            StockAllocationItemUIState(
                symbol = "CTRA",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#E65BBF"
            ),
            StockAllocationItemUIState(
                symbol = "GGRM",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#D5625D"
            ),
            StockAllocationItemUIState(
                symbol = "HSMP",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#D74342"
            ),
            StockAllocationItemUIState(
                symbol = "HRUM",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#FF3540"
            ), //
            StockAllocationItemUIState(
                symbol = "ICBP",
                value = "Rp2,000,000",
                percentageText = "2.00%",
                percentage = 0.02f,
                color = "#E4830C"
            ),
            StockAllocationItemUIState(
                symbol = "INCO",
                value = "Rp1,000,000",
                percentageText = "1.00%",
                percentage = 0.01f,
                color = "#FCBC1D"
            ),
            StockAllocationItemUIState(
                symbol = "INDF",
                value = "Rp1,000,000",
                percentageText = "1.00%",
                percentage = 0.01f,
                color = "#966B40"
            ),
            StockAllocationItemUIState(
                symbol = "INTP",
                value = "Rp1,000,000",
                percentageText = "1.00%",
                percentage = 0.01f,
                color = "#89A23F"
            ),
            StockAllocationItemUIState(
                symbol = "ITMG",
                value = "Rp1,000,000",
                percentageText = "1.00%",
                percentage = 0.01f,
                color = "#008D00"
            ),
        )
}
