package payslip

import kotlin.math.roundToInt

object PayslipGenerator {
    fun generate(annualSalary: Int): Payslip {
        val grossIncome = (annualSalary / 12.0).roundToInt()
        val incomeTax = calculateMonthlyIncomeTax(annualSalary)
        val netIncome = grossIncome - incomeTax
        return Payslip(grossIncome, incomeTax, netIncome)
    }

    private fun calculateMonthlyIncomeTax(annualSalary: Int): Int {
        return when {
            annualSalary <= 18_200 -> 0
            annualSalary <= 37_000 -> calculateTax(annualSalary, 0, 18_200, 0.19)
            else -> calculateTax(annualSalary, 3572, 37_000, 0.325)
        }
    }

    private fun calculateTax(annualSalary: Int, previousTax: Int, lowerBound: Int, taxRate: Double) =
        ((previousTax + (annualSalary - lowerBound) * taxRate) / 12).roundToInt()
}
