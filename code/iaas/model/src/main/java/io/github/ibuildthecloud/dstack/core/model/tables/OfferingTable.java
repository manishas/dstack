/**
 * This class is generated by jOOQ
 */
package io.github.ibuildthecloud.dstack.core.model.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OfferingTable extends org.jooq.impl.TableImpl<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord> {

	private static final long serialVersionUID = 1366811197;

	/**
	 * The singleton instance of <code>dstack.offering</code>
	 */
	public static final io.github.ibuildthecloud.dstack.core.model.tables.OfferingTable OFFERING = new io.github.ibuildthecloud.dstack.core.model.tables.OfferingTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord> getRecordType() {
		return io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord.class;
	}

	/**
	 * The column <code>dstack.offering.id</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>dstack.offering.name</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>dstack.offering.account_id</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.Long> ACCOUNT_ID = createField("account_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>dstack.offering.kind</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>dstack.offering.uuid</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>dstack.offering.description</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>dstack.offering.state</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

	/**
	 * The column <code>dstack.offering.created</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.util.Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.github.ibuildthecloud.dstack.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>dstack.offering.removed</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.util.Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.github.ibuildthecloud.dstack.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>dstack.offering.remove_time</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.util.Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP.asConvertedDataType(new io.github.ibuildthecloud.dstack.db.jooq.converter.DateConverter()), this, "");

	/**
	 * The column <code>dstack.offering.data</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.util.Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB.length(65535).asConvertedDataType(new io.github.ibuildthecloud.dstack.db.jooq.converter.DataConverter()), this, "");

	/**
	 * The column <code>dstack.offering.is_public</code>.
	 */
	public final org.jooq.TableField<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.Boolean> IS_PUBLIC = createField("is_public", org.jooq.impl.SQLDataType.BIT.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>dstack.offering</code> table reference
	 */
	public OfferingTable() {
		this("offering", null);
	}

	/**
	 * Create an aliased <code>dstack.offering</code> table reference
	 */
	public OfferingTable(java.lang.String alias) {
		this(alias, io.github.ibuildthecloud.dstack.core.model.tables.OfferingTable.OFFERING);
	}

	private OfferingTable(java.lang.String alias, org.jooq.Table<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord> aliased) {
		this(alias, aliased, null);
	}

	private OfferingTable(java.lang.String alias, org.jooq.Table<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.github.ibuildthecloud.dstack.core.model.DstackTable.DSTACK, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, java.lang.Long> getIdentity() {
		return io.github.ibuildthecloud.dstack.core.model.Keys.IDENTITY_OFFERING;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord> getPrimaryKey() {
		return io.github.ibuildthecloud.dstack.core.model.Keys.KEY_OFFERING_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord>>asList(io.github.ibuildthecloud.dstack.core.model.Keys.KEY_OFFERING_PRIMARY, io.github.ibuildthecloud.dstack.core.model.Keys.KEY_OFFERING_IDX_OFFERING_UUID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<io.github.ibuildthecloud.dstack.core.model.tables.records.OfferingRecord, ?>>asList(io.github.ibuildthecloud.dstack.core.model.Keys.FK_OFFERING__ACCOUNT_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.github.ibuildthecloud.dstack.core.model.tables.OfferingTable as(java.lang.String alias) {
		return new io.github.ibuildthecloud.dstack.core.model.tables.OfferingTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.github.ibuildthecloud.dstack.core.model.tables.OfferingTable rename(java.lang.String name) {
		return new io.github.ibuildthecloud.dstack.core.model.tables.OfferingTable(name, null);
	}
}
