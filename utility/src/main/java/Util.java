import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;


public class Util {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

	public static boolean validateInputInt(String property){
		return StringUtils.isNumeric(property);
	}

	public static boolean validateInputDate(String property) {
		boolean valid = false;
		try {
			LocalDate.parse(property, formatter);
			valid = true;
		} catch (java.time.format.DateTimeParseException e) {
			valid = false;
		}
		return valid;
	}

}