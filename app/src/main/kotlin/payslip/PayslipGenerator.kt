package payslip

import kotlin.math.roundToInt

object PayslipGenerator {

    private val taxRanges = listOf(
        TaxRange(0, 18_200, 0, 0.00),
        TaxRange(18200, 37_000, 0, 0.19),
        TaxRange(37_000, 87_000, 3572, 0.325),
        TaxRange(87_000, 180_000, 19_822, 0.37),
        TaxRange(180_000, Int.MAX_VALUE, 54_232, 0.45)
    )

    fun generate(annualSalary: Int): Payslip {
        val grossIncome = (annualSalary / 12.0).roundToInt()
        val incomeTax = calculateMonthlyIncomeTax(annualSalary)
        val netIncome = grossIncome - incomeTax
        return Payslip(grossIncome, incomeTax, netIncome)
    }

    private fun calculateMonthlyIncomeTax(annualSalary: Int): Int {
        return if (annualSalary == 0) 0
        else
            taxRanges.firstOrNull { annualSalary > it.lowerBound && annualSalary <= it.higherBound }
                ?.let { calculateTax(annualSalary, it.previousTax, it.lowerBound, it.taxRate) }
                ?: throw IllegalArgumentException()
    }

    private fun calculateTax(annualSalary: Int, previousTax: Int, lowerBound: Int, taxRate: Double) =
        ((previousTax + (annualSalary - lowerBound) * taxRate) / 12).roundToInt()
}

data class TaxRange(val lowerBound: Int, val higherBound: Int, val previousTax: Int, val taxRate: Double)
