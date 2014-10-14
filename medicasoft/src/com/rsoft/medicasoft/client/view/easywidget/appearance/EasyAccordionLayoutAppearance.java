package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.panel.AccordionLayoutBaseAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderDefaultAppearance;

public class EasyAccordionLayoutAppearance extends AccordionLayoutBaseAppearance {
  
  public interface GrayAccordionLayoutResources extends ContentPanelResources {

    @Source({"com/sencha/gxt/theme/base/client/panel/ContentPanel.css", "EasyContentPanel.css"})
    @Override
    GrayAccordionLayoutStyle style();

  }

  public interface GrayAccordionLayoutStyle extends ContentPanelStyle {

  }
  
  public EasyAccordionLayoutAppearance() {
    super(GWT.<GrayAccordionLayoutResources> create(GrayAccordionLayoutResources.class));
  }

  public EasyAccordionLayoutAppearance(GrayAccordionLayoutResources resources) {
    super(resources);
  }
  
  @Override
  public HeaderDefaultAppearance getHeaderAppearance() {
    return new EasyAccordionHeaderAppearance();
  }
}
