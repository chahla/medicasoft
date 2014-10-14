package com.rsoft.medicasoft.client.service;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.rsoft.medicasoft.shared.model.RequestDescriptor;

public class EasyRequestBuilder extends RpcRequestBuilder {
	private RequestDescriptor descriptor;

	public EasyRequestBuilder(RequestDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	protected void doFinish(RequestBuilder rb) {
		super.doFinish(rb);
		if (descriptor != null && descriptor.getSessionId() != null) {
			rb.setHeader("userSession", descriptor.getSessionId());
		}
	}

	public void setRequest(ServiceDefTarget service) {
		service.setRpcRequestBuilder(this);
	}
}
