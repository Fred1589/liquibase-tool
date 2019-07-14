package com.fb.dbmig.migration.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;

import com.fb.commons.liquibase.AbstractMigrationService;
import com.fb.commons.liquibase.LiquibaseBuilder;
import com.fb.commons.liquibase.LiquibaseOperation;
import com.fb.commons.liquibase.LiquibaseOperationExecutor;

import liquibase.Liquibase;

public class MigrationService extends AbstractMigrationService {

	public static void main(final String[] args) {
		/// add project to args
		String[] newArgs = new String[args.length + 2];
		System.arraycopy(args, 0, newArgs, 0, args.length);
		newArgs[newArgs.length - 2] = "-project";
		newArgs[newArgs.length - 1] = "showcase";
		final MigrationService migrationService = new MigrationService();
		migrationService.init(newArgs, readKey());
		migrationService.execute();
	}

	@Override
	public void execute() {
		LOG.info("build liquibase instance");
		final Liquibase liquibase = new LiquibaseBuilder().withDatabase(getDatabase())
				.withDatabaseChangeLog(getProperties().getLocations(), getProperties().getRootChangeLogName())
				.withResourceAccessor().withChangeLogTableName(getProperties().getVersionTable()).build();

		LOG.info("execute liquibase operation");
		final LiquibaseOperationExecutor executor = new LiquibaseOperationExecutor();
		executor.setTag(getOptions().getTag());
		final LiquibaseOperation liquibaseOperation = getOptions().getOperation();

		executor.setTargetDatabase(getDatabase());
		executor.setReferenceDatabase(getDiffDatabase());
		executor.processOperation(liquibaseOperation, liquibase);

		LOG.info("DONE");
	}

	private static byte[] readKey() {
		try {
			InputStream input = ClassLoader.getSystemResourceAsStream("keyfile");
			return IOUtils.toByteArray(input);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Failed to read keyfile", e);
			return null;
		}
	}

}
