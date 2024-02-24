package no.uio.ifi.in2000.malikts.oblig1.ui.palindrome

fun isPalindrome(tekst: String): Boolean {
    return tekst.lowercase() == tekst.lowercase().reversed()
}
