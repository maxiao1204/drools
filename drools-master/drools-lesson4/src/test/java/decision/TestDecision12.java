package decision;

import com.demo.entity.Cep;
import com.demo.entity.Obligor;
import com.demo.utils.GlobalUtil;
import lombok.extern.slf4j.Slf4j;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 获取规则文件内容，并生成对应的规则
 * @author sec
 * @version 1.0
 * @date 2020/3/12 10:27 AM
 **/
@Slf4j
public class TestDecision12 {

	@Test
	public void createDrl() throws IOException {

		File file = new File("C:\\Users\\xma\\Desktop\\drools-master\\drools-lesson4\\src\\main\\resources\\com\\decision12\\decision12.xls");
		InputStream is = new FileInputStream(file);
		SpreadsheetCompiler compiler = new SpreadsheetCompiler();
		String drl = compiler.compile(is, InputType.XLS);
		System.out.println(drl);
		// 关闭资源
		is.close();
	}

	@Test
	public void checkDrl2() {
		KieContainer container = KieServices.get().getKieClasspathContainer();
		Results results = container.verify();
		if (results.hasMessages(Message.Level.ERROR)) {
			List<Message> messages = results.getMessages();
			for (Message message : messages) {
				log.info("错误信息:{}", message);
			}
		}

		KieSession kieSession = container.newKieSession("decision-rules12");

		Obligor obligor = new Obligor();
		obligor.setClassification("L");
		obligor.setOrr(22);
		Cep cep = new Cep();
		kieSession.insert(obligor);
		kieSession.insert(cep);

		int count = kieSession.fireAllRules();

		System.out.println("cep orr is:"+cep.getOrr());
		log.info("count:{}", GlobalUtil.getCount());
		log.info("Fired {} rules!", count);
		kieSession.dispose();



//		SpreadsheetCompiler compiler = new SpreadsheetCompiler();
//		String drl = compiler.compile(ResourceFactory.newClassPathResource("com/decision1/decision.xls"),
//				InputType.XLS);
//		System.out.println(drl);
	}
}
