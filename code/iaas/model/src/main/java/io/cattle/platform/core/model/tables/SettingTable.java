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
public class SettingTable extends org.jooq.impl.TableImpl<io.cattle.platform.core.model.tables.records.SettingRecord> {

	private static final long serialVersionUID = -1629554359;

	/**
	 * The singleton instance of <code>cattle.setting</code>
	 */
	public static final io.cattle.platform.core.model.tables.SettingTable SETTING = new io.cattle.platform.core.model.tables.SettingTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<io.cattle.platform.core.model.tables.records.SettingRecord> getRecordType() {
		return io.cattle.platform.core.model.tables.records.SettingRecord.class;
	}

	/**
	 * The column <code>cattle.setting.id</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.SettingRecord, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>cattle.setting.name</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.SettingRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>cattle.setting.value</code>.
	 */
	public final org.jooq.TableField<io.cattle.platform.core.model.tables.records.SettingRecord, java.lang.String> VALUE = createField("value", org.jooq.impl.SQLDataType.VARCHAR.length(1024).nullable(false), this, "");

	/**
	 * Create a <code>cattle.setting</code> table reference
	 */
	public SettingTable() {
		this("setting", null);
	}

	/**
	 * Create an aliased <code>cattle.setting</code> table reference
	 */
	public SettingTable(java.lang.String alias) {
		this(alias, io.cattle.platform.core.model.tables.SettingTable.SETTING);
	}

	private SettingTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.SettingRecord> aliased) {
		this(alias, aliased, null);
	}

	private SettingTable(java.lang.String alias, org.jooq.Table<io.cattle.platform.core.model.tables.records.SettingRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, io.cattle.platform.core.model.CattleTable.CATTLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<io.cattle.platform.core.model.tables.records.SettingRecord, java.lang.Long> getIdentity() {
		return io.cattle.platform.core.model.Keys.IDENTITY_SETTING;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.SettingRecord> getPrimaryKey() {
		return io.cattle.platform.core.model.Keys.KEY_SETTING_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.SettingRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<io.cattle.platform.core.model.tables.records.SettingRecord>>asList(io.cattle.platform.core.model.Keys.KEY_SETTING_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public io.cattle.platform.core.model.tables.SettingTable as(java.lang.String alias) {
		return new io.cattle.platform.core.model.tables.SettingTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public io.cattle.platform.core.model.tables.SettingTable rename(java.lang.String name) {
		return new io.cattle.platform.core.model.tables.SettingTable(name, null);
	}
}
