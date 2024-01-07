package decision;

import com.demo.entity.Address;
import com.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

/**
 * @author sec
 * @version 1.0
 * @date 2020/3/12 10:51 AM
 **/
@Slf4j
public class TestMultiTables {

	@Test
	public void checkDrl2() {
		SpreadsheetCompiler compiler = new SpreadsheetCompiler();
		String drl = compiler.compile(ResourceFactory.newClassPathResource("com/decision10/decision10.xls"),
				InputType.XLS);
		System.out.println(drl);
	}

	@Test
	public void testDecision() {

		KieContainer container = KieServices.get().getKieClasspathContainer();

		Person person = new Person();
		person.setAge(24);
		person.setName("Tom");

		Address address = new Address();
		address.setCountry("China");
		address.setCity("BeiJing");

		KieSession kieSession = container.newKieSession("decision-rules10");

		kieSession.insert(person);
		kieSession.insert(address);
		int count = kieSession.fireAllRules();

		log.info("Fired {} rules!", count);
		kieSession.dispose();
	}
}
