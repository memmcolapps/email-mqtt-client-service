package com.memmcol.emailmqttclientservice.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class SbcRegisterRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sbc sbc;
	private SbcPublishTopic sbcPublishTopic;
	private SbcSubscribeTopic sbcSubscribeTopic;
	public Sbc getSbc() {
		return sbc;
	}

	public void setSbc(Sbc sbc) {
		this.sbc = sbc;
	}

	public SbcPublishTopic getSbcPublishTopic() {
		return sbcPublishTopic;
	}

	public void setSbcPublishTopic(SbcPublishTopic sbcPublishTopic) {
		this.sbcPublishTopic = sbcPublishTopic;
	}

	public SbcSubscribeTopic getSbcSubscribeTopic() {
		return sbcSubscribeTopic;
	}

	public void setSbcSubscribeTopic(SbcSubscribeTopic sbcSubscribeTopic) {
		this.sbcSubscribeTopic = sbcSubscribeTopic;
	}

}
