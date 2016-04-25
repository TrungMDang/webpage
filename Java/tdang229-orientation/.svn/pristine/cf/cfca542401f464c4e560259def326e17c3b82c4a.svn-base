/*
 * TCSS 305D Autumn 2014
 * Assignment 0 - Orientation
 */

/**
 * This program prints into the console one of my favorite hero's quote.
 * 
 * @author Trung Dang
 * @version 27 September 2014
 */
public final class IntroductionMain {

    /**
     * Prevent other classes to instantiate this class.
     * I really didn't know this. I searched stackoverflow for a suggestion.
     */
    private IntroductionMain() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * @param args0 A command line argument.
     */
    public static void main(final String[] args0) {
        final Introduction intro = new Introduction("Trung");
        printMyFavoriteQuote(intro);
    }

    /**
     * @param intro0 A class that contains heroName, quote, and some methods.
     */
    private static void printMyFavoriteQuote(final Introduction intro0) {
        intro0.setHeroName("Carl the Invoker");
        intro0.setQuote("I am a beacon of knowledge blazing out"
                + " across a black sea of ignorance");
        System.out.println("My name is " + intro0.getMyName() + ".");
        System.out.println("This is one of my favorite quotes from a game which I play: ");
        System.out.println(intro0.getQuote() + " (Dota 2 - " + intro0.getHeroName() + ").");
        System.out.println("Credits: http://dota2.gamepedia.com/Invoker_responses");
    }
}
