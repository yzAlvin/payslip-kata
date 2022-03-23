package payslip

import kotlin.math.roundToInt

object PayslipGenerator {
    fun generate(annualSalary: Int): Payslip {
        val grossIncome = (annualSalary / 12.0).roundToInt()
        val incomeTax = calculateIncomeTax(annualSalary)
        val netIncome = grossIncome - incomeTax
        return Payslip(grossIncome, incomeTax, netIncome)
    }

    private fun calculateIncomeTax(grossIncome: Int): Int = if (grossIncome <= 18_200) 0 else 29
}
