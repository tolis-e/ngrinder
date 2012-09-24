package org.ngrinder.infra.init;

import java.io.File;

import javax.annotation.PostConstruct;

import net.grinder.util.GrinderClassPathUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Classpath jar file filtering class.
 * 
 * Actually this class is used for grinder agent's javaagent. grinder agent is run with java agent
 * name grinder-dcr-agent-**.jar but grinder agent mistakenly takes the javadoc and sources file as
 * javaagent . So.. This class deletes out the sources and javadoc files of grinder-dcr-agent in
 * class path.
 * 
 * @author JunHo Yoon
 */
@Component
public class ClassPathInit {
	private static final Logger LOG = LoggerFactory.getLogger(ClassPathInit.class);

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void init() {
		final String systemClasspath = System.getProperty("java.class.path");
		if (systemClasspath != null) {
			for (String pathEntry : systemClasspath.split(File.pathSeparator)) {
				final File f = new File(pathEntry).getParentFile();
				final File parentFile = f != null ? f : new File(".");

				final File[] children = parentFile.listFiles();

				if (children != null) {
					for (File candidate : children) {
						final String name = candidate.getName();
						if (name.startsWith("grinder-dcr-agent") && name.endsWith(".jar")
										&& (name.contains("javadoc") || name.contains("source"))) {
							candidate.delete();
						}
					}
				}
			}
		}
		LOG.info("===========================================================================");
		LOG.info("Total Class Path for validation is {}",
						GrinderClassPathUtils.buildClasspathBasedOnCurrentClassLoader(LOG));
	}
}