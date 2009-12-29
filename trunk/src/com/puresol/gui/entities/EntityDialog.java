/***************************************************************************
 *
 *   EntityDialog.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.entities;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;

import javax.i18n4j.Translator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swingx.Dialog;

import org.apache.log4j.Logger;

import com.puresol.appserv.EntityManagerFactories;
import com.puresol.entities.forms.TemplateInformation;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.gui.entities.forms.GUITemplateRenderer;

/**
 * This dialog is for showing, adding and changing EJB3 beans. The form for the
 * dialog is created by the GUITemplateRenderer.
 * 
 * Entities which are used with this class should be persistent or must not have
 * lazy fetched lists.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntityDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(EntityDialog.class);
	private static final Translator translator = Translator
			.getTranslator(EntityDialog.class);

	static public final int SHOW = 0;
	static public final int CREATE = 1;
	static public final int CHANGE = 2;

	private int type = SHOW;
	private GUITemplateRenderer template = null;
	private EntityManager entityManager = null;

	static public EntityDialog show(Object entity) {
		return new EntityDialog(SHOW, entity);
	}

	static public EntityDialog create(Class<?> entity) {
		try {
			return new EntityDialog(CREATE, entity.getConstructor()
					.newInstance());
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (SecurityException e) {
			throw new StrangeSituationException(e);
		} catch (InstantiationException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		} catch (InvocationTargetException e) {
			throw new StrangeSituationException(e);
		} catch (NoSuchMethodException e) {
			throw new StrangeSituationException(e);
		}
	}

	static public EntityDialog change(Object entity) {
		return new EntityDialog(CHANGE, entity);
	}

	private EntityDialog(int type, Object entity) {
		super("EntityDialog", true);
		this.type = type;
		if (type != CREATE) {
			PersistenceContext persistenceContext = (PersistenceContext) entity
					.getClass().getAnnotation(PersistenceContext.class);
			entityManager = EntityManagerFactories.create(
					persistenceContext.unitName()).createEntityManager();
			if (!entityManager.contains(entity)) {
				// entityManager.persist(entity);
			}
		}

		setTitle(type, entity);
		initUI(entity);
	}

	private void setTitle(int type, Object entity) {
		TemplateInformation information = TemplateInformation.from(entity);
		switch (type) {
		case SHOW:
			setTitle(translator.i18n("Show: {0}", translator.i18n(information
					.getName())));
			break;
		case CREATE:
			setTitle(translator.i18n("Create: {0}", translator.i18n(information
					.getName())));
			break;
		case CHANGE:
			setTitle(translator.i18n("Change: {0}", translator.i18n(information
					.getName())));
			break;
		default:
			setTitle("");
		}
	}

	private void initUI(Object entity) {
		setDefaultLayout();
		if (type == SHOW) {
			getDefaultCancelButton().setVisible(false);
		}
		template = GUITemplateRenderer.render(entity, type);
		add(template, BorderLayout.CENTER);
		pack();
	}

	public Object getEntity() {
		return template.getUpdatedEntity();
	}

	public void dispose() {
		if (entityManager != null) {
			logger.info("clearing and closing entity manager");
			entityManager.clear();
			entityManager.close();
			entityManager = null;
		}
		super.dispose();
	}
}
