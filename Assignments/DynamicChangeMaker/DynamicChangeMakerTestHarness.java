public class DynamicChangeMakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_USA();
        // Add more!
        // A few examples:
        // test_Euros(); // 1,2,5,10,20,50
        // test_SwissFrancs(); // 5,10,20,50 (NO 1 CENT COIN!)
        // test_Keckels(); // 7,3,29,15,53 (Made-up currency with non-standard denominations)

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_USA() {
        System.out.println("\nTests for USA denominations {25,10,5,1}:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        int[] usaDenominations = new int[] { 25, 10, 5, 1 };

        try {
            DynamicChangeMaker usaTest = new DynamicChangeMaker(usaDenominations, 99);
            usaTest.solveTable();
            displaySuccessIfTrue(usaTest.getSolution().length() == 4
                    && 3 == usaTest.getSolution().getElement(0)
                    && 2 == usaTest.getSolution().getElement(1)
                    && 0 == usaTest.getSolution().getElement(2)
                    && 4 == usaTest.getSolution().getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] usaDenominationsShuffled = new int[] { 5, 1, 25, 10 };

        try {
            DynamicChangeMaker usaTest = new DynamicChangeMaker(usaDenominationsShuffled, 99);
            usaTest.solveTable();
            displaySuccessIfTrue(usaTest.getSolution().length() == 4
                    && 0 == usaTest.getSolution().getElement(0)
                    && 4 == usaTest.getSolution().getElement(1)
                    && 3 == usaTest.getSolution().getElement(2)
                    && 2 == usaTest.getSolution().getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        // Add more test for different amounts of change for both sorted
        // and unsorted denominations!

        System.out.println("\nUSA denominations: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

}
