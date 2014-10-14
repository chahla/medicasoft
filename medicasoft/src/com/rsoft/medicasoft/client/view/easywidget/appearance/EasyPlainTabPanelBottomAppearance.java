package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.rsoft.medicasoft.client.view.easywidget.appearance.EasyPlainTabPanelAppearance.EasyPlainTabPanelResources;
import com.rsoft.medicasoft.client.view.easywidget.appearance.EasyPlainTabPanelAppearance.EasyPlainTabPanelStyle;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel.PlainTabPanelAppearance;

/**
 * A Easy-coloured appearance for {@link PlainTabPanel} with tabs below the
 * content area. This appearance differs from
 * {@link EasyTabPanelBottomAppearance} in that it has a simplified tab strip.
 */
public class EasyPlainTabPanelBottomAppearance extends EasyTabPanelBottomAppearance implements PlainTabPanelAppearance {

  public interface PlainTabPanelBottomTemplates extends BottomTemplate {

    @XTemplate(source = "TabPanelBottom.html")
    SafeHtml render(TabPanelStyle style);

    @XTemplate(source = "PlainTabPanelBottom.html")
    SafeHtml renderPlain(EasyPlainTabPanelStyle style);

  }

  protected PlainTabPanelBottomTemplates template;
  protected EasyPlainTabPanelResources resources;

  public EasyPlainTabPanelBottomAppearance() {
    this(GWT.<EasyPlainTabPanelResources> create(EasyPlainTabPanelResources.class),
        GWT.<PlainTabPanelBottomTemplates> create(PlainTabPanelBottomTemplates.class),
        GWT.<ItemTemplate> create(ItemTemplate.class));
  }

  public EasyPlainTabPanelBottomAppearance(EasyPlainTabPanelResources resources, PlainTabPanelBottomTemplates template,
      ItemTemplate itemTemplate) {
    super(resources, template, itemTemplate);
    this.resources = resources;
    this.template = template;
  }

  @Override
  public void render(SafeHtmlBuilder builder) {
    builder.append(template.renderPlain(resources.style()));
  }

}
