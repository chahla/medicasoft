package com.rsoft.medicasoft.shared;

import java.util.Collections;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;

public class ModelFilter<M> extends Filter<M, ModelBase> {

	/**
	 * The default locale-sensitive messages used by this class.
	 */
	public class DefaultModelFilterMessages implements ModelFilterMessages {

		@Override
		public String emptyText() {
			return DefaultMessages.getMessages().stringFilter_emptyText();
		}

	}

	/**
	 * The locale-sensitive messages used by this class.
	 */
	public interface ModelFilterMessages {
		String emptyText();
	}

	protected TextField field;

	private ModelFilterMessages messages;
	private DelayedTask updateTask = new DelayedTask() {

		@Override
		public void onExecute() {
			fireUpdate();
		}
	};

	/**
	 * Creates a string filter for the specified value provider. See
	 * {@link Filter#Filter(ValueProvider)} for more information.
	 * 
	 * @param valueProvider
	 *            the value provider
	 */
	public ModelFilter(ValueProvider<? super M, ModelBase> valueProvider) {
		super(valueProvider);
		setHandler(new ModelFilterHandler());

		field = new TextField() {
			protected void onKeyUp(Event event) {
				super.onKeyUp(event);
				onFieldKeyUp(event);
			};
		};

		menu.add(field);
		menu.addBeforeHideHandler(new BeforeHideHandler() {

			@Override
			public void onBeforeHide(BeforeHideEvent event) {
				// blur the field because of empty text
				// field.el().firstChild().blur();
				// blurField(field);
				field.getElement().selectNode("input").blur();
			}
		});

		setMessages(getMessages());
	}

	@Override
	public List<FilterConfig> getFilterConfig() {
		FilterConfig cfg = createNewFilterConfig();
		cfg.setType("ModelBase");
		cfg.setComparison("contains");
		String dummy = field.getCurrentValue();
		ModelBase valueToSend = new ModelBase() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getKey() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void validateModel() throws ModelException {
				// TODO Auto-generated method stub

			}

			@Override
			public void merge(ModelBase model) {
				// TODO Auto-generated method stub

			}

			@Override
			public Object getPrimaryKey() {
				// TODO Auto-generated method stub
				return null;
			}

		};
		if (dummy != null && !dummy.trim().isEmpty()) {
			valueToSend.setEntityId(Long.parseLong(dummy.trim()));
		}
		cfg.setValue(getHandler().convertToString(valueToSend));

		return Collections.singletonList(cfg);
	}

	/**
	 * Returns the locale-sensitive messages used by this class.
	 * 
	 * @return the local-sensitive messages used by this class.
	 */
	public ModelFilterMessages getMessages() {
		if (messages == null) {
			messages = new DefaultModelFilterMessages();
		}
		return messages;
	}

	@Override
	public Object getValue() {
		return field.getCurrentValue();
	}

	@Override
	public boolean isActivatable() {
		return field.getCurrentValue() != null
				&& field.getCurrentValue().length() > 0;
	}

	/**
	 * Sets the local-sensitive messages used by this class.
	 * 
	 * @param messages
	 *            the locale sensitive messages used by this class.
	 */
	public void setMessages(ModelFilterMessages messages) {
		this.messages = messages;
		field.setEmptyText(messages.emptyText());
	}

	protected Class<ModelBase> getType() {
		return ModelBase.class;
	}

	protected void onFieldKeyUp(Event event) {
		int key = event.getKeyCode();
		if (key == KeyCodes.KEY_ENTER && field.isValid()) {
			event.stopPropagation();
			event.preventDefault();
			menu.hide(true);
			return;
		}
		updateTask.delay(getUpdateBuffer());
	}

	protected boolean validateModel(M model) {
		ModelBase val = getValueProvider().getValue(model);
		Object value = getValue();
		String v = value == null ? "" : value.toString();
		if (v.length() == 0 && (val == null || val.getEntityId() == null)) {
			return true;
		} else if (val == null) {
			return false;
		} else {
			return Long.toString(val.getEntityId()).toLowerCase()
					.indexOf(v.toLowerCase()) > -1;
		}
	}
}
