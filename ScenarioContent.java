// All gameplay scenarios. The math is written out so each result can be explained.
public class ScenarioContent {

    public static final Scenario[] TAX_SCENARIOS = {

        new Scenario(
            "You have $1,000 in your account. A jacket you like costs $400, plus 8% sales tax at checkout. What do you do?",
            new Scenario.Choice[]{
                // price + (price x 8%)
                new Scenario.Choice("Buy the $400 jacket", -(400 + 400 * 8 / 100), 0, 10, 0,
                    "Sales tax adds $32 (400 x 8%), so you really pay $432. It's affordable, but it's a want that uses up a big part of your cash."),
                new Scenario.Choice("Buy a $100 jacket instead", -(100 + 100 * 8 / 100), 0, 50, 1,
                    "Tax is only $8 (100 x 8%), so you pay $108. You still get a jacket and keep most of your cash."),
                new Scenario.Choice("Skip it and save", 0, 0, 30, 1,
                    "You avoid the $32 of sales tax and keep your cash. Waiting is a good habit for wants, but buying things you will use is fine if you budget for tax.")
            }),

        new Scenario(
            "Your boss offers extra weekend shifts. Each shift pays $300 before taxes, and 20% is withheld for income tax. What do you do?",
            new Scenario.Choice[]{
                // net pay = gross - (gross x 20%)
                new Scenario.Choice("Work 2 extra shifts", 600 - 600 * 20 / 100, 0, 50, 1,
                    "$600 gross - $120 tax = $480 net. Extra income is still worth it: you keep 80 cents of every extra dollar."),
                new Scenario.Choice("Work 1 extra shift", 300 - 300 * 20 / 100, 0, 30, 0,
                    "$300 gross - $60 tax = $240 net. A balanced choice if you need time for school or rest."),
                new Scenario.Choice("Say no, taxes take it all", 0, 0, 0, -1,
                    "Taxes take a percentage (20%), not everything. You would give up $240 per shift. Saying no for other reasons, like school, is fine.")
            }),

        new Scenario(
            "Tax season! You get a $600 refund because too much tax was withheld from your paychecks. You still owe $500 on a credit card at 24% interest. What do you do?",
            new Scenario.Choice[]{
                new Scenario.Choice("Pay off the card with it", 600 - 500, -500, 50, 1,
                    "A refund is your own money coming back, not a bonus. Clearing 24% debt saves about $10 every month (500 x 24% / 12)."),
                new Scenario.Choice("Save all $600", 600, 0, 30, 0,
                    "Savings give you a safety net, but the card keeps charging about $10 a month in interest, more than most savings accounts earn."),
                new Scenario.Choice("Spend it on a trip", 0, 0, 10, -1,
                    "The $600 is spent and the $500 balance keeps charging interest. A big refund also means you overpaid: adjusting your withholding puts more in each paycheck.")
            })
    };

    public static final Scenario[] DEBT_SCENARIOS = {

        new Scenario(
            "You have $1,000 in your account and a $500 credit card balance. The card charges 24% per year (2% per month). What do you do?",
            new Scenario.Choice[]{
                new Scenario.Choice("Pay off the entire $500", -500, -500, 50, 1,
                    "Your debt is $0, so you pay no more interest. This uses half your cash, so keep an emergency fund in mind."),
                // remaining 300 x 2% = 6 interest, so debt change = -200 + 6
                new Scenario.Choice("Pay $200 now", -200, -(200 - 300 * 2 / 100), 30, 0,
                    "Your balance drops to $300, then one month of interest is $6 (300 x 2%), so you owe $306. Progress, but interest keeps running."),
                // late fee 25 + interest 500 x 2% = 10
                new Scenario.Choice("Save it, skip the payment", 0, 25 + 500 * 2 / 100, 0, -1,
                    "Skipping the payment adds a $25 late fee (our assumption) plus $10 interest (500 x 2%). Your debt grows to $535 while cash sits idle.")
            }),

        new Scenario(
            "Your phone broke and a new one costs $600. What do you do?",
            new Scenario.Choice[]{
                // 600 + one month of interest (600 x 2% = 12)
                new Scenario.Choice("Put $600 on the credit card", 0, 600 + 600 * 2 / 100, 0, -1,
                    "One month of 2% interest adds $12 (600 x 2%), and it keeps growing until you pay the balance off."),
                new Scenario.Choice("Buy refurbished for $250", -250, 0, 50, 1,
                    "Paying cash avoids interest, and a refurbished phone meets the same need for much less."),
                new Scenario.Choice("Use a 0% store payment plan", 0, 600, 30, 0,
                    "0% means no extra cost if you pay every installment on time. A missed payment can bring fees and interest, and you still owe $600.")
            }),

        new Scenario(
            "Rent is due and you are $200 short. What do you do?",
            new Scenario.Choice[]{
                // payday loan: 200 borrowed + 30 fee
                new Scenario.Choice("Take a $200 payday loan", 200, 200 + 30, 0, -1,
                    "A $30 fee for two weeks works out to about 390% a year (30 / 200 x 26 periods). It is one of the most expensive ways to borrow."),
                new Scenario.Choice("Borrow from family, no interest", 200, 200, 50, 1,
                    "No interest and no fees. It is still debt, so pay it back as promised to protect the relationship."),
                // cash advance: 200 + 5% fee (10) + 1 month interest (200 x 2% = 4)
                new Scenario.Choice("Credit card cash advance", 200, 200 + 200 * 5 / 100 + 200 * 2 / 100, 20, 0,
                    "A cash advance charges a fee (assumed 5% = $10) plus interest right away ($4), so $200 becomes $214. Cheaper than a payday loan, but still costly.")
            })
    };
}