package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelBottomAppearance;

public class EasyTabPanelBottomAppearance extends EasyTabPanelAppearance
		implements TabPanelBottomAppearance {

	public interface BottomTemplate extends Template {

		@XTemplate(source = "TabPanelBottom.html")
		SafeHtml render(TabPanelStyle style);

	}

	public EasyTabPanelBottomAppearance() {
		this(GWT.<EasyTabPanelResources> create(EasyTabPanelResources.class),
				GWT.<BottomTemplate> create(BottomTemplate.class), GWT
						.<ItemTemplate> create(ItemTemplate.class));
	}

	public EasyTabPanelBottomAppearance(EasyTabPanelResources resources,
			BottomTemplate template, ItemTemplate itemTemplate) {
		super(resources, template, itemTemplate);
	}

	@Override
	public XElement getBar(XElement parent) {
		return parent.selectNode("." + style.tabFooter());
	}

	@Override
	public XElement getStrip(XElement parent) {
		return parent.selectNode("." + style.tabStripBottom());
	}

}
