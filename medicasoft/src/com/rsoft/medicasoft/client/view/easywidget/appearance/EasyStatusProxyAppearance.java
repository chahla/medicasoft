package com.rsoft.medicasoft.client.view.easywidget.appearance;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.statusproxy.StatusProxyBaseAppearance;

public class EasyStatusProxyAppearance extends StatusProxyBaseAppearance {

  public interface GrayStatusProxyResources extends StatusProxyResources, ClientBundle {

    ImageResource dropAllowed();

    ImageResource dropDisallowed();

    @Source({"com/sencha/gxt/theme/base/client/statusproxy/StatusProxy.css", "EasyStatusProxy.css"})
    GrayStatusProxyStyle style();

  }

  public interface GrayStatusProxyStyle extends StatusProxyStyle {
  }

  public EasyStatusProxyAppearance() {
    this(GWT.<GrayStatusProxyResources> create(GrayStatusProxyResources.class),
        GWT.<StatusProxyTemplates> create(StatusProxyTemplates.class));
  }

  public EasyStatusProxyAppearance(GrayStatusProxyResources resources, StatusProxyTemplates templates) {
    super(resources, templates);
  }

}
