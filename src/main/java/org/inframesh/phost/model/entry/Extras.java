package org.inframesh.phost.model.entry;

import org.inframesh.phost.model.AbstractDTO;
import org.inframesh.phost.util.annotation.Column;
import org.inframesh.phost.util.annotation.JdbcType;
import org.inframesh.phost.util.annotation.Table;

/**
 * @author ${user}
 *
 * ${tags}
 */

/**
 * Table Name: EXTRAS
 *
 * Generated by ormapper plug-in
 */

@Table(name="EXTRAS")
public class Extras extends AbstractDTO
{
    private static final Long serialVersionUID = 4050015350586336746L;
    
    public final static String MT_PROJECT = "1";
    public final static String MT_DOWNLOAD = "2";
    
    public final static String CT_LABEL = "1";
    public final static String CT_LINK = "2";
    public final static String CT_DISCUSSGROUP = "3";
    public final static String CT_BLOG = "4";

	@Column(pk=true, name="EXTRAS_ID", type=JdbcType.BIGINT, nullable=false)
    private Long extrasId;

    @Column(name="MODULE_ID", type=JdbcType.BIGINT)
    private Long moduleId;

    @Column(name="MODULE_TYPE", type=JdbcType.VARCHAR)
    private String moduleType;

    @Column(name="CATEGORY", type=JdbcType.VARCHAR)
    private String category;

    @Column(name="TEXT", type=JdbcType.VARCHAR)
    private String text;

    @Column(name="CONTENT", type=JdbcType.VARCHAR)
    private String content;


    public void setExtrasId(Long extrasId) {
        this.extrasId = extrasId;
    }

    public Long getExtrasId() {
        return this.extrasId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getModuleId() {
        return this.moduleId;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleType() {
        return this.moduleType;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

}