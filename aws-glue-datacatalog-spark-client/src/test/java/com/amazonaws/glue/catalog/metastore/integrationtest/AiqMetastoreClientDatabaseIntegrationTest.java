package com.amazonaws.glue.catalog.metastore.integrationtest;

import com.amazonaws.glue.catalog.converters.BaseCatalogToHiveConverter;
import com.amazonaws.glue.catalog.converters.CatalogToHiveConverter;
import com.amazonaws.glue.catalog.metastore.AWSCatalogMetastoreClient;
import com.amazonaws.glue.catalog.metastore.GlueClientFactory;
import com.amazonaws.glue.catalog.util.AWSGlueConfig;
import com.amazonaws.glue.catalog.util.GlueTestClientFactory;
import com.amazonaws.services.glue.AWSGlue;
import com.amazonaws.services.glue.model.DeleteDatabaseRequest;
import com.amazonaws.services.glue.model.EntityNotFoundException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.AlreadyExistsException;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.thrift.TException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.amazonaws.glue.catalog.util.TestObjects.getTestDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AiqMetastoreClientDatabaseIntegrationTest extends MetastoreClientDatabaseIntegrationTest {
  @Override
  public void setup() throws MetaException {
    super.setup();
    conf.set(AWSGlueConfig.AWS_CHECK_DEFAULT_DATABASE, "false");
  }

  @Test(expected = NoSuchObjectException.class)
  public void testNoDefaultDatabase() throws TException {
    // default db should NOT exist
    metastoreClient.getDatabase("default");
  }
}
