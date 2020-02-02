import static org.junit.Assert.*;

import org.junit.Test;

import com.ramesh.fileparse.impl.FileValidation;

public class fileParseTest {

	@Test
	public void testValidateExtn() {
		boolean ans = true;
		boolean val;
		String path = "C:\\temp\\temp.txt";
		FileValidation fv = new FileValidation();
		val = fv.validateExtn(path);
		assertEquals(ans, val);
	}

	@Test
	public void testValidateExtnFalse() {
		boolean ans = false;
		boolean val;
		String path = "C:\\temp\\temp.csv";
		FileValidation fv = new FileValidation();
		val = fv.validateExtn(path);
		assertEquals(ans, val);
	}

}
