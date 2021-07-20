package com.presinal.photo.sheet.generator;

import com.presinal.photo.sheet.generator.service.PhotoSheetPdfService;
import com.presinal.photo.sheet.generator.ui.MainJFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.presinal.photo.sheet.generator.config")
public class PhotoSheetGeneratorApp implements CommandLineRunner {

	private final Logger LOGGER = LoggerFactory.getLogger(PhotoSheetGeneratorApp.class);

	@Autowired
	private PhotoSheetPdfService photoSheetPdfService;

	public static void main(String[] args) {
		//SpringApplication.run(PhotoSheetGeneratorApp.class, args);
		new SpringApplicationBuilder(PhotoSheetGeneratorApp.class)
				.headless(false)
				.run(args);
	}

	@Override
	public void run(String[] args) {
		setupLookAndFeel();
		/* Create and display UI */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				LOGGER.info("Showing UI");
				new MainJFrame(photoSheetPdfService).setVisible(true);
			}
		});

	}

	private void setupLookAndFeel() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

}
