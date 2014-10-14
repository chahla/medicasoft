package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.Element;
import com.sencha.gxt.widget.core.client.menu.HeaderMenuItem.HeaderMenuItemAppearance;

public class EasyHeaderMenuItemAppearance extends EasyItemAppearance implements
		HeaderMenuItemAppearance {

	public interface GrayHeaderMenuItemResources extends GrayItemResources {

		@Source("EasyHeaderMenuItem.css")
		GrayHeaderMenuItemStyle headerStyle();

	}

	public interface GrayHeaderMenuItemStyle extends CssResource {

		public String menuText();

	}

	private GrayHeaderMenuItemStyle headerStyle;

	public EasyHeaderMenuItemAppearance() {
		this(
				GWT.<GrayHeaderMenuItemResources> create(GrayHeaderMenuItemResources.class));
	}

	public EasyHeaderMenuItemAppearance(GrayHeaderMenuItemResources resources) {
		super(resources);
		headerStyle = resources.headerStyle();
		headerStyle.ensureInjected();
	}

	@Override
	public void applyItemStyle(Element element) {
		element.addClassName(headerStyle.menuText());
	}

}
