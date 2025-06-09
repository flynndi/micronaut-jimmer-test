package io.micronaut.test;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.data.connection.jdbc.advice.DelegatingDataSource;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;

@Singleton
public class AppLifecycleBean implements ApplicationEventListener<StartupEvent> {

	@Inject
	private DataSource dataSource;


	@Override
	public void onApplicationEvent(StartupEvent event) {
		try {
			this.initH2();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void initH2() throws Exception {
		DataSource unwrappedDataSource = DelegatingDataSource.unwrapDataSource(dataSource);
		try (Connection con = unwrappedDataSource.getConnection()) {
			InputStream inputStream = AppLifecycleBean.class.getClassLoader().getResourceAsStream("h2-database.sql");
			if (inputStream == null) {
				throw new RuntimeException("no `h2-database.sql`");
			}
			try (Reader reader = new InputStreamReader(inputStream)) {
				char[] buf = new char[1024];
				StringBuilder builder = new StringBuilder();
				while (true) {
					int len = reader.read(buf);
					if (len == -1) {
						break;
					}
					builder.append(buf, 0, len);
				}
				con.createStatement().execute(builder.toString());
			}
		}
	}
}
