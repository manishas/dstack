/**
 * This class is generated by jOOQ
 */
package io.cattle.platform.core.model.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InstanceTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.InstanceRecord> {

	private static final long serialVersionUID = -557586924;

	/**
	 * The singleton instance of <code>cattle.instance</code>
	 */
	public static final io.cattle.platform.core.model.tables.InstanceTable INSTANCE = new io.cattle.platform.core.model.tables.InstanceTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.InstanceRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.InstanceRecord.class;
	}

	/**
	 * The column <code>cattle.instance.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.instance.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.instance.account_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> ACCOUNT_ID = createField("account_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.instance.kind</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.instance.uuid</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.instance.description</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>cattle.instance.state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>cattle.instance.created</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.instance.removed</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.instance.remove_time</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.cattle.platform.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>cattle.instance.data</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(65535).asConvertedDataType(new io.cattle.platform.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>cattle.instance.allocation_state</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.String> ALLOCATION_STATE = createField("allocation_state", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.instance.compute</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> COMPUTE = createField("compute", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.instance.memory_mb</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> MEMORY_MB = createField("memory_mb", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.instance.image_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> IMAGE_ID = createField("image_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.instance.offering_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> OFFERING_ID = createField("offering_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cattle.instance.hostname</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.String> HOSTNAME = createField("hostname", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>cattle.instance.zone_id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> ZONE_ID = createField("zone_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * Create a <code>cattle.instance</code> table reference
	 */
	public InstanceTable() {
		this("instance", null);
	}

	/**
	 * Create an aliased <code>cattle.instance</code> table reference
	 */
	public InstanceTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.InstanceTable.INSTANCE);
	}

	private InstanceTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.InstanceRecord> aliased) {
		this(alias, aliased, null);
	}

	private InstanceTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.InstanceRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.InstanceRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.InstanceRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_INSTANCE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.InstanceRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.InstanceRecord>>asList(io.cattle.platform.core.model.Keys.KEY_INSTANCE_PRIMARY, io.cattle.platform.core.model.Keys.KEY_INSTANCE_IDX_INSTANCE_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.InstanceRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.cattle.platform.core.model.tables.records.InstanceRecord, ?>>asList(io.cattle.platform.core.model.Keys.FK_INSTANCE__ACCOUNT_ID, io.cattle.platform.core.model.Keys.FK_INSTANCE__IMAGE_ID, io.cattle.platform.core.model.Keys.FK_INSTANCE__OFFERING_ID, io.cattle.platform.core.model.Keys.FK_INSTANCE__ZONE_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.InstanceTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.InstanceTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.InstanceTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.InstanceTable(name, null);
	}
}
