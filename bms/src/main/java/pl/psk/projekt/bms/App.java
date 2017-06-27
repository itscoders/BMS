package pl.psk.projekt.bms;

import pl.psk.projekt.bms.ui.StartWindow;

/**
 * Klasa App wywołuje okno stratowe aplikacji. 
 * 
 * @author Paweł Pawelec i Kamil Świąder
 */

public class App 
{
	/** Metoda odpowiedzialna za wywołanie konstruktora klasy StartWindow. 
	 *  Dzięki wywołaniu konstruktora w tej metodzie możliwe jest prawidłowe uruchomienie gry poprzez jej kompilację 
	 *  lub bezpośrednio z poziomu archiwum .jar dla użytkownika.
	 *  @param args - domyślna tablica Stringów w celu poprawnego wywołania metody statycznej main(). 
	 */
    public static void main( String[] args )
    {
    	new StartWindow();
       
    }
}
