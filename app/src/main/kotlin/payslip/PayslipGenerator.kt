package payslip

import kotlin.math.roundToInt

class PayslipGenerator {
    fun generate(annualSalary: Int): Payslip {
        val grossIncome = (annualSalary / 12.0).roundToInt()
        return Payslip(grossIncome, 0, grossIncome)
    }
}
