// All quiz questions in one place
public class QuizContent {

    public static final QuizQuestion[] TAX_QUIZ = {
        new QuizQuestion("What is NET pay?",
            new String[]{"Pay before taxes", "Pay after taxes are taken out", "The total tax you owe", "The sales tax on a purchase"},
            1, "Net pay is your take-home pay, what you actually receive after taxes."),
        new QuizQuestion("You earn $2,000 gross and $300 goes to taxes. What is your net pay?",
            new String[]{"$300", "$2,300", "$1,700", "$1,500"},
            2, "Net pay = gross pay - taxes, so $2,000 - $300 = $1,700."),
        new QuizQuestion("An item costs $50 and the sales tax is 8%. What is the total price?",
            new String[]{"$54", "$58", "$50.80", "$4"},
            0, "Sales tax = $50 x 0.08 = $4, so the total is $50 + $4 = $54."),
        new QuizQuestion("What do taxes help pay for?",
            new String[]{"Only video game prices", "Your personal rent", "Only the president's salary", "Roads, public schools, and fire departments"},
            3, "Taxes fund public services like roads, schools, police, and fire departments."),
        new QuizQuestion("You earn $500 and 10% is taken out for taxes. How much do you take home?",
            new String[]{"$400", "$450", "$490", "$550"},
            1, "10% of $500 is $50, so you take home $500 - $50 = $450.")
    };

    public static final QuizQuestion[] DEBT_QUIZ = {
        new QuizQuestion("What is interest?",
            new String[]{"A gift from the bank", "Money you earn from work", "The price of borrowing money", "A type of tax"},
            2, "Interest is a percentage you pay for borrowing money."),
        new QuizQuestion("You owe $1,000 at 12% interest per year. About how much interest do you owe after one year?",
            new String[]{"$12", "$120", "$1,200", "$100"},
            1, "Interest = $1,000 x 0.12 = $120 per year."),
        new QuizQuestion("What happens if you only pay the minimum on a credit card?",
            new String[]{"The debt disappears", "The interest stops", "Nothing changes", "You stay in debt longer and pay more interest"},
            3, "Paying only the minimum leaves most of the balance, which keeps charging interest."),
        new QuizQuestion("To save the most money on interest, which debt should you pay off first?",
            new String[]{"The one with the highest interest rate", "The one with the lowest balance", "The newest one", "None of them"},
            0, "Higher interest costs you more each month, so paying it down first saves the most."),
        new QuizQuestion("Which is most likely 'good debt'?",
            new String[]{"A credit card used for impulse buys", "A payday loan for fun", "A student loan for a degree that raises your future income", "Borrowing for things you don't need"},
            2, "Good debt helps build your future earnings. High-interest debt for wants usually hurts.")
    };
}