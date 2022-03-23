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
        val monthsInYear = 12
        return if (annualSalary <= 18_200)
            0
        else ((annualSalary - 18_200) * 0.19 / monthsInYear).roundToInt()
    }
}
