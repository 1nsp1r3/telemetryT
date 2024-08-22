package org.inspir3.telemetryt

object Format {
    private fun getShortAsFloat(value: Short): Float = value.toFloat().div(100)
    private fun psiToBar(psi: Float): Float = psi / 14.5038f

    fun getTemperatureAsText(value: Short): String = "%.2f".format(
        getShortAsFloat(value)
    )

    fun getPressureAsText(value: Short): String = "%.2f".format(
        psiToBar(
            getShortAsFloat(value)
        )
    )
}