import java.awt.*;

// Holds every color and font in one place so the whole game stays consistent
public class Theme {
    public static final Color NAVY   = new Color(18, 32, 72);    // backgrounds
    public static final Color GREEN  = new Color(46, 204, 113);  // good choices
    public static final Color BLUE   = new Color(52, 152, 219);  // standard actions
    public static final Color ORANGE = new Color(243, 156, 18);  // neutral/risky
    public static final Color PURPLE = new Color(155, 89, 182);  // upgrades
    public static final Color RED    = new Color(231, 76, 60);   // bad consequences

    public static final Font TITLE   = new Font("Arial", Font.BOLD, 44);
    public static final Font HEADING = new Font("Arial", Font.BOLD, 32);
    public static final Font BODY    = new Font("Arial", Font.PLAIN, 22);
    public static final Font BUTTON  = new Font("Arial", Font.BOLD, 24);
}