package no.uio.ifi.in2000.malikts.oblig1.ui.unitconverter

import kotlin.math.roundToInt

enum class ConverterUnits(val liter: Double) {

    OUNCE(0.02957),
    CUP(0.23659),
    GALLON(3.78541),
    HOGSHEAD(238.481);
}

fun converter(verdi: Int, enhet: ConverterUnits): Int {
    return (verdi * enhet.liter).roundToInt()
}
