package com.textiq.api;

import com.textiq.api.config.EmbeddedJettyConfig;

public class Application {
    public static void main(String[] args) throws Exception {
    	EmbeddedJettyConfig.startJetty();
    }
}
