package raz.test.uri.templates;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.abdera.i18n.templates.CachingContext;
import org.apache.abdera.i18n.templates.Context;
import org.apache.abdera.i18n.templates.Template;

//http://www.ibm.com/developerworks/web/library/wa-uri/

public class URITemplateExample {

	public static void main(String[] args) {

		final Template template = new Template(
				"http://example.org/{foo}/{bar}/{baz}");

		Map<String, String> variableValues = new HashMap<String, String>();

		variableValues.put("foo", "a");
		variableValues.put("bar", "b");
		variableValues.put("baz", "c");

		System.out.println(">>" + template.expand(variableValues));

		Context context = new CachingContext() {

			public Iterator<String> iterator() {

				return template.iterator();
			}

			@Override
			protected <T> T resolveActual(String var) {
				T result = null;

				result = (T) (var.equals("foo") ? "a" : var.equals("bar") ? "b"
						: var.equals("baz") ? "c" : null);

				return result;
			}
		};

		System.out.println(">>" + template.expand(context));
	}

}
