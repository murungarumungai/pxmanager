package annotations.writer;

import org.springframework.stereotype.Service;

@Service
public class NiceWriter implements IWriter {

	public void write(String s) {
		System.out.println("The string is:" + s);
	}

}
