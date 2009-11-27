package com.puresol.entities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import com.puresol.entities.forms.AutomaticContent;
import com.puresol.entities.forms.AutomaticContentType;
import com.puresol.entities.forms.Template;
import com.puresol.entities.forms.TemplateElement;
import com.puresol.entities.forms.TemplateElementLayout;
import com.puresol.entities.forms.TemplateInformation;

import junit.framework.Assert;

/**
 * This is a small API for entity testing. This class is called Tester not to be
 * captured by JUnit as a separate test case.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntityTester {

	static public boolean testEntity(Class<?> clazz) {
		try {
			testConstructor(clazz);
			testClassAnnotations(clazz);
			testFieldAnnotations(clazz);
			testMethodAnnotations(clazz);
			testTemplateInformation(clazz.getConstructor().newInstance());
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (SecurityException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return false;
	}

	static private void testConstructor(Class<?> clazz) {
		try {
			Assert.assertNotNull(clazz.getConstructor());
		} catch (SecurityException e) {
			Assert.fail("No default constructor found due to security issue!");
		} catch (NoSuchMethodException e) {
			Assert.fail("No default constructor found!");
		}
	}

	static private void testClassAnnotations(Class<?> clazz) {
		try {
			Assert.assertNotNull(clazz.getAnnotation(Entity.class));

			PersistenceContext persistenceContext = clazz
					.getAnnotation(PersistenceContext.class);
			Assert.assertNotNull(persistenceContext);
			Assert.assertFalse(persistenceContext.unitName().isEmpty());

			Table table = clazz.getAnnotation(Table.class);
			Assert.assertNotNull(table);
			Assert.assertFalse(table.name().isEmpty());

			Template layout = clazz.getAnnotation(Template.class);
			Assert.assertNotNull(layout);
			Assert.assertFalse(layout.name().isEmpty());

			NamedQueries queries = clazz.getAnnotation(NamedQueries.class);
			NamedQuery[] query = queries.value();
			boolean foundGetAllQuery = false;
			for (int index = 0; index < query.length; index++) {
				if (query[index].name().equals(
						clazz.getSimpleName() + ".getAll")) {
					foundGetAllQuery = true;
				}
			}
			Assert.assertTrue(foundGetAllQuery);

			IdClass idClass = clazz.getAnnotation(IdClass.class);
			if (idClass != null) {
				Assert.assertNotNull(clazz.getMethod("createIdClass"));
			}
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		}
	}

	static private void testFieldAnnotations(Class<?> clazz) {
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			Assert.assertNotNull(field.getAnnotations());
		}
	}

	static private void testMethodAnnotations(Class<?> clazz) {
		Method methods[] = clazz.getMethods();
		boolean foundID = false;
		Vector<String> templateNames = new Vector<String>();
		Vector<Integer> templateIDs = new Vector<Integer>();
		for (int index = 0; index < methods.length; index++) {
			Method method = methods[index];
			String methodName = method.getName();
			if (methodName.equals("getClass")) {
				continue;
			}
			if (methodName.startsWith("set")) {
				Assert.assertEquals(0, method.getAnnotations().length);
				continue;
			}
			if (!methodName.startsWith("get")) {
				continue;
			}
			AutomaticContent automatic = method
					.getAnnotation(AutomaticContent.class);
			if (automatic != null) {
				if (automatic.type().equals(AutomaticContentType.CURRENT_DATE)) {
					Assert
							.assertTrue(method.getReturnType().equals(
									Date.class));
				} else if (automatic.type().equals(
						AutomaticContentType.CURRENT_USER)) {
					Assert.assertTrue(method.getReturnType().equals(
							String.class));
				} else {
					Assert.fail("Unknown and untested automatic content type: "
							+ automatic.type().toString());
				}
			}
			Column column = method.getAnnotation(Column.class);
			JoinColumn joinColumn = method.getAnnotation(JoinColumn.class);
			JoinColumns joinColumns = method.getAnnotation(JoinColumns.class);
			JoinTable joinTable = method.getAnnotation(JoinTable.class);
			if (column != null) {
				Assert.assertFalse(column.name().isEmpty());
			} else if (joinColumn != null) {
				Assert.assertFalse(joinColumn.name().isEmpty());
			} else if (joinColumns != null) {
				JoinColumn[] columns = joinColumns.value();
				for (JoinColumn col : columns) {
					Assert.assertFalse(col.name().isEmpty());
				}
			} else if (joinTable != null) {
				Assert.assertFalse(joinTable.name().isEmpty());
			} else if (method.getAnnotation(OneToMany.class) != null) {
			} else {
				Assert.fail("No column name found!");
			}

			TemplateElementLayout elementLayout = method
					.getAnnotation(TemplateElementLayout.class);
			Assert.assertNotNull(elementLayout);
			Assert.assertFalse(elementLayout.name().isEmpty());
			Assert.assertTrue(elementLayout.id() >= 0);
			Assert.assertFalse(templateNames.contains(elementLayout.name()));
			templateNames.add(elementLayout.name());
			Assert.assertFalse(templateIDs.contains(elementLayout.id()));
			templateIDs.add(elementLayout.id());

			if (method.getAnnotation(Id.class) != null) {
				foundID = true;
			}
		}
		Assert.assertTrue(foundID);
		for (int id = 0; id < templateIDs.size(); id++) {
			Assert.assertTrue(templateIDs.contains(id));
		}
	}

	static private void testTemplateInformation(Object entity) {
		TemplateInformation template = TemplateInformation.from(entity);
		Assert.assertNotNull(template);
		Assert.assertNotNull(template.getEntity());
		Assert.assertTrue(template.getInputCount() > 0);
		Assert.assertFalse(template.getName().isEmpty());

		for (int index = 0; index < template.getInputCount(); index++) {
			TemplateElement element = template.get(index);
			Assert.assertNotNull(element.getEntity());
			Assert.assertNotNull(element.getGetter());
			Assert.assertNotNull(element.getSetter());
			Class<?> type = element.getType();
			Assert.assertNotNull(type);
			Assert.assertFalse(type.isPrimitive());
			Assert.assertFalse(element.getName().isEmpty());
			Assert.assertFalse(element.getIdString().isEmpty());
		}
	}
}
