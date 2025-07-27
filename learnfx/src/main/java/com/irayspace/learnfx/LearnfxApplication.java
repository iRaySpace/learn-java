package com.irayspace.learnfx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;


@SpringBootApplication
public class LearnfxApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(LearnfxApplication.class).run(args);
		FxApplication.setContext(context);
		Application.launch(FxApplication.class, args);
	}

}