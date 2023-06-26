package id.co.indivara.jdt12.carrental;

import id.co.indivara.jdt12.carrental.testcase1.TestCaseCustomerController;
import id.co.indivara.jdt12.carrental.testcase2.TestCaseDriverController;
import id.co.indivara.jdt12.carrental.testcase3.TestCaseVehicleController;
import id.co.indivara.jdt12.carrental.testcase4.TestCaseRentalController;
import id.co.indivara.jdt12.carrental.testcase5.TestCaseInvoiceController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
		TestCaseCustomerController.class,
		TestCaseDriverController.class,
		TestCaseVehicleController.class
})
public class CarrentalApplicationTests {

}
